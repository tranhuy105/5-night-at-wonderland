package map;

import java.awt.image.BufferedImage;

public class Tile {  
    public BufferedImage onImg;
    public BufferedImage offImg;
    public boolean isOn = false;
    public int width;
    public int height;
    private int x;
    private int y; 
    public String name;

    public Tile() {
        
    }
    
    public Tile(String name, int x, int y, int w, int h, BufferedImage onImg) {
    	this.onImg = onImg;
    	this.name = name;
    	this.x = x;
    	this.y = y;
    	this.width = w;
    	this.height = h;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    public void setX(int x) {
    	this.x = x;
    }
    
    public void setY(int y)  {
    	this.y = y;
    }

 
}
