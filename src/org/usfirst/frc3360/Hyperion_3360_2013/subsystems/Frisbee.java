package org.usfirst.frc3360.Hyperion_3360_2013.subsystems;

import org.usfirst.frc3360.Hyperion_3360_2013.RobotMap;
import org.usfirst.frc3360.Hyperion_3360_2013.commands.*;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc3360.Hyperion_3360_2013.Robot;

public class Frisbee extends Subsystem {

    DoubleSolenoid shooterSolenoid = RobotMap.canonShooterSolenoid;
    
    
    private boolean m_bShootFrisbeeRequest;
    private boolean m_bFrisbeeShot;
    // Shooting state
    // 0: Waiting for command,
    // 1: Pushed piston,
    // 2: Waiting to Pull piston,
    // 3: Waiting for the spinner to spin)
    private int m_ShootingState;
    private long m_ShootTimeMs;
    // Private 
    private final DoubleSolenoid.Value m_ShooterPushFrisbeePosition = DoubleSolenoid.Value.kReverse;
    private final DoubleSolenoid.Value m_ShooterPullFrisbeePosition = DoubleSolenoid.Value.kForward;
    private final long m_PullBackShooterTimeMs = 300;
    private final long m_NextShotDelayMs = 2500;
    private final long m_SpinningTimeForFirstShootMs = 2000;
    
    private boolean m_bStateInitialized;

    public Frisbee() {
        m_bStateInitialized = false;
        // No command must be executed at the begining.
        m_bShootFrisbeeRequest = false;
        m_bFrisbeeShot = false;
        // Set the default state.
        m_ShootingState = 0;
        m_ShootTimeMs = 0;
        // Set the actuator in the good state.
        shooterSolenoid.set(m_ShooterPullFrisbeePosition);
        
        SmartDashboard.putBoolean("Can Shoot", false);
        SmartDashboard.putString("Shooting State", "Cannot shoot");
        SmartDashboard.putBoolean("Requested Frisbee Shot", false);
        SmartDashboard.putNumber("Shooting state number", m_ShootingState);
    }
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        setDefaultCommand(new DefaultFrisbeeHandler());
    }
    // Public control methods

    public void ShootFrisbee() {
        SmartDashboard.putBoolean("Requested Frisbee Shot", true);
        // System.out.println("FRISBEE: Requested shoot");
        m_bShootFrisbeeRequest = true;
        m_bFrisbeeShot = false;
    }
    
    public boolean IsWaitingToShoot()
    {
        return m_ShootingState == 1;
    }
    
    public boolean IsFrisbeeShot()
    {
        return m_bFrisbeeShot;
    }

    // Internal canon handlers
    public void HandleShoot() {
        switch (m_ShootingState)
        {
            case 0:
            {
                if (!m_bStateInitialized)
                {
                    // System.out.println("FRISBEE: State 0 initialized");
                    m_bStateInitialized = true;
                    SmartDashboard.putString("Shooting State", "Cannot shoot");
                }
                
                if (CanShoot())
                {
                    //System.out.println("Going to state 1");
                    m_ShootingState = 1;
                    m_bStateInitialized = false;
                }
                break;
            }
            case 1:
            {
                if (!m_bStateInitialized)
                {
                   // System.out.println("FRISBEE: State 1 initialized");
                    m_bStateInitialized = true;
                    SmartDashboard.putString("Shooting State", "Waiting to Shoot");
                    SmartDashboard.putBoolean("Can Shoot", true);
                }
                
                if (!CanShoot())
                {
                    //System.out.println("Going to state 0");
                    m_ShootingState = 0;
                    m_bStateInitialized = false;
                    SmartDashboard.putBoolean("Can Shoot", false);
                }
                else if (m_bShootFrisbeeRequest)
                {
                    m_bFrisbeeShot = false;
                    //System.out.println("Going to state 2");
                    m_ShootingState = 2;
                    m_bStateInitialized = false;
                    SmartDashboard.putBoolean("Can Shoot", false);
                }
                
                break;
            }
            case 2:
            {
                if (!m_bStateInitialized)
                {
                   // System.out.println("FRISBEE: State 2 initialized");
                    m_bStateInitialized = true;
                    SmartDashboard.putString("Shooting State", "Frisbee Shoot");
                    shooterSolenoid.set(m_ShooterPushFrisbeePosition);
                    m_ShootTimeMs = System.currentTimeMillis();
                }
                
                long shotTimeElapsed = System.currentTimeMillis() - m_ShootTimeMs;
                if (shotTimeElapsed > m_PullBackShooterTimeMs)
                {
                    //System.out.println("Going to state 3");
                    m_ShootingState = 3;
                    m_bStateInitialized = false;
                }
                break;
            }
            case 3:
            {
                if (!m_bStateInitialized)
                {
                   // System.out.println("FRISBEE: State 3 initialized");
                    m_bStateInitialized = true;
                    SmartDashboard.putString("Shooting State", "Piston Ready to Shoot");
                    shooterSolenoid.set(m_ShooterPullFrisbeePosition);
                }
                
                long timeSinceLastShot = System.currentTimeMillis() - m_ShootTimeMs;
                if (timeSinceLastShot > m_NextShotDelayMs)
                {
                    //System.out.println("Going to state 0");
                    m_bFrisbeeShot = true;
                    m_ShootingState = 0;
                    m_bStateInitialized = false;
                }
                break;
            }
        }
        
        m_bShootFrisbeeRequest = false;
        SmartDashboard.putBoolean("Requested Frisbee Shot", false);
        SmartDashboard.putNumber("Shooting state number", m_ShootingState);
    }

   
    // Helpers
    private boolean CanShoot() {
        long timeSinceLastShot = System.currentTimeMillis() - m_ShootTimeMs;
        return /* Must be spinning */ Robot.shooterSpinner.IsSpinning()
                && /* Must be spinning for at least X second */ (Robot.shooterSpinner.GetSpinningTime() > m_SpinningTimeForFirstShootMs)
                && /* Must not have shoot since X second */ (timeSinceLastShot > m_NextShotDelayMs);
                
    }
}
