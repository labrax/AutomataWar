package br.unicamp.secomp.hackaton.automatawar;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.opengl.*;

import static org.lwjgl.opengl.GL11.*;

public class Screen {
	Drawers drawer;
	
	public Screen()
	{
		System.out.println("Hello LWJGL " + Sys.getVersion() + "!");
		drawer = new Drawers();
		
		Config.setResolution(Display.getDesktopDisplayMode().getWidth(), Display.getDesktopDisplayMode().getHeight(), true);
	}
	
	
	public void init() {
	    try
	    {
	    	//Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
	        Display.setTitle("Automata War!");
	        Display.setDisplayModeAndFullscreen(Display.getDesktopDisplayMode());
	        Display.create();
	    }
	    catch(LWJGLException e)
	    {
	        e.printStackTrace();
	        System.exit(-1);
	    }
	    
	    glMatrixMode(GL_PROJECTION);
	    glLoadIdentity();
	    glOrtho(0, Config.SCREEN_WIDTH, 0, Config.SCREEN_HEIGHT, 1, -1);
	    glMatrixMode(GL_MODELVIEW);
	    glEnable(GL_TEXTURE_2D);
	    glEnable(GL_BLEND);
	    glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
	}
	
	public void close()
	{
		Display.destroy();
	}
	
	public void draw(GameState gs, Player p1, Player p2, ModelSelection ms, boolean gg)
	{
		glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
		drawer.draw(gs, p1, p2, ms, gg);
        Display.update();
        Display.sync(60); //60 fps?
	}
	
	public boolean display_closed()
	{
		return Display.isCloseRequested();
	}
}
