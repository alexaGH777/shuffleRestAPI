package com.micros.shuffle.service;

import java.util.Random;
import java.util.stream.IntStream;

import org.springframework.stereotype.Service;

@Service
public class ShuffleService {
	
	public String getShuffledArray(String input)
	{
		StringBuilder toReturn = new StringBuilder();
		if(!isNumeric(input.trim(), toReturn)) return toReturn.toString();
		
		int inputNumber = Integer.parseInt(input);
		if(!isValidRange(inputNumber, toReturn)) return toReturn.toString();
		
		int[] array = getArray(1, inputNumber);
		int[] shuffledArr = shuffleArray(inputNumber, array);
		
		return String.join(",", IntStream.of(shuffledArr).mapToObj(String::valueOf).toArray(String[]::new));
	}
	
	private int[] shuffleArray(int maxNumber, int[] array) 
	{
		for (int i = maxNumber-1; i > 0; i--) {
            
            // Pick a random index from 0 to i
            int randomeInx = getRandomNumber(i+1);
            // Swap array[i] with the element at random index, in case random index different from current index 
            if(randomeInx != i)
            {
	            int temp = array[i];
	            array[i] = array[randomeInx];
	            array[randomeInx] = temp;
            }
        }
		
		return array;
	}
	
	private int getRandomNumber(int bound)
	{
		return new Random().nextInt(bound);
	}
	
	private int[] getArray(int startFrom, int len) {
		int[] array = new int[len];
		 
		for(int i=0; i< len; i++){
			array[i] = i+startFrom;
		}
		return array;
	}
	
	private static boolean isValidRange(int number, StringBuilder errMsg)
	{
		int min = 1;
		int max = 1000;
		if(number >= min && number <= max) {
			return true;
		}
		else {
			errMsg.append("Not a valid range. Valid input range is from "+min+" to "+max+".");
			return false;
		}
	}
	
	private static boolean isNumeric(String string, StringBuilder errMsg) {
	    	
	    if(string == null || string.equals("")) {
	    	errMsg.append("Input String cannot be empty.");
	    	return false;
	    }
	    try {
	        Integer.parseInt(string);
	        return true;
	    } catch (NumberFormatException e) {
	    	errMsg.append("Input String cannot be parsed to Integer.");
	    }
	    return false;
	}
}
