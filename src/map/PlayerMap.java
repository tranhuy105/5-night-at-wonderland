package map;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import static utils.Constant.PLAYERSCENE.*;

import entities.Animation;
import entities.Animatronics;
import main.Game;
import utils.Constant.BOT;
import utils.Constant.ROOM;
import utils.SoundPlayer;

public class PlayerMap {

	
	private int battery = 100;
    private final int DEFAULT_BATTERY_DECREASE_INTERVAL = 4 * 200; // 10 seconds
    private final int DOOR_BATTERY_DECREASE_INTERVAL = 2 * 200; // 6 seconds 

	public static boolean isLeftDoorOpen = true;
	public static boolean isRightDoorOpen = true;
	
	private final int CAMERA_X = Game.GAME_WIDTH - 160;
	private final int CAMERA_Y = Game.GAME_HEIGHT - 160;
	private final int CAMERA_SIZE = 120;
	
	// view control
	public int VIEW_X_SPEED = 32;
	public final double VIEW_SCALE = 1.5;
	public final int BG_WIDTH = (int) (BG.getWidth() * VIEW_SCALE);
	public int VIEW_X = -192;
	
	private final int LEFT_DOOR_X = 360;
	private final int RIGHT_DOOR_X = BG_WIDTH - 360 - 65;
	private final int DOOR_Y = 190;
	private final int DOOR_WIDTH = 60;
	private final int DOOR_HEIGHT = 300;
	
	private String next;
	private int counter = 0;
    private final int switchInterval = 12 * 200; 
    
    // smoother the animation
    public final double INTERPOLATION_FACTOR = 0.1;
    private double targetViewX = VIEW_X;
    
	private BufferedImage[] markFrames = {
			MARK_1,
			MARK_2
	};
	
	private BufferedImage[] heartFrames = {
			HEART_1,
			HEART_2
	};
	
	private BufferedImage[] nhapnhayFrames = {
			BG,
			BGM100,
			BG20,
			BGM20,
			BG20,
			BGM20,
			BGM50,
			BG
	};
	
	private BufferedImage[] samsetFrames = {
			BG50,
			BG100,
			BG100,
			BG100,
			BG20,
			BG100,
			BG100,
			BG50,
			BG20,
			BG20,
			BG
	};
	
	private Animation samset;
	private Animation nhapnhay;
	private Animation heartAni;
	private Animation markAni;
	
	private SoundPlayer door;
	private SoundPlayer BGM;
	private SoundPlayer powerOff;
	
	public PlayerMap() {
		markAni = new Animation(markFrames, 50);
		heartAni = new Animation(heartFrames, 50);
		samset = new Animation(samsetFrames, 10);
		nhapnhay = new Animation(nhapnhayFrames, 15);
		samset.setPlaying(false);
		nhapnhay.setPlaying(true);
		door = new SoundPlayer("/audio/door.wav", false);
		BGM = new SoundPlayer("/audio/game-bgm.wav", true);
		powerOff = new SoundPlayer("/audio/power_off.wav", false);
	}
	
	public void moveView(String type) {
		if (type == "left" && VIEW_X < -64) {
			targetViewX += VIEW_X_SPEED;
		} else if (type == "right" && VIEW_X > -396){
			targetViewX -= VIEW_X_SPEED;
		}
	}
	
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		if (battery == 0) {
			g2d.drawImage(BGM100, VIEW_X, 0, BG_WIDTH, Game.GAME_HEIGHT, null);
		}
		else if (!samset.isPlaying() && !nhapnhay.isPlaying()) {
	        g2d.drawImage(BG, VIEW_X, 0, BG_WIDTH, Game.GAME_HEIGHT, null);
	    } else if (samset.isPlaying()){
	        g2d.drawImage(samset.getCurrentFrame(), VIEW_X, 0, BG_WIDTH, Game.GAME_HEIGHT, null);
	    } else {
	    	g2d.drawImage(nhapnhay.getCurrentFrame(), VIEW_X, 0, BG_WIDTH, Game.GAME_HEIGHT, null);
	    }
		
		g2d.setColor(Color.RED);
		
