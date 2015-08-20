
package DSA_Project.Breakout;

import java.awt.Color;

public abstract class Block {
    //FIELDS--------------------------------------------------------------------
    private int locationX, locationY, width, height;
    private Color color;
    private boolean isVisible;
    
    //CONSTRUCTOR
    public Block(int locationX, int locationY, int width, int height, Color color) {
        this.locationX = locationX;
        this.locationY = locationY;
        this.width = width;
        this.height = height;
        this.color = color;
        this.isVisible = true;
    }
    
    //GETTERS-------------------------------------------------------------------
    public int getLocationX() {
        return locationX;
    }

    public int getLocationY() {
        return locationY;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Color getColor() {
        return color;
    }

    public boolean isVisible() {
        return isVisible;
    }
    
    //SETTERS-------------------------------------------------------------------

    public void setLocationX(int locationX) {
        this.locationX = locationX;
    }

    public void setLocationY(int locationY) {
        this.locationY = locationY;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setIsVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }
}
