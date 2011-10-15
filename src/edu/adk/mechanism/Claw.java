/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.adk.mechanism;

import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.Victor;

/**
 *
 * @author Techplex Engineer
 */
public class Claw implements Mechanism
{

	Victor vic_top = new Victor(8);
	Victor vic_bot = new Victor(9);
	double wristTolerance = 3;
	AnalogChannel wrist_pos = new AnalogChannel(3);
	Victor wrist = new Victor(10);

	//-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
	/**
	 * Prints the scaled pos to the screen
	 */
	public void printPos()
	{
		System.out.println(" Wrist Pos: " + this.getPos());
	}

	/**
	 * Scale factor of 10.
	 * @return scaled value
	 */
	public int getPos()
	{
		return ((int) ((wrist_pos.getVoltage()) * 10));
	}

	/**
	 * No Scale
	 * @return unscaled value
	 */
	public double getRawPos()
	{
		return wrist_pos.getVoltage();
	}

	//-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
	/**
	 * Runs the wrist motor at the given speed.
	 * @param speed on the range -1 to 1
	 */
	public void wristRun(double speed)
	{
		wrist.set(speed);
	}

	/**
	 * Runs the wrist motor in the upward direction.
	 */
	public void wristUp()
	{
		wrist.set(.375);
	}

	/**
	 * Runs the wrist motor in the downward direction.
	 */
	public void wristDwn()
	{
		wrist.set(-.2);//-.375
	}



// DUMB
//	/**
//	 * Runs the wrist motor in the upward direction, at the given speed
//	 * @param speed on the range -1 to 1
//	 */
//	public void wristUp(double speed)
//	{
//		wrist.set(speed);
//	}
//	/**
//	 * Runs the wrist motor in the upward direction, at the given speed
//	 * @param speed on the range -1 to 1
//	 */
//	public void wristDwn(double speed)
//	{
//		wrist.set(speed);
//	}
	//-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
//	DEPRECIATED
//	public void spitterTopRun(double value) {
//		vic_top.set(value);
//	}
//
//	public void spitterBotRun(double value) {
//		vic_bot.set(value);
//	}
	/**
	 * Sets the spitter motors running at the given speed
	 * @param speed on the range -1 to 1
	 */
	public void spitterRun(double speed)
	{
		vic_top.set(speed);
		vic_bot.set(speed);
	}

	/**
	 * Sets the spitter motors running, moving the tube towards the inner of the robot
	 * @param haveTube will not allow the tube to be ruined
	 */
	public void spitterIn(boolean haveTube)
	{
		if (!haveTube)
		{
			vic_top.set(-1);
			vic_bot.set(-1);
		}
	}

	public void spitterIn()
	{
		vic_top.set(-1);
		vic_bot.set(-1);

	}

	/**
	 * Sets the spitter motors running, moving the tube away from the inner of the robot
	 */
	public void spitterOut()
	{
		vic_top.set(1);
		vic_bot.set(1);
	}

	/**
	 * Sets the spitter motors running, rotating the tube up
	 */
	public void spitterRotateTubeUp()
	{
		vic_top.set(1);
		vic_bot.set(-1);
	}

	/**
	 * Sets the spitter motors running, rotating the tube down
	 */
	public void spitterRotateTubeDwn()
	{
		vic_top.set(-1);
		vic_bot.set(1);
	}

	/**
	 * This method attempts to move the claw into position.
	 * @param voltageGo the voltage reading we hope to achieve
	 * @return true if in position within tolerence else false
	 * @todo Implement this // PID? //Just do a range. Accurace negligable?
	 */
	public boolean moveTo(double voltageGo)
	{
		if (Math.abs(wrist_pos.getVoltage() * 10 - voltageGo) < wristTolerance)
		{
			this.wristRun(0);
			return true;
		} else
		{
			if ((wrist_pos.getVoltage() * 10 - voltageGo) > 0)
			{
				this.wristRun(.1875);
			} else
			{
				this.wristRun(-.1875);
			}
			return false;
		}
	}

	/**
	 * This method returns whether the claw is at the specified scaled voltage.
	 * @param voltageGo where the wrist should be
	 * @return true if the wrist is within the tolerence else false
	 */
	public boolean at(double voltageGo)
	{
		if (Math.abs(wrist_pos.getVoltage() * 10 - voltageGo) < wristTolerance)
		{
			return true;
		} else
		{
			return false;
		}
	}

	/**
	 * Initialize timers and such
	 */
	public void initialize()
	{
		stop();
	}

	/**
	 * Stops all motors in this class
	 */
	public void stop()
	{
		vic_top.set(0);
		vic_bot.set(0);
		wrist.set(0);
	}
// DEPRICATED
//	public boolean moveToTop(boolean high) {
//		//System.out.println("Arm moveToTop is called");
//		double pos = tpl.position.wristTop;
//		double posHigh = tpl.position.wristTopHigh;
//
//		if (high) {
//			boolean var = this.moveTo(posHigh);
//			//System.out.println("Result from arm.moveTo(posHigh): " + var);
//			return var;
//		}
//		return this.moveTo(pos);
//	}
//
//	public boolean moveToMiddle(boolean high) {
//				//System.out.println("Arm moveToMiddle is called");
//
//		double pos = tpl.position.wristMid;
//		double posHigh = tpl.position.wristMidHigh;
//
//		if (high) {
//			return this.moveTo(posHigh);
//		}
//		return this.moveTo(pos);
//	}
//
//	public boolean moveToBottom(boolean high) {
//				//System.out.println("Arm moveToBottom is called");
//
//		double pos = tpl.position.wristBot;
//		double posHigh = tpl.position.wristBotHigh;
//
//		if (high) {
//			return this.moveTo(posHigh);
//		}
//		return this.moveTo(pos);
//	}
//	Not Implemented
//	public boolean moveToPickup() {
//		double pos = 0;
//
//		return this.moveTo(pos);
//	}
}