		// door 1
//		g2d.drawRect(LEFT_DOOR_X + VIEW_X, DOOR_Y, DOOR_WIDTH, DOOR_HEIGHT);
		if (isLeftDoorOpen) {
			g2d.drawImage(LEFT_DOOR_OPEN, VIEW_X, 0, BG_WIDTH, Game.GAME_HEIGHT, null);
			g2d.drawImage(markAni.getCurrentFrame(), LEFT_DOOR_X + VIEW_X - 60, DOOR_Y + 150, 45,45, null);
		} else {
			g2d.drawImage(heartAni.getCurrentFrame(), LEFT_DOOR_X + VIEW_X - 60, DOOR_Y + 150, 45,45, null);
		}
		
		// door 2
//		g2d.drawRect(RIGHT_DOOR_X + VIEW_X, DOOR_Y, DOOR_WIDTH, DOOR_HEIGHT);
		if (isRightDoorOpen) {
			g2d.drawImage(RIGHT_DOOR_OPEN, VIEW_X, 0, BG_WIDTH, Game.GAME_HEIGHT, null);
			g2d.drawImage(markAni.getCurrentFrame(), RIGHT_DOOR_X + VIEW_X + 80, DOOR_Y+ 150, 45,45, null);
		} else {
			g2d.drawImage(heartAni.getCurrentFrame(), RIGHT_DOOR_X + VIEW_X + 80, DOOR_Y+ 150, 45,45, null);
		}
		
		
		// EMU NENE
		if (isLeftDoorOpen && Animatronics.IS_EMU_IN_LEFT_ROOM) {
			g2d.drawImage(BOT.EMU, LEFT_DOOR_X + VIEW_X, DOOR_Y, DOOR_WIDTH, DOOR_HEIGHT, null);
		}
		if (isRightDoorOpen && Animatronics.IS_EMU_IN_RIGHT_ROOM) {
			g2d.drawImage(BOT.EMU,RIGHT_DOOR_X + VIEW_X, DOOR_Y, DOOR_WIDTH, DOOR_HEIGHT,null);
		}
		if (isLeftDoorOpen && Animatronics.IS_MAFUYU_IN_LEFT_ROOM) {
			g2d.drawImage(BOT.MAFUYU, LEFT_DOOR_X + VIEW_X, DOOR_Y, DOOR_WIDTH, DOOR_HEIGHT, null);
		}
		if (isRightDoorOpen && Animatronics.IS_MAFUYU_IN_RIGHT_ROOM) {
			g2d.drawImage(BOT.MAFUYU,RIGHT_DOOR_X + VIEW_X, DOOR_Y, DOOR_WIDTH, DOOR_HEIGHT,null);
		}
		
		
		// camera
		if (battery > 0) {
			g2d.setColor(Color.LIGHT_GRAY);
			g2d.fillRect(CAMERA_X+4, CAMERA_Y+4, CAMERA_SIZE, CAMERA_SIZE);
			g2d.setColor(Color.WHITE);
			g2d.fillRect(CAMERA_X, CAMERA_Y, CAMERA_SIZE, CAMERA_SIZE);
			g2d.drawImage(HOMECAMERA, CAMERA_X, CAMERA_Y,CAMERA_SIZE,CAMERA_SIZE, null);
		} else {
			g2d.drawImage(HOMECAMERAOFF, CAMERA_X, CAMERA_Y,CAMERA_SIZE,CAMERA_SIZE, null);
		}
		
		
		// ui info
		g2d.setFont(new Font("MV Boli", Font.BOLD, 24));
		g2d.setColor(Color.LIGHT_GRAY);
		g2d.drawString("Left Door: "+(isLeftDoorOpen ? "Open" : "Lock"), CAMERA_X -96 +2, CAMERA_Y-48+2);
		g2d.setColor(Color.WHITE);
		g2d.drawString("Left Door: "+(isLeftDoorOpen ? "Open" : "Lock"), CAMERA_X -96, CAMERA_Y-48);
		
		g2d.setFont(new Font("MV Boli", Font.BOLD, 24));
		g2d.setColor(Color.LIGHT_GRAY);
		g2d.drawString("Right Door: "+(isRightDoorOpen ? "Open" : "Lock"), CAMERA_X -96 +2, CAMERA_Y-90+2);
		g2d.setColor(Color.WHITE);
		g2d.drawString("Right Door: "+(isRightDoorOpen ? "Open" : "Lock"), CAMERA_X -96, CAMERA_Y-90);
		
