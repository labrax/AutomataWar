package br.unicamp.secomp.hackaton.automatawar;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
	public static void playSoundAcabou() {
	    try {
	        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(System.getProperty("user.dir") + "/sounds/acabou.wav").getAbsoluteFile());
	        Clip clip = AudioSystem.getClip();
	        clip.open(audioInputStream);
	        clip.start();
	    } catch(Exception ex) {
	        System.out.println("Error with playing sound.");
	        ex.printStackTrace();
	    }
	}
	
	public static void playSoundPontos() {
	    try {
	        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(System.getProperty("user.dir") + "/sounds/glass_ping-Go445-1207030150.wav").getAbsoluteFile());
	        Clip clip = AudioSystem.getClip();
	        clip.open(audioInputStream);
	        clip.start();
	    } catch(Exception ex) {
	        System.out.println("Error with playing sound.");
	        ex.printStackTrace();
	    }
	}
	
	public static void playSoundPlacement() {
	    try {
	        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(System.getProperty("user.dir") + "/sounds/Button Clicking-SoundBible.wav").getAbsoluteFile());
	        Clip clip = AudioSystem.getClip();
	        clip.open(audioInputStream);
	        clip.start();
	    } catch(Exception ex) {
	        System.out.println("Error with playing sound.");
	        ex.printStackTrace();
	    }
	}
	
	public static void playSoundTimeLimit() {
	    try {
	        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(System.getProperty("user.dir") + "/sounds/BOMB_SIREN-BOMB_SIREN-247265934.wav").getAbsoluteFile());
	        Clip clip = AudioSystem.getClip();
	        clip.open(audioInputStream);
	        clip.start();
	    } catch(Exception ex) {
	        System.out.println("Error with playing sound.");
	        ex.printStackTrace();
	    }
	}
}
