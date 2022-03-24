package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.SparkMaxLimitSwitch;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ArmSubsystem extends SubsystemBase {
    private final CANSparkMax m_motor = new CANSparkMax(10, CANSparkMaxLowLevel.MotorType.kBrushless);

    boolean shouldGo = false;
    boolean isUp = true;
    Timer timer = new Timer();
    boolean flag = false;

    // Number of seconds the motor should travel for, in order to switch positions
    private final double travelSeconds = 0.75;
    private final double motorUp = 0.28;
    private final double motorDown = -0.22;
    public ArmSubsystem() {
        m_motor.burnFlash();
    }

    public void stop() {
        m_motor.stopMotor();
    }

    public void toggle() {
        if(!flag){
            flag = true;
            shouldGo = true;
            timer.start();
            timer.reset();
        }
    }

    @Override
    public void periodic() {
        if (!shouldGo) return;

        if (timer.get() < travelSeconds) {
            if (!isUp) {
                m_motor.set(motorUp);
            } else {
                m_motor.set(motorDown);
            }
        }
        if (timer.get() >= travelSeconds) {
            if(!isUp){
                m_motor.set(0.03);
            }else{
                m_motor.set(-0.09);
                System.out.println("Going down");
            }
            isUp = !isUp;
            flag = false;
            shouldGo = false;
            timer.reset();
        }
    }

    public void stayUp() {
        m_motor.set(0.03);
    }
}