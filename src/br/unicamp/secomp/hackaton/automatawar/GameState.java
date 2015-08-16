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
		int i, j;
		int aux[][] = new int[height][width];
		
		for(j = 0; j < height; j++)
			for(i = 0; i < width; i++)
				aux[j][i] = rule(states[j][i], i, j);

		for(j = 0; j < height; j++)
			for(i = 0; i < width; i++)
				states[j][i] = aux[j][i];
					
		return;
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
		
		if (getElement(src_x, src_y) == elem)
			count--;
		
		return count;
	}
	
	public int getElement(int x, int y) {
		if(x >= 0 && x < width)
			if(y >= 0 && y < height)
				return states[y][x];
		return 0;
	}
	
	public int rule(int player, int x, int y)
	{
		int currstate = getElement(x, y);
		int amount_1 = getAmount(1, x, y);
		int amount_2 = getAmount(2, x, y);
		int amount_3 = getAmount(3, x, y);
		int amount_t = amount_1 + amount_2 + amount_3;
		
		if (currstate == 0)
		{
			if (amount_t == 3)
			{
				if (amount_1 == amount_t) //Apenas azuis
					return 1;
				else if (amount_2 == amount_t) //Apenas vermelhos
					return 2;
				return 3; //IT BEGINS
			}
			return 0;
		}
		if ((currstate == 1) || (currstate == 2) || (currstate == 3))
		{
			if ((amount_t != 2) && (amount_t != 3))
				return 0;
			else
				return currstate;
		}
		return 0;
	}
}
