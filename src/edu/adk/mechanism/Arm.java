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
public class Arm implements Mechanism {

	//Victor elbow_rt = new Victor(6);
	Victor elbow_lt = new Victor(7);

	double elbowTolerance = 3;

	AnalogChannel elbow_pos = new AnalogChannel(2);


	/**
	 * Prints the scaled pos to the screen
	 */
	public void printPos() {
		System.out.println(" Elbow Pos: " + this.getPos());
	}

	/**
	 * Scale factor of 10.
	 * @return scaled value
	 */
	public int getPos() {
		return ((int) ((elbow_pos.getVoltage()) * 10));
	}

	/**
	 * No Scale
	 * @return unscaled value
	 */
	public double getRawPos() {
		return elbow_pos.getVoltage();
	}

	//-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

	/**
	 * Runs the elbow motors at the given speed.
	 * @param speed on the range -1 to 1
	 */
	public void elbowRun(double speed) {
		//elbow_rt.set(speed);
		elbow_lt.set(speed);
	}

	/**
	 * Runs the elbow motors in the upward direction.
	 */
	public void elbowUp() {
		//elbow_rt.set(1);
		elbow_lt.set(1);
	}

	/**
	 * Runs the elbow motors in the downward direction.
	 */
	public void elbowDwn() {
		//elbow_rt.set(-.5);
		elbow_lt.set(-.5);
	}


	/**
	 * This method attempts to move the arm into position.
	 * @param voltageGo the voltage reading we hope to achieve
	 * @return true if in position within tolerence else false
	 * @todo Implement this // PID? //Just do a range. Accurace negligable?
	 */
	public boolean moveTo(double voltageGo) {
		//@todo Implement this // PID? //Just do a range. Accurace negligable?

		if (Math.abs(elbow_pos.getVoltage() * 10 - voltageGo) < elbowTolerance) {
			this.elbowRun(0);
			return true;
		} else {
			if ((elbow_pos.getVoltage() * 10 - voltageGo) > 0) {
				this.elbowDwn();
			} else {
				this.elbowUp();
			}

			return false;
		}
	}
	
	/**
	 * This method returns whether the arm is at the specified scaled voltage.
	 * @param voltageGo where the wrist should be
	 * @return true if the wrist is within the tolerence else false
	 */
	public boolean at(double voltageGo) {

		if (Math.abs(elbow_pos.getVoltage() * 10 - voltageGo) < elbowTolerance) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Initialize timers and such
	 */
	public void initialize() {
		stop();
	}

	/**
	 * Stops all motors in this class
	 */
	public void stop() {
		//elbow_rt.set(0);
		elbow_lt.set(0);
	}

	// ** Depreciated
//	public boolean moveToTop(boolean high) {
//		//System.out.println("Arm moveToTop is called");
//		double pos = tpl.position.elbowTop;// 34;
//		double posHigh = tpl.position.elbowTopHigh;// 32;
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
//		//System.out.println("Arm moveToMiddle is called");
//
//		double pos = tpl.position.elbowMid;//24;
//		double posHigh = tpl.position.elbowMidHigh;//24;
//
//		if (high) {
//			return this.moveTo(posHigh);
//		}
//		return this.moveTo(pos);
//	}
//
//	public boolean moveToBottom(boolean high) {
//		//System.out.println("Arm moveToBottom is called");
//
//		double pos = tpl.position.elbowBot;//13;
//		double posHigh = tpl.position.elbowBotHigh;//13;
//
//		if (high) {
//			return this.moveTo(posHigh);
//		}
//		return this.moveTo(pos);
//	}

//	NOT IMPLEMENTED
//	public boolean moveToPickup() {
//		double pos = 0;
//
//		return this.moveTo(pos);
//	}
}
