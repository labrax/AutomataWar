package br.unicamp.secomp.hackaton.automatawar;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
	public enum SoundType { SOUND_ACABOU, SOUND_PONTOS, SOUND_PLACEMENT, SOUND_TIMELIMIT };
	
	public void playSound(SoundType type)
	{
		if(Config.SOUND)
		{
			try
			{
				AudioInputStream audioInputStream = null;
				Clip clip;
				switch(type)
				{
					case SOUND_ACABOU:
						audioInputStream = AudioSystem.getAudioInputStream(new File(System.getProperty("user.dir") + "/sounds/acabou.wav").getAbsoluteFile());
						break;
					case SOUND_PONTOS:
				        audioInputStream = AudioSystem.getAudioInputStream(new File(System.getProperty("user.dir") + "/sounds/glass_ping-Go445-1207030150.wav").getAbsoluteFile());
						break;
					case SOUND_PLACEMENT:
				        audioInputStream = AudioSystem.getAudioInputStream(new File(System.getProperty("user.dir") + "/sounds/Button Clicking-SoundBible.wav").getAbsoluteFile());
						break;
					case SOUND_TIMELIMIT:
				        audioInputStream = AudioSystem.getAudioInputStream(new File(System.getProperty("user.dir") + "/sounds/BOMB_SIREN-BOMB_SIREN-247265934.wav").getAbsoluteFile());
						break;
					default:
						break;
				}
		        clip = AudioSystem.getClip();
		        clip.open(audioInputStream);
		        clip.start();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
}