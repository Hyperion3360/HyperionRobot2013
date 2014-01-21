package org.usfirst.frc3360.Hyperion_3360_2013.subsystems;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SpeedController;
import org.usfirst.frc3360.Hyperion_3360_2013.RobotMap;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc3360.Hyperion_3360_2013.commands.DefaultAscentMonitoring;

public class Ascent_Monitoring extends Subsystem {
   DigitalInput LimitLeft = RobotMap.HookLeftLimitLED;
   DigitalInput LimitRight = RobotMap.HookRightLimitLED;
    
    
    public Ascent_Monitoring()
    {
        SmartDashboard.putBoolean("HookLeftLocked:", false);
        SmartDashboard.putBoolean("HookRightLocked", false);  
    }
    
    public void initDefaultCommand() {
        setDefaultCommand(new DefaultAscentMonitoring());
    }
    
    public void MonitoringHandle()
    {
      SmartDashboard.putBoolean("HookLeftLocked:", !LimitLeft.get());
      SmartDashboard.putBoolean("HookRightLocked", !LimitRight.get());  
    }
}