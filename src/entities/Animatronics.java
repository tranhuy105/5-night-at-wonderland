package entities;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import main.Game;
import map.MyMap;
import map.PlayerMap;
import map.Tile;
import utils.SoundPlayer;
import utils.Constant.BOT;
import utils.Constant.ROOM;

public class Animatronics {
	
	private String name;
	private int x;
	private int y;
	private int width;
	private int height;
	private Tile currentRoom;
	private int failTime = 0 ;
	private long lastTeleportTime;

	private int maxFailTime;
	private BufferedImage image;
	private long teleportInterval;
	
	float hardLevel = 0.8f;
	
	public static boolean IS_EMU_IN_RIGHT_ROOM;
    public static boolean IS_EMU_IN_LEFT_ROOM;
    public static boolean IS_MAFUYU_IN_RIGHT_ROOM;
    public static boolean IS_MAFUYU_IN_LEFT_ROOM;
	
    private SoundPlayer random1;
    private SoundPlayer random2;
    private SoundPlayer random3;
	
	public Animatronics(int x, int y, int width, int height, String type) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		init(type);
		this.currentRoom = MyMap.icons[1];
        this.lastTeleportTime = System.currentTimeMillis();
        this.name = type;
        
        random1 = new SoundPlayer("/audio/random1.wav", false);
        random2 = new SoundPlayer("/audio/random2.wav", false);
        random3 = new SoundPlayer("/audio/random3.wav", false);
	}
	
	private void init(String type) {
		if (type == "emu") {
			this.image = BOT.EMU;
			this.maxFailTime = 3;
			this.teleportInterval = 9000;
		} else if (type == "mafuyu") {
			this.image = BOT.MAFUYU;
			this.maxFailTime = 2;
			this.teleportInterval = 12000;
		}
	}
	
	 public void update() {
		 	long currentTime = System.currentTimeMillis();
	        if (currentTime - lastTeleportTime >= teleportInterval) {
	            teleportToRandomRoom();
	            lastTeleportTime = currentTime;
	        }
	    }

	 private void teleportToRandomRoom() {
		    Random random = new Random();
		    
		    if (random.nextDouble() <= hardLevel || this.failTime == maxFailTime) {
		        int currentRoomIndex = Arrays.asList(MyMap.icons).indexOf(this.currentRoom);
		        
		        int randomRoomIndex;
		        do {
		            randomRoomIndex = random.nextInt(MyMap.icons.length - 1);
		        } while (randomRoomIndex == currentRoomIndex);
		        
		        int randomSound = random.nextInt(3);
		        if (randomSound == 0) {
		        	random1.play();
		        	random1.setIsPlay(false);
		        } else if (randomSound == 1) {
		        	random2.play();
		        	random2.setIsPlay(false);
		        } else {
		        	random3.play();
		        	random3.setIsPlay(false);
		        }
		        
		        this.failTime = 0;
		        System.out.println(name+" go to room: " + MyMap.icons[randomRoomIndex].name+" from room: " +currentRoom.name);
		        moveTo(MyMap.icons[randomRoomIndex]);
		        this.currentRoom = MyMap.icons[randomRoomIndex];
		        updateRoomVariables();
		    } else {
		    	System.out.println(name+" fail to move");
		    	this.failTime++;
		    }
		}
	
	public void teleport(int targetX, int targetY) {
		if (currentRoom == MyMap.icons[3] && PlayerMap.isLeftDoorOpen) {
			System.out.println("you lose");
			Game.gameState = Game.gameoverState;
		}
		if (currentRoom == MyMap.icons[4] && PlayerMap.isRightDoorOpen) {
			System.out.println("you lose");
			Game.gameState = Game.gameoverState;
		}
		
		x = targetX;
		y = targetY;
    }

	
	public void moveTo(Tile room) {
		int centerX = Game.GAME_WIDTH / 2;
		int centerY = Game.GAME_HEIGHT / 2;

		if (name.equals("mafuyu")) { 
		    teleport(centerX - width / 2, centerY - height / 2);
		} else {
		    teleport(centerX + width / 2, centerY - height / 2);
		}
    }
	
	public void draw(Graphics2D g2d, Tile room) {
		if (room == currentRoom) {
			if (currentRoom == MyMap.icons[ROOM.LEFT]) {
				if (name.equals("emu")) {
					g2d.drawImage(BOT.EMULEFT, 0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT, null);
				} else {
					g2d.drawImage(BOT.MAFUYULEFT, 0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT, null);
				}
			} else if (currentRoom == MyMap.icons[ROOM.RIGHT]) {
				if (name.equals("emu")) {
					g2d.drawImage(BOT.EMURIGHT, 0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT, null);
				} else {
					g2d.drawImage(BOT.MAFUYURIGHT, 0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT, null);
				}
			} else {
				g2d.drawImage(image, x, y, width, height, null);
			}
		}
	}
	
	private void updateRoomVariables() {
		if (name == "emu") {
			IS_EMU_IN_RIGHT_ROOM = currentRoom == MyMap.icons[ROOM.RIGHT];
	        IS_EMU_IN_LEFT_ROOM = currentRoom == MyMap.icons[ROOM.LEFT];
		}
		else if (name == "mafuyu"){	
	        IS_MAFUYU_IN_RIGHT_ROOM = currentRoom == MyMap.icons[ROOM.RIGHT];
	        IS_MAFUYU_IN_LEFT_ROOM = currentRoom == MyMap.icons[ROOM.LEFT];
		}
        
	}
}
