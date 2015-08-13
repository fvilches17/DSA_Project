package DSA_Project.Breakout;

import java.awt.Color;
import java.io.Serializable;

public class Ball implements Serializable {
    //FIELDS--------------------------------------------------------------------
    private double positionX;
    private double positionY;
    private double velocityX;
    private double velocityY;
    private double radius;
    private double deltaT;
    private final Color DEFAULT_COLOR = Color.MAGENTA;
    private final int DIAMETER = 50;
    
    //CONSTRUCTOR---------------------------------------------------------------
    public Ball() {
        
    }
    
    //GETTERS-------------------------------------------------------------------
    public double getPositionX() {
        return positionX;
    }

    public double getPositionY() {
        return positionY;
    }

    double getVelocityX() {
        return velocityX;
    }

    public double getVelocityY() {
        return velocityY;
    }

    public double getRadius() {
        return radius;
    }

    public double getDeltaT() {
        return deltaT;
    }

    public Color getDEFAULT_COLOR() {
        return DEFAULT_COLOR;
    }

    public int getDIAMETER() {
        return DIAMETER;
    }

    //SETTERS-------------------------------------------------------------------
    public void setPositionX(double positionX) {
        this.positionX = positionX;
    }

    public void setPositionY(double positionY) {
        this.positionY = positionY;
    }

    public void setVelocityX(double velocityX) {
        this.velocityX = velocityX;
    }

    public void setVelocityY(double velocityY) {
        this.velocityY = velocityY;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public void setDeltaT(double deltaT) {
        this.deltaT = deltaT;
    }

    //CLASS METHODS-------------------------------------------------------------
    public void move() {
        positionX += deltaT * velocityX;
        positionY += deltaT * velocityY;
    }
    
    @Override
    public String toString() { //Used for debugging purposes
        return "position(" + positionX + ", " + positionY + "), "
                + "velocity(" + velocityX + ", " + velocityY + "), "
                + "deltaT(" + deltaT + "), radius(" + radius + ")";
    }
}
