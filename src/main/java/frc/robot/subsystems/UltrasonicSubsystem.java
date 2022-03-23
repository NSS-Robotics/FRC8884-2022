package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class UltrasonicSubsystem extends SubsystemBase {
    AnalogInput ultrasonic = new AnalogInput(0);

    public double get() {
        double raw_value = ultrasonic.getValue();

        double voltage_scale_factor = 5/ RobotController.getVoltage5V();

        return raw_value * voltage_scale_factor * 0.125;
    }
}
