package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Moth.StickBruh;
import frc.robot.subsystems.DriveSubsystem;

public class DriveCommand extends CommandBase {
    private final DriveSubsystem driveSubsystem;
    private final XboxController controller;
    private final StickBruh filter = new StickBruh(0.05);
    private final Timer timer = new Timer();

    private final double deadzone = 0.05;
    private final double increaseEvery = 0.05;

    public DriveCommand(DriveSubsystem driveSubsystem, XboxController controller) {
        this.driveSubsystem = driveSubsystem;
        this.controller = controller;

        addRequirements(driveSubsystem);
    }

    @Override
    public void initialize() {
        timer.reset();
        timer.start();
    }

    @Override
    public void end(boolean interrupted) {
        timer.stop();

        super.end(interrupted);
    }

    @Override
    public void execute() {
        double throttle = controller.getLeftY();

        SmartDashboard.putNumber("Stickpos", throttle);

        if (timer.get() > increaseEvery && (throttle > deadzone || throttle < -deadzone)) {
            filter.increase(throttle);
            timer.reset();
        }

        if (throttle < deadzone && throttle > -deadzone) {
            filter.reset();
        }

        driveSubsystem.arcadeDrive(filter.calculate(), -controller.getRightX()*0.7);
    }
}
