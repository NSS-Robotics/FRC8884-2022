package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.UltrasonicSubsystem;


public class DriveDistanceCommand extends CommandBase {
    private final DriveSubsystem m_driveSubsystem;
    private final UltrasonicSubsystem m_ultrasonicSubsystem;

    private final Timer timer = new Timer();
    private double maxSeconds = 100;

    public DriveDistanceCommand(
            double time,
            DriveSubsystem driveSubsystem,
            UltrasonicSubsystem ultrasonicSubsystem
    ) {
        m_driveSubsystem = driveSubsystem;
        m_ultrasonicSubsystem = ultrasonicSubsystem;

        addRequirements(driveSubsystem, ultrasonicSubsystem);
    }

    public void drive(double mm, double maxSeconds) {
        this.maxSeconds = maxSeconds;

        timer.reset();
        timer.start();

        while (m_ultrasonicSubsystem.get() > mm) {
            if (timer.get() > maxSeconds) {
                return;
            }

            m_driveSubsystem.arcadeDrive(0.2, 0);
        }
    }

    @Override
    public boolean isFinished() {
        return maxSeconds > timer.get();
    }
}
