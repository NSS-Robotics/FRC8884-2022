// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.UltrasonicSubsystem;

/**
 * An example command that uses an example subsystem.
 */
public class AutonomousCommandB extends SequentialCommandGroup {
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

    /**
     * Creates a new ExampleCommand.
     *
     */
    public AutonomousCommandB(
            DriveSubsystem driveSubsystem,
            UltrasonicSubsystem ultrasonicSubystem,
            ArmSubsystem armSubsystem,
            IntakeSubsystem intakeSubsystem,
            CenterRobotCommand centerRobotCommand
    ) {
        Timer timer = new Timer();

        addRequirements(driveSubsystem, ultrasonicSubystem, armSubsystem, intakeSubsystem);

        addCommands(
                // Put stuff here
        );
    }
}
