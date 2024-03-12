package utils;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Constant {
	private static BufferedImage loadImage(String path) {
        try {
            return ImageIO.read(Constant.class.getResourceAsStream(path));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
	
	public static class LightOnImage {
		public static final BufferedImage CAM;
        public static final BufferedImage TV;
        public static final BufferedImage GYM;
        public static final BufferedImage ICE_SCREAM_SHOP;
        public static final BufferedImage RIGHT_ROOM;
        public static final BufferedImage LEFT_ROOM;
        public static final BufferedImage PLAYER_BASE;

        static {
        	CAM = loadImage("/map/cam.png");
            TV = loadImage("/map/Tv.png");
            GYM = loadImage("/map/Gym.png");
            ICE_SCREAM_SHOP = loadImage("/map/Ice_Scream_Shop.png");
            RIGHT_ROOM = loadImage("/map/Shooting_Range.png");
            LEFT_ROOM = loadImage("/map/Shooting_Range_Flip.png");
            PLAYER_BASE = loadImage("/map/Player_Base.png");
        }
    }
	
	public static class LightOffImage {
		public static final BufferedImage TV;
        public static final BufferedImage GYM;
        public static final BufferedImage ICE_SCREAM_SHOP;
        public static final BufferedImage RIGHT_ROOM;
        public static final BufferedImage LEFT_ROOM;
        public static final BufferedImage PLAYER_BASE;

        static {
            TV = loadImage("/map/TvOff.png");
            GYM = loadImage("/map/GymOff.png");
            ICE_SCREAM_SHOP = loadImage("/map/Ice_Scream_ShopOff.png");
            RIGHT_ROOM = loadImage("/map/Shooting_RangeOff.png");
            LEFT_ROOM = loadImage("/map/Shooting_Range_FlipOff.png");
            PLAYER_BASE = loadImage("/map/Player_BaseOff.png");
        }
	}
	
	public static class BOT {
		public static final BufferedImage EMU;
		public static final BufferedImage EMULEFT;
		public static final BufferedImage EMURIGHT;
        public static final BufferedImage MAFUYU;
        public static final BufferedImage MAFUYULEFT;
        public static final BufferedImage MAFUYURIGHT;
        
        static {
        	EMU = loadImage("/animatronics/emu.png");
        	EMULEFT = loadImage("/animatronics/eLeft.jpg");
        	EMURIGHT = loadImage("/animatronics/eRight.jpg");
        	MAFUYU = loadImage("/animatronics/mafuyu.png");
        	MAFUYULEFT = loadImage("/animatronics/mleft.png");
        	MAFUYURIGHT = loadImage("/animatronics/mright.jpg");
        }
	}

	public static class ROOM {
		public static final int TV = 1;
		public static final int GYM = 0;
		public static final int ICESCREAM = 2;
		public static final int RIGHT = 4;
		public static final int LEFT = 3;
		public static final int PLAYER = 5;
		
	}
	
	public static class MENU {
		public static final BufferedImage ENALOOKUP;
		public static final BufferedImage BG;
		public static final BufferedImage CONTINUE;
		
		static {
			ENALOOKUP = loadImage("/menu/ena-lookup.png");
			BG = loadImage("/menu/cute-bg.jpg");
			CONTINUE = loadImage("/menu/continue.png");
		}
	}
	
	public static class PLAYERSCENE {
		public static final BufferedImage BG;
		public static final BufferedImage BGM100;
		public static final BufferedImage BGM50;
		public static final BufferedImage BGM20;
		public static final BufferedImage BG20;
		public static final BufferedImage BG50;
		public static final BufferedImage BG100;
		
		public static final BufferedImage MARK_1;
		public static final BufferedImage MARK_2;
		public static final BufferedImage HEART_1;
		public static final BufferedImage HEART_2;
		public static final BufferedImage HOMECAMERA;
		public static final BufferedImage HOMECAMERAOFF;
		
		public static final BufferedImage LEFT_DOOR_OPEN;
		public static final BufferedImage RIGHT_DOOR_OPEN;
		
		static {
			BG = loadImage("/player/player_base.png");
			BGM100 = loadImage("/player/player_base-100.png");
			BGM50 = loadImage("/player/player_base-50.png");
			BGM20 = loadImage("/player/player_base-20.png");
			BG20 = loadImage("/player/player_base20.png");
			BG50 = loadImage("/player/player_base50.png");
			BG100 = loadImage("/player/player_base100.png");
			
			MARK_1 = loadImage("/player/chanthan1.png");
			MARK_2 = loadImage("/player/chanthan2.png");
			HEART_1 = loadImage("/player/traitim1.png");
			HEART_2 = loadImage("/player/traitim2.png");
			HOMECAMERA = loadImage("/player/home-camera.png");
			HOMECAMERAOFF = loadImage("/player/home-camera-off.png");
			
			LEFT_DOOR_OPEN = loadImage("/player/left_door_open.png");
			RIGHT_DOOR_OPEN = loadImage("/player/right_door_open.png");
		}
	}
	
	public static class GAMEOVER {
		public static final BufferedImage EMUJUMPSCARE;
		public static final BufferedImage EMUJUMPSCAREM30;
		public static final BufferedImage EMUJUMPSCAREM60;
		public static final BufferedImage EMUJUMPSCARE20;
		public static final BufferedImage EMUJUMPSCARE40;
		public static final BufferedImage GAMEOVERBUTTON;
		
		static {
			EMUJUMPSCARE = loadImage("/gameover/emu-jumpscare.png");
			EMUJUMPSCAREM30 = loadImage("/gameover/emu-jumpscare-30.png");
			EMUJUMPSCAREM60 = loadImage("/gameover/emu-jumpscare-60.png");
			EMUJUMPSCARE20 = loadImage("/gameover/emu-jumpscare20.png");
			EMUJUMPSCARE40 = loadImage("/gameover/emu-jumpscare40.png");
			GAMEOVERBUTTON = loadImage("/gameover/gameover.png");
		}
	}
	
	
}
