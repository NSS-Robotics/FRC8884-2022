// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.*;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.UltrasonicSubsystem;

/**
 * An example command that uses an example subsystem.
 */
public class AutonomousCommandA extends SequentialCommandGroup {
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

    /**
     * Creates a new ExampleCommand.
     *
     */
    public AutonomousCommandA(
            DriveSubsystem driveSubsystem,
            UltrasonicSubsystem ultrasonicSubsystem,
            ArmSubsystem armSubsystem,
            IntakeSubsystem intakeSubsystem,
            CenterRobotCommand centerRobotCommand
    ) {
        Timer timer = new Timer();

        addRequirements(driveSubsystem, ultrasonicSubsystem, armSubsystem, intakeSubsystem);

        addCommands(
                new ParallelCommandGroup(
                        new InstantCommand(
                                armSubsystem::stayUp,
                                armSubsystem
                        )
                ),

                centerRobotCommand,

                new RunTimedCommand(
                        0.3,
                        intakeSubsystem::backward,
                        intakeSubsystem
                ),

                new InstantCommand(
                        intakeSubsystem::stop,
                        intakeSubsystem
                ),

                // Go back for 1 second
                new RunTimedCommand(
                        1,
                        () -> driveSubsystem.drive(0.5, 0.2),
                        driveSubsystem
                ),

                // Rotate for 0.5 seconds
                new RunTimedCommand(
                        0.5,
                        () -> driveSubsystem.drive(0, 0.2),
                        driveSubsystem
                ),

                // Go back for 2 seconds
                new RunTimedCommand(
                        2,
                        () -> driveSubsystem.arcadeDrive(0.5, 0),
                        driveSubsystem
                )
        );
    }
}
