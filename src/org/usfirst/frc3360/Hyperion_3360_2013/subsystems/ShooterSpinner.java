package org.usfirst.frc3360.Hyperion_3360_2013.subsystems;

import org.usfirst.frc3360.Hyperion_3360_2013.RobotMap;
import org.usfirst.frc3360.Hyperion_3360_2013.commands.*;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ShooterSpinner extends Subsystem {

    SpeedController canon_SpinnerMotor = RobotMap.canonCanon_SpinnerMotor;
    
    private boolean m_bSpinRequested;
    private boolean m_bSpinning;
    // Spinning spinningState
    private long m_SpinStartTime;
   // private long timeToSpeedTower;
    private double m_DesiredSpeed;
    private int spinningState;
    
    
    private final long burstTimeMs = 150;

    public ShooterSpinner() {
        // No command must be executed at the begining.
        m_bSpinRequested = false;
        m_bSpinning = false;
        // Set the default spinningState.
        m_SpinStartTime = 0;
        // Set the actuator in the good spinningState.
        canon_SpinnerMotor.set(0);
        m_DesiredSpeed = 1;
        spinningState = 0;
        SmartDashboard.putBoolean("Spinning Status", false);
    }
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        setDefaultCommand(new DefaultShooterSpinnerHandler());
    }

    // Public control methods
    public void SetDesiredSpeed(double speed) {
        m_DesiredSpeed = speed;
        SmartDashboard.putNumber("Spinner Desired Speed", m_DesiredSpeed);
    }
    
    public void SetSpinning(boolean spinning) {
        SmartDashboard.putBoolean("Spinning Command", spinning);
        System.out.println("Set Spin Command:" + spinning);
        m_bSpinRequested = spinning;
    }

    public boolean IsSpinning() {
        return m_bSpinning;
    }

    public long GetSpinningTime() {
        long spinningTime = 0;
        if (m_bSpinning)
        {
            spinningTime = System.currentTimeMillis() - m_SpinStartTime;
        }
        //System.out.println("Spinning since: " + spinningTime);
        return spinningTime;
    }
    
    public void HandleSpinner() {
        if (m_bSpinRequested)
        {
            if (!m_bSpinning)
            {
                spinningState = 0;
                m_bSpinning = true;
                m_SpinStartTime = System.currentTimeMillis();
                SmartDashboard.putBoolean("Spinning Status", true);
            }
            
            if (m_bSpinning)
            {
             switch (spinningState){
                 case 0:{
                     canon_SpinnerMotor.set(-1);
                     
                     if (burstTimeMs < (System.currentTimeMillis()-m_SpinStartTime)){
                         
                         spinningState =1;
                     }
                     
                     break;
                     
                 }
                 case 1:{
                        canon_SpinnerMotor.set(-m_DesiredSpeed); 
                     break;
                                  
                 }
                     
             }
               
             
            }
        }
        else
        {
            if (m_bSpinning)
            {
                m_bSpinning = false;
                canon_SpinnerMotor.set(0);
                spinningState = 0;
                SmartDashboard.putBoolean("Spinning Status", false);
            }
        }
    }
}
