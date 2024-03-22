// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.CANClimber;

/*This is an example of creating a command as a class. The base Command class provides a set of methods that your command
 * will override.
 */
public class ClimbChain extends Command {

  CANClimber m_climber;

  /** Creates a new ClimbChain. */
  public ClimbChain(CANClimber climber) {
    // save the climber system internally
    m_climber = climber;

    // indicate that this command requires the climber system
    addRequirements(m_climber);
  }

  // The initialize method is called when the command is initially scheduled.
  @Override
  public void initialize() {
    // Start Climber
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_climber.setClimber();
    // There is nothing we need this command to do on each iteration. You could remove this method
    // and the default blank method
    // of the base class will run.
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    // Always return false so the command never ends on it's own. In this project we use the
    // scheduler to end the command when the button is released.
    return false;
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    // Stop the wheels when the command ends.
    m_climber.stop();
  }
}
