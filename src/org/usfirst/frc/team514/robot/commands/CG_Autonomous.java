/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc.team514.robot.commands;

import org.usfirst.frc.team514.robot.RobotMap;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 * @author marnold
 */
public class CG_Autonomous extends CommandGroup {
    
    public CG_Autonomous(int target) {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
        
        addParallel(new DRV_AutoOn(target));
        addSequential(new WaitCommand(RobotMap.cg_AUTO_DRIVE_DELAY));
        addSequential(new DRV_AutoOff());
        addSequential(new SAU_AutoDuck(target));
        //addSequential(new TU_FindTarget(target));
        addSequential(new TU_TrackY_Auto(target));
        addSequential(new WaitCommand(RobotMap.cg_AUTO_TIMER_DELAY));
        //addSequential(new TU_TrackY(target));
        //Fire First Disk
        addSequential(new SU_FireControl());
        addSequential(new WaitCommand(RobotMap.cg_AUTO_TIMER_DELAY));        
        addSequential(new SU_Fire());
        //Fire Second Disk
        addSequential(new SU_FireControl());
        addSequential(new WaitCommand(RobotMap.cg_AUTO_TIMER_DELAY));
        addSequential(new SU_Fire());
        //Fire Third Disk
        addSequential(new SU_FireControl());
        addSequential(new WaitCommand(RobotMap.cg_AUTO_TIMER_DELAY));        
        addSequential(new SU_Fire());
        addParallel(new TU_TrackOff());
        addSequential(new SAU_GetDisks());
        //end();
    }
}
