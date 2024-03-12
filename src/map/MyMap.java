package map;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import entities.Animatronics;
import main.Game;
import utils.SoundPlayer;

import static main.Game.GAME_HEIGHT;
import static main.Game.GAME_WIDTH;

import static utils.Constant.*;

public class MyMap {
	
	private static final double SCALE = 2;
	public static Tile[] tiles;
	
	private Tile gymIcon;
	private Tile tvIcon;
	private Tile iceScreamIcon;
	private Tile leftIcon;
	private Tile rightIcon;
	private Tile playerIcon;
	public static Tile[] icons;
	
	private Tile playerRoom;
	public static Tile activeRoom = null;
	
	private Animatronics emu;
	private Animatronics mafuyu;
	
	private SoundPlayer BGM;
	private SoundPlayer cameraChange;
	
	public MyMap() {
		tiles = new Tile[6];
		BGM = new SoundPlayer("/audio/camera-bg.wav", true);
		cameraChange = new SoundPlayer("/audio/camera-change.wav", false);
		
		gymIcon = new Tile("GYM",Game.GAME_WIDTH - 240,Game.GAME_HEIGHT - 160,48,40,LightOnImage.GYM);
		tvIcon = new Tile("TV",Game.GAME_WIDTH - 160,Game.GAME_HEIGHT - 170,48,40,LightOnImage.TV);
		iceScreamIcon = new Tile("ICESCREAM",Game.GAME_WIDTH - 80,Game.GAME_HEIGHT - 160,48,40,LightOnImage.ICE_SCREAM_SHOP);
		leftIcon = new Tile("LEFT",Game.GAME_WIDTH - 200,Game.GAME_HEIGHT - 96,48,32,LightOnImage.LEFT_ROOM);
		rightIcon = new Tile("RIGHT",Game.GAME_WIDTH - 120,Game.GAME_HEIGHT - 96,48,32,LightOnImage.RIGHT_ROOM);
		activeRoom = tvIcon;
		
		getTileMap();
		playerRoom = tiles[5];
		playerIcon = playerRoom;
		icons = new Tile[]{gymIcon, tvIcon, iceScreamIcon, leftIcon, rightIcon, playerIcon};
		
		botInit();
		
	}
	
	public void update() {
		emu.update();
		mafuyu.update();
	}
	
	private void getTileMap() {
			tiles[5] = new Tile();
			tiles[5].onImg = LightOnImage.PLAYER_BASE;
			tiles[5].offImg = LightOffImage.PLAYER_BASE;
			tiles[5].width = (int)(tiles[5].onImg.getWidth() * 5/4 * SCALE);
			tiles[5].height =(int)(tiles[5].onImg.getHeight() - 32);
			tiles[5].setX((GAME_WIDTH - tiles[5].width) / 2);
			tiles[5].setY(GAME_HEIGHT - tiles[5].height +16);
			tiles[5].name = "PLAYER";
	}
	
	private void botInit() {
		int size = 64;
		int startX = (Game.GAME_WIDTH -size) / 2;
		int startY = (Game.GAME_HEIGHT -size) / 2;
		
		emu = new Animatronics(startX-160, startY, 240, 240, "emu");
		mafuyu = new Animatronics(startX +40, startY, 240, 240, "mafuyu");
	}
	
	public void checkMouseClick(int mouseX, int mouseY) {		
	    for (Tile tile : icons) {    	
	    	if (isWithinTileBounds(mouseX, mouseY, tile)) {
	    		if (tile != playerIcon) {
	    			cameraChange.play();
	    			cameraChange.setIsPlay(false);
	            	if (tile == activeRoom) {
//	            		activeRoom = null;
	            	} else {
	            		activeRoom = tile;
	            	}
	            } else {
	            	activeRoom = null;
	            	Game.gameState = Game.playerState;
	            }
	    	}
	    }
	}

	private boolean isWithinTileBounds(int mouseX, int mouseY, Tile tile) {
	    return mouseX >= tile.getX() && mouseX <= (tile.getX() + tile.width) &&
	           mouseY >= tile.getY() && mouseY <= (tile.getY() + tile.height);
	}
	
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g.create();
		
		g2d.setColor(Color.DARK_GRAY);
		g2d.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);

		if (activeRoom != null) {
			double scale = 0.9;

			int newWidth = (int) (Game.GAME_WIDTH * scale);
			int newHeight = (int) (Game.GAME_HEIGHT * scale);

			int x = (Game.GAME_WIDTH - newWidth) / 2;
			int y = (Game.GAME_HEIGHT - newHeight) / 2;

			g2d.drawImage(activeRoom.onImg, x, y, newWidth, newHeight, null);

			mafuyu.draw(g2d, activeRoom);
			emu.draw(g2d, activeRoom);
		}
		
//		g2d.drawImage(LightOnImage.CAM,0,0,Game.GAME_WIDTH, Game.GAME_HEIGHT , null);
		
		g2d.setColor(Color.decode("#f23869"));
		g2d.fillRect(playerRoom.getX(), playerRoom.getY(), playerRoom.width, playerRoom.height-36);
//		g2d.setColor(Color.LIGHT_GRAY);
		float thickness = 3f; 
		g2d.setStroke(new BasicStroke(thickness));
		
		g2d.setColor(Color.decode("#fdf1bc"));
		g2d.drawRect(gymIcon.getX(), gymIcon.getY(), gymIcon.width, gymIcon.height);
		g2d.drawRect(tvIcon.getX(), tvIcon.getY(), tvIcon.width, tvIcon.height);
		g2d.drawRect(iceScreamIcon.getX(), iceScreamIcon.getY(), iceScreamIcon.width, iceScreamIcon.height);
		g2d.drawRect(leftIcon.getX(), leftIcon.getY(), leftIcon.width, leftIcon.height);
		g2d.drawRect(rightIcon.getX(), rightIcon.getY(), rightIcon.width, rightIcon.height);
		g2d.fillRect(Game.GAME_WIDTH - 143, Game.GAME_HEIGHT - 88, 16, 16);
		
		g2d.dispose();
	}
	
	public void startBackgroundMusic() {
		if (!BGM.isPlay()) {
			BGM.play();
		}
    }

    public void stopBackgroundMusic() {
    	if (BGM.isPlay()) {
            BGM.stop();
    	}
    }
}
