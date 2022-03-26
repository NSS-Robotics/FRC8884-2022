package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.UltrasonicSubsystem;

public class CenterRobotCommand extends CommandBase {
    private final UltrasonicSubsystem m_ultrasonicSubsystem;
    private final DriveSubsystem m_driveSubsystem;
    private double leftSpeed = 0;
    private double rightSpeed = 0;
    private boolean isDone = false;

    public CenterRobotCommand(
            UltrasonicSubsystem ultrasonicSubsystem,
            DriveSubsystem driveSubsystem
    ) {
        m_ultrasonicSubsystem = ultrasonicSubsystem;
        m_driveSubsystem = driveSubsystem;

        addRequirements(m_ultrasonicSubsystem, m_driveSubsystem);
    }

    @Override
    public void execute() {
        m_driveSubsystem.drive(leftSpeed, rightSpeed);

        double left = m_ultrasonicSubsystem.getLeft();
        double right = m_ultrasonicSubsystem.getRight();
        double delta = Math.abs(left - right);

        SmartDashboard.putNumber("Ultrasonic Left", left);
        SmartDashboard.putNumber("Ultrasonic Right", right);
        SmartDashboard.putNumber("Ultrasonic Delta", delta);

        if (delta < 0.4) {
            isDone = true;
        }

        double highestVal = Math.max(left, right);
        rightSpeed = highestVal == right ? -0.3 : 0.3;
    }

    @Override
    public void initialize() {
        isDone = false;
    }

    @Override
    public boolean isFinished() {
        return isDone;
    }
}
