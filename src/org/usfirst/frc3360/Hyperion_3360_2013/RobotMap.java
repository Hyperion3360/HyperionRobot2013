package org.usfirst.frc3360.Hyperion_3360_2013;
    
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;



public class RobotMap {
    // Canon subsystem hardware
    public static SpeedController canonCanon_SpinnerMotor;
    public static DoubleSolenoid canonShooterSolenoid;
    public static SpeedController canonSetShooterAngle;
    public static AnalogChannel canonPotentiometer; 
    // DriveTrain subsystem hardware
    public static SpeedController driveTrainFrontLeftMotor;
    public static SpeedController driveTrainRearLeftMotor;
    public static SpeedController driveTrainFrontRightMotor;
    public static SpeedController driveTrainRearRightMotor;
    public static RobotDrive driveTrainRobotDrive41;
    public static DoubleSolenoid driveTrainRearPiston;
    // Ascent subsystem hardware
  //  public static DigitalInput ascentLimitFront;
    public static DigitalInput ascentLimitDown;
    public static DigitalInput HookRightLimitLED;
    public static DigitalInput HookLeftLimitLED;
    public static DoubleSolenoid ascentAscent_SolenoidBlockHookRight;
    public static DoubleSolenoid ascentAscent_SolenoidBlockHookLeft;
    public static SpeedController ascentAscent_MotorLeft;
    public static SpeedController ascentAscent_MotorRight;
    public static SpeedController ascentAscent_SetAngle;
    public static DoubleSolenoid ascentPistonHookR;
    public static DoubleSolenoid ascentPistonHookL;
    public static Servo shooterDeviator;
    // Compressor subsystem hardware(forever alone =( )
    public static Compressor robotCompressor;
    
    
    public static void init() {
        
        
        shooterDeviator = new Servo(10);
        robotCompressor = new Compressor(1, 1);
        
        canonPotentiometer = new AnalogChannel(1);
        
        canonCanon_SpinnerMotor = new Victor(1, 5);
        
        canonShooterSolenoid = new DoubleSolenoid(1, 1, 2);        
        
        canonSetShooterAngle = new Victor(1, 6);      
	
        
        driveTrainFrontLeftMotor = new Jaguar(1, 1);
        
        driveTrainRearLeftMotor = new Jaguar(1, 2);
        
        driveTrainFrontRightMotor = new Jaguar(1, 3);
        
        driveTrainRearRightMotor = new Jaguar(1, 4);
        
        driveTrainRobotDrive41 = new RobotDrive(driveTrainFrontLeftMotor, driveTrainRearLeftMotor,
              driveTrainFrontRightMotor, driveTrainRearRightMotor);
	
        driveTrainRobotDrive41.setSafetyEnabled(true);
        driveTrainRobotDrive41.setExpiration(0.5);
        driveTrainRobotDrive41.setSensitivity(0.5);
        driveTrainRobotDrive41.setMaxOutput(1.0);
        driveTrainRearPiston = new DoubleSolenoid(1, 7, 8);      
	
        
        ascentAscent_SolenoidBlockHookRight = new DoubleSolenoid(2, 1, 2);      
	
        
        ascentAscent_SolenoidBlockHookLeft = new DoubleSolenoid(2, 3, 4);      
	
        
        ascentAscent_MotorLeft = new Talon(1, 7);
        
        ascentAscent_MotorRight = new Talon(1, 8);
        
        ascentAscent_SetAngle = new Jaguar(1, 9);
        
        ascentPistonHookR = new DoubleSolenoid(2, 5, 6);      
	
        
        ascentPistonHookL = new DoubleSolenoid(2, 7, 8);
        ascentLimitDown = new DigitalInput(10);
        HookLeftLimitLED = new DigitalInput(2);
        HookRightLimitLED = new DigitalInput(3);
    }
}
