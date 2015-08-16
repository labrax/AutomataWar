package br.unicamp.secomp.hackaton.automatawar;


public class Game {
	public static int STATES_WIDTH = 280;
	public static int STATES_HEIGHT = 140;
	
	public static int SCREEN_WIDTH = 1400;
	public static int SCREEN_HEIGHT = 800;
	
	public static int BARRIER = 25; //barreira a cada lado no meio
	
	public static int UPDATES_PER_SECOND = 1;
	
	public static void main(String args[])
	{
		Player p1 = new Player(1), p2 = new Player(2);
		Controllers c = new Controllers(p1, p2);
		GameState gs = new GameState(STATES_HEIGHT, STATES_WIDTH);
		Screen screen = new Screen(SCREEN_HEIGHT, SCREEN_WIDTH, gs, c, p1, p2);
		
		p1.set_sel(105, 70);
		p2.set_sel(175, 70);
		
		screen.run(); //game!
	}
}
