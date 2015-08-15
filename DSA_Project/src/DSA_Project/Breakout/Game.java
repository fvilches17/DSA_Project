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
    private final int BRICK_WIDTH = 60;
    private final int BRICK_HEIGHT = 20;
    private final int NUM_BRICKS_PER_LINE = 10;
    private ArrayList<Brick> bricks = new ArrayList<Brick>();

    //Setting fields and default values for Ball
    private final double BALL_RADIUS = 40.0;
    private final Color BALL_COLOR = Color.MAGENTA;
    private final Ball ball = new Ball(BALL_RADIUS, BALL_COLOR);

    //Setting Fields and default values for Paddle
    private final int PADDLE_WIDTH = 175;
    private final int PADDLE_HEIGHT = 10;
    private final int PADDLE_Y_POSITION = 600;
    private final int PADDLE_X_START = (DEFAULT_FRAME_WIDTH / 2) - PADDLE_WIDTH / 2;
    private final Color PADDLE_COLOR = Color.DARK_GRAY;
    private final Paddle paddle = new Paddle(PADDLE_X_START,
            PADDLE_Y_POSITION, PADDLE_WIDTH, PADDLE_HEIGHT, PADDLE_COLOR);

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
        int startPointY = 95;

        //Creating bricks
        for (int j = 0; j < BRICK_COLORS.length; j++) {
            for (int i = 0; i < NUM_BRICKS_PER_LINE; i++) {
                this.bricks.add(new Brick(
                        startPointX, startPointY, BRICK_WIDTH, BRICK_HEIGHT, BRICK_COLORS[j], j + 1));
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
            g.setColor(brick.getColor());
            g.fill3DRect((int) brick.getLocationX(), (int) brick.getLocationY(), (int) brick.getWidth(), (int) brick.getHeight(), true);
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
        //TODO ball direction has to be random every time the game starts
        double xMovement = 100.0;
        double yMovement = 5000.0;
        double n = Math.sqrt(xMovement * xMovement + yMovement * yMovement);
        ball.setVelocityX(xMovement / n);
        ball.setVelocityY(yMovement / n);
        ball.setDeltaT(2.20);

        /*
         this.ball.setPositionX(this.getWidth() / 2.0 - this.ball.getRadius() / 2.0);
         this.ball.setPositionY(this.getHeight() / 2.0 - this.ball.getRadius() / 2.0);
         */
        while (true) {
            //Moving  ball
            ball.move();
            /*
             this.ball.setPositionX(this.ball.getPositionX() + xMovement);
             this.ball.setPositionY(this.ball.getPositionY() + yMovement);
             */

            //Bounce if ball touches paddle
            if ((this.ball.getPositionX() > this.paddle.getLocationX() - PADDLE_WIDTH)
                    && (this.ball.getPositionX() < this.paddle.getLocationX() + PADDLE_WIDTH)
                    && (this.ball.getPositionY() > PADDLE_Y_POSITION - BALL_RADIUS)) {
                ball.setDeltaT(ball.getDeltaT() * -1);
                /*
                 xMovement *= -1;
                 yMovement *= -2; //TODO use delta x here instead
                 */
            }
            //Bounce if ball touches a wall
            if ((this.ball.getPositionX() + this.ball.getRadius() > this.getWidth()) || (this.ball.getPositionX() < 0)
                    || (this.ball.getPositionY() + this.ball.getRadius() > this.getHeight())
                    || (this.ball.getPositionY() < 0)) {
                xMovement *= -1;
                yMovement *= -1; //TODO use delta x here instead
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
