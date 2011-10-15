/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.adk.mechanism;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Victor;

/**
 *
 * @author Techplex Engineer
 */
public class MiniBot implements Mechanism
{
	SpeedController bot	= new Victor(5);
	boolean pinPulled = false;
	Servo pinPuller = new Servo(6);
	Victor aligner = new Victor(4);
	//Servo guide = new Servo(2);

	/**
	 * Calls the MBDS stop method
	 */
	public void initialize()
	{
		stop();
		pinPull(1);
	}
	public void pinPull()
	{
		pinPulled = true;
		pinPuller.set(0);
	}

	public void align(boolean align)
	{
		if(align)
			aligner.set(-.5);
		else
			aligner.set(0);
	}

//	public void guideDwn()
//	{
//		guide.set(0);
//	}
//	public void guideUp()
//	{
//		guide.set(1);
//	}
//	public void guideGo(double a)
//	{
//			guide.set(a);
//	}
//
//	public void guidePrint()
//	{
//		System.out.println(guide.get());
//	}

	public void pinPull(boolean pos)
	{
		if(!pos)
		{
			pinPulled = false;
			pinPuller.set(1);
		}
		else
		{
			pinPulled = true;
			pinPuller.set(0);
		}
	}

	public void pinPull(double pos)
	{
		//pinPulled = true;
		pinPuller.set(pos);
	}

	/**
	 * Deploy's the minibot, simply runs the motor for a soecified amount of time.
	 */
	public void deploy()
	{
		if(pinPuller.get() < .8)
			bot.set(1);
		else
			this.pinPull();
	}
	/**
	 * Stops all the MBDS's motors
	 */
	public void stop()
	{
		bot.set(0);
	}

}
