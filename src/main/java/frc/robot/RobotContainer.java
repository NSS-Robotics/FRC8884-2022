// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.AutonomousCommandA;
import frc.robot.commands.CenterRobotCommand;
import frc.robot.commands.DriveCommand;
import frc.robot.subsystems.*;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
    private final DriveSubsystem  m_robotDrive = new DriveSubsystem();
    private final ArmSubsystem m_robotArm = new ArmSubsystem();
    private final IntakeSubsystem m_intake = new IntakeSubsystem();
    private final UltrasonicSubsystem m_ultrasonicSubsystem = new UltrasonicSubsystem();
    private final ClimbSubsystem m_climbSubsystem = new ClimbSubsystem();

    private final CenterRobotCommand m_centerRobotCommand_auto = new CenterRobotCommand(m_ultrasonicSubsystem, m_robotDrive);
    private final AutonomousCommandA m_complexAuto = new AutonomousCommandA(m_robotDrive, m_ultrasonicSubsystem, m_robotArm, m_intake, m_centerRobotCommand_auto);

    private final XboxController controller = new XboxController(0);

    private final DriveCommand m_driveCommand = new DriveCommand(m_robotDrive, controller);
    private final CenterRobotCommand m_centerRobotCommand = new CenterRobotCommand(m_ultrasonicSubsystem, m_robotDrive);

    public RobotContainer() {
        configureAuto();
        configureButtonBindings();
    }

    private void configureAuto() {
//        m_chooser.setDefaultOption("Simple Auto", m_simpleAuto);
//        m_chooser.addOption("Complex Auto", m_complexAuto);
//        m_chooser.setDefaultOption("Auto A", m_complexAuto);

//        SmartDashboard.putData(m_chooser);
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
                                        controller.getLeftY(), -controller.getRightX() * 0.7),
                        m_robotDrive
                ));

        // Arm
        new JoystickButton(controller, XboxController.Button.kRightBumper.value).
                whenPressed(
                        m_robotArm::toggle,
                        m_robotArm
                );

        // Intake
        new JoystickButton(controller, XboxController.Button.kB.value).
                whenPressed(
                        m_intake::shoot,
                        m_intake
                ).whenReleased(m_intake::stop, m_intake);
        new JoystickButton(controller, XboxController.Button.kX.value).
                whenPressed(
                        m_intake::intake,
                        m_intake
                ).whenReleased(m_intake::stop, m_intake);

        // Climber
        new JoystickButton(controller, XboxController.Button.kY.value).
                whenPressed(
                        m_climbSubsystem::raise,
                        m_climbSubsystem
                );

        new JoystickButton(controller, XboxController.Button.kBack.value).
                whenPressed(
                        m_climbSubsystem::down,
                        m_climbSubsystem
                ).whenReleased(
                        m_climbSubsystem::stop,
                        m_climbSubsystem
                );
        new JoystickButton(controller, XboxController.Button.kStart.value).
                whenPressed(
                        m_climbSubsystem::up,
                        m_climbSubsystem
                ).whenReleased(
                        m_climbSubsystem::stop,
                        m_climbSubsystem
                );

//          Ultrasonic
//        new JoystickButton(controller, XboxController.Button.kLeftBumper.value).
//                whenPressed(
//                        new SequentialCommandGroup(
//                                m_centerRobotCommand,
//                                new InstantCommand(
//                                        () -> controller.setRumble(GenericHID.RumbleType.kLeftRumble, 0.8)
//                                )
//                        )
//                ).whenReleased(m_centerRobotCommand::cancel);
//        new JoystickButton(controller, XboxController.Button.kLeftBumper.value).
//                whenPressed(
//                        new RunCommand(
//                                () -> {
//                                    double left = m_ultrasonicSubsystem.getLeft();
//                                    double right = m_ultrasonicSubsystem.getRight();
//                                    double delta = Math.abs(left - right);
//
//                                    SmartDashboard.putNumber("Ultrasonic Left", left);
//                                    SmartDashboard.putNumber("Ultrasonic Right", right);
//                                    SmartDashboard.putNumber("Ultrasonic Delta", delta);
//                                }
//                        )
//                );
    }

    public Command getAutonomousCommand() {
        return m_complexAuto;
    }

}