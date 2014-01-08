package org.usfirst.frc3360.Hyperion_3360_2013.subsystems;

import org.usfirst.frc3360.Hyperion_3360_2013.RobotMap;
import org.usfirst.frc3360.Hyperion_3360_2013.commands.*;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ShooterAngle extends Subsystem {

    DoubleSolenoid setShooterAngle = RobotMap.canonSetShooterAngle;
    private int m_uShooterAngle;
    // Angle request
    private boolean m_bAngleRequest;
    // Private 
    private final DoubleSolenoid.Value m_ShooterAngle1 = DoubleSolenoid.Value.kForward;
    private final DoubleSolenoid.Value m_ShooterAngle2 = DoubleSolenoid.Value.kReverse;
    public final int m_ShooterAngle1Val = 1;
    public final int m_ShooterAngle2Val = 2;

    public ShooterAngle() {
        // Set the default state
        m_uShooterAngle = m_ShooterAngle1Val;
        // No command must be executed at the begining.
        m_bAngleRequest = false;
        // Set the actuator in the good state.
        setShooterAngle.set(m_ShooterAngle1);
    }
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        setDefaultCommand(new DefaultShooterAngleHandler());
    }

    // Public control methods
    public void SetShooterAngle(int angle) {
        if (angle == m_ShooterAngle1Val
                || angle == m_ShooterAngle2Val) {
            m_uShooterAngle = angle;
            m_bAngleRequest = true;
        }
    }

    public void HandleShooterAngle() {
        if (m_bAngleRequest) {
            m_bAngleRequest = false;
            switch (m_uShooterAngle) {
                case (1): {
                    setShooterAngle.set(m_ShooterAngle1);
                    break;
                }
                case (2): {
                    setShooterAngle.set(m_ShooterAngle2);
                    break;
                }
            }
        }
    }
}
