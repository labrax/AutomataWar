package br.unicamp.secomp.hackaton.automatawar;


public class Game {
	private static int count_frames = 0;
	private static long lastTime = 0;
	
	public static void main(String args[])
	{
		Player p1 = new Player(1), p2 = new Player(2);
		Controllers c = new Controllers(p1, p2);
		GameState gs = new GameState(54, 80);
		Screen screen = new Screen(640, 800, gs, c, p1, p2);
		
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
