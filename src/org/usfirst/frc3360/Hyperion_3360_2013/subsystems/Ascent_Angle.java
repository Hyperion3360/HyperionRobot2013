package org.usfirst.frc3360.Hyperion_3360_2013.subsystems;
import edu.wpi.first.wpilibj.SpeedController;
import org.usfirst.frc3360.Hyperion_3360_2013.RobotMap;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Ascent_Angle extends Subsystem {
    
    SpeedController ascent_SetAngle = RobotMap.ascentAscent_SetAngle;
    int m_currentCommand;
    
    public Ascent_Angle()
    {
        SmartDashboard.putNumber("Ascent angle", 0);
        SmartDashboard.putString("Ascent angle status", "Standing Still");
        m_currentCommand = 0;
        ascent_SetAngle.set(m_currentCommand);
    }
    
    public void initDefaultCommand() {
    }
    
    public void AngleGoFront(){
        if (m_currentCommand != 1)
        {
            SmartDashboard.putNumber("Ascent angle", 1);
            SmartDashboard.putString("Ascent angle status", "Tilting Forward");
            m_currentCommand = 1;
            ascent_SetAngle.set(m_currentCommand);
        }
    }
    
    public void AngleGoRear(){
        if (m_currentCommand != -1)
        {
            SmartDashboard.putNumber("Ascent angle", -1);
            SmartDashboard.putString("Ascent angle status", "Tilting Backward");
            m_currentCommand = -1;
            ascent_SetAngle.set(m_currentCommand);
        }
    }
    
    public void AngleDoNothing(){
        if (m_currentCommand != 0)
        {
            SmartDashboard.putNumber("Ascent angle", 0);
            SmartDashboard.putString("Ascent angle status", "Standing Still");
            m_currentCommand = 0;
            ascent_SetAngle.set(m_currentCommand);
        }
    }
}