package org.usfirst.frc3360.Hyperion_3360_2013.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc3360.Hyperion_3360_2013.Robot;

/**
 *
 */
public class AutonomousCommand1 extends Command {

    int state;
    long currenttime;
    long m_StateStartTime;
    boolean m_StateInitialized;
    long autonomousBegin;
    int nbFrisbeeShot;
    
    long timeBetweenShot;
    

    // Autonomous state 0: Avancer la crémaillaire (x secondes) (Change state after y seconds)
    private final long m_timeToStartSpinning = 1000;
    // Autonomous state 1: démarer le spinner et changer d'état
    // Autonomous state 2: Atteindre la position désiré de la crémmailaire avant de changer d'état
    private final long m_timeToStopTilting = 1500;
    // Autonomous state 3: Mettre le canon a la bonne angle et atteindre la position désiré
    // Autonomous state 4: Attendre quelque seconde pour stabilisé l'angle de tir.
    private final long m_timeToStartShooting = 300;
    // Autonomous state 5: Tir 3 fois.
    private final int m_nbFrisbeeToShoot = 3;
    // Autonomous completed
    public AutonomousCommand1() {
        requires(Robot.ascentAngle);
    }
    // Called just before this Command runs the first time

    protected void initialize() {
        m_StateStartTime = 0;
        m_StateInitialized = false;
        state = 0;
        autonomousBegin = System.currentTimeMillis();
        nbFrisbeeShot = 0;
        // Autonomous shooter speed is full blast!
        Robot.shooterSpinner.SetDesiredSpeed(0.95);
    }
    
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        SmartDashboard.putNumber("Autonomous Time", System.currentTimeMillis() - autonomousBegin);
        switch (state) {
            
            // Autonomous state 0: Avancer la crémaillaire (x secondes) (Change state after y seconds)                
            case 0:
            {
                // Initializing the state 0.
                if (!m_StateInitialized)
                {
                    SmartDashboard.putString("Autonomous State", "Configuring angle");
                    SmartDashboard.putString("Autonomous Next State", "Spinning");
                    //System.out.println("autonomous state : " + state);
                    
                    m_StateStartTime = System.currentTimeMillis();
                    m_StateInitialized = true;
                    
                    // Start tilting the angle
                    Robot.ascentAngle.AngleGoFront();
                }
                // Changing to state 1.
                else if (System.currentTimeMillis() - m_StateStartTime > m_timeToStartSpinning)
                {
                    m_StateInitialized = false;
                    m_StateStartTime = 0;
                    state = 1;
                }
                // There is nothing to continuously perform in this state.
                
                break;
            }
            // Autonomous state 1: démarer le spinner et changer d'état
            case 1:
            {
                SmartDashboard.putString("Autonomous State", "Spinning");
                SmartDashboard.putString("Autonomous Next State", "Getting to angle");

                //System.out.println("autonomous state : " + state);
                
                // Start spinning so it can be ready as soon as possible.
                Robot.shooterSpinner.SetSpinning(true);
                
                state = 2;

                m_StateInitialized = false;
                m_StateStartTime = 0;
                break;
            }
            // Autonomous state 2: Atteindre la position désiré de la crémailaire avant de changer d'état
            case 2:
            {
                // Initializing the state 0.
                if (!m_StateInitialized)
                {
                    SmartDashboard.putString("Autonomous State", "Getting to angle");
                    SmartDashboard.putString("Autonomous Next State", "Positioning shooter");
                  //  System.out.println("autonomous state : " + state);
                    
                    m_StateStartTime = System.currentTimeMillis();
                    m_StateInitialized = true;
                }
                // Changing to state 1.
                else if ((System.currentTimeMillis() - m_StateStartTime) > m_timeToStopTilting)
                {
                    // Stop tilting.
                    Robot.ascentAngle.AngleDoNothing();
                    
                    m_StateInitialized = false;
                    m_StateStartTime = 0;
                    state = 3;
                }
                // There is nothing to continuously perform in this state.
                
                break;
            }
            // Autonomous state 3: Mettre le canon a la bonne angle et atteindre la position désiré
            case 3:
            {
                // Initializing the state 0.
                if (!m_StateInitialized)
                {
                    SmartDashboard.putString("Autonomous State", "Positioning shooter");
                    SmartDashboard.putString("Autonomous Next State", "Waiting to shoot");
                   // System.out.println("autonomous state : " + state);
                    m_StateStartTime = System.currentTimeMillis();
                    
                    m_StateInitialized = true;
                    
                    // Start positionning the shooter at the good angle
                    Robot.shooterAngle.SetShooterAngle(Robot.m_canonAngleToShoot);
                }
                // Changing to state 1.
                else if (Robot.shooterAngle.IsShooterDesiredAngleReached() &&
                         Robot.frisbee.IsWaitingToShoot())
                {
                    m_StateInitialized = false;
                    m_StateStartTime = 0;
                    state = 4;
                }
                // There is nothing to continuously perform in this state.
                
                break;
            }
            // Autonomous state 4: Wait half a second before shooting.
            case 4:
            { 
                // Initializing the state 0.
                if (!m_StateInitialized)
                {
                    SmartDashboard.putString("Autonomous State", "Waiting to shoot");
                    SmartDashboard.putString("Autonomous Next State", "Shooting");
                   // System.out.println("autonomous state : " + state);
                    m_StateStartTime = System.currentTimeMillis();
                    
                    m_StateInitialized = true;
                    
                    // Start positionning the shooter at the good angle
                    Robot.shooterAngle.SetShooterAngle(Robot.m_canonAngleToShoot);
                }
                // Changing to state 1.
                else if (Robot.shooterAngle.IsShooterDesiredAngleReached() &&
                         Robot.frisbee.IsWaitingToShoot() &&
                         (System.currentTimeMillis() - m_StateStartTime) > m_timeToStartShooting)
                {
                    m_StateInitialized = false;
                    m_StateStartTime = 0;
                    state = 5;
                }
                // There is nothing to continuously perform in this state.
                
                break;
            }
            // Autonomous state 4: Tir 3 fois.
            case 5:
            {
                // Initializing the state 0.
                if (!m_StateInitialized)
                {
                    SmartDashboard.putString("Autonomous State", "Shooting");
                    SmartDashboard.putString("Autonomous Next State", "Completed");
                   // System.out.println("autonomous state : " + state);
                    
                    m_StateInitialized = true;
                    nbFrisbeeShot = 0;
                    
                    Robot.frisbee.ShootFrisbee();
                    nbFrisbeeShot++;
                    timeBetweenShot =  System.currentTimeMillis();
                }
                else if (Robot.frisbee.IsFrisbeeShot())
                {
                    //System.out.println("AUTONOMOUS COMMAND: Time to shoot" + (System.currentTimeMillis() - timeBetweenShot));
                    SmartDashboard.putNumber("Autonomous Frisbee Shot", nbFrisbeeShot);
                    
                    // Changing to completed state.
                    if (nbFrisbeeShot == m_nbFrisbeeToShoot)
                    {
                        m_StateInitialized = false;
                        m_StateStartTime = 0;
                        state = 6;

                        SmartDashboard.putString("Autonomous State", "Completed");
                        SmartDashboard.putString("Autonomous Next State", "");
                      //  System.out.println("autonomous state : " + state);
                    }
                    else if (Robot.frisbee.IsWaitingToShoot())
                    {
                        timeBetweenShot =  System.currentTimeMillis();
                        nbFrisbeeShot++;
                        Robot.frisbee.ShootFrisbee();
                      //  System.out.println("AUTONOMOUS: Shooting a new frisbee");
                    }
                }
                
                break;
            }
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return state == 6;
    }
    // Called once after isFinished returns true

    protected void end() {
        
        // Stop spinning when autonomous mode is completed
        Robot.shooterSpinner.SetSpinning(false);
        // Make sure the angle motor is stopped
        Robot.ascentAngle.AngleDoNothing();
        
    }
    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run

    protected void interrupted() {
        // Call the end method to make sure the motors are stopped.
        end();
    }
}
