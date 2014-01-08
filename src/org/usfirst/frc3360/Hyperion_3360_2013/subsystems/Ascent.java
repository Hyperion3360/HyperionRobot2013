package org.usfirst.frc3360.Hyperion_3360_2013.subsystems;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Timer;
import org.usfirst.frc3360.Hyperion_3360_2013.RobotMap;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Ascent extends Subsystem {
    
    DoubleSolenoid ascent_SolenoidBlockHookRight = RobotMap.ascentAscent_SolenoidBlockHookRight;
    DoubleSolenoid ascent_SolenoidBlockHookLeft = RobotMap.ascentAscent_SolenoidBlockHookLeft;
    SpeedController ascent_MotorLeft = RobotMap.ascentAscent_MotorLeft;
    SpeedController ascent_MotorRight = RobotMap.ascentAscent_MotorRight;
    SpeedController ascent_SetAngle = RobotMap.ascentAscent_SetAngle;
    DoubleSolenoid pistonHookR = RobotMap.ascentPistonHookR;
    DoubleSolenoid pistonHookL = RobotMap.ascentPistonHookL;
    
    
    //A CHANGER DEPENDAMENT DE L'ANGLE DU SERVO
    final double TimePistonHooks = 1.5;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    public void initDefaultCommand() {
        //setDefaultCommand(new MySpecialCommand());
    }
    public void CharoitGoUp(){
     //   if (!ascent_LimitSwitchUp.get()){
        //il faut inverser un moteur car ils sont sur la meme gear...
        ascent_MotorLeft.set(1);
        ascent_MotorRight.set(-1);
     //   }
      //  else {
      //  ChariotDoNothing();
        
      //  }
    }
    public void ChariotGoDown(){
      //  if (!ascent_LimitSwitchDown.get()){
        //il faut inverser un moteur car ils sont sur la meme gear...
        ascent_MotorLeft.set(-1);
        ascent_MotorRight.set(1);
      //  }
     //   else {
      //  ChariotDoNothing();
        
      //  }
        
    }
    public void ChariotDoNothing(){
        ascent_MotorLeft.set(0);
        ascent_MotorRight.set(0);
    }
    public void AngleGoFront(){
      //  if (!ascent_LimitSwitchFront.get()){
        ascent_SetAngle.set(1);
        
        }
       // else {      
     //   AngleDoNothing();
      //  }
   // }
    public void AngleGoRear(){
     //   if (!ascent_LimitSwitchRear.get()){
        ascent_SetAngle.set(-1);
        
        }
      //  else {     
      //  AngleDoNothing();
       // }     
  //  }
    public void AngleDoNothing(){
        ascent_SetAngle.set(0);
    }
    public void HooksOn(){
        pistonHookR.set(DoubleSolenoid.Value.kForward);
        pistonHookL.set(DoubleSolenoid.Value.kForward);
        Timer.delay(TimePistonHooks);
        ascent_SolenoidBlockHookLeft.set(DoubleSolenoid.Value.kForward);
        ascent_SolenoidBlockHookRight.set(DoubleSolenoid.Value.kForward);
    }
    public void HooksOff(){
        ascent_SolenoidBlockHookLeft.set(DoubleSolenoid.Value.kReverse);
        ascent_SolenoidBlockHookRight.set(DoubleSolenoid.Value.kReverse);
        Timer.delay(TimePistonHooks);
        pistonHookR.set(DoubleSolenoid.Value.kReverse);
        pistonHookL.set(DoubleSolenoid.Value.kReverse);
    }
}