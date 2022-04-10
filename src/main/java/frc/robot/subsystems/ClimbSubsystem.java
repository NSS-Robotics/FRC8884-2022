package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ClimbSubsystem extends SubsystemBase {
    private final CANSparkMax m_motor = new CANSparkMax(12, CANSparkMaxLowLevel.MotorType.kBrushless);

    boolean shouldGo = false;
    boolean isUp = false;
    Timer timer = new Timer();
    boolean flag = false;

    // Number of seconds the motor should travel for, in order to switch positions
    private final double travelDownSeconds = 6;
    private final double travelUpSeconds = 2;
    private final double motorUp = 0.35;
    private final double motorDown = -0.6;

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
        SmartDashboard.putNumber("timer", timer.get());
        SmartDashboard.putNumber("climb speed", m_motor.get());

        if (!shouldGo) return;

        System.out.println("Running " + timer.get());

        if (timer.get() < travelDownSeconds && isUp) {
                m_motor.set(motorDown);
        }

        if (timer.get() < travelUpSeconds && !isUp) {
            m_motor.set(motorUp);
        }

        if (timer.get() >= travelDownSeconds && isUp) {
            m_motor.set(-0.06);

            isUp = !isUp;
            flag = false;
            shouldGo = false;
            timer.reset();

            m_motor.stopMotor();
        }

        if (timer.get() >= travelUpSeconds && !isUp) {
            m_motor.set(0.04);

            isUp = !isUp;
            flag = false;
            shouldGo = false;
            timer.reset();

            if (isUp) m_motor.stopMotor();
        }
    }

    public void up() {
        m_motor.set(motorUp);
    }

    public void down() {
        m_motor.set(motorDown);
    }

    public void stop() {
        m_motor.stopMotor();
    }
}