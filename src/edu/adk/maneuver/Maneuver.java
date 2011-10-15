/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.adk.maneuver;

import edu.wpi.first.wpilibj.Timer;

/**
 *
 * @author Developer
 */
public abstract class Maneuver {
    public Maneuver passManeuver = null;
    public Maneuver failManeuver = null;
    public Maneuver timeoutManeuver = null;
    public Maneuver nextManeuver = null;
    public Timer timer = new Timer();
    boolean finished = false;
    boolean started = false;
    double maxTime;
    
    public Maneuver(double maxTime){
        this.maxTime = maxTime;
    }
    
    public Maneuver(Maneuver pass, Maneuver fail, Maneuver timeout, double maxTime){
        this.maxTime = maxTime;
        this.passManeuver = pass;
        this.failManeuver = fail;
        this.timeoutManeuver = timeout;
    }
    
    public void reset(){
        stop();
        timer = new Timer();
        started = false;
    }
    
    public void start(){
        //System.out.println(this.toString() + ":" + timer.get());
        if(timer.get() > maxTime)
            timeout();
        if(!started){
            timer.reset();
            timer.start();
            started = true;
        }
        run();
    }
    
    protected abstract void run();
    
    public abstract void stop();
    
    public void pass(){
        stop();
        finished = true;
        nextManeuver = passManeuver;
    }
    
    public void fail(){
        stop();
        finished = true;
        nextManeuver = failManeuver;
    }
    
    public void timeout(){
        stop();
        finished = true;
        nextManeuver = timeoutManeuver;
    }
    public boolean hasFinished(){
       return finished; 
    }
}
