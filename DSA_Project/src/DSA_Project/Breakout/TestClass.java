package DSA_Project.Breakout;

import java.awt.Color;
import java.util.ArrayList;
import java.io.*;
import java.util.*;
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
    
    /*
    Highscores hs = null;
		//assume we have a file called highscores.txt
    //read in

        try {
	Scanner scan = new Scanner(new File("./path/to/highscores.txt"));
        String buf = "";
        while (scan.hasNextLine()) {
            buf += scan.nextLine();
        }
        scan.close();
        hs = Highscores.fromString(buf);
        }
        catch (FileNotFoundException e) {
			//handle missing file
        } catch (IllegalArgumentException e) {
			//something went wrong in the parsing
		}

		//write out
	try {
			BufferedWriter bw = new BufferedWriter(new FileWriter("./path/to/highscores.txt"));
        String s = hs.toString();
        bw.write(s);
        bw.close();
        }
        catch (IOException e) {
     //bad file path
		}

		//how to start
		Main m = Main.aStaticFactory();
    Game g = new Game(m);
		//launch g

}
}
    */
