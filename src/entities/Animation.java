package entities;

import java.awt.image.BufferedImage;

public class Animation {

    private BufferedImage[] frames;
    private int currentFrame;
    public int frameDelay;
    private int delayCounter;
    private boolean isPlaying;


    public Animation(BufferedImage[] frames, int frameDelay) {
        this.frames = frames;
        this.frameDelay = frameDelay;
        this.currentFrame = 0;
        this.delayCounter = 0;
        this.isPlaying = true;
    }

    public void update() {
    	if (isPlaying) {
	        delayCounter++;
	        if (delayCounter >= frameDelay) {
	            currentFrame = (currentFrame + 1) % frames.length;
	            delayCounter = 0;
	        }
    	}
    }

    public BufferedImage getCurrentFrame() {
        return frames[currentFrame];
    }
    
    public void resetFrame() {
    	currentFrame = 0;
    }
    
    public void setPlaying(boolean isPlaying) {
        this.isPlaying = isPlaying;
    }
    
    public boolean isPlaying () {
    	return this.isPlaying;
    }
   
}