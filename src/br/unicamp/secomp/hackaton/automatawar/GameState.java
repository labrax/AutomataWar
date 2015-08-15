package br.unicamp.secomp.hackaton.automatawar;

import java.util.Random;

public class GameState {
	private char[][] states;
	private int y, x;
	
	GameState(int y, int x)
	{
		states = new char[y][x];
		
	    Random rand = new Random(); 
	    rand.setSeed(System.currentTimeMillis()); 
	    for (int i = 0; i < y; i++)
	    {     
		    for (int j = 0; j < x; j++)
		    {
		        Integer r = rand.nextInt()%4; 
		        states[i][j] = (char) Math.abs(r);
		    }
		}
	    
	    for(int i=0; i < y; i++)
	    	for(int j=0; j < x; j++)
	    	{
	    		System.out.print(states[i][j]);
	    		if(j == x-1)
	    			System.out.println();
	    	}
	}
	
	public char getState(int y, int x)
	{
		return states[y][x];
	}
	
	public int getHeight()
	{
		return y;
	}
	
	public int getWidth()
	{
		return x;
	}
	
	public void compute()
	{
		
	}
}
