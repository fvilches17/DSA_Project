
package DSA_Project.Breakout;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public final class Breakout extends JPanel {
    //FIELDS--------------------------------------------------------------------
    private JFrame mainFrame;
    private JButton easyButton;
    private JButton mediumButton;
    private JButton hardButton;
    private JButton exitButton;
    private JTextArea textArea;
    private boolean isRunning;
    private final int DEFAULT_FRAME_WIDTH = 500;
    private final int DEFAULT_FRAME_HEIGHT = 500;
    
    //CONSTRUCTOR
    public Breakout(JFrame mainFrame) {
       //Adjusting panel setting
       setSize(mainFrame.getWidth(), mainFrame.getHeight());
       setBackground(Color.white); 
       CardLayout layout = new CardLayout(200, 220);
       layout.layoutContainer(this);

        //Adjustting frame settings
        mainFrame.setContentPane(this);
        mainFrame.setName("BREAKOUT");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
        mainFrame.setSize(DEFAULT_FRAME_WIDTH, DEFAULT_FRAME_HEIGHT);
        mainFrame.setLocationRelativeTo(null);
        
        //Adding Start Game Button
        easyButton = new JButton("Easy");
        easyButton.addMouseListener(new MouseActionListener());
        add(easyButton);
        
        //Adding Start Game Button
        mediumButton = new JButton("Medium");
        mediumButton.addMouseListener(new MouseActionListener());
        add(mediumButton);
        
        //Adding Start Game Button
        hardButton = new JButton("Hard");
        hardButton.addMouseListener(new MouseActionListener());
        add(hardButton);
        
        //Adding ExitButton
        exitButton = new JButton("Exit");
        exitButton.addMouseListener(new MouseActionListener());
        add(exitButton);
    }
    //GETTERS-------------------------------------------------------------------
    public JFrame getMainFrame() {
        return mainFrame;
    }

    public JButton getExitButton() {
        return exitButton;
    }

    public boolean isIsRunning() {
        return isRunning;
    }
    
    //SETTERS-------------------------------------------------------------------
    public void setMainFrame(JFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    public void setExitButton(JButton exitButton) {
        this.exitButton = exitButton;
    }
    
    public void setTextArea(JTextArea textArea) {
        this.textArea = textArea;
    }

    public void setIsRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }
    
    //METHODS-------------------------------------------------------------------
    //TODO eliminate unecesary get and set methods, and do a paint component to draw high scores and game logo, message, etc. (if time, insert image)
    public class MouseActionListener extends MouseAdapter {
            @Override
            public void mousePressed(MouseEvent e) {
                JFrame gameFrame = new JFrame("BREAKOUT");
                if (e.getSource().equals(easyButton)) {
                    Game game = new Game(gameFrame, Difficulty.EASY);
                    game.start();
                    return;
                } 
                else if (e.getSource().equals(mediumButton)) {
                    Game game = new Game(gameFrame, Difficulty.MEDIUM);
                    game.start();
                    return;
                } 
                else if (e.getSource().equals(hardButton)) {
                    Game game = new Game(gameFrame, Difficulty.HARD);
                    game.start();
                    return;
                }
                else if (e.getSource().equals(getExitButton())) {
                    System.exit(1);
                }
            }
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponents(g);
        g.clearRect(0, 0, this.getWidth(), this.getHeight());
        String gameTitle = "BREAKOUT";
        g.setColor(Color.BLUE);
        Font font = new Font("sans serif", 1, 40);
        FontMetrics fontMetrics = g.getFontMetrics(font); 
        int fontLength = fontMetrics.stringWidth(gameTitle);
        g.setFont(font);
        g.drawString(gameTitle, DEFAULT_FRAME_WIDTH / 2 - fontLength / 2, DEFAULT_FRAME_HEIGHT / 2 - 50);
    }
    
    public static void main(String[] args) {
        //Initializing main frame and running game
        JFrame mainFrame = new JFrame();
        Breakout breakout = new Breakout(mainFrame);
    }
    
    
    
    
    
}
