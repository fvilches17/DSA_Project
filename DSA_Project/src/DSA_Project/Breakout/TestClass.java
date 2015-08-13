
package DSA_Project.Breakout;

import java.io.Serializable;
import java.util.ArrayList;

public class TestClass implements Serializable {
    public static void main(String[] args) {
        ArrayList<Ball> balls = new ArrayList<Ball>();
        Ball ball = new Ball();
        balls.add(ball);
        FileAccessor.writeArrayIntoFile(balls, "balls.bin");
        
        ArrayList<Ball> balls2 = new ArrayList<Ball>();
        
        balls2 = (ArrayList<Ball>) FileAccessor.getArrayFromFile("balls.bin");
        //System.out.println(balls2.get(0));
    }
}
