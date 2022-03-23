// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.*;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.UltrasonicSubsystem;

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
            IntakeSubsystem intakeSubsystem
    ) {
        System.out.println("In auto");

        addRequirements(driveSubsystem, ultrasonicSubsystem, armSubsystem, intakeSubsystem);

        addCommands(
//                new ParallelCommandGroup(
//                        new InstantCommand(
//                                () -> SmartDashboard.putNumber("ultrasonic", ultrasonicSubsystem.get()),
//                                ultrasonicSubsystem
//                        )
//                ),

//                new FunctionalCommand(
//                        () -> driveSubsystem.drive(0, 0),
//                        () -> {
//                            driveSubsystem.drive(0, 0);
//                            SmartDashboard.putNumber("ultrasonic", ultrasonicSubsystem.get());
//                        },
//                        interrupt -> driveSubsystem.drive(0, 0),
//                        () -> false,
//                        driveSubsystem,
//                        ultrasonicSubsystem
//                )

                new FunctionalCommand(
                        () -> driveSubsystem.drive(0, 0),
                        () -> driveSubsystem.drive(-0.5, 0),
                        interrupt -> driveSubsystem.drive(0, 0),
                        () -> ultrasonicSubsystem.get() <= 30,
                        driveSubsystem,
                        ultrasonicSubsystem
                ),

                new RunTimedCommand(
                        0.5,
                        () -> armSubsystem.up(0.3)
                ),

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
