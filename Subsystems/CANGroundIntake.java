// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Constants.GroundIntakeConstants.*;
import frc.robot.commands.GroundIntake;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Subsystem;

public class CANGroundIntake extends SubsystemBase {
  WPI_TalonSRX m_groundIntake;
  
  /** Creates a new CANGroundIntake. */
  public CANGroundIntake() {
    m_groundIntake = new WPI_TalonSRX(kGroundIntakeID);
    m_groundIntake.setNeutralMode(NeutralMode.Brake);
    m_groundIntake.setInverted(false);
  }

  public Command getGroundIntakeCommand() {
    return this.startEnd(
      () -> {
        setGroundIntake(1);
      },
      () -> {
        stop();
      }
    );
  }

  public void setGroundIntake(double speed) {
    m_groundIntake.set(speed);
  }

  public void stop() {
    m_groundIntake.set(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
