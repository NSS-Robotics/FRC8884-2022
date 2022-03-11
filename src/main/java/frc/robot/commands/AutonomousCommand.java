// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.OldArmSubsystem;
import frc.robot.subsystems.DriveSubsystem;

/** An example command that uses an example subsystem. */
public class AutonomousCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final ArmSubsystem m_armSubsystem;
  private final DriveSubsystem m_driveSubsystem;
  protected Timer m_timer = new Timer();

  /**
   * Creates a new ExampleCommand.
   *
   */
  public AutonomousCommand(ArmSubsystem armSubsystem, DriveSubsystem driveSubsystem) {
    m_armSubsystem = armSubsystem;
    m_driveSubsystem = driveSubsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(armSubsystem, driveSubsystem);
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
    // Drive for 2 seconds
    if (m_timer.get() < 2.0) {
      m_driveSubsystem.arcadeDrive(0.5, 0.0);
    } else {
      m_driveSubsystem.stopMotor();
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return m_timer.get() > 2.0;
  }
}
