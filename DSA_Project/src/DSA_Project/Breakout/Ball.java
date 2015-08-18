package DSA_Project.Breakout;

import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Ball implements Runnable {
    //FIELDS--------------------------------------------------------------------
    private double positionX;
    private double positionY;
    private double velocityX;
    private double velocityY;
    private double radius;
    private double deltaT;
    private boolean isRunning;
    private Color color;
    private Thread thread;
    private Game game;
    
    //CONSTRUCTOR---------------------------------------------------------------`
    public Ball(double positionX, double positionY, double velocityX, double velocityY, double radius, double deltaT, Color color, Game game) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.velocityX = velocityX; 
        this.velocityY = velocityY;
        this.radius = radius;
        this.deltaT = deltaT;
        this.color = color;
        this.game = game;
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

    public Color getColor() {
        return color;
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

    public void setColor(Color color) {
        this.color = color;
    }

    //CLASS METHODS-------------------------------------------------------------
    public void move() {
        positionX += deltaT * velocityX;
        positionY += deltaT * velocityY;
    }
    
    public void start() {
        thread = new Thread(this);
        isRunning = true;
        thread.start();
    }

    public void stop() {
        isRunning = false;
        thread = null;
    }
    
    public boolean isRunning() {
        return this.isRunning;
    }
    
    public void sleep(long milliseconds) {
        try {
            this.thread.sleep(milliseconds);
        } catch (InterruptedException ex) {
            Logger.getLogger(Ball.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    @Override
    public String toString() { //Used for debugging purposes
        return "position(" + positionX + ", " + positionY + "), "
                + "velocity(" + velocityX + ", " + velocityY + "), "
                + "deltaT(" + deltaT + "), radius(" + radius + ")";
    }

    @Override
    public void run() {
       while(isRunning) {
           this.move();
           //Bounce if this.touches paddle
            if ((this.getPositionX() + this.getRadius() > this.game.getPaddle().getLocationX())
                    && (this.getPositionX() < this.game.getPaddle().getLocationX() + this.game.getPaddle().getWidth())
                    && (this.getPositionY() + this.getRadius() > this.game.getPaddle().getLocationY())
                    && (this.getPositionY() < this.game.getPaddle().getLocationY())) {
                this.setVelocityY(this.getVelocityY() * -1);
                this.game.setIsBallGoingDown(false);
            }

            //Bounce if this.touches a side walls
            if ((this.getPositionX() + this.getRadius() > game.getWidth()) || (this.getPositionX() < 0)) {
                this.setVelocityX(this.getVelocityX() * -1);
            }

            //Bounce if this.touches roof
            if (this.getPositionY() < 0) {
                this.setVelocityY(this.getVelocityY() * -1);
                game.setIsBallGoingDown(true);
            }

            //Bounce if this.touches bottom of brick
            if (!this.game.IsBallGoingDown()) {
                for (Brick brick : this.game.getBricks()) {
                    if ((this.getPositionX() + this.getRadius() / 2 > brick.getLocationX()
                            && (this.getPositionX() + this.getRadius() / 2 < brick.getLocationX() + this.game.getBRICK_WIDTH()))
                            && (this.getPositionY() + this.getRadius() > game.getBricks().get(0).getLocationY() + this.game.getBRICK_HEIGHT())
                            && (this.getVelocityY() < 0)
                            && (this.getPositionY() < brick.getLocationY() + this.game.getBRICK_HEIGHT() - 1)
                            && brick.isVisible() == true) {

                        this.setVelocityY(this.getVelocityY() * -1);
                        brick.setIsVisible(false);
                        this.game.setGameScore(this.game.getScore() + brick.getScore()); 
                        break;
                    }
                }
            //Bounce if this.touches top of brick
            } else {
                for (Brick brick : this.game.getBricks()) {
                    if ((this.getPositionX() + this.getRadius() / 2 > brick.getLocationX()
                            && (this.getPositionX() + this.getRadius() / 2 < brick.getLocationX() + this.game.getBRICK_WIDTH()))
                            && (this.getPositionY() + this.getRadius() > brick.getLocationY())
                            && (this.getPositionY() + this.getRadius() < game.getBricks().get(game.getBricks().size() - 1).getLocationY() + this.game.getBRICK_HEIGHT())
                            && (this.getVelocityY() > 0)
                            && (brick.isVisible() == true)) {

                        this.setVelocityY(this.getVelocityY() * -1);
                        brick.setIsVisible(false);
                        this.game.setGameScore(this.game.getScore() + brick.getScore());
                        break;
                    }
                }
            }

            //Bounce if this.touches right side of brick
            if (this.getVelocityX() > 0) {
                for (Brick brick : this.game.getBricks()) {
                    if ((this.getPositionY() + this.getRadius() / 2 > brick.getLocationY()
                            && (this.getPositionY() + (this.getRadius() / 2) < brick.getLocationY() + this.game.getBRICK_HEIGHT()))
                            && (this.getPositionX() < brick.getLocationX())
                            && (this.getPositionX() + this.getRadius() > brick.getLocationX())
                            && (brick.isVisible() == true)) {

                        this.setVelocityX(this.getVelocityX() * -1);
                        brick.setIsVisible(false);
                        this.game.setGameScore(this.game.getScore() + brick.getScore());
                        break;
                    }
                }
            }
            //Bounce if this.touches left side of brick
            if(this.getVelocityX() < 0) {
                for (Brick brick : this.game.getBricks()) {
                    if ((this.getPositionY() + this.getRadius() / 2 > brick.getLocationY()
                            && (this.getPositionY() + this.getRadius() / 2 < brick.getLocationY() + this.game.getBRICK_HEIGHT()))
                            && (this.getPositionX() + this.getRadius() > brick.getLocationX() + this.game.getBRICK_WIDTH())
                            && (this.getPositionX() < brick.getLocationX() + this.game.getBRICK_WIDTH())
                            && (brick.isVisible() == true)) {

                        this.setVelocityX(this.getVelocityX() * -1);
                        brick.setIsVisible(false);
                        this.game.setGameScore(this.game.getScore() + brick.getScore());
                        break;
                    }
                }

            }
            //Deduct number of lives if ball ends up at bottom of screen
            if (this.getPositionY() > this.game.getHeight()) {
                this.game.setNumberOfLives(this.game.getNumberOfLives() - 1);
                this.setPositionX(this.game.getBALL_X_POSITION());
                this.setPositionY(this.game.getBALL_Y_POSITION());
                this.setVelocityX(this.getVelocityX() * -1);
                try {
                    this.thread.sleep(2000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            this.sleep(5);
       }
    }
}
