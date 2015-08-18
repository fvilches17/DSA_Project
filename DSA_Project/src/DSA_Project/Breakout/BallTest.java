package DSA_Project.Breakout;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class BallTest extends JPanel implements Runnable {
    //FIELDS--------------------------------------------------------------------
    private double positionX;
    private double positionY;
    private double velocityX;
    private double velocityY;
    private double radius;
    private double deltaT;
    private Color color;
    private Game game;
    
    //CONSTRUCTOR---------------------------------------------------------------`
    public BallTest(double positionX, double positionY, double velocityX, double velocityY, double radius, double deltaT, Color color, Game gameTest) {
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
    
    
    public void paintComponent(Graphics g) {
        super.paintComponents(g);
        g.clearRect(0, 0, game.getWidth(), game.getHeight());
        g.setColor(Color.BLACK);
        g.fillOval((int) this.getPositionX(), (int) this.getPositionY(),
                (int) this.getRadius(), (int) this.getRadius());
    }
    
    @Override
    public String toString() { //Used for debugging purposes
        return "position(" + positionX + ", " + positionY + "), "
                + "velocity(" + velocityX + ", " + velocityY + "), "
                + "deltaT(" + deltaT + "), radius(" + radius + ")";
    }

    @Override
    public void run() {
        /*
        while (this.gameTest.isRunning()) {            
            //Bounce if ball touches paddle
            if ((this.getPositionX() + this.getRadius() > this.gameTest.paddle.getLocationX())
                    && (this.getPositionX() < this.gameTest.paddle.getLocationX() + this.gameTest.paddle.getWidth())
                    && (this.getPositionY() + this.getRadius() > paddle.getLocationY())
                    && (this.getPositionY() < paddle.getLocationY())) {
                this.setVelocityY(this.getVelocityY() * -1);
                this.gameTest.isBallGoingDown = false;
            }

            //Bounce if ball touches a side walls
            if ((this.this.getPositionX() + this.this.getRadius() > this.getWidth()) || (this.this.getPositionX() < 0)) {
                this.setVelocityX(this.this.getVelocityX() * -1);
            }

            //Bounce if ball touches roof
            if (this.this.getPositionY() < 0) {
                this.setVelocityY(this.this.getVelocityY() * -1);
                this.isBallGoingDown = true;
            }

            //Bounce if ball touches bottom of brick
            if (!isBallGoingDown) {
                for (Brick brick : this.bricks) {
                    if ((this.this.getPositionX() + this.this.getRadius() / 2 > brick.getLocationX()
                            && (this.this.getPositionX() + this.this.getRadius() / 2 < brick.getLocationX() + BRICK_WIDTH))
                            && (this.this.getPositionY() + this.this.getRadius() > bricks.get(0).getLocationY() + BRICK_HEIGHT)
                            && (this.this.getVelocityY() < 0)
                            && (this.this.getPositionY() < brick.getLocationY() + BRICK_HEIGHT - 1)
                            && brick.isVisible() == true) {

                        this.setVelocityY(this.this.getVelocityY() * -1);
                        brick.setIsVisible(false);
                        this.gameScore += brick.getScore();
                        break;
                    }
                }
            //Bounce if ball touches top of brick
            } else {
                for (Brick brick : this.bricks) {
                    if ((this.this.getPositionX() + this.this.getRadius() / 2 > brick.getLocationX()
                            && (this.this.getPositionX() + this.this.getRadius() / 2 < brick.getLocationX() + BRICK_WIDTH))
                            && (this.this.getPositionY() + this.this.getRadius() > brick.getLocationY())
                            && (this.this.getPositionY() + this.this.getRadius() < bricks.get(bricks.size() - 1).getLocationY() + BRICK_HEIGHT)
                            && (this.this.getVelocityY() > 0)
                            && (brick.isVisible() == true)) {

                        this.setVelocityY(this.this.getVelocityY() * -1);
                        brick.setIsVisible(false);
                        this.gameScore += brick.getScore();
                        break;
                    }
                }
            }

            //Bounce if ball touches right side of brick
            if (this.this.getVelocityX() > 0) {
                for (Brick brick : this.bricks) {
                    if ((this.this.getPositionY() + this.getRadius() / 2 > brick.getLocationY()
                            && (this.this.getPositionY() + (this.getRadius() / 2) < brick.getLocationY() + BRICK_HEIGHT))
                            && (this.this.getPositionX() < brick.getLocationX())
                            && (this.this.getPositionX() + this.this.getRadius() > brick.getLocationX())
                            && (brick.isVisible() == true)) {

                        this.setVelocityX(this.this.getVelocityX() * -1);
                        brick.setIsVisible(false);
                        this.gameScore += brick.getScore();
                        break;
                    }
                }
            }
            //Bounce if ball touches left side of brick
            if(this.this.getVelocityX() < 0) {
                for (Brick brick : this.bricks) {
                    if ((this.this.getPositionY() + this.getRadius() / 2 > brick.getLocationY()
                            && (this.this.getPositionY() + this.getRadius() / 2 < brick.getLocationY() + BRICK_HEIGHT))
                            && (this.this.getPositionX() + this.this.getRadius() > brick.getLocationX() + BRICK_WIDTH)
                            && (this.this.getPositionX() < brick.getLocationX() + BRICK_WIDTH)
                            && (brick.isVisible() == true)) {

                        this.setVelocityX(this.this.getVelocityX() * -1);
                        brick.setIsVisible(false);
                        this.gameScore += brick.getScore();
                        break;
                    }
                }

            }
            try {
                Thread.sleep(20);
            } catch (InterruptedException ex) {
                Logger.getLogger(BallTest.class.getName()).log(Level.SEVERE, null, ex);
            }
                
        }
        */
    }
}
