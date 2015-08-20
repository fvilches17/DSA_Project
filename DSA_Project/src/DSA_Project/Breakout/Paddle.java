package DSA_Project.Breakout;

import java.awt.Color;


public class Paddle extends Block {
    //FIELDS--------------------------------------------------------------------
    private int displacementSpeed;
    
    //CONSTRUCTOR
    public Paddle(int locationX, int locationY, int width, int height, Color color, int displacementSpeed) {
        super(locationX, locationY, width, height, color);
        this.displacementSpeed = displacementSpeed;
    }
    
    //GETTERS-------------------------------------------------------------------
    public int getDisplacementSpeed() {
        return displacementSpeed;
    }
    
    //SETTERS

    public void setDisplacementSpeed(int displacementSpeed) {
        this.displacementSpeed = displacementSpeed;
    }
    
    //METHODS-------------------------------------------------------------------
    public void moveRight() {
        this.setLocationX(this.getLocationX() + this.getDisplacementSpeed());
    }
    
    public void moveLeft() {
        this.setLocationX(this.getLocationX() - this.getDisplacementSpeed());
        
    }
}
