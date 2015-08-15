package br.unicamp.secomp.hackaton.automatawar;

import java.util.Random;

public class GameState {
	private int[][] states;
	private int height, width;
	
	GameState(int y, int x)
	{
		this.height = y;
		this.width = x;
		states = new int[height][width];
		
	    Random rand = new Random(); 
	    rand.setSeed(System.currentTimeMillis()); 
	    for (int i = 0; i < y; i++)
	    {     
		    for (int j = 0; j < x; j++)
		    {
		        Integer r = rand.nextInt()%3; 
		        states[i][j] = Math.abs(r);
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
	
	public int getState(int y, int x)
	{
		return states[y][x];
	}
	
	public int getHeight()
	{
		return height;
	}
	
	public int getWidth()
	{
		return width;
	}
	
	public void compute()
	{
		
	}
	
	public int getAmount(int elem, int src_x, int src_y) {
		int count = 0;
		
		int y, x;
		for(y=src_y-1; y<=src_y+1; y++) {
			for(x=src_x-1; x<=src_x+1; x++) {
				if(getElement(x, y) == elem)
					count++;
			}
		}
		return count - 1;
	}
	
	public int getElement(int x, int y) {
		if(x >= 0 && x < width)
			if(y >= 0 && y < height)
				return states[y][x];
		return 0;
	}
	
	public int rule(int x, int y)
	{
		int amount = getAmount(1, x, y);
		int currstate = getElement(x, y);
		if (currstate == 1)
		{
			if (amount == 2 || amount == 3) /* 2 and 3 survive */
				return 1;
			else /* less than 2 or more than 3 die */
				return 0;
		}
		else if (currstate == 0)
		{
			if (amount == 3)
				return 1;
			else
				return 0;
		}
	}
}
