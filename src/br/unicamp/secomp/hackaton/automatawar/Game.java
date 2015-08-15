package br.unicamp.secomp.hackaton.automatawar;

public class Game {
	private static int count_frames = 0;
	private static long lastTime = 0;
	
	public static void main(String args[])
	{
		Screen screen = new Screen(800, 1200);
		GameState gs = new GameState(1000, 600);
		
		screen.run();
		
		/*while(true)
		{
			long currTime = System.currentTimeMillis();
			
			//pega input
			//trabalha o jogo
			screen.draw(gs); //desenha na tela
			
			if((currTime/1000 == lastTime/1000) && count_frames < 60)
			{
				count_frames++;
			}
			else
			{
				try {
					Thread.sleep((currTime/1000)*1000 - lastTime);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}*/
	}
}
