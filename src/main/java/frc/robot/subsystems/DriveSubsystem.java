package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveSubsystem extends SubsystemBase {
    // Left motors
    private final CANSparkMax m_frontLeft = new CANSparkMax(4, CANSparkMaxLowLevel.MotorType.kBrushless);
    private final CANSparkMax m_rearLeft = new CANSparkMax(8, CANSparkMaxLowLevel.MotorType.kBrushless);
    MotorControllerGroup m_left = new MotorControllerGroup(m_frontLeft, m_rearLeft);

    // Right motors
    private final CANSparkMax m_frontRight = new CANSparkMax(2, CANSparkMaxLowLevel.MotorType.kBrushless);
    private final CANSparkMax m_rearRight = new CANSparkMax(9, CANSparkMaxLowLevel.MotorType.kBrushless);
    MotorControllerGroup m_right = new MotorControllerGroup(m_frontRight, m_rearRight);

    private final DifferentialDrive m_drive = new DifferentialDrive(m_left, m_right);

    public DriveSubsystem() {
        // We need to invert one side of the drivetrain so that positive voltages
        // result in both sides moving forward. Depending on how your robot's
        // gearbox is constructed, you might have to invert the left side instead.
        this.m_left.setInverted(true);

        setMaxOutput(0.1);
    }

    public void setMaxOutput(double output) {
        this.m_drive.setMaxOutput(output);
    }

    public void arcadeDrive(double leftSpeed, double rightSpeed) {
     m_drive.arcadeDrive(leftSpeed, rightSpeed);
    }

    public void stopMotor() {
        m_drive.stopMotor();
    }

    public void tankDrive(double leftSpeed, double rightSpeed) {
        m_drive.tankDrive(leftSpeed, rightSpeed);
    }
}
