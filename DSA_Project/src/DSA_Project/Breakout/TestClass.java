
package DSA_Project.Breakout;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JFrame;

public class TestClass {
    public static void main(String[] args) {
        /*
        ArrayList<Ball> balls = new ArrayList<>();
        Ball ball = new Ball(5.0, 5.0, 5.0, 5.0, 5.0, 5.0, Color.yellow);
        balls.add(ball);
        FileAccessor.writeArrayIntoFile(balls, "balls.bin");
        
        ArrayList<Ball> balls2 = new ArrayList<>();
        
        balls2 = (ArrayList<Ball>) FileAccessor.getArrayFromFile("balls.bin");
        System.out.println(balls2.get(0));
        */
        JFrame frame = new JFrame();
        Game game = new Game(frame, 1);
        game.start();
        
        
    }
}
