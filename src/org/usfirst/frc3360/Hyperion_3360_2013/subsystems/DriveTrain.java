package org.usfirst.frc3360.Hyperion_3360_2013.subsystems;

import org.usfirst.frc3360.Hyperion_3360_2013.RobotMap;
import org.usfirst.frc3360.Hyperion_3360_2013.commands.*;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc3360.Hyperion_3360_2013.Robot;

public class DriveTrain extends Subsystem {

    SpeedController frontLeftMotor = RobotMap.driveTrainFrontLeftMotor;
    SpeedController rearLeftMotor = RobotMap.driveTrainRearLeftMotor;
    SpeedController frontRightMotor = RobotMap.driveTrainFrontRightMotor;
    SpeedController rearRightMotor = RobotMap.driveTrainRearRightMotor;
    RobotDrive robotDrive41 = RobotMap.driveTrainRobotDrive41;
    DoubleSolenoid rearPiston = RobotMap.driveTrainRearPiston;
    
    boolean robotInitialized;
    
    public void DriveTrain()
    {
        robotInitialized = false;
    }

    public void initDefaultCommand() {
        setDefaultCommand(new DefaultDriveTrainHandler());
    }

    public void MoveWithJoysticks() {
        if (!robotInitialized)
        {
            // Start the robot with the piston pulled.
            rearPiston.set(DoubleSolenoid.Value.kForward); 
            
            robotInitialized = true;
        }
        
        double driveRightValue = -Robot.oi.getJoyR().getRawAxis(2);
        double driveLeftValue = -Robot.oi.getJoyL().getRawAxis(2);
        SmartDashboard.putNumber("Drive right", driveRightValue);
        SmartDashboard.putNumber("Drive left", driveLeftValue);
        robotDrive41.tankDrive(driveLeftValue, driveRightValue);
    }

    public void PistonPush() {
        rearPiston.set(DoubleSolenoid.Value.kForward);
    }

    public void PistonPull() {
        rearPiston.set(DoubleSolenoid.Value.kReverse);
    }
}
