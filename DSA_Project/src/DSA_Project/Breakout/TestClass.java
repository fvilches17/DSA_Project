
package DSA_Project.Breakout;

import com.sun.media.jfxmedia.events.PlayerEvent;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JFrame;

public class TestClass extends JFrame{
    
    public TestClass() {
        this.setSize(500,500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public static void main(String[] args) {
        Game game = new Game(Difficulty.EASY);
        game.start();
        
    }
}
