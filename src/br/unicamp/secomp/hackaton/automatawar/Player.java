package br.unicamp.secomp.hackaton.automatawar;

public class Player {
	private int sel_x, sel_y;
	private int op;
	
	private int player_number; //1 or 2
	
	public Player(int p)
	{
		op = 0;
		player_number = p;
	}
	
	public void set_sel(int x, int y)
	{
		sel_x = x;
		sel_y = y;
		
		System.out.println("Player " + player_number + " at: " + x + "," + y);
	}
	
	public int getX()
	{
		return sel_x;
	}
	
	public int getY()
	{
		return sel_y;
	}
	
	public void act()
	{
		op = 1;
	}
	
	public void sel()
	{
		op = 2;
	}
	
	public int getAcao()
	{
		return op;
	}
}
