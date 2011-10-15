/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.adk.core;

import edu.adk.maneuver.Maneuver;
import edu.wpi.first.wpilibj.Timer;

/**
 *
 * @author mtidd
 */
public class Autonomous
{

	private Maneuver currentManeuver;
	private static final double AUTONOMOUS_TIMER_MAX = 14.0;
	Timer timer = new Timer();
	public boolean finished = false;

	public Autonomous(Maneuver rootManeuver)
	{
		this.currentManeuver = rootManeuver;
		initialize();
	}

	public void initialize()
	{
		timer.stop();
		timer.reset();
		timer.start();
	}

	public void run()
	{
		System.out.println("Next " +currentManeuver.nextManeuver);
		if (!currentManeuver.hasFinished() && timer.get() < AUTONOMOUS_TIMER_MAX)
		{
			currentManeuver.start();
			System.out.println(currentManeuver);
		} else if (currentManeuver.nextManeuver != null && timer.get() < AUTONOMOUS_TIMER_MAX)
		{
			//getting the next maneuver
			currentManeuver.reset();
			currentManeuver = currentManeuver.nextManeuver;
			System.out.println("2nd");
		} else
		{
			//autonomous finished
			finished = true;
			System.out.println("finished");
		}
		Timer.delay(0.005);
	}
}
