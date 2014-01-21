package org.usfirst.frc3360.Hyperion_3360_2013.subsystems;
import edu.wpi.first.wpilibj.SpeedController;
import org.usfirst.frc3360.Hyperion_3360_2013.RobotMap;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Ascent_Chariot extends Subsystem {
    SpeedController ascent_MotorLeft = RobotMap.ascentAscent_MotorLeft;
    SpeedController ascent_MotorRight = RobotMap.ascentAscent_MotorRight;
    int m_currentCommand;
    
    public Ascent_Chariot()
    {
        SmartDashboard.putNumber("Ascent chartio", 0);
        SmartDashboard.putString("Ascent chariot status", "Standing Still");
        m_currentCommand = 0;
        ascent_MotorLeft.set(0);
        ascent_MotorRight.set(0);
    }
    
    public void initDefaultCommand() {
    }
    
    public void ChariotGoUp(){
        if (m_currentCommand != 1)
        {
            SmartDashboard.putNumber("Ascent chartio", 1);
            SmartDashboard.putString("Ascent chariot status", "Going Up");
            m_currentCommand = 1;
            //il faut inverser un moteur car ils sont sur la meme gear...
            ascent_MotorLeft.set(1);
            ascent_MotorRight.set(-1); 
        }
    }
    
    public void ChariotGoDown(){
        if (m_currentCommand != -1)
        {
            SmartDashboard.putNumber("Ascent chartio", -1);
            SmartDashboard.putString("Ascent chariot status", "Going Down");
            m_currentCommand = -1;
            ascent_MotorLeft.set(-1);
            ascent_MotorRight.set(1);
        }
    }
    
    public void ChariotDoNothing(){
        if (m_currentCommand != 0)
        {
            SmartDashboard.putNumber("Ascent chartio", 0);
            SmartDashboard.putString("Ascent chariot status", "Standing Still");
            m_currentCommand = 0;
            ascent_MotorLeft.set(0);
            ascent_MotorRight.set(0);
        }
    }
}