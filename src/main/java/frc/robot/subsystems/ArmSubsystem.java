package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.RelativeEncoder;
import edu.wpi.first.math.controller.PIDController;

public class ArmSubsystem extends edu.wpi.first.wpilibj2.command.PIDSubsystem {
    private final CANSparkMax m_motor = new CANSparkMax(3, CANSparkMaxLowLevel.MotorType.kBrushless);
    private final RelativeEncoder m_encoder = m_motor.getEncoder();

    static final double kP = 0;
    static final double kI = 0;
    static final double kD = 0;

    public ArmSubsystem() {
        super(new PIDController(kP, kI, kD));
    }

    @Override
    protected void useOutput(double output, double setpoint) {
//        m_encoder.setPositionConversionFactor();
//        m_motor.getPIDController().setv
    }

    @Override
    protected double getMeasurement() {
        return 0;
    }
}
