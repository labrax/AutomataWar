package br.unicamp.secomp.hackaton.automatawar;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.*;

import static org.lwjgl.opengl.GL11.*;

public class Screen {
	int p1_color = 0;
	int p2_color = 0;
	
	private int WIDTH, HEIGHT;
	
	private GameState gs;
	private Controllers c;
	private Player p1, p2;
	
	ModelSelection ms;
	
	Numbers numbers;
	
	private boolean gg = false;
	
	public Screen(int y, int x, GameState gs, Controllers c, Player p1, Player p2, ModelSelection ms)
	{
		this.gs = gs;
		this.c = c;
		this.p1 = p1;
		this.p2 = p2;
		this.ms = ms;
		this.numbers = new Numbers();
		
		System.out.println("Hello LWJGL " + Sys.getVersion() + "!");
		HEIGHT = y;
		WIDTH = x;
		
		addBase();
		init();
	}
	
	
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
		
		p3.set_sel(0, Game.STATES_HEIGHT/2 - 15);
		gs.addModel(p3, m);
		
		p3.set_sel(Game.STATES_WIDTH-m.getWidth(), Game.STATES_HEIGHT/2);
		gs.addModel(p3, m);
	}
	
	public boolean run()
	{
		boolean end = false, cont = false;
		long currTime, lastTime=0;
		gs.start();
		while(!Display.isCloseRequested())
		{
			glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
			if(gg == false)
			{
				c.handle();
				
				//------------------- atualiza jogo
				currTime = System.currentTimeMillis();
				if(currTime >= lastTime + 1000/Game.UPDATES_PER_SECOND)
				{
					if(Game.COMPUTE)
						gs.compute(); //calcula novo estado e pontos
					lastTime = currTime;
					
					if(gs.isGG())
					{
						gg = true;
						
						if(gs.isGGtime())
							Sound.playSoundTimeLimit();
						else
							Sound.playSoundAcabou();
						System.out.println("GG!");
					}
				}
				//-------------------
				
				//------------------- movimento jogador
				currTime = System.currentTimeMillis();
				if(currTime >= p1.getTime() + 1000/Game.MOVEMENT_PER_SECOND)
				{
					p1.movimento_acumulado();
					p1.updateTime();
				}
				if(currTime >= p2.getTime() + 1000/Game.MOVEMENT_PER_SECOND)
				{
					p2.movimento_acumulado();
					p2.updateTime();
				}
				//-------------------
				
				//------------------- adiciona modelos
				if(p1.getAcao() == 1)
				{
					Model n = ms.getModel(1, p1.getModel()%ms.getAmount(1));
					if((p1.getX() + n.getWidth() <= (Game.STATES_WIDTH/2 - Game.BARRIER/Game.TILE_SIZE + 1)) || Game.ITS_A_PUTARIA)
					{
						if(p1.getY() + n.getHeight() <= Game.STATES_HEIGHT)
						{
							//System.out.println("Inserting for p1");
							gs.addModel(p1, n);
						}
					}
				}
				if(p2.getAcao() == 1)
				{
					Model n = ms.getModel(2, p2.getModel()%ms.getAmount(2));
					if( ((p2.getX() >= (Game.STATES_WIDTH/2 + Game.BARRIER/Game.TILE_SIZE)) || Game.ITS_A_PUTARIA ) && (p2.getX() + n.getWidth() < Game.STATES_WIDTH))
					{
						if(p2.getY() + n.getHeight() <= Game.STATES_HEIGHT)
						{
							//System.out.println("Inserting for p2");
							gs.addModel(p2, n);
						}
					}
				}
				//------------------
			}
			else
			{
				while(Keyboard.next())
				{
					if(Keyboard.getEventKey() == Keyboard.KEY_RETURN)
					{
						end = true;
						cont = true;
					}
					else if(Keyboard.getEventKey() == Keyboard.KEY_ESCAPE)
					{
						end = true;
						cont = false;
					}
				}
			}
		    //desenha tudo
		    draw();
		    
	        Display.update();
	        Display.sync(60); //60 fps?
	        if(end == true)
	        	break;
	    }
		     
	    Display.destroy();
	    if(cont == true)
	    	return true;
	    else
	    	return false;
	}
	
	private void init() {
		initDisplay();
		initOGL();
	}
	 
	private void initDisplay() {
	    try
	    {
	    	Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
	        Display.setTitle("Automata War!");
	        Display.create();
	    }
	    catch(LWJGLException e)
	    {
	        e.printStackTrace();
	    }
	}
	 
	private void initOGL() {
	    glMatrixMode(GL_PROJECTION);
	    glLoadIdentity();
	    glOrtho(0, WIDTH, 0, HEIGHT, 1, -1);
	    glMatrixMode(GL_MODELVIEW);
	    glEnable(GL_TEXTURE_2D);
	    glEnable(GL_BLEND);
	    glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
	}
	
	public void draw()
	{
		//limpa tela
	    GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
	    
		//System.out.println("Drawing");
        int BORDER_TOP = Game.BORDER_TOP * Game.TILE_SIZE;
        int BORDER_LEFT = 0;
        
		draw_rect(0, HEIGHT-BORDER_TOP, 0+BORDER_LEFT, WIDTH-BORDER_LEFT, 4); //desenha fundo preto
		
		draw_rect(0, HEIGHT-BORDER_TOP, Game.SCREEN_WIDTH/2-Game.BARRIER, Game.SCREEN_WIDTH/2+Game.BARRIER, 5); //desenha zona sem contato
		
		int gs_w = gs.getWidth();
		int gs_h = gs.getHeight();
		
		// está desenhando de cima pra baixo
		for(int i=0; i < gs_h; i++)
		{
			for(int j=0; j < gs_w; j++)
			{
				/*
				//debug, print tile map:
				System.out.print(">" + gs.getState(i, j) + "<");
				if(j == gs_w-1)
					System.out.println();*/
				
				int cima = HEIGHT - (i*Game.TILE_SIZE + 1 + BORDER_TOP);
				int baixo = cima - (Game.TILE_SIZE-2);
				int esquerda = j*Game.TILE_SIZE + 1 + BORDER_LEFT;
				int direita = esquerda + (Game.TILE_SIZE-2);
				
				if(p1.getY() == i && p1.getX() == j)
				{
					draw_rect(cima, baixo, esquerda, direita, 21);
				}
				else if(p2.getY() == i && p2.getX() == j)
				{
					draw_rect(cima, baixo, esquerda, direita, 22);
				}
				else if(gs.getState(i, j) != 10)
				{
					draw_rect(cima, baixo, esquerda, direita, gs.getState(i, j));
				}
			}
		}
		
		//--- draw new models
		Model m = ms.getModel(1, p1.getModel()%ms.getAmount(1));
		int w = m.getWidth();
		int h = m.getHeight();
		int map[][] = m.getMap();
		
		for(int j=0; j<h; j++)
		{
			for(int i=0; i<w; i++)
			{
				if(map[j][i] == 1)
				{
					int cima = HEIGHT - (j*Game.MODEL_SIZE + 1);
					int baixo = cima - (Game.MODEL_SIZE-2);
					int esquerda = i*Game.MODEL_SIZE + 1 + BORDER_LEFT;
					int direita = esquerda + (Game.MODEL_SIZE-2);
					
					draw_rect(cima, baixo, esquerda, direita, 21);
				}
			}
		}
		
		Model m2 = ms.getModel(2, p2.getModel()%ms.getAmount(2));
		int w2 = m2.getWidth();
		int h2 = m2.getHeight();
		int map2[][] = m2.getMap();
		
		for(int j=0; j<h2; j++)
		{
			for(int i=0; i<w2; i++)
			{
				if(map2[j][i] == 1)
				{
					int cima = HEIGHT - (j*Game.MODEL_SIZE + 1);
					int baixo = cima - (Game.MODEL_SIZE-2);
					int esquerda = Game.SCREEN_WIDTH/2 + i*Game.MODEL_SIZE + 1 + BORDER_LEFT;
					int direita = esquerda + (Game.MODEL_SIZE-2);
					
					draw_rect(cima, baixo, esquerda, direita, 22);
				}
			}
		}
		//---
		
		//--- draw time limit
		draw_number((int) gs.getTimeleft(), HEIGHT-Game.POINTS_SIZE*5, Game.SCREEN_WIDTH/2 - Game.POINTS_SIZE*5/2, Game.POINTS_SIZE, 4, true);
		
		//--- draw points
		draw_number(gs.getPontos1(), HEIGHT-Game.POINTS_SIZE*5, Game.SCREEN_WIDTH/4 - Game.POINTS_SIZE*5/2, Game.POINTS_SIZE, 1, false);
		draw_number(gs.getPontos2(), HEIGHT-Game.POINTS_SIZE*5, Game.SCREEN_WIDTH*3/4 - Game.POINTS_SIZE*5/2, Game.POINTS_SIZE, 2, false);
		//---
		
		if(gg)
		{
			draw_rect(Game.SCREEN_HEIGHT/2 - 100, Game.SCREEN_HEIGHT/2 + 100, Game.SCREEN_WIDTH/2 - 320, Game.SCREEN_WIDTH/2 + 320, 4);
			int pontos_vencedor = gs.getPontos1();
			int cor = 1;
			if(gs.getPontos1() < gs.getPontos2())
			{
				pontos_vencedor = gs.getPontos2();
				cor = 2;
			}
			else if(gs.getPontos1() == gs.getPontos2())
			{
				pontos_vencedor = 0;
				cor = 3;
			}
			draw_number(pontos_vencedor, Game.SCREEN_HEIGHT/2 + 50, Game.SCREEN_WIDTH/2 + 60, 25, cor, false);
		}
	}
	
	public void draw_number(int number, int ref_y, int ref_x, int size_pixel, int color, boolean time)
	{
		if(!time)
		{
			if(number/10000 >= 0)
			{
				Model n0 = numbers.getModel((int) (number/10000)%10);
				int w_N = n0.getWidth();
				int h_N = n0.getHeight();
				int map_N[][] = n0.getMap();
				
				for(int j=0; j<h_N; j++)
				{
					for(int i=0; i<w_N; i++)
					{
						if(map_N[j][i] == 1)
						{
							int cima = ref_y - (j*size_pixel + 1);
							int baixo = cima - (size_pixel-2);
							int esquerda = ref_x + i*size_pixel + 1 - 3*size_pixel*w_N;
							int direita = esquerda + (size_pixel-2);
							
							draw_rect(cima, baixo, esquerda, direita, color);
						}
					}
				}
			}
			if(number/1000 >= 0)
			{
				Model n0 = numbers.getModel((int) (number/1000)%10);
				int w_N = n0.getWidth();
				int h_N = n0.getHeight();
				int map_N[][] = n0.getMap();
				
				for(int j=0; j<h_N; j++)
				{
					for(int i=0; i<w_N; i++)
					{
						if(map_N[j][i] == 1)
						{
							int cima = ref_y - (j*size_pixel + 1);
							int baixo = cima - (size_pixel-2);
							int esquerda = ref_x + i*size_pixel + 1 - 2*size_pixel*w_N;
							int direita = esquerda + (size_pixel-2);
							
							draw_rect(cima, baixo, esquerda, direita, color);
						}
					}
				}
			}
		}
		if(number/100 >= 0)
		{
			Model n0 = numbers.getModel((int) (number/100)%10);
			int w_N = n0.getWidth();
			int h_N = n0.getHeight();
			int map_N[][] = n0.getMap();
			
			for(int j=0; j<h_N; j++)
			{
				for(int i=0; i<w_N; i++)
				{
					if(map_N[j][i] == 1)
					{
						int cima = ref_y - (j*size_pixel + 1);
						int baixo = cima - (size_pixel-2);
						int esquerda = ref_x + i*size_pixel + 1 - size_pixel*w_N;
						int direita = esquerda + (size_pixel-2);
						
						draw_rect(cima, baixo, esquerda, direita, color);
					}
				}
			}
		}
		
		if(number/10 >= 0)
		{
			Model n0 = numbers.getModel((int) (number/10)%10);
			int w_N = n0.getWidth();
			int h_N = n0.getHeight();
			int map_N[][] = n0.getMap();
			
			for(int j=0; j<h_N; j++)
			{
				for(int i=0; i<w_N; i++)
				{
					if(map_N[j][i] == 1)
					{
						int cima = ref_y - (j*size_pixel + 1);
						int baixo = cima - (size_pixel-2);
						int esquerda = ref_x + i*size_pixel + 1;
						int direita = esquerda + (size_pixel-2);
						
						draw_rect(cima, baixo, esquerda, direita, color);
					}
				}
			}
		}
		
		int nnumber;
		if(((int) (number%10)) > 0)
			nnumber = Math.abs((int) (number%10));
		else
			nnumber = 0;
		
		Model n0 = numbers.getModel(nnumber);
		int w_N = n0.getWidth();
		int h_N = n0.getHeight();
		int map_N[][] = n0.getMap();
		
		for(int j=0; j<h_N; j++)
		{
			for(int i=0; i<w_N; i++)
			{
				if(map_N[j][i] == 1)
				{
					int cima = ref_y - (j*size_pixel + 1);
					int baixo = cima - (size_pixel-2);
					int esquerda = ref_x + i*size_pixel + 1 + size_pixel*w_N;
					int direita = esquerda + (size_pixel-2);
					
					draw_rect(cima, baixo, esquerda, direita, color);
				}
			}
		}
		//---
	}
	
	public void draw_rect(int y1, int y2, int x1, int x2, int cor)
	{
		if(cor == 1) //p1
			GL11.glColor3f(0.0f, 0.0f, 1.0f);
		else if(cor == 2) //p2
			GL11.glColor3f(1.0f, 0f, 0.0f);
		else if(cor == 3) //neutro
			GL11.glColor3f(0.9f, 0.547f, 0.4f);
		else if(cor == 4) //fundo
			GL11.glColor3f(0.07f, 0.07f, 0.07f);
		else if(cor == 5) //terra neutra
			GL11.glColor3f(0.5f, 0.5f, 0.5f);
		else if(cor == 6)//verde - oscilador
			GL11.glColor3f(0.6f, 1.0f, 0.0f);
		else if(cor == 21)
		{
			if(p1_color==0)
				GL11.glColor3f(0.2f, 0.5f, 1.0f);
			else
				GL11.glColor3f(0.2f, 0.4f, 0.8f);
			
			p1_color = (p1_color+1)%5;
		}
		else if(cor == 22)
		{
			if(p2_color==0)
				GL11.glColor3f(1.0f, 0.5f, 0.2f);
			else
				GL11.glColor3f(0.8f, 0.4f, 0.2f);
			p2_color = (p2_color+1)%5;
		}
		else
			GL11.glColor3f(1.0f, 1.0f, 1.0f);
           
        // draw quad
		GL11.glBegin(GL11.GL_QUADS);
	    GL11.glVertex2f(x1, y1);
	    GL11.glVertex2f(x2, y1);
	    GL11.glVertex2f(x2, y2);
	    GL11.glVertex2f(x1, y2);
	    GL11.glEnd();
	}
	

}
