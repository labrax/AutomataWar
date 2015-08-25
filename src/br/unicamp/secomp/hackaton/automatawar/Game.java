package br.unicamp.secomp.hackaton.automatawar;

import java.io.File;

public class Game {
	//objetos auxiliares controlando diversas partes do jogo
	private Player p1, p2;
	private Controllers c;
	private ModelSelection ms;
	private GameState gs;
	private Screen screen;
	private Sound sound;
	
	private boolean gg; //indica se o jogo acabou ou não
	private int high1, high2; //variável para indicar o valor anterior que teve som de highscore!
	
	public static void start_native_libraries()
	{
		if(System.getProperty("os.name").startsWith("Windows"))
			System.setProperty("org.lwjgl.librarypath", new File("native/windows").getAbsolutePath());
		else if(System.getProperty("os.name").startsWith("Linux"))
			System.setProperty("org.lwjgl.librarypath", new File("native/linux").getAbsolutePath());
		else if(System.getProperty("os.name").startsWith("Mac"))
			System.setProperty("org.lwjgl.librarypath", new File("native/macosx").getAbsolutePath());
	}
	
	public static void main(String args[])
	{
		start_native_libraries();
		
		Game game = new Game();
		
		game.init();
		game.reset();
		game.run();
	}
	
	protected Game()
	{
		p1 = new Player(1);
		p2 = new Player(2);
		c = new Controllers(p1, p2);
		ms = new ModelSelection();
		screen = new Screen(); //SCREEN DEVE SER INICIADO ANTES DE GAMESTATE!
		gs = new GameState(Config.STATES_HEIGHT, Config.STATES_WIDTH);
		sound = new Sound();
	}
	
	public void init()
	{
		screen.init();
	}
	
	public void reset()
	{
		gs.reset();
		p1.reset();
		p2.reset();
		
		p1.set_sel(gs.getWidth()/2-5, gs.getHeight()/2);
		p2.set_sel(gs.getWidth()/2+4, gs.getHeight()/2);
		
		high1 = 500;
		high2 = 500;
		gg = false;
		
		addBase();
	}
	
