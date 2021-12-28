package savethebunniesclient.controller.music;

import java.io.File;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import savethebunniesclient.model.music.SoundType;
import savethebunniesclient.util.Resources;

public class Music {
	  
	private static double volumeEffects = 0.5;
	private static double volumeMusic = 0.5;
	private static MediaPlayer mediaPlayerEffects;
	private static MediaPlayer mediaPlayerMusic;
	
	public static void playSound(SoundType sound) {
		Thread playSoundThread = new Thread(new Runnable() {
			@Override
			public void run() {
				File file = new File(Resources.SOUNDS +  sound.toString().toLowerCase() + ".mp3");				
				Media media = new Media (file.toURI().toString());
				if(sound == SoundType.BUNNY || sound == SoundType.FOX) {
					mediaPlayerEffects = new MediaPlayer(media);
					mediaPlayerEffects.setVolume(volumeEffects);
					mediaPlayerEffects.play();
				} else {
					mediaPlayerMusic = new MediaPlayer(media);
					mediaPlayerMusic.setVolume(volumeMusic);				
					mediaPlayerMusic.setOnEndOfMedia(new Runnable() {
			            @Override
			            public void run() {
			            	playSound(sound);
			            }
					});
					mediaPlayerMusic.play();
				}
			}
		});
		playSoundThread.start();  
	}	  
	
	public static MediaPlayer getMediaPlayerEffects() {
		return mediaPlayerEffects;
	}
	
	public static MediaPlayer getMediaPlayerMusic() {
		return mediaPlayerMusic;
	}
	
	public static void setVolumeMusic(double volume) {
		mediaPlayerMusic.setVolume(volume);
	}
	public static void setVolumeMusicValue(double volume) {
		volumeMusic = volume;
	}
	
	public static void setVolumeEffects(double volume) {
		System.out.println("Le estoy poniendo este valor: " + volume);
		mediaPlayerEffects.setVolume(volume);
	}
	public static void setVolumeEffectsValue(double volume) {
		
		volumeEffects = volume;
	}
	
	public static double getVolumeMusicValue() {
		return volumeMusic;
	}
	
	public static double getVolumeEffectsValue() {
		return volumeEffects;
	}
}
