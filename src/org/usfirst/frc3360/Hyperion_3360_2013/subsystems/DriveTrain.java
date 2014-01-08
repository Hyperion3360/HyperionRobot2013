package org.usfirst.frc3360.Hyperion_3360_2013.subsystems;

import org.usfirst.frc3360.Hyperion_3360_2013.RobotMap;
import org.usfirst.frc3360.Hyperion_3360_2013.commands.*;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc3360.Hyperion_3360_2013.Robot;

public class DriveTrain extends Subsystem {

    SpeedController frontLeftMotor = RobotMap.driveTrainFrontLeftMotor;
    SpeedController rearLeftMotor = RobotMap.driveTrainRearLeftMotor;
    SpeedController frontRightMotor = RobotMap.driveTrainFrontRightMotor;
    SpeedController rearRightMotor = RobotMap.driveTrainRearRightMotor;
    RobotDrive robotDrive41 = RobotMap.driveTrainRobotDrive41;
    DoubleSolenoid rearPiston = RobotMap.driveTrainRearPiston;

    public void initDefaultCommand() {
        setDefaultCommand(new DriveWithJoysticks());
    }

    public void MoveWithJoysticks() {
        robotDrive41.tankDrive(Robot.oi.getJoyL(), Robot.oi.getJoyR());
    }

    public void PistonPush() {
        rearPiston.set(DoubleSolenoid.Value.kForward);
    }

    public void PistonPull() {
        rearPiston.set(DoubleSolenoid.Value.kReverse);
    }
}
