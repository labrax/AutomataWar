package br.unicamp.secomp.hackaton.automatawar;


public class Game {
	public static int STATES_WIDTH = 280;
	public static int STATES_HEIGHT = 140;
	
	public static int UPDATES_PER_SECOND = 1;
	
	public static void main(String args[])
	{
		Player p1 = new Player(1), p2 = new Player(2);
		Controllers c = new Controllers(p1, p2);
		GameState gs = new GameState(140, 280);
		Screen screen = new Screen(800, 1400, gs, c, p1, p2);
		
		p1.set_sel(105, 70);
		p2.set_sel(175, 70);
		
		screen.run(); //game!
	}
}
