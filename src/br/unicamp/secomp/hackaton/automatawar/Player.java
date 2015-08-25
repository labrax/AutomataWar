package br.unicamp.secomp.hackaton.automatawar;

public class Player {
	private int sel_x, sel_y; //posição do jogador
	private int op; //indica se o jogador clicou
	
	long time;
	
	private int selected_model = 0;
	
	private int player_number; //1 or 2
	
	private int vertical = 0;
	private int horizontal = 0;
	
	int points;
	
	public Player(int p)
	{
		op = 0;
		player_number = p;
	}
	
	public void reset()
	{
		op = 0;
		vertical = 0;
		horizontal = 0;
		selected_model = 0;
	}
	
	public void set_sel(int x, int y)
	{
		if(x >= 0 && x < Config.STATES_WIDTH)
			sel_x = x;
		if(y >= 0 && y < Config.STATES_HEIGHT)
			sel_y = y;
		
		//System.out.println("Player " + player_number + " at: " + sel_x + "," + sel_y);
	}
	
	public int getNumber()
	{
		return player_number;
	}
	
	public int getX()
	{
		return sel_x;
	}
	
	public int getY()
	{
		return sel_y;
	}
	
	public long getTime()
	{
		return time;
	}
	
	public void up(boolean down)
	{
		//System.out.println(vertical);
		if(down)
		{
			//System.out.println("Player " + player_number + ": is down!");
			this.set_sel(this.getX(), this.getY()-1);
			vertical=-1;
			time = System.currentTimeMillis();
		}
		else
		{
			//System.out.println("Player " + player_number + ": is up!");
			vertical=0;
		}
	}
	
	public void down(boolean down)
	{
		if(down)
		{
			this.set_sel(this.getX(), this.getY()+1);
			vertical=+1;
			time = System.currentTimeMillis();
		}
		else
			vertical=0;
	}
	
	public void left(boolean down)
	{
		if(down)
		{
			this.set_sel(this.getX()-1, this.getY());
			horizontal=-1;
			time = System.currentTimeMillis();
		}
		else
		{
			horizontal = 0;
		}
	}
	
	public void right(boolean down)
	{
		if(down)
		{
			this.set_sel(this.getX()+1, this.getY());
			horizontal=+1;
			time = System.currentTimeMillis();
		}
		else
			horizontal=0;
	}
	
	public void movimento_acumulado()
	{
		if(horizontal!=0)
		{
			this.set_sel(this.getX()+horizontal, this.getY());
		}
		if(vertical!=0)
		{
			this.set_sel(this.getX(), this.getY()+vertical);
		}
	}
	
	public void updateTime()
	{
		time = System.currentTimeMillis();
	}
	
	public void act()
	{
		//System.out.println("Player + " + player_number + " act.");
		op = 1;
	}
	
	public void sel()
	{
		//System.out.println("Player + " + player_number + " sel.");
		selected_model++;
	}
	
	public int getAcao()
	{
		int ret = op;
		op = 0;
		return ret;
	}
	
	public int getModel()
	{
		return selected_model;
	}
	
	public void setPoints(int points)
	{
		this.points = points;
	}
	
	public int getPoints()
	{
		return points;
	}
}
