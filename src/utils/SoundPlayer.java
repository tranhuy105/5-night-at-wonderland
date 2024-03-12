package utils;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundPlayer {

    private Clip clip;
    private boolean isPlaying = false;
    private boolean shouldLoop = false;

    public SoundPlayer(String soundFilePath, boolean shouldLoop) {
    	this.shouldLoop = shouldLoop;
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(getClass().getResource(soundFilePath));
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play() {
        if (clip != null && !isPlaying) {
            clip.setFramePosition(0);
            clip.start();
            isPlaying = true;

            if (shouldLoop) {
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            }
        }
    }

    public void stop() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
            isPlaying = false;
        }
    }

	public boolean isPlay() {
		return isPlaying;
	}

	public void setIsPlay(boolean iS_PLAY) {
		isPlaying = iS_PLAY;
	}
    
    
}
