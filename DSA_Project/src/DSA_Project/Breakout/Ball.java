package DSA_Project.Breakout;
//TODO
import java.awt.Color;

public class Ball {
    //FIELDS--------------------------------------------------------------------
    private double positionX;
    private double positionY;
    private double velocityX;
    private double velocityY;
    private double radius;
    private double deltaT;
    private Color color;
    
    //CONSTRUCTOR---------------------------------------------------------------
    public Ball() {
        this.radius = 10.0;
        this.color = Color.CYAN;
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
    
    @Override
    public String toString() {
        return "position(" + positionX + ", " + positionY + "), "
                + "velocity(" + velocityX + ", " + velocityY + "), "
                + "deltaT(" + deltaT + "), radius(" + radius + ")";
    }
    
    //TESTING BALL VALUES
    public static void main(String[] args) {
        int iteration = -1;
        Ball ball = new Ball();
        ball.setPositionX(16 + 480 * Math.random());
        ball.setPositionY(16 + 480 * Math.random());
        ball.setVelocityX(10 + 5 * Math.random());
        ball.setVelocityY(10 + 5 * Math.random());
        ball.setColor(Color.getHSBColor((float) Math.random(), 1.0f, 1.0f));
        ball.setRadius(20);
        ball.setDeltaT(1.0);

        while (iteration++ < 100) {
            System.out.println(ball);
            ball.move();
        }
    }
}
