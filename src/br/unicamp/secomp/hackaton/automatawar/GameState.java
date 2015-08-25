package br.unicamp.secomp.hackaton.automatawar;

import java.util.Random;

public class GameState {
	private int[][] states;
	private int height, width;
	
	private int pontos1, pontos2;
	private int pontos1_acumulado=0, pontos2_acumulado=0;
	
	long startTime;
	
	private boolean gg = false, time = false;
	long last_time = 0;
	
	GameState(int y, int x)
	{
		this.height = y;
		this.width = x;
		states = new int[height][width];
		
		reset();
	}
	
	public void reset()
	{
		states = new int[height][width];
		if(Config.RANDOM)
		{
		    Random rand = new Random(); 
		    rand.setSeed(System.currentTimeMillis()); 
		    for (int i = 0; i < height; i++)
		    {     
			    for (int j = 0; j < width; j++)
			    {
			        Integer r = rand.nextInt()%3; 
			        states[i][j] = Math.abs(r);
			    }
			}
		    
		    for(int i=0; i < height; i++)
		    	for(int j=0; j < width; j++)
		    	{
		    		System.out.print(states[i][j]);
		    		if(j == width-1)
		    			System.out.println();
		    	}
		}
		
		gg = false;
		time = false;
		last_time = 0;
		
		startTime = System.currentTimeMillis();
	}
	
	public void addModel(Player p, Model a)
	{
		int count_pixels=0;
		int map[][] = a.getMap();
		
		for(int j = 0; j < a.getHeight(); j++)
		{
			for(int i = 0; i < a.getWidth(); i++)
			{
				if(map[j][i] == 1)
				{
					states[p.getY() + j][p.getX() + i] = p.getNumber();
					count_pixels++;
				}
			}
		}
		
		if(p.getNumber() == 1)
		{
			pontos2_acumulado += count_pixels*Config.POINTS_PER_MODEL_PIXEL;
			System.out.println("Player 1 added a \"" + a.getName() + "\"! Player 2 awarded " + count_pixels*Config.POINTS_PER_MODEL_PIXEL);
		}
		else if(p.getNumber() == 2)
		{
			pontos1_acumulado += count_pixels*Config.POINTS_PER_MODEL_PIXEL;
			System.out.println("Player 2 added a \"" + a.getName() + "\"! Player 1 awarded " + count_pixels*Config.POINTS_PER_MODEL_PIXEL);
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
					
		compute_scores();
	}
	
	public void compute_scores()
	{
		pontos1 = 0;
		pontos2 = 0;
		
		for(int j=0; j < height; j++)
		{
			for(int i=0; i < width/2; i++)
			{
				if(states[j][i] != 0 && states[j][i] != 6)
					pontos2 += Config.POINTS_PER_UNIT_ENEMY_BASE;
				else if(states[j][i] == 6)
					pontos1 += Config.POINTS_PER_MY_OSCILATOR;
			}
			for(int i=width/2+1; i < width; i++)
			{
				if(states[j][i] != 0 && states[j][i] != 6)
					pontos1 += Config.POINTS_PER_UNIT_ENEMY_BASE;
				else if(states[j][i] == 6)
					pontos2 += Config.POINTS_PER_MY_OSCILATOR;
			}
		}
	}
	
	public boolean isGG()
	{
		int c1=0, c2=0;
		for(int j=0; j < height; j++)
		{
			for(int i=0; i < width/2; i++)
			{
				if(states[j][i] == 6)
					c1++;
			}
			for(int i=width/2+1; i < width; i++)
			{
				if(states[j][i] == 6)
					c2++;
			}
		}
		if(c1 == 0 || c2 == 0)
		{
			gg = true;
			return true;
		}
		
		if(getTimeleft() <= 0)
		{
			gg = true;
			time = true;
			return true;
		}
		
		return false;
	}
	
	public boolean isGGtime()
	{
		return time;
	}
	
	public long getTimeleft()
	{
		if(gg == false)
			last_time = (Config.GAME_TIME - (System.currentTimeMillis() - startTime)/1000);
		return last_time;
	}
	
	public int getPontos1()
	{
		return pontos1+pontos1_acumulado;
	}
	
	public int getPontos2()
	{
		return pontos2+pontos2_acumulado;
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
		int amount_6 = getAmount(6, x, y);
		int amount_t = amount_1 + amount_2 + amount_3 + amount_6;
		
		if (currstate == 0)
		{
			if (amount_t == 3)
			{
				if (amount_1 == amount_t) //Apenas azuis
					return 1;
				else if (amount_2 == amount_t) //Apenas vermelhos
					return 2;
				else if (amount_6 == amount_t)
					return 6;
				return 3; //IT BEGINS
			}
			return 0;
		}
		if ((currstate == 1) || (currstate == 2) || (currstate == 3) || (currstate == 6))
		{
			if ((amount_t != 2) && (amount_t != 3))
				return 0;
			else
				return currstate;
		}
		return 0;
	}
}
