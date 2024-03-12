package map;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import main.Game;
import utils.SoundPlayer;

import static utils.Constant.GAMEOVER.*;
import entities.Animation;


public class FailScreen {
	Game mainGame;
	
	private SoundPlayer BGM;
	private SoundPlayer jumpscareSound;
	
	private BufferedImage[] jumpscareFrames = {
			EMUJUMPSCARE,
			EMUJUMPSCARE20,
			EMUJUMPSCARE40,
			EMUJUMPSCARE20,
			EMUJUMPSCARE,
			EMUJUMPSCAREM30,
			EMUJUMPSCAREM60,
			EMUJUMPSCAREM30,
			EMUJUMPSCARE
	};
	
	Animation jumpscare;
	
	public FailScreen(Game mainGame) {
		jumpscare = new Animation(jumpscareFrames, 6);
		BGM = new SoundPlayer("/audio/gameover-bg.wav", true);
		jumpscareSound = new SoundPlayer("/audio/jump-scare.wav", false);
		
		this.mainGame = mainGame;
	}
	
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.drawImage(jumpscare.getCurrentFrame(),0,0,Game.GAME_WIDTH, Game.GAME_HEIGHT, null);
		
		g2d.drawImage(GAMEOVERBUTTON, 
				(Game.GAME_WIDTH - 480),
				(Game.GAME_HEIGHT - 230), 
				640, 320, null);	
		
		g2d.dispose();
	}
	
	public void update() {
		jumpscare.update();
	}
	
	public void checkMouseClick(int mouseX, int mouseY) {
		int buttonX = Game.GAME_WIDTH - 480;
		int buttonY = Game.GAME_HEIGHT - 230;
		
		if(mouseX >= buttonX && mouseX <= buttonX + 640 && mouseY >= buttonY && mouseY <= buttonY + 320) {
			mainGame.resetGame();
		}
		
	}
	
	public void startBackgroundMusic() {
		if (!BGM.isPlay()) {
			jumpscareSound.play();
			BGM.play();
		}
    }

    public void stopBackgroundMusic() {
    	if (BGM.isPlay()) {
    		jumpscareSound.stop();
            BGM.stop();
    	}
    }
}
