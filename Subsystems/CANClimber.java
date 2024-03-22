// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

//import static frc.robot.Constants.ClimberConstants.*;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class CANClimber extends SubsystemBase {
  WPI_VictorSPX m_climber;
  /** Creates a new Climber. */
  public CANClimber() {
    m_climber = new WPI_VictorSPX(7);
    m_climber.setInverted(true);
    m_climber.setNeutralMode(NeutralMode.Brake);
  }

  /**
   * This method is an example of the 'subsystem factory' style of command creation. A method inside
   * the subsytem is created to return an instance of a command. This works for commands that
   * operate on only that subsystem, a similar approach can be done in RobotContainer for commands
   * that need to span subsystems. The Subsystem class has helper methods, such as the startEnd
   * method used here, to create these commands.
   */
  
  // An accessor method to set the speed (technically the output percentage) of the launch wheel
  public void setClimber() {
    m_climber.set(0.5);
  }

  public void resetClimber() {
    m_climber.set(-0.5);
  }
  // A helper method to stop both wheels. You could skip having a method like this and call the
  // individual accessors with speed = 0 instead
  public void stop() {
    m_climber.set(0);
  }
}
