package DSA_Project.Breakout;

import java.awt.Color;


public class Paddle extends Block {
    //FIELDS--------------------------------------------------------------------
    //private static final Color COLOR = Color.DARK_GRAY;
    private static final int DISPLACEMENT = 15;
    
    //CONSTRUCTOR
    public Paddle(int locationX, int locationY, int width, int height, Color color) {
        super(locationX, locationY, width, height, color);
    }
    
    //METHODS-------------------------------------------------------------------
    public void moveRight() {
        this.setLocationX(this.getLocationX() + DISPLACEMENT);
    }
    
    public void moveLeft() {
        this.setLocationX(this.getLocationX() - DISPLACEMENT);
        
    }
}