		g2d.setFont(new Font("MV Boli", Font.BOLD, 24));
		g2d.setColor(Color.LIGHT_GRAY);
		g2d.drawString("Battery: "+battery + "%", 96 +2, CAMERA_Y+2 +80);
		g2d.setColor(Color.WHITE);
		g2d.drawString("Battery: "+battery + "%", 96, CAMERA_Y + 80);
		
		g2d.dispose();
	}
	
	public void update() {

		VIEW_X += (targetViewX - VIEW_X) * INTERPOLATION_FACTOR;
		
		counter++;
		
		nhapnhay.update();
		samset.update();
		if (samset.isPlaying() && counter == samsetFrames.length * samset.frameDelay * 2) {
			samset.resetFrame();
			samset.setPlaying(false);
			counter= 0;
			next = "nhapnhay";
		}
		
		if (nhapnhay.isPlaying() && counter == nhapnhayFrames.length * nhapnhay.frameDelay * 2) {
			nhapnhay.resetFrame();
			nhapnhay.setPlaying(false);
			counter= 0;
			next = "samset";
		}
		
		if (counter == switchInterval) {
			if (next == "samset") {
				samset.setPlaying(true);
			} else if (next == "nhapnhay") {
				nhapnhay.setPlaying(true);
			}
			counter = 0;
		}
		
        
        // door animation
		markAni.update();
		heartAni.update();
		
		// battery logic
		if (battery == 0) {
			if (!isLeftDoorOpen) isLeftDoorOpen = true;
			if (!isRightDoorOpen) isRightDoorOpen = true;
			return;
		}

		if (counter != 0 && counter % DEFAULT_BATTERY_DECREASE_INTERVAL == 0) {
	        decreaseBattery();
	    }

	    // Update battery based on door events
	    if (!isLeftDoorOpen) {
	        if (counter != 0 && counter % DOOR_BATTERY_DECREASE_INTERVAL == 0) {
	            decreaseBattery();
	        }
	    }
	    if (!isRightDoorOpen) {
	        if (counter != 0 && counter % DOOR_BATTERY_DECREASE_INTERVAL == 0) {
	            decreaseBattery();
	        }
	    }
	}
	
	public void checkMouseClick(int mouseX, int mouseY) {
		if (mouseX >= LEFT_DOOR_X + VIEW_X 
				&& mouseX <= LEFT_DOOR_X + VIEW_X + DOOR_WIDTH 
				&& mouseY >= DOOR_Y 
				&& mouseY <= DOOR_Y + DOOR_HEIGHT) {
			clickLeftDoor();
		}
		
		if (mouseX >= RIGHT_DOOR_X + VIEW_X  
				&& mouseX <= RIGHT_DOOR_X + VIEW_X + DOOR_WIDTH 
				&& mouseY >= DOOR_Y 
				&& mouseY <= DOOR_Y + DOOR_HEIGHT) {
			clickRightDoor();
		}
		
		if (mouseX >= CAMERA_X 
				&& mouseX <= CAMERA_X + CAMERA_SIZE 
				&& mouseY >= CAMERA_Y 
				&& mouseY <= CAMERA_Y + CAMERA_SIZE) {
			if (battery > 0) {
				Game.gameState = Game.cameraState;
				MyMap.activeRoom = MyMap.icons[ROOM.TV];
			}
		}
	}
	
	
	private void clickLeftDoor() {
		if (battery > 0) {			
			this.isLeftDoorOpen = !this.isLeftDoorOpen;
			door.play();
			door.setIsPlay(false);
		}
	}
	
	private void clickRightDoor() {
		if (battery > 0) {
			this.isRightDoorOpen = !this.isRightDoorOpen;
			door.play();
			door.setIsPlay(false);
		}
	}
	
	private void decreaseBattery() {
	    if (battery > 0) {
	        battery--;
	        if (battery == 0) {
	        	powerOff.play();
	        	Game.gameState = Game.playerState;
	        }
	    }
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
