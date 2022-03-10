// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.AutonomousCommand;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.OldArmSubsystem;
import frc.robot.subsystems.DriveSubsystem;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  private final DriveSubsystem m_robotDrive = new DriveSubsystem();
  private final ArmSubsystem m_robotArm = new ArmSubsystem();
  private final OldArmSubsystem m_oldRobotArm = new OldArmSubsystem();

//  private final AutonomousCommand m_autoCommand = new AutonomousCommand(m_robotArm, m_robotDrive);

  private final XboxController controller = new XboxController(0);

  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();

    m_robotDrive.setDefaultCommand(
            new RunCommand(
                    () ->
                            m_robotDrive.arcadeDrive(
                                    controller.getLeftY(), controller.getRightX()),
                    m_robotDrive));
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    // Drive
    new JoystickButton(controller, XboxController.Button.kRightBumper.value)
            .whenPressed(
                    () -> m_robotDrive.setMaxOutput(0.9),
                    m_robotDrive)
            .whenReleased(() -> m_robotDrive.setMaxOutput(0.1), m_robotDrive);

//    new JoystickButton(controller, XboxController.Button.kA.value).
//            whenPressed(
//                    m_robotArm::updatePos,
//              m_robotArm
//            );

    new JoystickButton(controller, XboxController.Button.kY.value).
            whenPressed(
                    m_robotArm::up,
                    m_robotArm
            );

    new JoystickButton(controller, XboxController.Button.kA.value).
            whenPressed(
                    m_robotArm::down,
                    m_robotArm
            );

//        new JoystickButton(controller, XboxController.Button.kA.value).
//            whenPressed(
//                    () -> ,
//                    m_robotArm
//            );
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
//  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
//    return m_autoCommand;
//  }
}
