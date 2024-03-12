package main;


import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;

import utils.SoundPlayer;

import static utils.Constant.MENU.*;

public class Menu{
	
	SoundPlayer soundPlayer;

	
	public Menu() {
		soundPlayer = new SoundPlayer("/audio/menu_bgm.wav", true);
	}
	
	public void startBackgroundMusic() {
		if (!soundPlayer.isPlay()) {
//			soundPlayer.setIsPlay(true);
			soundPlayer.play();
		}
    }

    public void stopBackgroundMusic() {
    	if (soundPlayer.isPlay()) {
//    		soundPlayer.setIsPlay(false);
            soundPlayer.stop();
    	}
    }
	
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		int x = (Game.GAME_WIDTH - ENALOOKUP.getWidth()) / 2;
	    int y = (Game.GAME_HEIGHT - ENALOOKUP.getHeight()) / 2;
	    
	    g2d.drawImage(BG, 0,0,Game.GAME_WIDTH, Game.GAME_HEIGHT, null);
	    
	    g2d.drawImage(CONTINUE, (Game.GAME_WIDTH - 360)/2, 220, 360, 120, null);
	    
	    g2d.drawImage(ENALOOKUP, x, y, ENALOOKUP.getWidth(), ENALOOKUP.getHeight(), null);
	    drawCenteredText(g, 
	    		"Five Night At Wonderland", 
	    		new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 64),
	    		Color.LIGHT_GRAY,
	    		Color.WHITE, 
	    		Game.GAME_WIDTH, 
	    		240);
	    
	}
	
	public void checkMouseClick(int mouseX, int mouseY) {
		int buttonX = (Game.GAME_WIDTH - 360) / 2;
	    int buttonY = 220;
	    int buttonWidth = 360;
	    int buttonHeight = 120;

	    if (mouseX >= buttonX && mouseX <= buttonX + buttonWidth &&
	        mouseY >= buttonY && mouseY <= buttonY + buttonHeight) {
	        Game.gameState = Game.playerState;
	    }
	}
	
	public void drawCenteredText(
			Graphics g, 
			String text, 
			Font font,
			Color shadowColor,  
			Color color, 
			int containerWidth, 
			int containerHeight
	) {
		
		
	    Graphics2D g2d = (Graphics2D) g;

	    g2d.setFont(font);
	    g2d.setColor(shadowColor);

	    FontMetrics fontMetrics = g2d.getFontMetrics(font);
	    int textWidth = fontMetrics.stringWidth(text);
	    
	    int shadowX = (containerWidth - textWidth) / 2 + 4;  
	    int shadowY = (containerHeight - fontMetrics.getHeight()) / 2 + fontMetrics.getAscent() + 4;

	    g2d.drawString(text, shadowX, shadowY);
	    
	    g2d.setColor(color);

	    int x = (containerWidth - textWidth) / 2;
	    int y = (containerHeight - fontMetrics.getHeight()) / 2 + fontMetrics.getAscent();

	    g2d.drawString(text, x, y);
	}
}
