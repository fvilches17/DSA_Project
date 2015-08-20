package DSA_Project.Breakout;

//IMPORTS-----------------------------------------------------------------------
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

//CLASS-------------------------------------------------------------------------
public class Game extends JPanel implements Runnable {

    //FIELDS--------------------------------------------------------------------
    //Setting fields and default values for miscellaneous items 
    private boolean isRunning;
    private boolean isGameFinished = false;
    private int numberOfLives = 3;
    private Thread thread;
    private Random random;
    private final Player player;
    private final Difficulty difficulty;

    //Setting Fields and default values for Frame
    private JFrame frame;
    private final int DEFAULT_FRAME_WIDTH = 1000;
    private final int DEFAULT_FRAME_HEIGHT = 700;

    //Setting Fields and default values for Bricks
    private final Color[] BRICK_COLORS = {Color.red, Color.blue, Color.green, Color.yellow, Color.cyan}; //Bricks with score values
    private final Color SPECIAL_BRICK_COLOR = Color.BLACK;
    private final int BRICK_WIDTH = 70;
    private final int BRICK_HEIGHT = 20;
    private final int NUM_BRICKS_PER_LINE = 10;
    private final ArrayList<Brick> bricks = new ArrayList<Brick>();

    //Setting fields and default initial values for the main Ball
    private final Color BALL_COLOR = Color.MAGENTA;
    private boolean isFirstBallLaunched = false;
    private final double BALL_RADIUS = 35.0;
    private final double BALL_X_POSITION = (DEFAULT_FRAME_WIDTH / 2.0) - BALL_RADIUS / 2.0; //starting X position
    private final double BALL_Y_POSITION = (DEFAULT_FRAME_HEIGHT / 2.0) - BALL_RADIUS / 2.0; //starting Y position
    private final double BALL_X_VELOCITY = 2.0;
    private final double BALL_Y_VELOCITY = 4.0;
    private final double BALL_DELTA = 1.0;
    private final Ball ball = new Ball(BALL_X_POSITION, BALL_Y_POSITION, BALL_X_VELOCITY,
            BALL_Y_VELOCITY, BALL_RADIUS, BALL_DELTA, BALL_COLOR, this);

    //Setting fields and default vaues for the Special Ball (special ball starting position is same as original ball)
    private boolean isSpecialBallActivated = false;
    private final Color SPECIAL_BALL_COLOR = Color.ORANGE;
    private final double SPECIAL_BALL_RADIUS = 50.0;
    private final double SPECIAL_BALL_X_VELOCITY = 2.0;
    private final double SPECIAL_BALL_Y_VELOCITY = 4.0;
    private final double SPECIAL_BALL_DELTA = 0.2;
    private final Ball specialBall = new Ball(BALL_X_POSITION, BALL_Y_POSITION, SPECIAL_BALL_X_VELOCITY,
            SPECIAL_BALL_Y_VELOCITY, SPECIAL_BALL_RADIUS, SPECIAL_BALL_DELTA, SPECIAL_BALL_COLOR, this);

    //Setting Fields and default values for Paddle
    private final int PADDLE_WIDTH = 175;
    private final int PADDLE_HEIGHT = 10;
    private final int PADDLE_Y_POSITION = 600;
    private final int PADDLE_X_START = (DEFAULT_FRAME_WIDTH / 2) - PADDLE_WIDTH / 2;
    private final int PADDLE_DISPLACEMENT_SPEED = 15;
    private final Color PADDLE_COLOR = Color.GRAY;
    private final Paddle paddle = new Paddle(PADDLE_X_START, PADDLE_Y_POSITION,
            PADDLE_WIDTH, PADDLE_HEIGHT, PADDLE_COLOR, PADDLE_DISPLACEMENT_SPEED);

