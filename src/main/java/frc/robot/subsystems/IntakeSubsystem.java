package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeSubsystem extends SubsystemBase {
    private final CANSparkMax m_motor = new CANSparkMax(100, CANSparkMaxLowLevel.MotorType.kBrushless);

    public IntakeSubsystem() {}

    public void forward() {
        m_motor.set(0.7);
    }

    public void backward() {
        m_motor.set(-0.7);
    }

    public void stop() {
        m_motor.stopMotor();
    }
}
