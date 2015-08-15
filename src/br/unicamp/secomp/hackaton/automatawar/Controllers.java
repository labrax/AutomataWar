package br.unicamp.secomp.hackaton.automatawar;

import org.lwjgl.input.Keyboard;

public class Controllers {
	Player p1, p2;
	
	public static final int up1 = Keyboard.KEY_W;
	public static final int down1 = Keyboard.KEY_S;
	public static final int left1 = Keyboard.KEY_A;
	public static final int right1 = Keyboard.KEY_D;
	
	public static final int act1 = Keyboard.KEY_1;
	public static final int select1 = Keyboard.KEY_2;
	
	public static final int up2 = Keyboard.KEY_UP;
	public static final int down2 = Keyboard.KEY_DOWN;
	public static final int left2 = Keyboard.KEY_LEFT;
	public static final int right2 = Keyboard.KEY_RIGHT;
	
	public static final int act2 = Keyboard.KEY_N;
	public static final int select2 = Keyboard.KEY_M;
	
	public Controllers(Player p1, Player p2) 
	{
		this.p1 = p1;
		this.p2 = p2;
	}
	
	public void handle()
	{
		while(Keyboard.next())
		{
			int key_state = Keyboard.getEventKey();
			switch(key_state)
			{
				case up1:
					p1.set_sel(p1.getX(), p1.getY()-1);
					break;
				case down1:
					p1.set_sel(p1.getX(), p1.getY()+1);
					break;
				case left1:
					p1.set_sel(p1.getX()-1, p1.getY());
					break;
				case right1:
					p1.set_sel(p1.getX()+1, p1.getY());
					break;
				case act1:
					p1.act();
					break;
				case select1:
					p1.sel();
					
				case up2:
					p2.set_sel(p2.getX(), p2.getY()-1);
					break;
				case down2:
					p2.set_sel(p2.getX(), p2.getY()+1);
					break;
				case left2:
					p2.set_sel(p2.getX()-1, p2.getY());
					break;
				case right2:
					p2.set_sel(p2.getX()+1, p2.getY());
					break;
				case act2:
					p2.act();
					break;
				case select2:
					p2.sel();
			}
		}
	}
}
