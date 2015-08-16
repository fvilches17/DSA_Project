package DSA_Project.Breakout;

//IMPORTS-----------------------------------------------------------------------
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;

//CLASS-------------------------------------------------------------------------
public class Game extends JPanel implements Runnable {
    //FIELDS--------------------------------------------------------------------
    //Setting Fields and default values for Frame
    private final int DEFAULT_FRAME_WIDTH = 1000;
    private final int DEFAULT_FRAME_HEIGHT = 700;

    //Setting Fields and default values for Bricks
    private final Color[] BRICK_COLORS = {Color.red, Color.blue, Color.green, Color.yellow, Color.cyan};
    private final int BRICK_WIDTH = 70;
    private final int BRICK_HEIGHT = 20;
    private final int NUM_BRICKS_PER_LINE = 10;
    private ArrayList<Brick> bricks = new ArrayList<Brick>();

    //Setting fields and default initial values for the main Ball
    private boolean isBallGoingDown;
    private final Color BALL_COLOR = Color.MAGENTA;
    private final double BALL_RADIUS = 35.0;
    private final double BALL_X_POSITION = (DEFAULT_FRAME_WIDTH / 2.0) - BALL_RADIUS / 2.0;
    private final double BALL_Y_POSITION = (DEFAULT_FRAME_HEIGHT / 2.0) - BALL_RADIUS / 2.0;
    private final double BALL_X_VELOCITY = 4.0;
    private final double BALL_Y_VELOCITY = 5.0;
    private final double BALL_DELTA = 1.0; //TODO defines velocity, need to relate to level chosen by player
    private Ball ball = new Ball(BALL_X_POSITION, BALL_Y_POSITION, BALL_X_VELOCITY, 
                                 BALL_Y_VELOCITY, BALL_RADIUS, BALL_DELTA, BALL_COLOR);

    //Setting Fields and default values for Paddle
    private final int PADDLE_WIDTH = 175;
    private final int PADDLE_HEIGHT = 10;
    private final int PADDLE_Y_POSITION = 600;
    private final int PADDLE_X_START = (DEFAULT_FRAME_WIDTH / 2) - PADDLE_WIDTH / 2;
    private final Color PADDLE_COLOR = Color.DARK_GRAY;
    private final Paddle paddle = new Paddle(PADDLE_X_START, PADDLE_Y_POSITION, 
                                             PADDLE_WIDTH, PADDLE_HEIGHT, PADDLE_COLOR);

