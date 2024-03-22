// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants.LauncherConstants;
import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.AutoForward;
import frc.robot.commands.AutoTightShootBack;
import frc.robot.commands.AutoMidShootBack;
import frc.robot.commands.AutoOpenShootBack;
import frc.robot.commands.ClimbChain;
import frc.robot.commands.DriveManual;
import frc.robot.commands.LaunchNote;
import frc.robot.commands.PrepareLaunch;
import frc.robot.commands.resetClimber;
import frc.robot.commands.GroundIntake;
import frc.robot.subsystems.CANClimber;
import frc.robot.subsystems.CANDrivetrain;
import frc.robot.subsystems.CANLauncher;
import frc.robot.subsystems.CANGroundIntake;
import edu.wpi.first.wpilibj.smartdashboard.*;


/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems are defined here.
  public final CANDrivetrain m_drivetrain = new CANDrivetrain();
  public final CANLauncher m_launcher = new CANLauncher();
  public final CANClimber m_climber = new CANClimber();
  public final CANGroundIntake m_groundIntake = new CANGroundIntake();
  private final SendableChooser<String> m_autoChooser = new SendableChooser<>();


  /*The gamepad provided in the KOP shows up like an XBox controller if the mode switch is set to X mode using the
   * switch on the top.*/
  public final static CommandXboxController m_driverController =
      new CommandXboxController(OperatorConstants.kDriverControllerPort);
      
  public final CommandXboxController m_operatorController =
      new CommandXboxController(OperatorConstants.kOperatorControllerPort);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {

    // Configure the trigger bindings
    configureBindings();

    this.initializeAutoChooser();

  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be accessed via the
   * named factory methods in the Command* classes in edu.wpi.first.wpilibj2.command.button (shown
   * below) or via the Trigger constructor for arbitary conditions
   */
  private void configureBindings() {
    // Set the default command for the drivetrain to drive using the joysticks
    
    m_drivetrain.setDefaultCommand(new DriveManual(m_drivetrain));
                    
    /*Create an inline sequence to run when the operator presses and holds the A (green) button. Run the PrepareLaunch
     * command for 1 seconds and then run the LaunchNote command */
    m_operatorController
        .a()
        .whileTrue(
            new PrepareLaunch(m_launcher)
                .withTimeout(LauncherConstants.kLauncherDelay)
                .andThen(new LaunchNote(m_launcher))
                .handleInterrupt(() -> m_launcher.stop()));

    // Set up a binding to run the intake command while the operator is pressing and holding the
    // left Bumper & right Bumper
    m_operatorController.leftBumper().whileTrue(m_launcher.getIntakeCommand());
    m_operatorController.rightBumper().whileTrue(m_groundIntake.getGroundIntakeCommand());

    m_operatorController
        .y()
          .whileTrue(
            new ClimbChain(m_climber));

    m_operatorController
        .x()
          .whileTrue(
            new resetClimber(m_climber));
  }

  public void initializeAutoChooser() {
    m_autoChooser.setDefaultOption("Drive Forward", "AutoForward");
    m_autoChooser.addOption("Shoot and Drive Back", "AutoMidShootBack");
    m_autoChooser.addOption("Open Shoot and Back", "AutoOpenShootBack");
    m_autoChooser.addOption("Tight Shoot and Back", "AutoTightShootBack");
    SmartDashboard.putData(m_autoChooser);
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   */
  public Command getAutonomousCommand() {
    switch (m_autoChooser.getSelected())
    {
      case "AutoForward":
        return AutoForward.autoDriveForward(m_drivetrain);
      case "AutoMidShootBack":
        return AutoMidShootBack.shootForward(m_launcher, m_drivetrain);
      case "AutoOpenShootBack":
        return AutoOpenShootBack.shootRight(m_launcher, m_drivetrain);
      case "AutoTightShootBack":
        return AutoTightShootBack.shootLeft(m_launcher, m_drivetrain);
      default:
        System.out.println("\nError selecting Auto.\n");
        return null;
    }
  }
}
