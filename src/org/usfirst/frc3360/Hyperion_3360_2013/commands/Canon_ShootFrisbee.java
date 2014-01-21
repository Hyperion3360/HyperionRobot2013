package org.usfirst.frc3360.Hyperion_3360_2013.commands;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc3360.Hyperion_3360_2013.Robot;
/**
 *
 */
public class  Canon_ShootFrisbee extends Command {
    public Canon_ShootFrisbee() {
    }
    // Called just before this Command runs the first time
    protected void initialize() {
        Robot.frisbee.ShootFrisbee();
    }
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.frisbee.IsFrisbeeShot();
    }
    // Called once after isFinished returns true
    protected void end() {
    }
    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
