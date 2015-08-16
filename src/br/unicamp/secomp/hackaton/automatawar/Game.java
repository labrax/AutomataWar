package br.unicamp.secomp.hackaton.automatawar;


public class Game {	
	public static boolean RANDOM = false; // somente para testes!
	
	public static int SCREEN_WIDTH = 1500;
	public static int SCREEN_HEIGHT = 900;
	
	public static int BORDER_TOP = 10;
	
	public static int TILE_SIZE = 15;
	
	public static int MODEL_SIZE = 10;
	
	public static int STATES_WIDTH = SCREEN_WIDTH/TILE_SIZE;
	public static int STATES_HEIGHT = SCREEN_HEIGHT/TILE_SIZE - BORDER_TOP;
	
	public static int BARRIER = TILE_SIZE*5; //barreira a cada lado no meio
	
	public static int UPDATES_PER_SECOND = 1;
	public static int MOVEMENT_PER_SECOND = 10;
	
	public static void main(String args[])
	{
		Player p1 = new Player(1), p2 = new Player(2);
		Controllers c = new Controllers(p1, p2);
		GameState gs = new GameState(STATES_HEIGHT, STATES_WIDTH);
		ModelSelection ms = new ModelSelection();
		Screen screen = new Screen(SCREEN_HEIGHT, SCREEN_WIDTH, gs, c, p1, p2, ms);
		
		p1.set_sel(STATES_WIDTH/2-5, STATES_HEIGHT/2);
		p2.set_sel(STATES_WIDTH/2+4, STATES_HEIGHT/2);
		
		screen.run(); //game!
	}
}
