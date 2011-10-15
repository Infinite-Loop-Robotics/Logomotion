/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.team2648;

/**
 *
 * @author student
 */
import edu.adk.mechanism.Arm;
import edu.adk.mechanism.Claw;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;

public class PositionEnum
{

	public static final PositionEnum TOP = new PositionEnum(3, 34);
	public static final PositionEnum MID = new PositionEnum(20, 24);
	public static final PositionEnum BOT = new PositionEnum(29, 13);
	private final double wristPosition;
	private final double elbowPosition;

	private PositionEnum(double wristPosition, double elbowPosition)
	{
		this.wristPosition = wristPosition;
		this.elbowPosition = elbowPosition;
	}

	/**
	 * Move the arm to the position encapsulated by this instance
	 */
	public void move(Joystick joystick, Arm arm, Claw claw)
	{
		//perhaps replace 11 with an enum, or at least the constant from MyRobot
		if (joystick.getRawButton(11))
		{
			while (joystick.getRawButton(11) && !arm.at(elbowPosition))
			{
				arm.moveTo(elbowPosition);
				Timer.delay(.05);
			}
			while (joystick.getRawButton(11) && !claw.at(wristPosition))
			{
				claw.moveTo(wristPosition);
				Timer.delay(.05);
			}
			arm.elbowRun(0);
			claw.wristRun(0);
			while (joystick.getRawButton(11))
			{
				System.out.println("Arm and Claw are in the correct Position");
				Timer.delay(.05);
			}
		}
	}

	/**
	 * Figure out the appropriate position for the given driver's input

	 */
	public static PositionEnum valueOf(DriverStation input)
	{
		if (input.getDigitalIn(3) == input.getDigitalIn(5))
		{
			return MID;
		} else if (input.getDigitalIn(5) && !input.getDigitalIn(3))
		{
			return TOP;
		} else if (!input.getDigitalIn(5) && input.getDigitalIn(3))
		{
			return BOT;
		} else
		{
			throw new RuntimeException("Unable to determine position for " + input.getDigitalIn(5) + " " + input.getDigitalIn(3));
		}
	}
}
