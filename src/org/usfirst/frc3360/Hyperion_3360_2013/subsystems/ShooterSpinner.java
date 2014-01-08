package org.usfirst.frc3360.Hyperion_3360_2013.subsystems;

import org.usfirst.frc3360.Hyperion_3360_2013.RobotMap;
import org.usfirst.frc3360.Hyperion_3360_2013.commands.*;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ShooterSpinner extends Subsystem {

    SpeedController canon_SpinnerMotor = RobotMap.canonCanon_SpinnerMotor;
    private boolean m_bSpinning;
    // Spinning state
    private long m_SpinStartTime;

    public ShooterSpinner() {
        // No command must be executed at the begining.
        m_bSpinning = false;
        // Set the default state.
        m_SpinStartTime = 0;
        // Set the actuator in the good state.
        canon_SpinnerMotor.set(0);
    }
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        setDefaultCommand(new DefaultShooterSpinnerHandler());
    }

    // Public control methods
    public void SetSpinnerSpeed(boolean spinning) {
        System.out.println("Set spin command:" + spinning);
        m_bSpinning = spinning;
    }

    public boolean IsSpinning() {
        return m_bSpinning;
    }

    public long GetSpinningTime() {
        System.out.println("Spinning since: " + (System.currentTimeMillis() - m_SpinStartTime));
        return System.currentTimeMillis() - m_SpinStartTime;
    }

    // Internal canon handlers
    public void SetSpinnerSpeedTower(boolean request) {
        if (request) {
            if (canon_SpinnerMotor.get() != -.10) {
                canon_SpinnerMotor.set(-.10);
                m_SpinStartTime = System.currentTimeMillis() + 1;
            }

        }
        if (!request) {
            canon_SpinnerMotor.set(0);
        }
    }

    public void HandleSpinner() {

        if (m_bSpinning && canon_SpinnerMotor.get() != -1) {
            // Start the spinner.
            canon_SpinnerMotor.set(-1);
            m_SpinStartTime = System.currentTimeMillis();
        } else if (!m_bSpinning) {
            // Stop the spinner.
            canon_SpinnerMotor.set(0);
        }
    }
}
