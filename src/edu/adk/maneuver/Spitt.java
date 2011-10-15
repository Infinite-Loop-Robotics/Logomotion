/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.adk.maneuver;

import edu.adk.mechanism.Claw;

/**
 *
 * @author mtidd
 */
public class Spitt extends Maneuver {

    Claw claw;

    public Spitt(Claw claw, Maneuver pass, Maneuver fail, Maneuver timeout, double maxTime){
        super(pass,fail,timeout,maxTime);
        this.claw = claw;
    }

    protected void run() {
        claw.spitterOut();
		System.out.println("out");
    }

    public void stop() {
		claw.spitterRun(0);
    }

}