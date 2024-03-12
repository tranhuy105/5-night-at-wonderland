package main;

import java.awt.Graphics;

import entities.Animatronics;
import map.FailScreen;
import map.MyMap;
import map.PlayerMap;

public class Game implements Runnable {
	
	private Thread gameThread;
//	private GameWindow gameWindow;
	private GamePanel gamePanel;
	private MyMap map;
	private Menu menu;
	private PlayerMap playerMap;
	private FailScreen failScreen;
	
	private final int FPS_SET = 120;
	private final int UPS_SET = 200;
	
	
	public final static int GAME_WIDTH = 1200;
	public final static int GAME_HEIGHT = 700;
	
	public static final int menuState = 0;
	public static final int playerState = 1;
	public static final int cameraState = 2;
	public static final int gameoverState = 3;

	public static int gameState = menuState;
	
	public Game() {
		initClasses();
		gamePanel = new GamePanel(this);
		new GameWindow(gamePanel);
		gamePanel.requestFocus();
		
		startGameLoop();
	}
	
	private void initClasses() {
		map = new MyMap();
		menu = new Menu();
		playerMap = new PlayerMap();
		failScreen = new FailScreen(this);
	}

	public void update() {
		if (menu == null || map == null || playerMap == null || failScreen == null) return;
		if (gameState != menuState && gameState != gameoverState) {	
			map.update();
			playerMap.update();
			playerMap.startBackgroundMusic();
		} else {
			playerMap.stopBackgroundMusic();
		}
	
		
		if (gameState == cameraState) {
			map.startBackgroundMusic();
		} else {
			map.stopBackgroundMusic();
		}
		
		
		if (gameState == gameoverState) {
			failScreen.update();
			failScreen.startBackgroundMusic();
		} else {
			failScreen.stopBackgroundMusic();
		}
		
		if (gameState == menuState) {
            menu.startBackgroundMusic();
        } else {
            menu.stopBackgroundMusic();
        }
	}
	
	public void resetGame() {
		Animatronics.IS_EMU_IN_LEFT_ROOM = false;
		Animatronics.IS_EMU_IN_RIGHT_ROOM = false;
		Animatronics.IS_MAFUYU_IN_LEFT_ROOM = false;
		Animatronics.IS_MAFUYU_IN_RIGHT_ROOM = false;
		failScreen.stopBackgroundMusic();
	    gameState = menuState;
	    map = null;
	    playerMap = null;
	    menu = null;
	    failScreen = null;
	    initClasses();
	    gamePanel.requestFocus();
	}
	
	public PlayerMap getPlayerMap () {
		return this.playerMap;
	}
	
	public void render (Graphics g) {
		if (gameState == cameraState) {			
			map.draw(g);
		} else if (gameState == menuState) {
			menu.draw(g);
		} else if (gameState == playerState) {
			playerMap.draw(g);
		} else if (gameState == gameoverState) {
			failScreen.draw(g);
		}
	} 
	
	public void checkMouse(int mouseX, int mouseY) {
		if (gameState == cameraState) {
			map.checkMouseClick(mouseX, mouseY);
		} else if (gameState == menuState) {
			menu.checkMouseClick(mouseX, mouseY);
		} else if (gameState == playerState) {
			playerMap.checkMouseClick(mouseX, mouseY);
		} else {
			failScreen.checkMouseClick(mouseX, mouseY);
		}
	}
	
	private void startGameLoop() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	public void windowFocusLost() {

	}
	
	// gameLoop thread start this method will be execute
	@Override
	public void run() {
		
		double timePerFrame = 1000000000.0 / FPS_SET;		
		double timePerUpdate = 1000000000.0 / UPS_SET;
		
		long previousTime = System.nanoTime();
		
		int frames = 0;
		int updates = 0;
		long lastCheck = System.currentTimeMillis();
		
		double deltaU = 0;
		double deltaF = 0;
		
		while (true) {
			long currentTime = System.nanoTime();
			
			deltaU += (currentTime - previousTime) / timePerUpdate;
			deltaF += (currentTime - previousTime) / timePerFrame;
			previousTime = currentTime;
			
			if (deltaU >= 1) {
				update();
				updates++;
				deltaU--;
			}
			
			if (deltaF >= 1) {
				gamePanel.repaint();
				frames++;
				deltaF--;
			}
			
			if (System.currentTimeMillis() - lastCheck >= 1000) {
				lastCheck = System.currentTimeMillis();
//				System.out.println("FPS: "+frames+ "| UPS: "+updates);
				frames = 0;
				updates = 0;
			}
		}
	}
}
