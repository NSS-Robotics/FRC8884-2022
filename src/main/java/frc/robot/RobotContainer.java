// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.ConditionalCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.DriveSubsystem;

import java.util.function.BooleanSupplier;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
    private final DriveSubsystem m_robotDrive = new DriveSubsystem();
    private final ArmSubsystem m_robotArm = new ArmSubsystem();
    private final IntakeSubsystem m_intake = new IntakeSubsystem();

    private final XboxController controller = new XboxController(0);

    public RobotContainer() {
        // Configure the button bindings
        configureButtonBindings();
    }

    /**
     * Use this method to define your button->command mappings. Buttons can be created by
     * instantiating a {@link GenericHID} or one of its subclasses ({@link
     * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
     * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
     */
    private void configureButtonBindings() {
        // Drive
        m_robotDrive.setDefaultCommand(
                new RunCommand(
                        () ->
                                m_robotDrive.arcadeDrive(
                                        controller.getLeftY(), -controller.getRightX()*0.7),
                        m_robotDrive));

        // Arm
        new Trigger(() -> controller.getLeftTriggerAxis() > 0).whenActive(
                m_robotArm::up,
                m_intake
        ).whenInactive(m_robotArm::stop);

        new Trigger(() -> controller.getRightTriggerAxis() > 0).whenActive(
                m_robotArm::down,
                m_intake
        ).whenInactive(m_robotArm::stop);

        // Intake
        new JoystickButton(controller, XboxController.Button.kX.value).
                whenPressed(
                        m_intake::backward,
                        m_intake
                ).whenReleased(m_intake::stop, m_intake);
        new JoystickButton(controller, XboxController.Button.kB.value).
                whenPressed(
                        m_intake::forward,
                        m_intake
                ).whenReleased(m_intake::stop, m_intake);
    }

}