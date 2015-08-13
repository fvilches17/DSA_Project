
package DSA_Project.Breakout;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Game extends JPanel implements Runnable{
    //FIELDS--------------------------------------------------------------------
    //Setting Fields for Frame
    private static final int DEFAULT_FRAME_WIDTH = 1000;
    private static final int DEFAULT_FRAME_HEIGHT = 700;
    
    //Setting Fields for Bricks
    private static final int DEFAULT_NUM_BRICKS = 40; //HAS TO BE MULTIPLE OF 10!
    private final int BRICK_WIDTH = 50;
    private final int BRICK_HEIGHT = 20;
    private ArrayList<Brick> bricks = new ArrayList<Brick>();
    
    //Setting fields for Ball
    private final Ball ball = new Ball();
    
    //Setting Fields for Paddle
    private final int PADDLE_WIDTH = 175;
    private final int PADDLE_HEIGHT = 10;
    private final int PADDLE_Y_POSITION = 600;
    private final int PADDLE_X_START = (DEFAULT_FRAME_WIDTH / 2) - PADDLE_WIDTH/2;
    private final Color PADDLE_COLOR = Color.DARK_GRAY;
    private final Paddle paddle = new Paddle(PADDLE_X_START, 
            PADDLE_Y_POSITION, PADDLE_WIDTH, PADDLE_HEIGHT, PADDLE_COLOR);
    
    //CONSTRUCTOR--------------------------------------------------------------
    public Game() {
        //Adjusting Panel settings
        this.createBricks();
        this.setVisible(true);
        
        //Adjusting Frame settings
        JFrame frame = new JFrame("BREAKOUT!");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(DEFAULT_FRAME_WIDTH, DEFAULT_FRAME_HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setContentPane(this);  
    }
    
    //METHODS-------------------------------------------------------------------
    public void createBricks() {
        //Setting start location for bricks to be layed out.
        int startPointX = ((DEFAULT_FRAME_WIDTH - 500) / 2) - BRICK_WIDTH/2; //TODO add more standard calculation
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
            startPointX = ((DEFAULT_FRAME_WIDTH - 500) / 2) - BRICK_WIDTH/2;
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponents(g);
        g.clearRect(0, 0, this.getWidth(), this.getHeight());
        
        //Painting Bricks
        for(Brick b : bricks) {
            g.setColor(b.getColor());
            g.fill3DRect(b.getLocationX(), b.getLocationY(), b.getWidth(), b.getHeight(), true);
        }
        
        //Painting Ball
        g.setColor(this.ball.getDEFAULT_COLOR());
        g.fillOval(this.getWidth() / 2 - this.ball.getDIAMETER()/2, this.getHeight() / 2, this.ball.getDIAMETER(), this.ball.getDIAMETER());
        
        //Painting Paddle
        g.setColor(this.paddle.getColor());
        g.fillRect(this.paddle.getLocationX(), this.paddle.getLocationY(), 
                this.paddle.getWidth(), this.paddle.getHeight());
        
    }
    
    
    
        @Override
    public void run() {
    }
    
    public static void main(String[] args) {
        Game game = new Game();
        game.run();
    }


    
    
}
