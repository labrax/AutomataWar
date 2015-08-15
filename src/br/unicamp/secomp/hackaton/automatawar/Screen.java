package br.unicamp.secomp.hackaton.automatawar;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.opengl.*;

import static org.lwjgl.opengl.GL11.*;

public class Screen {
	private int WIDTH, HEIGHT;
	
	private GameState gs;
	private Controllers c;
	private Player p1, p2;
	
	public Screen(int y, int x, GameState gs, Controllers c, Player p1, Player p2)
	{
		this.gs = gs;
		this.c = c;
		this.p1 = p1;
		this.p2 = p2;
		
		System.out.println("Hello LWJGL " + Sys.getVersion() + "!");
		HEIGHT = y;
		WIDTH = x;
		
		init();
		
	}
		
	public void run()
	{
		while(!Display.isCloseRequested())
		{
			glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
			c.handle();
		    GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		    draw();
	        Display.update();
	        Display.sync(60);
	    }
		     
	    Display.destroy();
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
		//System.out.println("Drawing");
		
        int BORDER_TOP = 50;
        int BORDER_LEFT = 0;
        
		draw_rect(0+BORDER_TOP, HEIGHT-BORDER_TOP, 0+BORDER_LEFT, WIDTH-BORDER_LEFT, 4);
        
		int gs_w = gs.getWidth();
		int gs_h = gs.getHeight();
		
		// está desenhando de baixo pra cima
		for(int i=0; i < gs_h; i++)
		{
			for(int j=0; j < gs_w; j++)
			{
				/*System.out.print(">" + gs.getState(i, j) + "<");
				if(j == gs_w-1)
					System.out.println();*/
				if(gs.getState(i, j) != 10)
				{
					int cima = HEIGHT - (i*5 + 1 + BORDER_TOP);
					int baixo = cima - 3;
					int esquerda = WIDTH - (j*5 + 1 + BORDER_LEFT);
					int direita = esquerda - 3;
					draw_rect(cima, baixo, esquerda, direita, gs.getState(i, j));
				}
			}
		}
	}
	
	public void draw_rect(int y1, int y2, int x1, int x2, int cor)
	{
		if(cor == 1)
			GL11.glColor3f(0.0f, 0.0f, 1.0f);
		else if(cor == 2)
			GL11.glColor3f(1.0f, 0f, 0.0f);
		else if(cor == 3)
			GL11.glColor3f(0.6f, 0.2f, 0.5f);
		else if(cor == 4)
			GL11.glColor3f(0.0f, 0.0f, 0.0f);
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
