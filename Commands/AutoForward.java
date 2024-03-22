// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.subsystems.CANDrivetrain;

public final class AutoForward {
  /** Example static factory for an autonomous command. */
  public static Command autoDriveForward(CANDrivetrain drivetrain) {
    /**
     * RunCommand is a helper class that creates a command from a single method, in this case we
     * pass it the curvatureDrive method to drive straight back at half power. We modify that command
     * with the .withTimeout(1) decorator to timeout after 1 second, and use the .andThen decorator
     * to stop the drivetrain after the first command times out
     */
    return new RunCommand(() -> drivetrain.curvatureDrive(-1, 0, false), drivetrain)
      .withTimeout(1)
      .andThen(new RunCommand(() -> drivetrain.curvatureDrive(0, 0, false), drivetrain)
      .withTimeout(0)
      );
    }

  public AutoForward() {
    throw new UnsupportedOperationException("This is a utility class!");
  }
}
