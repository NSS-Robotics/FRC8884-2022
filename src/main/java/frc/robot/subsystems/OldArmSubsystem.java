// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class OldArmSubsystem extends SubsystemBase {
  private final CANSparkMax m_motor = new CANSparkMax(3, CANSparkMaxLowLevel.MotorType.kBrushless);
  private final RelativeEncoder m_encoder = m_motor.getEncoder();

  public OldArmSubsystem() {
    m_encoder.setPositionConversionFactor(Math.PI * 4/15);
  }

  public void up() {
    m_motor.set(0.3);
  }

  public void down() {
    m_motor.set(-0.3);
  }

  public void setPos(double position) {
    System.out.println("Setting pos");
    m_encoder.setPosition(position);
  }

  public void updatePos() {
    SmartDashboard.putNumber("Position", m_motor.getEncoder().getPosition());
  }

  public void set(double speed) {
    m_motor.set(speed);
  }

  public void stop() {
    m_motor.stopMotor();
  }
}
