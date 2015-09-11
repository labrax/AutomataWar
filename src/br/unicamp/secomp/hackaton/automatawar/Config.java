package br.unicamp.secomp.hackaton.automatawar;

public class Config {
	public static boolean RANDOM = false; //o mapa inicial é aleatório (normal = false)
	public static boolean COMPUTE = true; //se desativar isto o jogo fica estático! (false)
	
	//estes valores aqui não devem ser mudados pois o jogo só roda em tela cheia!
	public static boolean FULLSCREEN;
	public static int SCREEN_WIDTH;
	public static int SCREEN_HEIGHT;
	//---
	
	public static int STATES_WIDTH; //quantidade de elementos
	public static int STATES_HEIGHT;
	
	public static int BORDER_TOP = 10; //tamanho da borda de cima (10)
	
	public static int TILE_SIZE = 10; //tamanho dos quadrados (10)
	
	public static int MODEL_SIZE = 8; //tamanho do modelo de cima (5)
	
	public static int BARRIER = TILE_SIZE*5; //barreira a cada lado no meio (em pixels)
	
	public static int UPDATES_PER_SECOND = 10; //atualizações do jogo (normal = 10)
	public static int MOVEMENT_PER_SECOND = 20; //movimento do jogador em quadrados/s (normal = 20)
	
	public static int POINTS_PER_MODEL_PIXEL = 7; //fator de multiplicação de pontos (7)
	public static int POINTS_PER_UNIT_ENEMY_BASE = 5; // (5)
	public static int POINTS_PER_MY_OSCILATOR = 15; // (15)
	
	public static long GAME_TIME = 100; //tempo em segundos do jogo // (180)
	public static int POINTS_SIZE = 8; //tamanho da letra de pontos // (8)
	
	public static boolean ITS_A_PUTARIA = false; //CHEAT MODE! may place blocks on other players (false)
	
	public static boolean SHIELD = true; //may place shields! (true)
	public static boolean MAY_GLIDER = true; //and etc (true)
	public static boolean FACTORY = false; // (true)
	public static boolean SPACESHIP = true; // (true)
	
	public static boolean PENTOMINO = false; // super bomba!
	
	public static boolean NEVER_GG = false; //NEVER ENDING MODE (false)
	
	public static boolean SOUND = true;
	
	public static int SCORES_TO_SOUND = 500; //quantia de pontos acima do maior anterior para tocar som
	
	public static void setResolution(int width, int height, boolean fullscreen)
	{
		SCREEN_WIDTH = width;
		SCREEN_HEIGHT = height;
		FULLSCREEN = fullscreen;
		
		STATES_WIDTH = SCREEN_WIDTH/TILE_SIZE;
		STATES_HEIGHT = SCREEN_HEIGHT/TILE_SIZE - BORDER_TOP;
	}
}
