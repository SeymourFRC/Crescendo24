// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import static frc.robot.Constants.LauncherConstants.kLaunchFeederSpeed;
import static frc.robot.Constants.LauncherConstants.kLauncherSpeed;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.subsystems.CANDrivetrain;
import frc.robot.subsystems.CANLauncher;

public final class AutoTightShootBack {
  public static Command shootLeft (CANLauncher launcher, CANDrivetrain drivetrain) {
    return new RunCommand(() -> drivetrain.curvatureDrive(-0.5, 0, false))
        .withTimeout(0.15)
        .andThen(new RunCommand(() -> drivetrain.curvatureDrive(0, 0, false))
        .withTimeout(0)
        .andThen(new RunCommand(() -> launcher.setLaunchWheel(kLauncherSpeed), launcher)
        .withTimeout(3)
        .andThen(new RunCommand(() -> launcher.setFeedWheel(kLaunchFeederSpeed), launcher)
        .withTimeout(2)
        .andThen(new RunCommand(() -> launcher.setLaunchWheel(0), launcher)
        .withTimeout(0)
        .andThen(new RunCommand(() -> drivetrain.curvatureDrive(-0.5, 0, false), drivetrain)
        .withTimeout(1.5)
        .andThen(new RunCommand(() -> drivetrain.curvatureDrive(0, 0.25, false), drivetrain)
        .withTimeout(1)
        .andThen(new RunCommand(() -> drivetrain.curvatureDrive(-0.5, 0, false), drivetrain)
        .withTimeout(1.5)
        .andThen(new RunCommand(() -> drivetrain.curvatureDrive(0, 0, false), drivetrain)
        ))))))
        ));
  }

  private AutoTightShootBack() {
    throw new UnsupportedOperationException("This is a utility class!");
  }
}
