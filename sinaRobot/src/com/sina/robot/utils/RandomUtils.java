package com.sina.robot.utils;

import java.util.Random;

public class RandomUtils {

	public static double getRandom(int min,int max)
	{ 
	    Random random = new Random();

	   return random.nextDouble()*(max-min) + min;

	}
	
}