	//adiciona os osciladores no jogo
		public void addBase()
		{
			Player p3 = new Player(6);
			int v8[][] =
				{
					{0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0},
					{0 ,0 ,0 ,1 ,1 ,1 ,0 ,0 ,0 ,1 ,1 ,1 ,0 ,0 ,0},
					{0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0},
					{0 ,1 ,0 ,0 ,0 ,0 ,1 ,0 ,1 ,0 ,0 ,0 ,0 ,1 ,0},
					{0 ,1 ,0 ,0 ,0 ,0 ,1 ,0 ,1 ,0 ,0 ,0 ,0 ,1 ,0},
					{0 ,1 ,0 ,0 ,0 ,0 ,1 ,0 ,1 ,0 ,0 ,0 ,0 ,1 ,0},
					{0 ,0 ,0 ,1 ,1 ,1 ,0 ,0 ,0 ,1 ,1 ,1 ,0 ,0 ,0},
					{0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0},
					{0 ,0 ,0 ,1 ,1 ,1 ,0 ,0 ,0 ,1 ,1 ,1 ,0 ,0 ,0},
					{0 ,1 ,0 ,0 ,0 ,0 ,1 ,0 ,1 ,0 ,0 ,0 ,0 ,1 ,0},
					{0 ,1 ,0 ,0 ,0 ,0 ,1 ,0 ,1 ,0 ,0 ,0 ,0 ,1 ,0},
					{0 ,1 ,0 ,0 ,0 ,0 ,1 ,0 ,1 ,0 ,0 ,0 ,0 ,1 ,0},
					{0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0},
					{0 ,0 ,0 ,1 ,1 ,1 ,0 ,0 ,0 ,1 ,1 ,1 ,0 ,0 ,0},
					{0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0}
				};
			Model m = new Model("Base", v8, 15, 15);
			
			p3.set_sel(0, Config.STATES_HEIGHT/2 - 10);
			gs.addModel(p3, m);
			
			p3.set_sel(Config.STATES_WIDTH-m.getWidth(), Config.STATES_HEIGHT/2+10-m.getHeight());
			gs.addModel(p3, m);
			
			int v9[][] =
				{
					{1, 1, 0, 0, 1, 1},
					{1, 0, 0, 1, 0, 1},
					{0, 1, 0, 0, 0, 0},
					{0, 0, 0, 0, 1, 0},
					{1, 0, 1, 0, 0, 1},
					{1, 1, 0, 0, 1, 1}
				};
			m = new Model("Rel�gio", v9, 6, 6);
			p3.set_sel(m.getWidth()+4, Config.STATES_HEIGHT/2 + 10);
			gs.addModel(p3, m);
			
			p3.set_sel(Config.STATES_WIDTH-m.getWidth()-10, Config.STATES_HEIGHT/2-10-m.getHeight());
			gs.addModel(p3, m);
			
			int v10[][] =
				{
						{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 
						{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 
						{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 
						{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 
						{0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0}, 
						{0, 0, 0, 0, 0, 1, 0, 1, 1, 1, 1, 0, 1, 0, 0, 0, 0, 0}, 
						{0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0}, 
						{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 
						{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 
						{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 
						{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
				};
			m = new Model("Oscilador 2", v10, 11, 17);
			p3.set_sel(Config.STATES_WIDTH/2-m.getWidth()/2-1, 0);
			gs.addModel(p3, m);
			
			p3.set_sel(Config.STATES_WIDTH/2-m.getWidth()/2-1, Config.STATES_HEIGHT-m.getHeight());
			gs.addModel(p3, m);
			
			int v11[][] =
				{
					{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
					{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
					{0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
					{0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
					{0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
					{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
					{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
					{0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0},
					{0, 0, 1, 1, 1, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 1, 1, 1, 0, 0},
					{0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
					{1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1},
					{1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1},
					{1, 0, 0, 0, 0, 1, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 1, 0, 0, 0, 0, 1},
					{0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0},
					{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
					{0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0},
					{1, 0, 0, 0, 0, 1, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 1, 0, 0, 0, 0, 1},
					{1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1},
					{1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1},
					{0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
					{0, 0, 1, 1, 1, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 1, 1, 1, 0, 0},
					{0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0},
					{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
					{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
					{0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
					{0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
					{0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
					{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
					{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
				};
			m = new Model("Quasar", v11, 29, 29);
			p3.set_sel(Config.STATES_WIDTH/4 - m.getWidth()/2, 1);
			gs.addModel(p3, m);
			
			p3.set_sel(Config.STATES_WIDTH*3/4 - m.getWidth()/2, Config.STATES_HEIGHT-m.getHeight()-1);
			gs.addModel(p3, m);		
		}
	
	public void run()
	{
		boolean end = false; //pra ver se fecha ou n�o o jogo
		long currTime, lastTime=0;
		
		while(!screen.display_closed())
		{
			if(gg == false)
			{
				c.handle();
				
				//------------------- atualiza jogo
				currTime = System.currentTimeMillis();
				if(currTime >= lastTime + 1000/Config.UPDATES_PER_SECOND)
				{
					if(Config.COMPUTE)
						gs.compute(); //calcula novo estado e pontos
					lastTime = currTime;
					
					if(gs.isGG() && !Config.NEVER_GG)
					{
						gg = true;
						
						if(gs.isGGtime())
							sound.playSound(Sound.SoundType.SOUND_TIMELIMIT);
						else
							sound.playSound(Sound.SoundType.SOUND_ACABOU);
						System.out.println("GG!");
					}
				}
				//-------------------
				
				//------------------- movimento jogador
				currTime = System.currentTimeMillis();
				if(currTime >= p1.getTime() + 1000/Config.MOVEMENT_PER_SECOND)
				{
					p1.movimento_acumulado();
					p1.updateTime();
				}
				if(currTime >= p2.getTime() + 1000/Config.MOVEMENT_PER_SECOND)
				{
					p2.movimento_acumulado();
					p2.updateTime();
				}
				if(Controllers.exit)
				{
					break;
				}
				//-------------------
				
				//------------------- adiciona modelos
				if(p1.getAcao() == 1)
				{
					Model n = ms.getModel(1, p1.getModel()%ms.getAmount(1));
					if((p1.getX() + n.getWidth() <= (Config.STATES_WIDTH/2 - Config.BARRIER/Config.TILE_SIZE + 1)) || Config.ITS_A_PUTARIA)
					{
						if(p1.getY() + n.getHeight() <= Config.STATES_HEIGHT)
						{
							//System.out.println("Inserting for p1");
							gs.addModel(p1, n);
							sound.playSound(Sound.SoundType.SOUND_PLACEMENT);
						}
					}
				}
				if(p2.getAcao() == 1)
				{
					Model n = ms.getModel(2, p2.getModel()%ms.getAmount(2));
					if( ((p2.getX() >= (Config.STATES_WIDTH/2 + Config.BARRIER/Config.TILE_SIZE)) || Config.ITS_A_PUTARIA ) && (p2.getX() + n.getWidth() < Config.STATES_WIDTH))
					{
						if(p2.getY() + n.getHeight() <= Config.STATES_HEIGHT)
						{
							//System.out.println("Inserting for p2");
							gs.addModel(p2, n);
							sound.playSound(Sound.SoundType.SOUND_PLACEMENT);
						}
					}
				}
				//------------------
			}
			else //if gg
			{
				end = c.exit_game();
				if(end == false)
					reset();
			}
			//---------- calcula se deve tocar som de highscore
			p1.setPoints(gs.getPontos1());
			if(p1.getPoints() > high1 + Config.SCORES_TO_SOUND)
			{
				high1 = p1.getPoints();
				sound.playSound(Sound.SoundType.SOUND_PONTOS);
			}
			p2.setPoints(gs.getPontos2());
			if(p2.getPoints() > high2 + Config.SCORES_TO_SOUND)
			{
				high2 = p2.getPoints();
				sound.playSound(Sound.SoundType.SOUND_PONTOS);
			}
			//----------
			
		    //desenha tudo
		    screen.draw(gs, p1, p2, ms, gg);
		    
	        if(end == true)
	        	break;
	    }
	}
}
