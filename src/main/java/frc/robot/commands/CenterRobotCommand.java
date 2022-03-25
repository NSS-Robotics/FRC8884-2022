package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.UltrasonicSubsystem;

public class CenterRobotCommand extends CommandBase {
    private final UltrasonicSubsystem m_ultrasonicSubsystem;
    private final DriveSubsystem m_driveSubsystem;

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
        double left = m_ultrasonicSubsystem.getLeft();
        double right = m_ultrasonicSubsystem.getRight();
        double delta = Math.abs(left - right);

        SmartDashboard.putNumber("Ultrasonic Left", left);
        SmartDashboard.putNumber("Ultrasonic Right", right);
        SmartDashboard.putNumber("Ultrasonic Delta", delta);

        if (delta < 2.0) {
            this.end(false);
        }

        double highestVal = Math.max(left, right);
        m_driveSubsystem.drive(0, highestVal == right ? 0.1 : -0.1);
    }
}
