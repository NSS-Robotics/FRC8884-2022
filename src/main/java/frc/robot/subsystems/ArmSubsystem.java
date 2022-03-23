package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.SparkMaxLimitSwitch;
import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ArmSubsystem extends SubsystemBase {
    private final CANSparkMax m_motor = new CANSparkMax(10, CANSparkMaxLowLevel.MotorType.kBrushless);

    public ArmSubsystem() {
        m_motor.burnFlash();
    }

    public void up(double speed) {
        m_motor.set(0.2);
    }

    public void down(double speed) {
        m_motor.set(-0.2);
    }


    public void stop() {
        m_motor.stopMotor();
    }
}