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

/** An example command that uses an example subsystem. */
public class AutonomousCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final ArmSubsystem m_armSubsystem;
  private final DriveSubsystem m_driveSubsystem;
  private final IntakeSubsystem m_intakeSubsystem;
  private final UltrasonicSubsystem m_ultrasonicSubsystem;

  protected Timer m_timer = new Timer();

  /**
   * Creates a new ExampleCommand.
   *
   */
  public AutonomousCommand(
          ArmSubsystem armSubsystem, DriveSubsystem driveSubsystem,
          IntakeSubsystem intakeSubsystem,
          UltrasonicSubsystem ultrasonicSubsystem
  ) {
    m_armSubsystem = armSubsystem;
    m_driveSubsystem = driveSubsystem;
    m_intakeSubsystem = intakeSubsystem;
    m_ultrasonicSubsystem = ultrasonicSubsystem;

    addRequirements(armSubsystem, driveSubsystem, intakeSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_timer.reset();
    m_timer.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (m_timer.get() < 2.0) {
      m_armSubsystem.up(0.3);
    }

    if (m_timer.get() > 2.0) {
      m_intakeSubsystem.backward();
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    this.m_intakeSubsystem.stop();
    this.m_armSubsystem.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return m_timer.get() > 2.3;
  }
}
