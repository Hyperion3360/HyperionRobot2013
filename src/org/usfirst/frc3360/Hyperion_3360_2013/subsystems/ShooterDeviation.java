package org.usfirst.frc3360.Hyperion_3360_2013.subsystems;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.SpeedController;
import org.usfirst.frc3360.Hyperion_3360_2013.RobotMap;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc3360.Hyperion_3360_2013.commands.DefaultAscentMonitoring;

public class ShooterDeviation extends Subsystem {
    Servo deviatorServo = RobotMap.shooterDeviator;
    private final double offAngle = 270;
    private final double onAngle = 95;
    public ShooterDeviation()
    {
        
    }
    
    public void initDefaultCommand() {
    }
    
    public void deviatorOn()
    {
        deviatorServo.setAngle(onAngle);
        SmartDashboard.putBoolean("Shooter Deviator:", true);
    }
    public void deviatorOff()
    {
        deviatorServo.setAngle(offAngle);
        SmartDashboard.putBoolean("Shooter Deviator:", false);
    }
}