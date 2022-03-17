package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeSubsystem extends SubsystemBase {
    private final CANSparkMax m_motor = new CANSparkMax(11, CANSparkMaxLowLevel.MotorType.kBrushed);
    // private final DigitalInput topLimitSwitch = new DigitalInput(0);
    // private final DigitalInput bottomLimitSwitch = new DigitalInput(9);

    public IntakeSubsystem() {}

    public void forward() {
        m_motor.set(-1);
        
    }

    public void backward() {
        m_motor.set(1);
    }

    public void stop() {
        m_motor.stopMotor();
        
    }
}
    



