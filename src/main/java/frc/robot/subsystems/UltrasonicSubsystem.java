package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class UltrasonicSubsystem extends SubsystemBase {
    AnalogInput leftSensor = new AnalogInput(0);
    AnalogInput rightSensor = new AnalogInput(1);

    // centimeters
    public double getLeft() {
        double raw_value = leftSensor.getValue();

        double voltage_scale_factor = 5/ RobotController.getVoltage5V();

        return raw_value * voltage_scale_factor * 0.125;
    }

    // centimeters
    public double getRight() {
        double raw_value = rightSensor.getValue();

        double voltage_scale_factor = 5/ RobotController.getVoltage5V();

        return raw_value * voltage_scale_factor * 0.125;
    }
}
