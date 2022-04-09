// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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
        addRequirements(driveSubsystem, ultrasonicSubsystem, armSubsystem, intakeSubsystem);

        addCommands(
                new SequentialCommandGroup(
                        new RunTimedCommand(
                                1,
                                armSubsystem::stayUp,
                                armSubsystem
                        ),

                        new RunTimedCommand(
                                0.3,
                                intakeSubsystem::shoot,
                                intakeSubsystem
                        ),

                        new InstantCommand(
                                intakeSubsystem::stop,
                                intakeSubsystem
                        ),
                        new RunTimedCommand(
                                4,
                                () -> driveSubsystem.drive(0.65, 0.2),
                                driveSubsystem
                        )
                )


//                 Go back for 4 seconds
        );
    }
}
