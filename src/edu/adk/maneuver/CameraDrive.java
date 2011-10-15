/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.adk.maneuver;

import edu.adk.core.Target;
import edu.adk.core.Tracker;
import edu.adk.mechanism.Drive;
import edu.wpi.first.wpilibj.camera.AxisCamera;
import edu.wpi.first.wpilibj.camera.AxisCameraException;
import edu.wpi.first.wpilibj.image.NIVisionException;

/**
 *
 * @author mtidd
 */
public class CameraDrive extends Maneuver{
    
    Drive drive;
    Tracker tracker = new Tracker();

    public CameraDrive(Drive drive, Maneuver pass,Maneuver fail,Maneuver timeout,double maxTime){
        super(pass,fail,timeout,maxTime);
        this.drive = drive;
    }
    
    protected void run() {
        
        if(tracker == null)
            return;
        
        AxisCamera.getInstance().freshImage();
        try{
            Target target = tracker.getTarget();
            if(target == null)
                return;
           
            //calculate the speeds from the camera
            double yspeed = -1-target.getYPosition();
            double xspeed = -target.getXPosition();
            if(!inYThreshold(target))
                drive.run(-.9*yspeed, xspeed);
            else if(!inXThreshold(target))
                drive.run(0, xspeed);
            else if(target.getSize() < .02){
                //double check the value
                AxisCamera.getInstance().freshImage();
                target = tracker.getTarget();
                
                if(target == null)
                    return;
                
                if(!inYThreshold(target))
                    return;
                // at the target, pass the maneuver
                System.out.println("cap");
                pass();
            }
        
        
        } catch (AxisCameraException e) {
        } catch (NIVisionException e) {
        }
    }
    
    /**
     * Is the image at the top most part of the window
     * 
     * @param target
     * @return atTargetY
     */
    public boolean inYThreshold(Target target){
        if(target.getYPosition() < -.9)
            return true;
        return false;
    }
    
    /**
     * Is the image at center of the window
     * 
     * @param target
     * @return atTargetX
     */
    public boolean inXThreshold(Target target){
        if(target.getXPosition() < .08 &&
                target.getXPosition() > -.08)
            return true;
        return false;
    }


    public void stop() {
        drive.stop();
    }

}
