package org.usfirst.frc3360.Hyperion_3360_2013;
import org.usfirst.frc3360.Hyperion_3360_2013.commands.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.buttons.*;


public class OI {

    public JoystickButton startDrive;
    public JoystickButton button_HooksOff;
    public JoystickButton button_AngleFront;
    public JoystickButton button_AngleRear;
    public Joystick joyL;
    public JoystickButton button_HooksOn;
    public JoystickButton button_ChariotUp;
    public JoystickButton button_ChariotDown;
    public JoystickButton button_PistonPush;
    public JoystickButton button_PistonPull;
    public JoystickButton button_OverrideSwitch;
    public Joystick joyR;
    public JoystickButton button_SetSpinnerSpeed;
    public JoystickButton button_ChargeAndShoot;
    public JoystickButton button_ShootFrisbee;
    public JoystickButton button_ChargeFrisbee;
    public JoystickButton button_SetShooterAngle1;
    public JoystickButton button_SetShooterAngle2;
    public JoystickButton button_SetShooterAngle3;
    public JoystickButton button_SetShooterAngle4;
    public JoystickButton button_SetSpinnerSpeedTower;
    public Joystick joyCoPilot;
    public JoystickButton button_ShooterDeviatorOn;
    public JoystickButton button_ShooterDeviatorOff;
    
    
    final double FullSpinnerSpeed = 1;
    final double TowerSpinnerSpeed = 0.4;

    public OI() {
        
        joyCoPilot = new Joystick(3);
        
        button_SetShooterAngle3 = new JoystickButton(joyCoPilot, 3);
        button_SetShooterAngle3.whileHeld(new Canon_SetShooterAngleDecrease1Degree());
        button_SetShooterAngle2 = new JoystickButton(joyCoPilot, 2);
        button_SetShooterAngle2.whileHeld(new Canon_SetShooterAngleIncrease1Degree());
        button_SetShooterAngle1 = new JoystickButton(joyCoPilot, 1);
        button_SetShooterAngle1.whenPressed(new Canon_SetShooterAngleAt54());
        button_ShootFrisbee = new JoystickButton(joyCoPilot, 6);
        button_ShootFrisbee.whileHeld(new Canon_ShootFrisbee());
        button_SetSpinnerSpeed = new JoystickButton(joyCoPilot, 5);
        button_SetSpinnerSpeed.whileHeld(new Canon_SetSpinnerSpeed(FullSpinnerSpeed));
        button_SetSpinnerSpeedTower = new JoystickButton(joyCoPilot, 7);
        button_SetSpinnerSpeedTower.whileHeld(new Canon_SetSpinnerSpeed(TowerSpinnerSpeed));
        button_ShooterDeviatorOn = new JoystickButton(joyCoPilot, 9);
        button_ShooterDeviatorOn.whenPressed(new ShooterDeviatorOn());
        button_ShooterDeviatorOff = new JoystickButton(joyCoPilot, 10);
        button_ShooterDeviatorOff.whileHeld(new ShooterDeviatorOff());
        
        joyR = new Joystick(2);
        
        button_HooksOn = new JoystickButton(joyR, 5);
        button_HooksOn.whenPressed(new Ascent_HooksOff());
        button_AngleRear = new JoystickButton(joyR, 2);
        button_AngleRear.whileHeld(new Ascent_AngleRear());
        button_AngleFront = new JoystickButton(joyR, 3);
        button_AngleFront.whileHeld(new Ascent_AngleFront());
        
        joyL = new Joystick(1);
        
        button_HooksOff = new JoystickButton(joyL, 4);
        button_HooksOff.whenPressed(new Ascent_HooksOn());
        button_ChariotDown = new JoystickButton(joyL, 2);
        button_ChariotDown.whileHeld(new Ascent_ChariotDown());
        button_ChariotUp = new JoystickButton(joyL, 3);
        button_ChariotUp.whileHeld(new Ascent_ChariotUp());
        button_PistonPull = new JoystickButton(joyL, 10);
        button_PistonPull.whenPressed(new DriveTrain_PistonPush());
        button_PistonPush = new JoystickButton(joyL, 11);
        button_PistonPush.whenPressed(new DriveTrain_PistonPull());
        startDrive = new JoystickButton(joyL, 8);
        startDrive. whenPressed(new DefaultDriveTrainHandler());
    }
    
    public Joystick getJoyL() {
        return joyL;
    }
    public Joystick getJoyR() {
        return joyR;
    }
    public Joystick getJoyCoPilot() {
        return joyCoPilot;
    }
}
