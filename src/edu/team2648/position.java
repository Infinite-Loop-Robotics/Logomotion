package edu.team2648;

/**
 * This class contains all the values 
 * @author Techplex Engineer
 */
public class position
{

	public static double wristTolerance = 3;
	public static double wristTop = 26;
	public static double wristTopHigh = 33;
	public static double wristMid = 35;
	public static double wristMidHigh = 42;
	public static double wristBot = 35;
	public static double wristBotHigh = 39;
	public static double elbowTolerance = 3;
	public static double elbowTop = 13;
	public static double elbowTopHigh = 17;
	public static double elbowMid = 22;
	public static double elbowMidHigh = 27;
	public static double elbowBot = 30;
	public static double elbowBotHigh = 32;

	//Theese are for the double
//	 public static double wristTolerance = 3;
//    public static double wristTop = 3;
//    public static double wristTopHigh = 9;
//    public static double wristMid = 20;
//    public static double wristMidHigh = 17;
//    public static double wristBot = 29;
//    public static double wristBotHigh = 23;
//    public static double elbowTolerance = 3;
//    public static double elbowTop = 34;
//    public static double elbowTopHigh = 32;
//    public static double elbowMid = 24;
//    public static double elbowMidHigh = 24;
//    public static double elbowBot = 13;
//    public static double elbowBotHigh = 13;
	/**
	 * What is this?
	 * @param pos  top, mid, or bottom
	 * @param high  on one of the higher center pegs?
	 * @return value of where the arm needs to move to get into position, -1 as an error
	 */
	/**
	 *
	 * @param up The up switch on the DS
	 * @param dwn The dwn switch on the DS
	 * @param high The mod sqitch on the DS
	 * @return Where the arm need to go.
	 */
	public static double armGo(boolean up, boolean dwn, boolean high)
	{
		String pos = "";
		if (up == dwn)
			//Move arm to mid
			pos = "mid";
		else if (up && !dwn)
			//Move to pos top
			pos = "top";
		else if (!up && dwn)
			//move to pos bot
			pos = "bot";


		if (high)
			if (pos.equals("top"))
				return elbowTopHigh;
			else if (pos.equals("mid"))
				return elbowMidHigh;
			else if (pos.equals("bot"))
				return elbowBotHigh;
			else
				return -1;
		else if (pos.equals("top"))
			return elbowTop;
		else if (pos.equals("mid"))
			return elbowMid;
		else if (pos.equals("bot"))
			return elbowBot;
		else
			return -1;

	}

	public static double clawGo(boolean up, boolean dwn, boolean high)
	{
		String pos = "";
		if (up == dwn)
			//Move arm to mid
			pos = "mid";
		else if (up && !dwn)
			//Move to pos top
			pos = "top";
		else if (!up && dwn)
			//move to pos bot
			pos = "bot";
		if (high)
		{
			if (pos.equals("top"))
				return wristTopHigh;
			else if (pos.equals("mid"))
				return wristMidHigh;
			else if (pos.equals("bot"))
				return wristBotHigh;
			else
				return -1;
		} else
		{
			if (pos.equals("top"))
				return wristTop;
			else if (pos.equals("mid"))
				return wristMid;
			else if (pos.equals("bot"))
				return wristBot;
			else
				return -1;
		}

	}

//	public double getElbowBot()
//	{
//		return elbowBot;
//	}
//
//	public double getElbowBotHigh()
//	{
//		return elbowBotHigh;
//	}
//
//	public double getElbowMid()
//	{
//		return elbowMid;
//	}
//
//	public double getElbowMidHigh()
//	{
//		return elbowMidHigh;
//	}
//
//	public double getElbowTolerance()
//	{
//		return elbowTolerance;
//	}
//
//	public double getElbowTop()
//	{
//		return elbowTop;
//	}
//
//	public double getElbowTopHigh()
//	{
//		return elbowTopHigh;
//	}
//
//	public double getWristBot()
//	{
//		return wristBot;
//	}
//
//	public double getWristMid()
//	{
//		return wristMid;
//	}
//
//	public double getWristMidHigh()
//	{
//		return wristMidHigh;
//	}
//
//	public double getWristTolerance()
//	{
//		return wristTolerance;
//	}
//
//	public double getWristTop()
//	{
//		return wristTop;
//	}
//
//	public double getWristTopHigh()
//	{
//		return wristTopHigh;
//	}
//
//	public double getWristbotHigh()
//	{
//		return wristBotHigh;
//	}
}
