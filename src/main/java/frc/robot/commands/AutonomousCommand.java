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

import java.sql.Time;

/**
 * An example command that uses an example subsystem.
 */
public class AutonomousCommand extends SequentialCommandGroup {
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

    /**
     * Creates a new ExampleCommand.
     *
     */
    public AutonomousCommand(
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

                new FunctionalCommand(
                        () -> {
                            timer.reset();
                            timer.start();

                            driveSubsystem.drive(0, 0);
                        },
                        () -> driveSubsystem.drive(-0.5, 0),
                        interrupt -> {
                            timer.reset();
                            driveSubsystem.drive(0, 0);
                        },
                        () -> ultrasonicSubsystem.getLeft() <= 30 || ultrasonicSubsystem.getRight() <= 30 || timer.get() >= 6,
                        driveSubsystem,
                        ultrasonicSubsystem
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

                new RunTimedCommand(
                        5,
                        () -> driveSubsystem.arcadeDrive(0.5, 0),
                        driveSubsystem
                )
        );
    }
}
