package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeSubsystem extends SubsystemBase {
    private final CANSparkMax m_motor = new CANSparkMax(11, CANSparkMaxLowLevel.MotorType.kBrushed);

    public IntakeSubsystem() {}

    public void intake() {
        m_motor.set(-0.8);
       System.out.println("I should intake");
    }

    public void shoot() {
        m_motor.set(0.8);
        System.out.println("I should shoot");
    }

    public void stop() {
        m_motor.stopMotor();
    }
}
    



