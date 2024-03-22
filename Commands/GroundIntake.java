// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.CANGroundIntake;
import static frc.robot.Constants.GroundIntakeConstants;

public class GroundIntake extends Command {
  CANGroundIntake m_groundIntake;
  /** Creates a new GroundIntake. */
  public GroundIntake(CANGroundIntake groundIntake) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_groundIntake = groundIntake;
    addRequirements(m_groundIntake);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_groundIntake.setGroundIntake(1);

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_groundIntake.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
