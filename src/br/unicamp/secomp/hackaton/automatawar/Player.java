package br.unicamp.secomp.hackaton.automatawar;

public class Player {
	private int sel_x, sel_y;
	private int op;
	
	private int mode_weapon = 0;
	private int selected_weapon = 0;
	
	private int player_number; //1 or 2
	
	public Player(int p)
	{
		op = 0;
		player_number = p;
	}
	
	public void set_sel(int x, int y)
	{
		if(x >= 0 && x < Game.STATES_WIDTH)
			sel_x = x;
		if(y >= 0 && y < Game.STATES_HEIGHT)
			sel_y = y;
		
		System.out.println("Player " + player_number + " at: " + sel_x + "," + sel_y);
	}
	
	public int getX()
	{
		return sel_x;
	}
	
	public int getY()
	{
		return sel_y;
	}
	
	public void up()
	{
		this.set_sel(this.getX(), this.getY()-1);
	}
	
	public void down()
	{
		this.set_sel(this.getX(), this.getY()+1);
	}
	
	public void left()
	{
		this.set_sel(this.getX()-1, this.getY());
	}
	
	public void right()
	{
		this.set_sel(this.getX()+1, this.getY());
	}
	
	public void act()
	{
		System.out.println("Player + " + player_number + " act.");
		op = 1;
	}
	
	public void sel()
	{
		System.out.println("Player + " + player_number + " sel.");
		op = 2;
	}
	
	public int getAcao()
	{
		return op;
	}
}
