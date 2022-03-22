package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class UltrasonicSubsystem extends SubsystemBase {
    Ultrasonic ultrasonic = new Ultrasonic(1, 2);

    public double get() {
        return ultrasonic.getRangeMM();
    }
}
