package frc.robot.Moth;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;
import org.opencv.core.Mat;

/**
 * every 0.05 seconds the speed increase by 0.05*stick pos
 */

public class StickBruh {
    private double increaseAmount;

    private double speed = 0;
    private double baseSpeed = 0;

    public StickBruh(double increaseAmount) {
        this.increaseAmount = increaseAmount;
    }

    public void increase(double stickPos) {
        if (stickPos < 0) {
            increaseAmount = -Math.abs(increaseAmount);
        } else {
            increaseAmount = Math.abs(increaseAmount);
        }
        baseSpeed += increaseAmount;

        SmartDashboard.putNumber("increaseAmount", increaseAmount);

        if(baseSpeed >= Constants.maxSpeed){
            baseSpeed = Constants.maxSpeed;
        }

        speed = (baseSpeed * Math.abs(stickPos)) + (stickPos < 0 ? -0.1 : 0.1);
    }

    public void reset() {
        baseSpeed = 0;
        speed = 0;
    }

    public double calculate() {
        return speed;
    }
}

/*

    make 0.1 increase/decrease only if stick pos is bigger than the deadzones
    if it is in the deadzones, decrease/increase speed by 0.05 until it reaches 0
 */