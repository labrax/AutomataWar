package br.unicamp.secomp.hackaton.automatawar;

import java.util.Random;

public class GameState {
	private int[][] states;
	private int y, x;
	
	GameState(int y, int x)
	{
		this.y = y;
		this.x = x;
		states = new int[y][x];
		
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
		return y;
	}
	
	public int getWidth()
	{
		return x;
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
	
	public int getElement(in[][] table, int x, int y) {
		if(x >= 0 && x < size_x)
			if(y >= 0 && y < size_y)
				return table[y][x];
		return 0;
	}
	
	public int rule(int[][] table, int x, int y)
	{
		amount = getAmount(table, 1, x, y, 1);
		currstate = getElement(table, x, y);
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
