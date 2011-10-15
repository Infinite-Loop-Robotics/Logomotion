/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.team2648;

/**
 *
 * @author student
 */
public class tolerence {
	public static final tolerence wrist= new tolerence(3, 3);
	//public static final tolerence elbow = new tolerence(3);


	private final double wristTol;
	private final double elbowTol;

	private tolerence(double wristPosition, double elbowPosition)
	{
		this.wristTol = wristPosition;
		this.elbowTol = elbowPosition;
	}

}
