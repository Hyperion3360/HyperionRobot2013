package org.usfirst.frc3360.Hyperion_3360_2013.commands;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc3360.Hyperion_3360_2013.Robot;


public class  Canon_SetShooterAngleAt54 extends Command {;
    public Canon_SetShooterAngleAt54() {
    }
    // Called just before this Command runs the first time
    protected void initialize() {
        System.out.println("Executing set shooter angle at 54");
        Robot.shooterAngle.SetShooterAngle(Robot.m_canonAngleToShoot);
    }
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        System.out.println("Executed set shooter angle at 54");
        return true;
    }
    // Called once after isFinished returns true
    protected void end() {
        
    }
    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
