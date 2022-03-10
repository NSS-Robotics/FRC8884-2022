package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class MeasureSubsystem extends SubsystemBase {
    private final CANSparkMax m_motor = new CANSparkMax(3, CANSparkMaxLowLevel.MotorType.kBrushless);

    public MeasureSubsystem() {

    }
}
