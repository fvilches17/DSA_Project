package DSA_Project.Breakout;

import com.sun.media.jfxmedia.events.PlayerEvent;
import java.util.ArrayList;
import java.io.*;
import java.util.*;
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
