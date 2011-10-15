package edu.adk.mechanism;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Joystick;

/**
 *
 * @author Techplex Engineer
 */
public class Drive implements Mechanism{
    private static final double SPEED_STOP            =  0.0;
    private static final double SPEED_FWD_MAX         = -1; //change
    private static final double SPEED_REV_MAX         = 1; //change

	Jaguar  left_front	= new Jaguar(1);
	//Jaguar	left_rear	= new Jaguar(2);
	Jaguar	right_front = new Jaguar(3);
	//Jaguar	right_rear	= new Jaguar(4);

	RobotDrive drivetrain = new RobotDrive(left_front,  right_front);


	//Encoder left_encoder = new Encoder(8, 7);
	//Encoder right_encoder = new Encoder(5, 6);

    public void initialize(){
        stop();

		drivetrain.setInvertedMotor(RobotDrive.MotorType.kFrontLeft, true);
		drivetrain.setInvertedMotor(RobotDrive.MotorType.kRearLeft, true);

//		left_encoder.reset();
//		right_encoder.reset();

    }
    /**
	 * Equivalent to arcade drive
	 * @param R value from -1 to 1
	 * @param L value from -1 to 1
	 */
    public void run(double x,double y){
        drivetrain.arcadeDrive(y,x,false);
    }

//	public void runScaled(double y,double x){
//        drivetrain.arcadeDrive(y*1.5,x*1.5,false);
//    }
	public void run(Joystick j1, double scale){
			this.run((j1.getX()/scale),(j1.getY()/scale));
	}
	

	/**
	 * Equivalent to arcade drive
	 * @param j1 joystick in question
	 */
	public void run(Joystick j1){
        drivetrain.arcadeDrive(j1,false);
	}
	/**
	 * uses the throttle to change the speed.
	 * @param j1 the joystick to get the values from
	 */
	public void runThrottled(Joystick j1){
		double scale = ((j1.getThrottle()-1) /-2)+.25;

		//System.out.println(scale);
        drivetrain.arcadeDrive(j1.getY()*scale,j1.getX()*scale,false);
	}
	/**
	 * Equivalent to arcade drive
	 * @param j1 joystick in question
	 * @param halfSpeed if true values fro joystic are cut in half
	 */
	public void run(Joystick j1, boolean halfSpeed){
		if(halfSpeed)
			this.run((j1.getX()/2),(j1.getY()/2));
		else
			this.run(j1);
	}

	/**
	 * This acts a programatical transmission, allowing the user to divide their speed by the gear variable
	 * @param hid joystick to use
	 * @param gear integer to divide by
	 */
	public void run(Joystick hid, int gear){
        this.run((hid.getX()/gear),(hid.getY()/gear));
	}



    /**
	 * Stops the drivetrain
	 */
    public void stop(){
        drivetrain.arcadeDrive(SPEED_STOP,SPEED_STOP,false);
    }

	/**
	 * Full speed ahead
	 */
    public void goForward() {
        drivetrain.arcadeDrive(SPEED_FWD_MAX, SPEED_STOP, false);
    }
	public void goForwardSlow() {
        drivetrain.arcadeDrive(SPEED_FWD_MAX/4, SPEED_STOP, false);
    }

	/**
	 * Full speed reverse
	 */
    public void goBackward() {
        drivetrain.arcadeDrive(SPEED_REV_MAX, SPEED_STOP, false);
    }

	/**
	 * Hard right turn
	 */
    public void goLeft() {
        drivetrain.arcadeDrive(SPEED_STOP, SPEED_FWD_MAX, false);
    }

	/**
	 * Hard left turn
	 */
    public void goRight() {
        drivetrain.arcadeDrive(SPEED_STOP, SPEED_REV_MAX, false);
    }
	/**
	 * Easy right turn
	 */
    public void goLeftSlow() {
        drivetrain.arcadeDrive(SPEED_STOP, SPEED_FWD_MAX/2, false);
    }
	public void goLeft(double coe) {
        drivetrain.arcadeDrive(SPEED_STOP, SPEED_FWD_MAX*coe, false);
    }

	public void goRight(double coe) {
        drivetrain.arcadeDrive(SPEED_STOP,SPEED_REV_MAX*coe,  false);
    }

	/**
	 * Easy left turn
	 */
    public void goRightSlow() {
        drivetrain.arcadeDrive(SPEED_STOP, SPEED_REV_MAX/2, false);
    }


	/**
	 * ??
	 */
    public void goRightBackward() {
        drivetrain.arcadeDrive(SPEED_REV_MAX, SPEED_FWD_MAX, false);
    }
	/**
	 * ??
	 */
    public void goLeftBackward() {
        drivetrain.arcadeDrive(SPEED_REV_MAX, SPEED_REV_MAX, false);
    }
	/**
	 * ??
	 */
    public void goForwardLeft() {
        drivetrain.arcadeDrive(SPEED_FWD_MAX, SPEED_FWD_MAX, false);
    }
	/**
	 * ??
	 */
    public void goForwardRight() {
        drivetrain.arcadeDrive(SPEED_FWD_MAX, SPEED_REV_MAX, false);
    }

	public void goBackwardSlow()
	{
		drivetrain.arcadeDrive(SPEED_REV_MAX/3, SPEED_STOP, false);
	}

	public void setLeftRightMotorSpeeds(double L, double R)
	{
		//drivetrain.setLeftRightMotorSpeeds(L, R);
		drivetrain.setLeftRightMotorOutputs(L, R);
	}




}
