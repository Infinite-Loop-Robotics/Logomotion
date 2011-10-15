/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.adk.maneuver;

import edu.adk.maneuver.*;
import edu.adk.mechanism.Drive;

/**
 *
 * @author mtidd
 */
public class DriveBackward extends Maneuver{
    Drive drive;
    
    public DriveBackward(Drive drive, Maneuver pass, Maneuver fail, Maneuver timeout, double maxTime){
        super(pass,fail,timeout,maxTime);
        this.drive = drive;
    }

    protected void run() {
        drive.goBackward();
    }

    public void stop() {
        drive.stop();
    }
}
