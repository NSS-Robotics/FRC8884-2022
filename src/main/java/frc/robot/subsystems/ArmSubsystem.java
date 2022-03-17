package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.SparkMaxLimitSwitch;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ArmSubsystem extends SubsystemBase {
    private final CANSparkMax m_motor = new CANSparkMax(10, CANSparkMaxLowLevel.MotorType.kBrushless);

    private SparkMaxLimitSwitch m_forwardLimit;
    private SparkMaxLimitSwitch m_reverseLimit;

    boolean shouldGo = false;
    boolean isUp = false;
    Timer timer = new Timer();
    boolean flag = false;

    // Number of seconds the motor should travel for, in order to switch positions
    private final double travelSeconds = 0.75;
    private final double motorUp = 0.3;
    private final double motorDown = -0.2;

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
//    public void up() {
//        shouldGo = true;
//        isUp = true;
//
//    }

    public void toggle() {
        if(flag == false){
            flag = true;
            shouldGo = true;
            timer.start();
            timer.reset();
            System.out.println("Bottom: "+m_forwardLimit.isLimitSwitchEnabled());
            System.out.println("Top: "+m_reverseLimit.isLimitSwitchEnabled());
        }
    }
    @Override
    public void periodic() {
        if (!shouldGo) return;

        if (timer.get() < travelSeconds) {

            if (isUp) {
                
                m_motor.set(motorUp);
            } else {
                m_motor.set(motorDown);
            }
        }
        if (timer.get() >= travelSeconds) {
            if(!isUp){
                m_motor.set(-0.1);
                 System.out.println("Bottom: "+m_forwardLimit.isLimitSwitchEnabled());
                 System.out.println("Top: "+m_reverseLimit.isLimitSwitchEnabled());
            }else{
                System.out.println("Bottom: "+m_forwardLimit.isLimitSwitchEnabled());
                System.out.println("Top: "+m_reverseLimit.isLimitSwitchEnabled());
                m_motor.set(0);
            }
            isUp = !isUp;
            flag = false;
            shouldGo = true;
            timer.reset();



        }


    }
}