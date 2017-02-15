package org.usfirst.frc.team6414.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team6414.robot.Robot;
import org.usfirst.frc.team6414.robot.RobotMap;

/**
 *
 */
public class Shoot extends Command {

    private boolean isAtDeafult = false;

    public Shoot() {
        requires(Robot.shooter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if (Robot.oi.getButSt(RobotMap.SET_SHOOTER_DEF)) {
            isAtDeafult = !isAtDeafult;
            while (Robot.oi.getButSt(RobotMap.SET_SHOOTER_DEF)) ;
        }
        if (Robot.oi.getTrigger()) {
            if (isAtDeafult) {
                Robot.shooter.shootAtDefault();
            } else {
                Robot.shooter.refreshSpeed((-(Robot.oi.getThrottle() + 1) / 2) * 0.7 + 0.3);
            }
        } else {
            Robot.shooter.stop();
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
        Robot.shooter.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