    //CONSTRUCTOR--------------------------------------------------------------
    public Game(JFrame frame) {
        //Adjusting Panel settings
        this.createBricks();
        this.setVisible(true);

        //Adjusting Frame settings
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(DEFAULT_FRAME_WIDTH, DEFAULT_FRAME_HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setContentPane(this);
        frame.setResizable(false);
    }

    //METHODS-------------------------------------------------------------------
    public void createBricks() {
        //Setting start location for bricks to be layed out.
        int startPointX = ((DEFAULT_FRAME_WIDTH - NUM_BRICKS_PER_LINE * BRICK_WIDTH) / 2) - BRICK_WIDTH / (BRICK_WIDTH / 10);
        int startPointY = 115;

        //Creating bricks
        for (int j = 0; j < BRICK_COLORS.length; j++) {
            for (int i = 0; i < NUM_BRICKS_PER_LINE; i++) {
                this.bricks.add(new Brick(startPointX, startPointY, BRICK_WIDTH, BRICK_HEIGHT, BRICK_COLORS[j], j + 1));
                //Setting new brick layout location to the right of last brick layed out
                startPointX += BRICK_WIDTH;
            }
            //Setting new row
            startPointY += BRICK_HEIGHT;
            startPointX = ((DEFAULT_FRAME_WIDTH - NUM_BRICKS_PER_LINE * BRICK_WIDTH) / 2) - BRICK_WIDTH / (BRICK_WIDTH / 10);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        //Default paint settings
        super.paintComponents(g);
        g.clearRect(0, 0, this.getWidth(), this.getHeight());

        //Painting Bricks
        for (Brick brick : bricks) {
            if (brick.isVisible()) {
                g.setColor(brick.getColor());
                g.fill3DRect((int) brick.getLocationX(), (int) brick.getLocationY(), (int) brick.getWidth(), (int) brick.getHeight(), true);
            }
        }

        //Painting Ball
        g.setColor(this.ball.getColor());
        g.fillOval((int) this.ball.getPositionX(), (int) this.ball.getPositionY(),
                (int) this.ball.getRadius(), (int) this.ball.getRadius());

        //Painting Paddle
        g.setColor(this.paddle.getColor());
        g.fillRect(this.paddle.getLocationX(), this.paddle.getLocationY(),
                this.paddle.getWidth(), this.paddle.getHeight());
    }

    @Override
    public void run() {
        while (true) {
            this.ball.move();
                  
            //Bounce if ball touches paddle
            if ((ball.getPositionX() + ball.getRadius() > paddle.getLocationX())
                    && (ball.getPositionX() < paddle.getLocationX() + paddle.getWidth() - 1)
                    && (ball.getPositionY() + ball.getRadius() > paddle.getLocationY())) {
                ball.setVelocityY(ball.getVelocityY() * -1);
                this.isBallGoingDown = false;
            }
            
            //Bounce if ball touches a side walls
            if ((this.ball.getPositionX() + this.ball.getRadius() > this.getWidth()) || (this.ball.getPositionX() < 0)) {
                ball.setVelocityX(this.ball.getVelocityX() * -1);
            }
            
            //Bounce if ball touches roof
            if (this.ball.getPositionY() < 0) {
                ball.setVelocityY(this.ball.getVelocityY() * -1);
                this.isBallGoingDown = true;
            }
            
            //Bounce if ball touches top or bottom of brick
            if (!isBallGoingDown) {
                for (Brick brick : this.bricks) {
                    if ((this.ball.getPositionX() + this.ball.getRadius() > brick.getLocationX()
                            && (this.ball.getPositionX() + this.ball.getRadius() < brick.getLocationX() + BRICK_WIDTH - 1))  
                            && (this.ball.getVelocityY() < 0
                            && this.ball.getPositionY() < brick.getLocationY() + BRICK_HEIGHT - 1)
                            && brick.isVisible() == true) {

                        ball.setVelocityY(this.ball.getVelocityY() * -1);
                        brick.setIsVisible(false);
                        break;
                    }
                }
            } else {
                for (Brick brick : this.bricks) {
                    if ((this.ball.getPositionX() + this.ball.getRadius() > brick.getLocationX()
                            && (this.ball.getPositionX() < brick.getLocationX() + BRICK_WIDTH - 1))
                            && (this.ball.getVelocityY() > 0
                            && this.ball.getPositionY() + this.ball.getRadius() > brick.getLocationY())
                            && brick.isVisible() == true) {

                        ball.setVelocityY(this.ball.getVelocityY() * -1);
                        brick.setIsVisible(false);
                        break;
                    }
                }
            }
            /*            
            //Bounce if ball touches side of brick
                for (Brick brick : this.bricks) {
                    if ((this.ball.getPositionY() + ball.getRadius() / 2 > brick.getLocationY()
                            && (this.ball.getPositionY() + (ball.getRadius() / 2) < brick.getLocationY() + BRICK_HEIGHT - 1))
                            && this.ball.getVelocityX() > 0
                            && this.ball.getPositionX() + this.ball.getRadius() > brick.getLocationX()
                            && brick.isVisible() == true) {

                        ball.setVelocityX(this.ball.getVelocityX() * -1);
                        brick.setIsVisible(false);
                        break;
                    }
                } 
                for (Brick brick : this.bricks) {
                    if ((this.ball.getPositionY() + ball.getRadius() / 2 > brick.getLocationY()
                            && (this.ball.getPositionY() + (ball.getRadius() / 2) < brick.getLocationY() + BRICK_HEIGHT - 1))
                            && this.ball.getVelocityX() < 0
                            && this.ball.getPositionX() < brick.getLocationX() + BRICK_WIDTH - 1
                            && brick.isVisible() == true) {

                        ball.setVelocityX(this.ball.getVelocityX() * -1);
                        brick.setIsVisible(false);
                        break;
                    }
                }
            */
            
            //Finishing gameplay if ball reaches floor
            if (this.ball.getPositionY() > this.getHeight()) {
                break;
            }
            
            
            
            try {
                Thread.sleep(15);
                repaint();
            } catch (InterruptedException ex) {
                Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    //MAIN METHOD---------------------------------------------------------------
    public static void main(String[] args) {
        JFrame frame = new JFrame("Breakout!");
        Game game = new Game(frame);
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                if (key == KeyEvent.VK_LEFT) {
                    game.paddle.moveLeft();
                    game.repaint();
                }
                if (key == KeyEvent.VK_RIGHT) {
                    game.paddle.moveRight();
                    game.repaint();
                }
                if (key == KeyEvent.VK_SPACE) {
                    System.exit(1);
                }
            }
        });
        game.run();
        
    }
}
