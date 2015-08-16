package br.unicamp.secomp.hackaton.automatawar;

public class Sound {
	public void playSoundAcabou() {
	    try {
	        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("C:\acabou.wav").getAbsoluteFile());
	        Clip clip = AudioSystem.getClip();
	        clip.open(audioInputStream);
	        clip.start();
	    } catch(Exception ex) {
	        System.out.println("Error with playing sound.");
	        ex.printStackTrace();
	    }
	}
	
	public void playSoundPontos() {
	    try {
	        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("C:\glass_ping-Go445-1207030150").getAbsoluteFile());
	        Clip clip = AudioSystem.getClip();
	        clip.open(audioInputStream);
	        clip.start();
	    } catch(Exception ex) {
	        System.out.println("Error with playing sound.");
	        ex.printStackTrace();
	    }
	}
	
	public void playSoundPlacement() {
	    try {
	        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("C:\Button Clicking-SoundBible").getAbsoluteFile());
	        Clip clip = AudioSystem.getClip();
	        clip.open(audioInputStream);
	        clip.start();
	    } catch(Exception ex) {
	        System.out.println("Error with playing sound.");
	        ex.printStackTrace();
	    }
	}
	
	public void playSoundTimeLimit() {
	    try {
	        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("C:\BOMB_SIREN-BOMB_SIREN-247265934").getAbsoluteFile());
	        Clip clip = AudioSystem.getClip();
	        clip.open(audioInputStream);
	        clip.start();
	    } catch(Exception ex) {
	        System.out.println("Error with playing sound.");
	        ex.printStackTrace();
	    }
	}
}
