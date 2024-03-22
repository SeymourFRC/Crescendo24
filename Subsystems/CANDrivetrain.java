// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import static frc.robot.Constants.DrivetrainConstants.*;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.NeutralMode;

/* This class declares the subsystem for the robot drivetrain if controllers are connected via CAN. Make sure to go to
 * RobotContainer and uncomment the line declaring this subsystem and comment the line for PWMDrivetrain.
 *
 * The subsystem contains the objects for the hardware contained in the mechanism and handles low level logic
 * for control. Subsystems are a mechanism that, when used in conjuction with command "Requirements", ensure
 * that hardware is only being used by 1 command at a time.
 */
public class CANDrivetrain extends SubsystemBase {
  /*Class member variables. These variables represent things the class needs to keep track of and use between
  different method calls. */
  DifferentialDrive m_drivetrain;

  /*Constructor. This method is called when an instance of the class is created. This should generally be used to set up
   * member variables and perform any configuration or set up necessary on hardware.
   */
  public CANDrivetrain() {
    //giving motor controllers IDs and connecting to motors
    WPI_TalonSRX leftFront = new WPI_TalonSRX(kLeftFrontID);
    WPI_TalonSRX leftRear = new WPI_TalonSRX(kLeftRearID);
    WPI_TalonSRX rightFront = new WPI_TalonSRX(kRightFrontID);
    WPI_TalonSRX rightRear = new WPI_TalonSRX(kRightRearID);

    /*Sets current limits for the drivetrain motors. This helps reduce the likelihood of wheel spin, reduces motor heating
     *at stall (Drivetrain pushing against something) and helps maintain battery voltage under heavy demand */


    // Set the rear motors to follow the front motors.
    leftRear.follow(leftFront);
    rightRear.follow(rightFront);

    // Invert the left side so both side drive forward with positive motor outputs
    rightRear.setInverted(true);
    rightFront.setInverted(true);
    
    //delaying inputs for motors
    rightFront.configOpenloopRamp(0.125);
    leftFront.configOpenloopRamp(.125);

    // Put the front motors into the differential drive object. This will control all 4 motors with
    // the rears set to follow the fronts
    m_drivetrain = new DifferentialDrive(leftFront, rightFront);

    //Brake Mode for Talons
    leftFront.setNeutralMode(NeutralMode.Brake);
    leftRear.setNeutralMode(NeutralMode.Brake);
    rightFront.setNeutralMode(NeutralMode.Brake);
    rightRear.setNeutralMode(NeutralMode.Brake);
  }  

  /*ALternate drivetrain using curvature drive*/
  public void curvatureDrive(double speed, double rotation, boolean quickturn) {
    m_drivetrain.curvatureDrive(speed, rotation, quickturn);
  }

  @Override
  public void periodic() {
    /*This method will be called once per scheduler run. It can be used for running tasks we know we want to update each
     * loop such as processing sensor data. Our drivetrain is simple so we don't have anything to put here */
  }
}
