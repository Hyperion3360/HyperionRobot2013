package org.usfirst.frc3360.Hyperion_3360_2013.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc3360.Hyperion_3360_2013.Robot;

/**
 *
 */
public class DefaultShooterAngleHandler extends Command {
    public DefaultShooterAngleHandler() {
        requires(Robot.shooterAngle);
    }
    // Called just before this Command runs the first time
    protected void initialize() {
    }
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        Robot.shooterAngle.HandleAngle();
    }
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }
    // Called once after isFinished returns true
    protected void end() {
    }
    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
