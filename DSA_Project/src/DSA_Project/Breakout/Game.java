package DSA_Project.Breakout;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Game extends JPanel implements Runnable {

    //FIELDS--------------------------------------------------------------------
    //Setting Fields for Frame
    private final int DEFAULT_FRAME_WIDTH = 1000;
    private final int DEFAULT_FRAME_HEIGHT = 700;

    //Setting Fields for Bricks
    private final int DEFAULT_NUM_BRICKS = 40; //HAS TO BE MULTIPLE OF 10!
    private final int BRICK_WIDTH = 70;
    private final int BRICK_HEIGHT = 20;
    private ArrayList<Brick> bricks = new ArrayList<Brick>();

    //Setting fields for Ball
    //TODO better to set standard final fields for ball here, instead of within the ball class
    private final Ball ball = new Ball();

    //Setting Fields for Paddle
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
    }

    //METHODS-------------------------------------------------------------------
    public void createBricks() {
        //Setting start location for bricks to be layed out.
        int startPointX = ((DEFAULT_FRAME_WIDTH - 700) / 2) - BRICK_WIDTH / 2; //TODO add more standard calculation
        int startPointY = 50;
        //Setting default colors for brick rows
        ArrayList<Color> colors = new ArrayList<>(); //TODO find more standard way, e.g., what if there were 6 rows
        colors.add(Color.red); //Row1
        colors.add(Color.blue); //Row2
        colors.add(Color.yellow); //Row3
        colors.add(Color.green); //Row4
        //Creating bricks
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < DEFAULT_NUM_BRICKS / 4; i++) { //get more standard variable, instead of 4
                this.bricks.add(new Brick(
                        startPointX, startPointY, BRICK_WIDTH, BRICK_HEIGHT, colors.get(j), j + 1));
                //Setting new brick layout location to the right of last brick layed out
                startPointX += BRICK_WIDTH;
            }
            //Setting new row
            startPointY += BRICK_HEIGHT;
            startPointX = ((DEFAULT_FRAME_WIDTH - 500) / 2) - BRICK_WIDTH / 2;
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponents(g);
        g.clearRect(0, 0, this.getWidth(), this.getHeight());

        //Painting Bricks
        for (Brick b : bricks) {
            g.setColor(b.getColor());
            g.fill3DRect(b.getLocationX(), b.getLocationY(), b.getWidth(), b.getHeight(), true);
        }

        //Painting Ball
        g.setColor(this.ball.getDEFAULT_COLOR());
        g.fillOval((int) this.ball.getPositionX(), (int) this.ball.getPositionY(), this.ball.getDIAMETER(), this.ball.getDIAMETER());

        //Painting Paddle
        g.setColor(this.paddle.getColor());
        g.fillRect(this.paddle.getLocationX(), this.paddle.getLocationY(),
                this.paddle.getWidth(), this.paddle.getHeight());

    }

    @Override
    public void run() {
        int xMovement = 2;
        int yMovement = 2;
        this.ball.setPositionX(this.getWidth() / 2 - this.ball.getDIAMETER() / 2);
        this.ball.setPositionY(this.getHeight() / 2);
        while (true) {
            this.ball.setPositionX(this.ball.getPositionX() + xMovement);
            this.ball.setPositionY(this.ball.getPositionY() + yMovement);

            //Bounce if ball touches paddle
            if ((this.ball.getPositionX() > this.paddle.getLocationX() - PADDLE_WIDTH
                    && this.ball.getPositionX() < this.paddle.getLocationX() + PADDLE_WIDTH)
                    && (this.ball.getPositionY() == PADDLE_Y_POSITION - ball.getDIAMETER())) {
                xMovement *= -1;
                yMovement *= -2; //TODO use delta x here instead
            }

            try {
                Thread.sleep(15);
                repaint();
            } catch (InterruptedException ex) {
                Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

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
