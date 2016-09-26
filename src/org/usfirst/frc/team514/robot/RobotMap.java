package org.usfirst.frc.team514.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
    // public static final int leftMotor = 1;
    // public static final int rightMotor = 2;
    
    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static final int rangefinderPort = 1;
    // public static final int rangefinderModule = 1;
    
    // **************  Constants and Values     (ClassName/ClassObjectName/RobotMapVariablePrefix ****** //
        // Compressor Subsystem (CompressorUtil/compUtil/cmp)
        
        // Drive Subsystem (DriveUtil/driveUtil/drv)
        
        // Magazine Sybsystem (MagazineUtil/magUtil/mag)
        
        // Shot Angle Subsystem (ShotAngleUtil/angleUtil/sau)
            public static final double sau_FEEDER = 457;
            public static final double sau_DUCK = 643;
            public static final double sau_WINDOW = 10; // Upper and Lower range... creates a window to stop motor in.
            public static final double sau_WINDOW_AUTO = 5;
            
        // Shot Motor Subsystem (ShotMotorUtil/motorUtil/smu)
        
        // Shot Subsystem (ShotUtil/shotUtil/su)
            public static final double su_TIMER_DELAY = .3;
        
        // Lift Subsystem (LiftUtil/liftUtil/lu)
            
        // Targeting Subsustem (TargetsUtil/targetsUtil/tu
            public static final double tu_X_TARGET_THRESHOLD = .05;
            public static final double tu_Y_TARGET_THRESHOLD = .06;
            public static final double tu_RIGHT_MOTOR = .60;
            public static final double tu_LEFT_MOTOR = .60;
            public static final int tu_3PT = 805;
    
        // Autonomous Mode (am)
            public static final double cg_AUTO_TIMER_DELAY = 1.00;
            public static final double cg_AUTO_DRIVE_DELAY = .75;
            
        // TeleOp Mode (tm)
            public static final double cg_FIRE_TIMER_DELAY = .50;
    
    // **************  Physical Device Mapping  ************************** //
        // Digital Side Car Mapping
            // All PWMs go here!
            public static final int drv_LEFT_MOTOR = 1;
            public static final int drv_RIGHT_MOTOR = 2;
            public static final int mag_LOADER_MOTOR = 3;
            public static final int smu_MOTOR_1 = 4;
            public static final int smu_MOTOR_2 = 5;
            
        
            // All DIO go here!
            public static final int cmp_SWITCH = 8;
            public static final int mag_PS = 1;
            public static final int su_CHAMBER_PS = 2;
            public static final int su_FIRING_PIN_LS = 3;
            public static final int sau_UPPER_LS = 4;
            public static final int sau_LOWER_LS = 5;
            
            // All Relays go here!
            public static final int cmp_RELAY = 8;
            public static final int sau_MOTOR_RELAY = 7;
            
            // All Analog Modules go here!
            public static final int sau_POT = 1;
            
            // All PNEUMATIC Modules go here!
            public static final int su_FIRING_PIN = 1;
            public static final int mag_GATE_OPEN = 2;
            public static final int mag_GATE_CLOSE = 3;
            public static final int lu_LIFTER = 4;
    
        // Right Joystick and Buttons
        public static final int RIGHT_STICK = 2;
        public static final int TANK_MODE = 2;
        public static final int ARCADE_MODE = 3;
        public static final int HOIST_UP = 4;
        public static final int LOAD_MAG_ANGLE = 8;
    
        // Left Joystick and Buttons
        public static final int LEFT_STICK = 1;
        public static final int HOIST_DOWN = 5;
        public static final int DUCK_DOWN = 6;
    
        // Controller and Buttons
        public static final int CONTROLLER = 3;
        public static final int ANGLE_UP = 5;
        public static final int ANGLE_DOWN = 7;
        public static final int TRACKING_OFF = 9;
        public static final int TARGET_1PT = 1;
        public static final int TARGET_2PT = 2;
        public static final int TARGET_3PT = 3;
        public static final int FIRE = 4; // Will have to work the loader...  Command Group
        public static final int SHOT_MOTOR_ON = 6;
        public static final int SHOT_MOTOR_OFF = 8;
        
        
    
    
}
