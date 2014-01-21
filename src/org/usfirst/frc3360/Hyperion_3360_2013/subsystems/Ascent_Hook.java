package org.usfirst.frc3360.Hyperion_3360_2013.subsystems;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import org.usfirst.frc3360.Hyperion_3360_2013.RobotMap;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Ascent_Hook extends Subsystem {
    
    DoubleSolenoid ascent_SolenoidBlockHookRight = RobotMap.ascentAscent_SolenoidBlockHookRight;
    DoubleSolenoid ascent_SolenoidBlockHookLeft = RobotMap.ascentAscent_SolenoidBlockHookLeft;
    DoubleSolenoid pistonHookR = RobotMap.ascentPistonHookR;
    DoubleSolenoid pistonHookL = RobotMap.ascentPistonHookL;
    DigitalInput LimitLeft = RobotMap.HookLeftLimitLED;
    DigitalInput LimitRight = RobotMap.HookRightLimitLED;
    
    final double TimePistonHooksMs = 500;
    
    long m_HookOnTime = 0;
    boolean m_HookStatus = false;
    boolean m_HookLockStatus = false;
    
    public Ascent_Hook()
    {
        pistonHookR.set(DoubleSolenoid.Value.kReverse);
        pistonHookL.set(DoubleSolenoid.Value.kReverse);
        m_HookLockStatus = false;
        System.out.println("Hook unlocked");
        SmartDashboard.putString("Hook lock status", "UNLOCKED");
        SmartDashboard.putBoolean("HookLeftLocked:", LimitLeft.get());
        SmartDashboard.putBoolean("HookRightLocked", LimitRight.get());
        
        
        ascent_SolenoidBlockHookLeft.set(DoubleSolenoid.Value.kReverse);
        ascent_SolenoidBlockHookRight.set(DoubleSolenoid.Value.kReverse);
        m_HookStatus = false;
        System.out.println("Hook status OFF");
        SmartDashboard.putString("Hook status", "OFF");
    }
    
    public void initDefaultCommand() {
    }
    
    public void HooksOn(){
        if (!m_HookStatus && !m_HookLockStatus)
        {
            pistonHookR.set(DoubleSolenoid.Value.kForward);
            pistonHookL.set(DoubleSolenoid.Value.kForward);
            m_HookOnTime = System.currentTimeMillis();
            m_HookStatus = true;
        
            System.out.println("Hook status ON");
            SmartDashboard.putString("Hook status", "ON");
        }
    }
    
    public void HooksOff(){
        if (m_HookStatus && !m_HookLockStatus)
        {
            ascent_SolenoidBlockHookLeft.set(DoubleSolenoid.Value.kReverse);
            ascent_SolenoidBlockHookRight.set(DoubleSolenoid.Value.kReverse);
            
            m_HookStatus = false;
        
            System.out.println("Hook status OFF");
            SmartDashboard.putString("Hook status", "OFF");
        }
    }
    
    public void LockHook(){
        if (!m_HookLockStatus && (System.currentTimeMillis() - m_HookOnTime) > TimePistonHooksMs)
        {
            ascent_SolenoidBlockHookLeft.set(DoubleSolenoid.Value.kForward);
            ascent_SolenoidBlockHookRight.set(DoubleSolenoid.Value.kForward);
            m_HookLockStatus = true;
            
            System.out.println("Hook locked");
            SmartDashboard.putString("Hook lock status", "LOCKED");
            SmartDashboard.putBoolean("Hook Status", true);
        }
        else
        {
            System.out.println("Cannot lock");
        }
    }
    
    public void UnlockHook(){
        if (m_HookLockStatus)
        {
            pistonHookR.set(DoubleSolenoid.Value.kReverse);
            pistonHookL.set(DoubleSolenoid.Value.kReverse);
            m_HookLockStatus = false;
            
            System.out.println("Hook unlocked");
            SmartDashboard.putString("Hook lock status", "UNLOCKED");
            SmartDashboard.putBoolean("Hook Status", false);
        }
        else
        {
            System.out.println("Cannot unlock");
        }
    }
    
    public boolean HookLockStatus()
    {
        return m_HookLockStatus;
    }
    
    public boolean HookStatus()
    {
        return m_HookStatus;
    }
    public void HooksLockStatus(){
        
        SmartDashboard.putBoolean("HookLeftLocked:", LimitLeft.get());
        SmartDashboard.putBoolean("HookRightLocked", LimitRight.get());
    }
}