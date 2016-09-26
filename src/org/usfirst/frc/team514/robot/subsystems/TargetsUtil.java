/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc.team514.robot.subsystems;

import org.usfirst.frc.team514.robot.RobotMap;

import com.ni.vision.NIVision;
//import edu.wpi.first.wpilibj.image.CriteriaCollection;
import com.ni.vision.NIVision.LinearAverages;
//import edu.wpi.first.wpilibj.camera.AxisCamera;
//import edu.wpi.first.wpilibj.camera.AxisCameraException;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.image.BinaryImage;
import edu.wpi.first.wpilibj.image.ColorImage;
import edu.wpi.first.wpilibj.image.NIVisionException;
import edu.wpi.first.wpilibj.image.ParticleAnalysisReport;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.vision.AxisCamera;

/**
 *
 * @author Team 514
 */
public class TargetsUtil extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    final int XMAXSIZE = 24;
    final int XMINSIZE = 24;
    final int YMAXSIZE = 24;
    final int YMINSIZE = 48;
    final double xMax[] = {1, 1, 1, 1, .5, .5, .5, .5, .5, .5, .5, .5, .5, .5, .5, .5, .5, .5, .5, .5, 1, 1, 1, 1};
    final double xMin[] = {.4, .6, .1, .1, .1, .1, .1, .1, .1, .1, .1, .1, .1, .1, .1, .1, .1, .1, .1, .1, .1, .1, 0.6, 0};
    final double yMax[] = {1, 1, 1, 1, .5, .5, .5, .5, .5, .5, .5, .5, .5, .5, .5, .5, .5, .5, .5, .5, 1, 1, 1, 1};
    final double yMin[] = {.4, .6, .05, .05, .05, .05, .05, .05, .05, .05, .05, .05, .05, .05, .05, .05, .05, .05, .05, .05, .05, .05,
								.05, .05, .05, .05, .05, .05, .05, .05, .05, .05, .05, .05, .05, .05, .05, .05, .05, .05, .05, .05, .05, .05,
								.05, .05, .6, 0};
    
    final int RECTANGULARITY_LIMIT = 60;
    final int ASPECT_RATIO_LIMIT = 75;
    final int X_EDGE_LIMIT = 40;
    final int Y_EDGE_LIMIT = 60;
    
    final int X_IMAGE_RES = 320;       
    final int Y_IMAGE_RES = 240;
    //X Image resolution in pixels, should be 160, 320 or 640
    //final double VIEW_ANGLE = 43.5;        //Axis 206 camera
    final double X_VIEW_ANGLE = 48.00;       //Axis M1011 camera  X Axis View Angle
    final double Y_VIEW_ANGLE = 32.00;       //Axis M1011 camera does not have a published Y Axis View Angle.  This is an estimate.
    
    //AxisCamera camera;          // the axis camera object (connected to the switch)
    //CriteriaCollection cc;      // the criteria for doing the particle filter operation
    //CameraServer server;
    AxisCamera camera;
    //variables for image filtering
    final boolean connectivity8 = true;
    final boolean connectivity4 = false;
    final int erosions = 2;

    double targetCMX, targetCMY, targetAR, targetDistance;
    int target, scores;
    boolean hasTarget, targetsLoaded;
    
    public TargetsUtil(){
    	
        camera = new AxisCamera("10.5.14.11");  // get an instance of the camera
        //cc = new CriteriaCollection();      // create the criteria for the particle filter
        //cc.addCriteria(NIVision.MeasurementType.IMAQ_MT_AREA, 500, 65535, false);
        this.targetsLoaded = false;
      
    }
    
    public class Scores {
        double rectangularity;
        double targetAspectRatio;
        //double aspectRatioOuter;
        double xEdge;
        double yEdge;

    }
    
    public class Targets {
        double aspectRatio;
        double targetHeight;

    }


    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
        
    }
    
    public void setTarget(int value){
        if((value > 0) && (value < 4)){
           this.target = (value - 1); 
        }else{
            this.target = 2;
        }
    }
    
    public int getTarget(){
        return this.target;
    }
    
    public boolean foundTarget(){
        return this.hasTarget;
    }
    
    public double getTargetCMX(){
        return this.targetCMX;
    }
    
    public double getTargetCMY(){
        return this.targetCMY;
    }
    
    public double getDistance(){
        return this.targetDistance;
    }
    
    public boolean onTarget(){
        boolean status = true;
        status &= onTargetX();
        status &= onTargetY();
        //status &= (this.targetCMY > (this.targetCMY - .001));
        //status &= (this.targetCMY < (this.targetCMY + .001));
        return status;
    }

    public boolean onTargetX(){
        boolean status = false;
        double x;
        x = Math.abs(this.targetCMX);
        if(x < (RobotMap.tu_X_TARGET_THRESHOLD)){
            status = true;
        }
        //status &= (this.targetCMY > (this.targetCMY - .001));
        //status &= (this.targetCMY < (this.targetCMY + .001));
        return status;
    }

    public boolean onTargetY(){
        boolean status = false;
        double y;
        y = Math.abs(this.targetCMY);
        if(y < (RobotMap.tu_Y_TARGET_THRESHOLD)){
            status = true;
        }
        //status &= (this.targetCMY > (this.targetCMY - .001));
        //status &= (this.targetCMY < (this.targetCMY + .001));
        return status;
    }
    

   public boolean getXDirection(){
       //True means target is to your Right!
        if(this.targetCMX >= 0.0){
            return false;
        }else{
            return true;
        }
    }

    public boolean getYDirection(){
        //True means target is above you!
        if(this.targetCMY >= 0.0){
            return false;
        }else{
            return true;
        }
    }

    public void updateStatus(){
        //SmartDashboard.putNumber("Scores", this.scores);
        //SmartDashboard.putNumber("Target", this.target);
        //SmartDashboard.putNumber("Target CM X", this.targetCMX);
        //SmartDashboard.putNumber("Target CM Y", this.targetCMY);
        //SmartDashboard.putNumber("Target AR", this.targetAR);
        SmartDashboard.putBoolean("Target Found:", this.hasTarget);
        SmartDashboard.putNumber("Target Distance", this.targetDistance);
        SmartDashboard.putBoolean("Robot  On Target", onTarget());
        SmartDashboard.putBoolean("X Axis On Target", onTargetX());
        SmartDashboard.putBoolean("Y Axis On Target", onTargetY());
        
    }
    
    public void manageTargets(){
        //while (isAutonomous() && isEnabled()) {
            try {
                /**
                 * Do the image capture with the camera and apply the algorithm described above. This
                 * sample will either get images from the camera or from an image file stored in the top
                 * level directory in the flash memory on the cRIO. The file name in this case is "testImage.jpg"
                 * 
                 */
                ColorImage image = camera.getImage();     // comment if using stored images
                //ColorImage image;                           // next 2 lines read image from flash on cRIO
                //image = new RGBImage("/testImage.jpg");		// get the sample image from the cRIO flash
                
                //BinaryImage thresholdImage = image.thresholdHSV(60, 100, 90, 255, 20, 255);   // keep only red objects
                //Use this for the Middle School Tech Room!
                BinaryImage thresholdImage = image.thresholdHSV(50, 143, 83, 255, 105, 255);   // keep only red objects
                
                //Use this for the Regionals!
                //BinaryImage thresholdImage = image.thresholdHSV(50, 143, 83, 255, 105, 255);   // keep only red objects
                
                
                //thresholdImage.write("/threshold.bmp");
                BinaryImage convexHullImage = thresholdImage.convexHull(false);          // fill in occluded rectangles
                //convexHullImage.write("/convexHull.bmp");
                BinaryImage filteredImage = convexHullImage.removeSmallObjects(connectivity8, erosions);           // filter out small particles
                //filteredImage.write("/filteredImage.bmp");
                //ImageBase image2 = (ImageBase) filteredImage;

                //Set your target parameters and store the data on the target
                //REFACTOR!!!

                Targets targets[] = new Targets[3];
                    for (int t = 0; t < targets.length; t++){
                        targets[t] = new Targets();
                        switch (t){
                            case (0):
                                targets[t].aspectRatio = (37.00/32.00);
                                targets[t].targetHeight = 32.00;
                                break;
                            case (1):
                                targets[t].aspectRatio = (62.00/29.00);
                                targets[t].targetHeight = 29.00;
                                break;
                            case (2):
                                targets[t].aspectRatio = (62.00/21.00);
                                targets[t].targetHeight = 21.00;
                                this.targetsLoaded = true;
                                break;
                            default:
                                break;
                        }
                    }
                
                //iterate through each particle and score to see if it is a target
                Scores scores[] = new Scores[filteredImage.getNumberParticles()];

                this.scores = scores.length;
                this.targetAR = targets[this.target].aspectRatio;

                for (int i = 0; i < scores.length; i++) {
                    ParticleAnalysisReport report = filteredImage.getParticleAnalysisReport(i);
                    scores[i] = new Scores();
                    
                    scores[i].rectangularity = scoreRectangularity(report, this.target);
                    scores[i].targetAspectRatio = scoreAspectRatio(filteredImage,report, i, targets[this.target].aspectRatio);
                    scores[i].xEdge = scoreXEdge(thresholdImage, report, this.target);
                    scores[i].yEdge = scoreYEdge(thresholdImage, report, this.target);
                    this.hasTarget = false;

                    if(scoreCompare(scores[i]))
                    {
                        this.targetCMX = report.center_mass_x_normalized;
                        this.targetCMY = report.center_mass_y_normalized;
                        this.targetDistance = computeDistance(report, targets[this.target].targetHeight);
                        this.hasTarget = true;
                        break;
                    }
                }
                
                /**
                 * all images in Java must be freed after they are used since they are allocated out
                 * of C data structures. Not calling free() will cause the memory to accumulate over
                 * each pass of this loop.
                 */
                filteredImage.free();
                convexHullImage.free();
                thresholdImage.free();
                image.free();
                
            //} catch (AxisCameraException ex) {        // this is needed if the camera.getImage() is called
            //    ex.printStackTrace();
            } catch (NIVisionException ex) {
                ex.printStackTrace();
            }
                
        //}
    }
    
    /**
     * Computes the estimated distance to a target using the height of the particle in the image. For more information and graphics
     * showing the math behind this approach see the Vision Processing section of the ScreenStepsLive documentation.
     * 
     * @param image The image to use for measuring the particle estimated rectangle
     * @param report The Particle Analysis Report for the particle
     * @param outer True if the particle should be treated as an outer target, false to treat it as a center target
     * @return The estimated distance to the target in Inches.
     */
    double computeDistance (ParticleAnalysisReport report, double targetHeight){
            //double rectShort, height;
            double height;

            //rectShort = NIVision.MeasureParticle(image.image, particleNumber, false, NIVision.MeasurementType.IMAQ_MT_EQUIVALENT_RECT_SHORT_SIDE);
            //using the smaller of the estimated rectangle short side and the bounding rectangle height results in better performance
            //on skewed rectangles
            //height = Math.min(report.boundingRectHeight, rectShort);
            height = report.boundingRectHeight;

            double a = Math.tan(Y_VIEW_ANGLE*Math.PI/(180*2));      //Need to do this because the Math.tan function returns radians and we have to convert to degrees.
            double w = (((Y_IMAGE_RES * targetHeight)/height)/24);  //Returns half the field of view in feet (Pixels cancel, and 24 is 12 inches per foot * 2 to half the Field of View
            
            return (w/a);
            
    }
    
    /**
     * Computes a score (0-100) comparing the aspect ratio to the ideal aspect ratio for the target. This method uses
     * the equivalent rectangle sides to determine aspect ratio as it performs better as the target gets skewed by moving
     * to the left or right. The equivalent rectangle is the rectangle with sides x and y where particle area= x*y
     * and particle perimeter= 2x+2y
     * 
     * @param image The image containing the particle to score, needed to perform a additional measurements
     * @param report The Particle Analysis Report for the particle, used for the width, height, and particle number
     * @param outer	Indicates whether the particle aspect ratio should be compared to the ratio for the inner target or the outer
     * @return The aspect ratio score (0-100)
     */
    public double scoreAspectRatio(BinaryImage image, ParticleAnalysisReport report, int particleNumber, double idealAspectRatio) throws NIVisionException
    {
        double rectLong, rectShort, aspectRatio; //, idealAspectRatio;  
        
        //Image xImage = (Image) image;
        
        rectLong = NIVision.imaqMeasureParticle(image.image, particleNumber, 0, NIVision.MeasurementType.MT_EQUIVALENT_RECT_LONG_SIDE);
        rectShort = NIVision.imaqMeasureParticle(image.image, particleNumber, 0, NIVision.MeasurementType.MT_EQUIVALENT_RECT_SHORT_SIDE);
        //idealAspectRatio = outer ? (58/25) : (58/16);	//Dimensions of goal opening + 4 inches on all 4 sides for reflective tape
	//(62/29) & (62/20)
        //Divide width by height to measure aspect ratio
        if(report.boundingRectWidth > report.boundingRectHeight){
            //particle is wider than it is tall, divide long by short
            aspectRatio = 100*(1-Math.abs((1-((rectLong/rectShort)/idealAspectRatio))));
        } else {
            //particle is taller than it is wide, divide short by long
                aspectRatio = 100*(1-Math.abs((1-((rectShort/rectLong)/idealAspectRatio))));
        }

        return (Math.max(0, Math.min(aspectRatio, 100.0)));		//force to be in range 0-100
    }
    
    /**
     * Compares scores to defined limits and returns true if the particle appears to be a target
     * 
     * @param scores The structure containing the scores to compare
     * @param outer True if the particle should be treated as an outer target, false to treat it as a center target
     * 
     * @return True if the particle meets all limits, false otherwise
     */
    boolean scoreCompare(Scores scores){
            boolean isTarget = true;

            isTarget &= scores.rectangularity > RECTANGULARITY_LIMIT;
            isTarget &= scores.targetAspectRatio > ASPECT_RATIO_LIMIT;
            isTarget &= scores.xEdge > X_EDGE_LIMIT;
            isTarget &= scores.yEdge > Y_EDGE_LIMIT;

            return isTarget;
    }
    
    /**
     * Computes a score (0-100) estimating how rectangular the particle is by comparing the area of the particle
     * to the area of the bounding box surrounding it. A perfect rectangle would cover the entire bounding box.
     * 
     * @param report The Particle Analysis Report for the particle to score
     * @return The rectangularity score (0-100)
     */
    double scoreRectangularity(ParticleAnalysisReport report, int t){
            if(report.boundingRectWidth*report.boundingRectHeight !=0){
                    return 100*report.particleArea/(report.boundingRectWidth*report.boundingRectHeight);
            } else {
                    return 0;
            }	
    }
    
    /**
     * Computes a score based on the match between a template profile and the particle profile in the X direction. This method uses the
     * the column averages and the profile defined at the top of the sample to look for the solid vertical edges with
     * a hollow center.
     * 
     * @param image The image to use, should be the image before the convex hull is performed
     * @param report The Particle Analysis Report for the particle
     * 
     * @return The X Edge Score (0-100)
     */
    public double scoreXEdge(BinaryImage image, ParticleAnalysisReport report, int t) throws NIVisionException
    {
        double total = 0;
        LinearAverages averages;
        
        NIVision.Rect rect = new NIVision.Rect(report.boundingRectTop, report.boundingRectLeft, report.boundingRectHeight, report.boundingRectWidth);
        averages = NIVision.imaqLinearAverages2(image.image, NIVision.LinearAveragesMode.COLUMN_AVERAGES, rect);
        //float columnAverages[] = NIVision.imaqL
        for(int i=0; i < (averages.size()); i++){
                if(xMin[(i*(XMINSIZE-1)/averages.size())] < averages.columnAverages[i] 
                   && averages.columnAverages[i] < xMax[i*(XMAXSIZE-1)/averages.size()]){
                        total++;
                }
        }
        total = 100*total/(averages.size());
        return total;
    }
    
    /**
	 * Computes a score based on the match between a template profile and the particle profile in the Y direction. This method uses the
	 * the row averages and the profile defined at the top of the sample to look for the solid horizontal edges with
	 * a hollow center
	 * 
	 * @param image The image to use, should be the image before the convex hull is performed
	 * @param report The Particle Analysis Report for the particle
	 * 
	 * @return The Y Edge score (0-100)
	 *
    */
    public double scoreYEdge(BinaryImage image, ParticleAnalysisReport report, int t) throws NIVisionException
    {
        double total = 0;
        LinearAverages averages;
        
        NIVision.Rect rect = new NIVision.Rect(report.boundingRectTop, report.boundingRectLeft, report.boundingRectHeight, report.boundingRectWidth);
        averages = NIVision.imaqLinearAverages2(image.image, NIVision.LinearAveragesMode.ROW_AVERAGES, rect);
        //float rowAverages[] = averages.getRowAverages();
        for(int i=0; i < (averages.size()); i++){
                if(yMin[(i*(YMINSIZE-1)/averages.size())] < averages.rowAverages[i] 
                   && (averages.rowAverages[i] < (yMax[i*(YMAXSIZE-1)/averages.size()]))){
                        total++;
                }
        }
        total = 100*total/(averages.size());
        return total;
    }
    
}
