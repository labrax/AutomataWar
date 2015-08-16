package br.unicamp.secomp.hackaton.automatawar;

import org.lwjgl.input.Keyboard;

public class Controllers {
	Player p1, p2;
	
	public static final int up1 = Keyboard.KEY_W;
	public static final int down1 = Keyboard.KEY_S;
	public static final int left1 = Keyboard.KEY_A;
	public static final int right1 = Keyboard.KEY_D;
	
	public static final int act1 = Keyboard.KEY_Q;
	public static final int select1 = Keyboard.KEY_E;
	
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
					if(Keyboard.isKeyDown(up1))
						p1.up(true);
					else
						p1.up(false);
					break;
				case down1:
					if(Keyboard.isKeyDown(down1))
						p1.down(true);
					else
						p1.down(false);
					break;
				case left1:
					if(Keyboard.isKeyDown(left1))
						p1.left(true);
					else
						p1.left(false);
					break;
				case right1:
					if(Keyboard.isKeyDown(right1))
						p1.right(true);
					else
						p1.right(false);
					break;
				case act1:
					if(Keyboard.isKeyDown(act1))
						p1.act();
					break;
				case select1:
					if(Keyboard.isKeyDown(select1))
						p1.sel();
					break;
					
				case up2:
					if(Keyboard.isKeyDown(up2))
						p2.up(true);
					else
						p2.up(false);
					break;
				case down2:
					if(Keyboard.isKeyDown(down2))
						p2.down(true);
					else
						p2.down(false);
					break;
				case left2:
					if(Keyboard.isKeyDown(left2))
						p2.left(true);
					else
						p2.left(false);
					break;
				case right2:
					if(Keyboard.isKeyDown(right2))
						p2.right(true);
					else
						p2.right(false);
					break;
				case act2:
					if(Keyboard.isKeyDown(act2))
						p2.act();
					break;
				case select2:
					if(Keyboard.isKeyDown(select2))
						p2.sel();
					break;
			}
		}
	}
}
