package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.SparkMaxLimitSwitch;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ArmSubsystem extends SubsystemBase {
    private final CANSparkMax m_motor = new CANSparkMax(10, CANSparkMaxLowLevel.MotorType.kBrushless);

    private final SparkMaxLimitSwitch m_forwardLimit;
    private final SparkMaxLimitSwitch m_reverseLimit;

    boolean shouldGo = false;
    boolean isUp = false;
    Timer timer = new Timer();
    boolean flag = false;

    // Number of seconds the motor should travel for, in order to switch positions
    private final double travelSeconds = 0.5;
    private final double motorUp = 0.3;
    private final double motorDown = -0.3;

    public ArmSubsystem() {
        m_motor.burnFlash();

        m_forwardLimit = m_motor.getForwardLimitSwitch(SparkMaxLimitSwitch.Type.kNormallyClosed);
        m_reverseLimit = m_motor.getReverseLimitSwitch(SparkMaxLimitSwitch.Type.kNormallyClosed);
    }
//    public void down() {
//        shouldGo = true;
//        isUp = false;
//
//    }
//
    public void up() {
        m_motor.set(motorUp);
    }

    public void down() {
        m_motor.set(motorDown);
    }


    public void stop() {
        m_motor.stopMotor();
    }

    public void toggle() {
        if(flag == false){
            flag = true;
            shouldGo = true;
            timer.start();
            timer.reset();
        }
    }

//    @Override
//    public void periodic() {
//        if (!shouldGo) return;
//
//        if (timer.get() < travelSeconds) {
//            if (isUp) {
//                m_motor.set(motorUp);
//            } else {
//                m_motor.set(motorDown);
//            }
//        }
//        if (timer.get() >= travelSeconds) {
//            if(!isUp){
//                m_motor.set(-0.1);
//            }else{
//                m_motor.set(0);
//            }
//            isUp = !isUp;
//            flag = false;
//            shouldGo = true;
//            timer.reset();
//        }
//
//
//    }
}