    //CONSTRUCTOR--------------------------------------------------------------
    public Game(JFrame frame, Difficulty difficulty) {
        //Setting up default values for player
        this.player = new Player("", 0);

        //Setting difficulty level. The greater the difficulty the faster the ball moves
        this.difficulty = difficulty;
        if (difficulty == Difficulty.EASY) {
            this.ball.setDeltaT(0.4);
        } else if (difficulty == Difficulty.MEDIUM) {
            this.ball.setDeltaT(0.6);
        } else if (difficulty == Difficulty.HARD) {
            this.ball.setDeltaT(0.8);
        }

        //Adjusting Panel settings and initializing creating brick values
        this.createBricks();
        this.setVisible(true);

        //Adjusting Frame settings
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //TODO should save high score here
        frame.setSize(DEFAULT_FRAME_WIDTH, DEFAULT_FRAME_HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setContentPane(this);
        frame.setResizable(false);
        frame.addKeyListener(new ActionListener());
        this.frame = frame;
    }

    //GETTERS-------------------------------------------------------------------
    public Thread getThread() {
        return thread;
    }

    public Paddle getPaddle() {
        return paddle;
    }

    public ArrayList<Brick> getBricks() {
        return bricks;
    }

    public int getBRICK_WIDTH() {
        return BRICK_WIDTH;
    }

    public int getBRICK_HEIGHT() {
        return BRICK_HEIGHT;
    }

    public int getNumberOfLives() {
        return numberOfLives;
    }

    public double getBALL_X_POSITION() {
        return BALL_X_POSITION;
    }

    public double getBALL_Y_POSITION() {
        return BALL_Y_POSITION;
    }

    public double getBALL_X_VELOCITY() {
        return BALL_X_VELOCITY;
    }

    public boolean isSpecialBallActivated() {
        return isSpecialBallActivated;
    }

    public Difficulty getDifficulty() {
        return this.difficulty;
    }

    public boolean isRunning() {
        return this.isRunning;
    }

    public Player getPlayer() {
        return player;
    }

    //SETTERS-------------------------------------------------------------------
    public void setNumberOfLives(int numberOfLives) {
        this.numberOfLives = numberOfLives;
    }

    public void setIsSpecialBallActivated(boolean isSpecialBallActivated) {
        this.isSpecialBallActivated = isSpecialBallActivated;
    }

    //METHODS-------------------------------------------------------------------
    public void start() {
        thread = new Thread(this);
        isRunning = true;
        thread.start();
    }

    public void stop() {
        isRunning = false;
        thread = null;
    }

    public void sleep(long milliseconds) {
        try {
            thread.sleep(milliseconds);
        } catch (InterruptedException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void createBricks() {
        //Setting start location for bricks to be layed out.
        int startPointX = ((DEFAULT_FRAME_WIDTH - NUM_BRICKS_PER_LINE * BRICK_WIDTH) / 2) - BRICK_WIDTH / (BRICK_WIDTH / 10);
        int startPointY = 115;

        //Creating bricks
        for (int j = 0; j < BRICK_COLORS.length; j++) {
            for (int i = 0; i < NUM_BRICKS_PER_LINE; i++) {
                this.bricks.add(new Brick(startPointX, startPointY, BRICK_WIDTH, BRICK_HEIGHT, BRICK_COLORS[j],
                        (BRICK_COLORS.length - j) * (this.difficulty.ordinal() + 1)));
                //Setting new brick layout location to the right of last brick layed out
                startPointX += BRICK_WIDTH;
            }
            //Setting new row
            startPointY += BRICK_HEIGHT;
            startPointX = ((DEFAULT_FRAME_WIDTH - NUM_BRICKS_PER_LINE * BRICK_WIDTH) / 2) - BRICK_WIDTH / (BRICK_WIDTH / 10);
        }
        //Choosing 1 special brick at random, assigning it the special brick color, and score value of 0
        random = new Random();
        int specialBrick = random.nextInt(this.bricks.size());
        this.bricks.get(specialBrick).setIsSpecial(true);
        this.bricks.get(specialBrick).setColor(this.SPECIAL_BRICK_COLOR);
        this.bricks.get(specialBrick).setScore(0);
        this.bricks.get(specialBrick).setIsSpecial(true);
    }

    public boolean areBricksRemaining() {
        int visibleBricks = 0;
        for (Brick brick : bricks) {
            if (brick.isVisible()) {
                visibleBricks++;
            }
        }
        if (visibleBricks == 0) {
            return false;
        }
        return true;
    }
    
    @Override
    public void paintComponent(Graphics g) {
        //Default paint settings
        super.paintComponents(g);
        g.clearRect(0, 0, this.getWidth(), this.getHeight());

        //Painting starting message (game instructions for player)
        if (!this.isFirstBallLaunched) {
            String gameStartMessage = "<-- LEFT     -->> RIGHT      SPACEBAR pause/start      ESCAPE exit";
            Font font = new Font("Arial", 1, 20);
            FontMetrics fontMetrics = g.getFontMetrics(font);
            int fontLength = fontMetrics.stringWidth(gameStartMessage);
            g.setXORMode(BALL_COLOR);
            g.setFont(font);
            g.drawString(gameStartMessage, DEFAULT_FRAME_WIDTH / 2 - fontLength / 2, DEFAULT_FRAME_HEIGHT / 2);
        }

        //Painting message on screen if game finishes. E.g.: GAME OVER
        if (!this.isRunning && (this.numberOfLives == 0 || this.areBricksRemaining() == false)) {
            String gameEndMessage = "";
            if (this.numberOfLives == 0) {
                gameEndMessage = "GAME OVER  Score: " + this.getPlayer().score();
            } else {
                this.getPlayer().addToScore(this.numberOfLives * (this.getDifficulty().ordinal() + 1));
                gameEndMessage = "YOU WIN!  Lives remaining: " + this.numberOfLives
                        + "  Bonus: " + (this.getDifficulty().ordinal() + 1) * 10
                        + "  Final Score: " + this.player.score();
            }
            Font font = new Font("Arial", 1, 20);
            FontMetrics fontMetrics = g.getFontMetrics(font);
            int fontLength = fontMetrics.stringWidth(gameEndMessage);
            g.setXORMode(BALL_COLOR);
            g.setFont(new Font("Arial", 1, 20));
            g.drawString(gameEndMessage, DEFAULT_FRAME_WIDTH / 2 - fontLength / 2, DEFAULT_FRAME_HEIGHT / 2);
        }

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

        //Painting Special ball (only if a special brick has already been hit)
        if (this.isSpecialBallActivated) {
            g.setColor(this.specialBall.getColor());
            g.fillOval((int) this.specialBall.getPositionX(), (int) this.specialBall.getPositionY(),
                    (int) this.specialBall.getRadius(), (int) this.specialBall.getRadius());
        }

        //Painting number of lives
        int livesIconPositionX = 5;
        int livesIconPositionY = 15;
        int livesIconRadius = 15;
        int i = 0;
        g.setColor(BALL_COLOR);
        while (i < this.numberOfLives) {
            g.fillOval(livesIconPositionX, livesIconPositionY, livesIconRadius, livesIconRadius);
            livesIconPositionX += livesIconRadius + 5;
            i++;
        }

        //Painting current player score
        g.setColor(Color.darkGray);
        g.setFont(new Font("Arial", 1, 12));
        g.drawString("Player Score " + this.getPlayer().score(), 7, 10);

        //Painting Paddle
        g.setColor(this.paddle.getColor());
        g.fillRect(this.paddle.getLocationX(), this.paddle.getLocationY(),
                this.paddle.getWidth(), this.paddle.getHeight());

        //Painting HighScores
        if (isGameFinished) {
            this.sleep(2000);
            Highscores.paintHighScores(g, this);
        }
    }

    //Creating action listener class to take keyboard commands from user
    public class ActionListener extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
            if (keyCode == e.VK_LEFT && isRunning()) {
                getPaddle().moveLeft();
            }
            if (keyCode == e.VK_RIGHT && isRunning()) {
                getPaddle().moveRight();
            }
            if (keyCode == e.VK_SPACE) {
                if (isRunning()) {
                    stop();
                } else if (isFirstBallLaunched) {
                    start();
                }
            }
            if (keyCode == e.VK_ESCAPE) {
                System.exit(1);
                //TODO according to assignment instrucctions, should save high scores in this event
            }
        }
    }

    @Override
    public void run() {
        if (isFirstBallLaunched == false) {
            this.isRunning = false;
            this.sleep(2500);
            this.isFirstBallLaunched = true;
            this.isRunning = true;
        }
        this.ball.start();
        int timesSpecialBrickHit = 0;
        while (isRunning) {
            //Starting specialBall if special brick hit
            if (this.isSpecialBallActivated && timesSpecialBrickHit == 0) {
                this.specialBall.start();
                timesSpecialBrickHit++;
            }

            //Finishing gameplay if number of lives = 0
            if (this.numberOfLives == 0) {
                this.isRunning = false;
            }

            //Finishing gameplay if no more bricks remaining
            if (this.areBricksRemaining() == false) {
                this.isRunning = false;
            }

            //Setting screen refresh rate
            this.sleep(2);
            repaint();

        }
        ball.stop();
        if (isSpecialBallActivated) {
            specialBall.stop();
        }
        
        //Checking if player has reached high score. If so, and asking to input player name
        if ((this.getNumberOfLives() == 0 || !areBricksRemaining()) && this.player.score() > Highscores.getLowestScore()) {
            JOptionPane optionPane = new JOptionPane();
            String playerName = optionPane.showInputDialog(frame, "High Score! Enter your name", "BREAKOUT", JOptionPane.INFORMATION_MESSAGE);
            if (playerName != null) {
                this.player.setName(playerName);
            }
            if (this.player.name().length() < 1) {
                this.player.setName("No name");
            }
            Highscores.saveHighScore(this.player);
            isGameFinished = true;
        }
        if (this.getNumberOfLives() == 0 || !areBricksRemaining()) {
            isGameFinished = true;
            repaint(); //painting high scores
            this.sleep(8000);
            frame.dispose();
        }
    }

    //MAIN METHOD---------------------------------------------------------------

    public static void main(String[] args) {
        JFrame frame = new JFrame("Breakout!");
        Game game = new Game(frame, Difficulty.MEDIUM);
        game.start();
    }
}
