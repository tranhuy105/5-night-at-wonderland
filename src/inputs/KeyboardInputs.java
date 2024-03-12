package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import main.Game;
import main.GamePanel;

public class KeyboardInputs implements KeyListener{
	
	GamePanel gamePanel;
	
	public KeyboardInputs(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
//		System.out.println("Key pressed: " + e.getKeyCode());
		switch(e.getKeyCode()) {
		case KeyEvent.VK_A:
			gamePanel.getGame().getPlayerMap().moveView("left");
			break;
		case KeyEvent.VK_D:
			gamePanel.getGame().getPlayerMap().moveView("right");
			break;
		default: 
			break;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch(e.getKeyCode()) {
		case KeyEvent.VK_W:
//			gamePanel.getGame().getPlayer().setUp(false);
			break;
		case KeyEvent.VK_A:
//			gamePanel.getGame().getPlayer().setLeft(false);
			break;
		case KeyEvent.VK_S:
//			gamePanel.getGame().getPlayer().setDown(false);
			break;
		case KeyEvent.VK_D:
//			gamePanel.getGame().getPlayer().setRight(false);
			break;
		}
	}

}
