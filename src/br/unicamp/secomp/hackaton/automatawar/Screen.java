package br.unicamp.secomp.hackaton.automatawar;

import org.lwjgl.Sys;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
 
import java.nio.ByteBuffer;
 
import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.*;

public class Screen {
    // We need to strongly reference callback instances.
    private GLFWErrorCallback errorCallback;
    private GLFWKeyCallback   keyCallback;
    
    // The window handle
    private long window;
    
	private int WIDTH = 1000, HEIGHT = 800;
	
	public Screen(int y, int x)
	{
		System.out.println("Hello LWJGL " + Sys.getVersion() + "!");
		HEIGHT = y;
		WIDTH = x;
		
        // Setup an error callback. The default implementation
        // will print the error message in System.err.
        glfwSetErrorCallback(errorCallback = errorCallbackPrint(System.err));
 
        // Initialize GLFW. Most GLFW functions will not work before doing this.
        if ( glfwInit() != GL11.GL_TRUE )
            throw new IllegalStateException("Unable to initialize GLFW");
 
        // Configure our window
        glfwDefaultWindowHints(); // optional, the current window hints are already the default
        glfwWindowHint(GLFW_VISIBLE, GL_FALSE); // the window will stay hidden after creation
        glfwWindowHint(GLFW_RESIZABLE, GL_TRUE); // the window will be resizable
 
        // Create the window
        window = glfwCreateWindow(WIDTH, HEIGHT, "Hello World!", NULL, NULL);
        if ( window == NULL )
            throw new RuntimeException("Failed to create the GLFW window");
 
        // Setup a key callback. It will be called every time a key is pressed, repeated or released.
        glfwSetKeyCallback(window, keyCallback = new GLFWKeyCallback() {
            @Override
            public void invoke(long window, int key, int scancode, int action, int mods) {
                if ( key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE )
                    glfwSetWindowShouldClose(window, GL_TRUE); // We will detect this in our rendering loop
            }
        });
 
        // Get the resolution of the primary monitor
        ByteBuffer vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        // Center our window
        glfwSetWindowPos(
            window,
            (GLFWvidmode.width(vidmode) - WIDTH) / 2,
            (GLFWvidmode.height(vidmode) - HEIGHT) / 2
        );
 
        // Make the OpenGL context current
        glfwMakeContextCurrent(window);
        // Enable v-sync
        glfwSwapInterval(1);
 
        // Make the window visible
        glfwShowWindow(window);
	}
	
	public void loop(GameState gs)
	{
        // This line is critical for LWJGL's interoperation with GLFW's
        // OpenGL context, or any context that is managed externally.
        // LWJGL detects the context that is current in the current thread,
        // creates the ContextCapabilities instance and makes the OpenGL
        // bindings available for use.
        GLContext.createFromCurrent();
 
        // Set the clear color
        glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
 
        // Run the rendering loop until the user has attempted to close
        // the window or has pressed the ESCAPE key.
        while ( glfwWindowShouldClose(window) == GL_FALSE ) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer
 
            glfwSwapBuffers(window); // swap the color buffers
 
            // Poll for window events. The key callback above will only be
            // invoked during this call.
            glfwPollEvents();
            draw(gs);
            
        }
	}
	
	public void draw(GameState gs)
	{
		System.out.println("Drawing");
		
        int BORDER_TOP = 200;
        
		int gs_w = gs.getWidth();
		int gs_h = gs.getHeight();
		
		for(int i=0; i < gs_h; i++)
		{
			for(int j=0; j < gs_w; j++)
			{
				System.out.print(gs.getState(i, j));
				if(j == gs_w-1)
					System.out.println();
				if(gs.getState(i, j) != 10)
				{
					int cima = i*10 + BORDER_TOP;
					int baixo = cima+7;
					int esquerda = j*10;
					int direita = esquerda+7;
					draw_box(cima, baixo, esquerda, direita, gs.getState(i, j));
				}
			}
		}
	}
	
	public void draw_box(int y1, int y2, int x1, int x2, int cor)
	{
		// set the color of the quad (R,G,B,A)
		if(cor == 1)
			GL11.glColor3f(0.5f, 1.0f, 1.0f);
		else if(cor == 2)
			GL11.glColor3f(1.0f, 0.5f, 1.0f);
		else if(cor == 3)
			GL11.glColor3f(1.0f, 1.0f, 0.5f);
		else
			GL11.glColor3f(0.7f, 0.7f, 0.7f);
		
		// draw quad
		GL11.glBegin(GL11.GL_QUADS);
		    GL11.glVertex2f(x1, y1);
		    GL11.glVertex2f(x2, y1);
		    GL11.glVertex2f(x2, y2);
		    GL11.glVertex2f(x1, y2);
		GL11.glEnd();
	}
	
	public void run(GameState gs)
	{
		try
		{
			loop(gs);
			
            // Release window and window callbacks
            glfwDestroyWindow(window);
            keyCallback.release();
        } finally {
            // Terminate GLFW and release the GLFWerrorfun
            glfwTerminate();
            errorCallback.release();
        }
	}

}
