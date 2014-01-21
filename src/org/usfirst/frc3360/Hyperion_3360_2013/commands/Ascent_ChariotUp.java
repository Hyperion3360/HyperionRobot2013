package org.usfirst.frc3360.Hyperion_3360_2013.commands;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc3360.Hyperion_3360_2013.Robot;
/**
 *
 */
public class  Ascent_ChariotUp extends Command {
    public Ascent_ChariotUp() {
        requires(Robot.ascentChariot);
    }
    
    // Called just before this Command runs the first time
    protected void initialize() {
        Robot.ascentChariot.ChariotGoUp();
    }
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        // This command executes as long as button is pressed.
        return false;
    }
    // Called once after isFinished returns true
    protected void end() {
        Robot.ascentChariot.ChariotDoNothing();
    }
    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
