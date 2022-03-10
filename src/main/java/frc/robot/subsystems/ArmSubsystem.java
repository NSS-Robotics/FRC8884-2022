package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ArmSubsystem extends SubsystemBase {
    private final CANSparkMax m_motor = new CANSparkMax(3, CANSparkMaxLowLevel.MotorType.kBrushless);

    boolean shouldGo = false;
    boolean isUp = false;
    int numRan = 0;
    Timer timer = new Timer();

    public ArmSubsystem() {
        m_motor.burnFlash();
    }

    public void down() {
        shouldGo = true;
        isUp = false;
    }

    public void up() {
        shouldGo = true;
        isUp = true;
    }

    @Override
    public void periodic() {
        if (!shouldGo) return;

        timer.start();

        System.out.printf("Num ran: %s\n", numRan);

        numRan++;

        if (numRan < 50) {
            if (isUp) {
                m_motor.set(0.1);
            } else {
                m_motor.set(-0.1);
            }
        }

        if (numRan >= 50) {
            isUp = !isUp;
            shouldGo = false;

            numRan = 0;

            m_motor.set(0);
        }

    }
}
