package br.unicamp.secomp.hackaton.automatawar;

import org.lwjgl.opengl.GL11;

public class Drawers {
	private int p1_color = 0; //contadores para a cor dos jogadores
	private int p2_color = 0;
	
	Numbers numbers; //guarda os números do jogo
	
	public Drawers()
	{
		this.numbers = new Numbers();
	}
	
	//funções para desenhar!
	public void draw(GameState gs, Player p1, Player p2, ModelSelection ms, boolean gg)
	{
		//limpa tela
	    GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
	    draw_rect(0, Config.SCREEN_HEIGHT, 0, Config.SCREEN_WIDTH, 7);
	    
		//System.out.println("Drawing");
        int BORDER_TOP = Config.BORDER_TOP * Config.TILE_SIZE;
        int BORDER_LEFT = 0;
        
		draw_rect(0, Config.SCREEN_HEIGHT-BORDER_TOP, 0+BORDER_LEFT, Config.SCREEN_WIDTH-BORDER_LEFT, 4); //desenha fundo preto
		
		draw_rect(0, Config.SCREEN_HEIGHT-BORDER_TOP, Config.SCREEN_WIDTH/2-Config.BARRIER, Config.SCREEN_WIDTH/2+Config.BARRIER, 5); //desenha zona sem contato
		
		int gs_w = gs.getWidth();
		int gs_h = gs.getHeight();
		
		// est� desenhando de cima pra baixo
		for(int i=0; i < gs_h; i++)
		{
			for(int j=0; j < gs_w; j++)
			{
				/*
				//debug, print tile map:
				System.out.print(">" + gs.getState(i, j) + "<");
				if(j == gs_w-1)
					System.out.println();*/
				
				int cima = Config.SCREEN_HEIGHT - (i*Config.TILE_SIZE + 1 + BORDER_TOP);
				int baixo = cima - (Config.TILE_SIZE-2);
				int esquerda = j*Config.TILE_SIZE + 1 + BORDER_LEFT;
				int direita = esquerda + (Config.TILE_SIZE-2);
				
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
					int cima = Config.SCREEN_HEIGHT - (j*Config.MODEL_SIZE + 1);
					int baixo = cima - (Config.MODEL_SIZE-2);
					int esquerda = i*Config.MODEL_SIZE + 1 + BORDER_LEFT;
					int direita = esquerda + (Config.MODEL_SIZE-2);
					
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
					int cima = Config.SCREEN_HEIGHT - (j*Config.MODEL_SIZE + 1);
					int baixo = cima - (Config.MODEL_SIZE-2);
					int esquerda = Config.SCREEN_WIDTH-Config.MODEL_SIZE*w2 + i*Config.MODEL_SIZE + 1 + BORDER_LEFT;
					int direita = esquerda + (Config.MODEL_SIZE-2);
					
					draw_rect(cima, baixo, esquerda, direita, 22);
				}
			}
		}
		//---
		
		//--- draw time limit
		draw_number((int) gs.getTimeleft(), Config.SCREEN_HEIGHT-Config.POINTS_SIZE*5, Config.SCREEN_WIDTH/2 - Config.POINTS_SIZE*5/2, Config.POINTS_SIZE, 4, true);
		
		//--- draw points
		draw_number(p1.getPoints(), Config.SCREEN_HEIGHT-Config.POINTS_SIZE*5, Config.SCREEN_WIDTH/4 - Config.POINTS_SIZE*5/2, Config.POINTS_SIZE, 1, false);
		draw_number(p2.getPoints(), Config.SCREEN_HEIGHT-Config.POINTS_SIZE*5, Config.SCREEN_WIDTH*3/4 - Config.POINTS_SIZE*5/2, Config.POINTS_SIZE, 2, false);
		//---
		
		if(gg)
		{
			draw_rect(Config.SCREEN_HEIGHT/2 - 100, Config.SCREEN_HEIGHT/2 + 100, Config.SCREEN_WIDTH/2 - 320, Config.SCREEN_WIDTH/2 + 320, 4);
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
			draw_number(pontos_vencedor, Config.SCREEN_HEIGHT/2 + 50, Config.SCREEN_WIDTH/2 + 60, 25, cor, false);
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
			GL11.glColor3f(0.8f, 0.8f, 0.8f);
		else if(cor == 5) //terra neutra
			GL11.glColor3f(0.9f, 0.9f, 0.9f);
		else if(cor == 6)//verde - oscilador
			GL11.glColor3f(0.6f, 0.8f, 0.0f);
		else if(cor == 7)//parte de cima
			GL11.glColor3f(0.9f, 0.9f, 0.9f);
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
				GL11.glColor3f(1.0f, 0.1f, 0.2f);
			else
				GL11.glColor3f(0.7f, 0.1f, 0.2f);
			p2_color = (p2_color+1)%5;
		}
		else
			GL11.glColor3f(0.8f, 0.8f, 0.8f);
           
        // draw quad
		GL11.glBegin(GL11.GL_QUADS);
	    GL11.glVertex2f(x1, y1);
	    GL11.glVertex2f(x2, y1);
	    GL11.glVertex2f(x2, y2);
	    GL11.glVertex2f(x1, y2);
	    GL11.glEnd();
	}
}
