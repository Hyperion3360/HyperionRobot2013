package org.usfirst.frc3360.Hyperion_3360_2013.subsystems;

import org.usfirst.frc3360.Hyperion_3360_2013.RobotMap;
import org.usfirst.frc3360.Hyperion_3360_2013.commands.*;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc3360.Hyperion_3360_2013.Robot;

public class ShooterAngle extends Subsystem {
    SpeedController setShooterAngle = RobotMap.canonSetShooterAngle;
    AnalogChannel potentiometer = RobotMap.canonPotentiometer;
    
    private final double m_MaxHighAngle = 3.229;
    private final double m_MaxLowAngle = 4.077;
    private double m_AngleTolerance = 0.007;
    private double m_SpeedFactor = 0.85;
    private double m_DesiredAngle;
    private boolean m_DesiredAngleSet;
    

    public ShooterAngle() {
        m_DesiredAngleSet = false;
        m_DesiredAngle = GetCurrentAngle();
    }
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        setDefaultCommand(new DefaultShooterAngleHandler());
    }
    
    public void ShooterGoDown(double nbDegree)
    {
        double newAngle = m_MaxHighAngle;
        
        if (m_DesiredAngleSet)
        {
            System.out.println("Current Angle: " + GetCurrentAngle() + "Sub degree:" + nbDegree);
            newAngle = GetCurrentAngle() + nbDegree;
            m_DesiredAngleSet = false;
        }
        else
        {
            System.out.println("Current Angle: " + GetCurrentAngle() + "Sub degree:" + nbDegree);
            newAngle = m_DesiredAngle + nbDegree;
        }
        System.out.println("New angle:" + newAngle);
        if (newAngle < m_MaxHighAngle)
        {
            newAngle = m_MaxHighAngle;
        }
        else if (newAngle > m_MaxLowAngle)
        {
            newAngle = m_MaxLowAngle;
        }
        
        if (nbDegree >= 0.01)
        {
            m_SpeedFactor = 0.85;
            m_AngleTolerance = 0.008;
        }
        else
        {
            m_SpeedFactor = 0.45;
            m_AngleTolerance = 0.004;
        }
        
        m_DesiredAngle = newAngle;
        System.out.println("Requesting to go to angle:" + m_DesiredAngle);
    }
    
    public void ShooterGoUp(double nbDegree)
    {
        double newAngle = m_MaxHighAngle;
        
        if (m_DesiredAngleSet)
        {
            System.out.println("Current Angle: " + GetCurrentAngle() + "Sub degree:" + nbDegree);
            newAngle = GetCurrentAngle() - nbDegree;
            m_DesiredAngleSet = false;
        }
        else
        {
            System.out.println("Current Angle: " + GetCurrentAngle() + "Sub degree:" + nbDegree);
            newAngle = m_DesiredAngle - nbDegree;
        }
        System.out.println("New angle:" + newAngle);
        if (newAngle < m_MaxHighAngle)
        {
            newAngle = m_MaxHighAngle;
        }
        else if (newAngle > m_MaxLowAngle)
        {
            newAngle = m_MaxLowAngle;
        }
        
        if (nbDegree >= 0.01)
        {
            m_SpeedFactor = 0.85;
            m_AngleTolerance = 0.008;
        }
        else
        {
            m_SpeedFactor = 0.45;
            m_AngleTolerance = 0.004;
        }
        
        m_DesiredAngle = newAngle;
        System.out.println("Requesting to go to angle:" + m_DesiredAngle);
    }

    // Public control methods
    public void SetShooterAngle(double angle) {
        if (angle < m_MaxHighAngle)
        {
            angle = m_MaxHighAngle;
        }
        else if (angle > m_MaxLowAngle)
        {
            angle = m_MaxLowAngle;
        }
        
        m_SpeedFactor = 0.85;
        m_AngleTolerance = 0.008;

        m_DesiredAngle = angle;
        m_DesiredAngleSet = true;
        
        System.out.println("Requesting to go to angle:" + m_DesiredAngle);
    }
    
    public boolean IsShooterDesiredAngleReached()
    {
        boolean bAngleReached = false;
        if (GetCurrentAngle() < (m_DesiredAngle + m_AngleTolerance) && GetCurrentAngle() > (m_DesiredAngle - m_AngleTolerance))
        {
            bAngleReached = true;
        }
        return bAngleReached;
    }
    
    public void HandleAngle()
    {
        if (Math.abs(Robot.oi.getJoyCoPilot().getRawAxis(4)) > 0.15)
        {
            if ((Robot.oi.getJoyCoPilot().getRawAxis(4) > 0 && (GetCurrentAngle() < m_MaxLowAngle)) ||
                (Robot.oi.getJoyCoPilot().getRawAxis(4) < 0 && (GetCurrentAngle() > m_MaxHighAngle)))
            {
                System.out.println("Modifying angle" + Robot.oi.getJoyCoPilot().getRawAxis(4));
                setShooterAngle.set(Robot.oi.getJoyCoPilot().getRawAxis(4));
            }
            else
            {
                setShooterAngle.set(0);
                System.out.println("Stoping canon angle motor");
            }
            
            m_DesiredAngle = 0;
            m_DesiredAngleSet = true;
            
            SmartDashboard.putNumber("Shooter Current Angle", GetCurrentAngle());
            SmartDashboard.putNumber("Shooter Desired Angle", 0);
        }
        else if (m_DesiredAngle == 0)
        {
            SmartDashboard.putNumber("Shooter Current Angle", GetCurrentAngle());
            SmartDashboard.putNumber("Shooter Desired Angle", 0);

            setShooterAngle.set(0);
        }
        else
        {
            SmartDashboard.putNumber("Shooter Current Angle", GetCurrentAngle());
            SmartDashboard.putNumber("Shooter Desired Angle", m_DesiredAngle);

            double aproachFactor = 1;
            if (Math.abs(GetCurrentAngle() - m_DesiredAngle) < 0.01)
            {
                aproachFactor = 0.4;
            }

            if (GetCurrentAngle() > (m_DesiredAngle + m_AngleTolerance))
            {
                SmartDashboard.putString("Shooter Current Command", "Going Up");
                SmartDashboard.putBoolean("Angle Reached", false);


                setShooterAngle.set(-m_SpeedFactor*aproachFactor);
            }
            else if (GetCurrentAngle() < (m_DesiredAngle - m_AngleTolerance))
            {
                SmartDashboard.putString("Shooter Current Command", "Going Down");
                SmartDashboard.putBoolean("Angle Reached", false);
                setShooterAngle.set(m_SpeedFactor*aproachFactor);
            }
            else
            {
                SmartDashboard.putString("Shooter Current Command", "Angle Reached");
                SmartDashboard.putBoolean("Angle Reached", true);
                setShooterAngle.set(0);
            }
        }
    }
    
    private double GetCurrentAngle()
    {
        return potentiometer.getAverageVoltage();
    }
}
