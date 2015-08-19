package DSA_Project.Breakout;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 *
 * @author olga
 */
public class Breakout {

    public static void main(String[] args) {

        System.out.println("Breakout!");
        String name;
        Scanner s = new Scanner(System.in);
        System.out.println("Please enter your name:");
        name = s.next();
        ////
        Player player = Player.fromInput(name);
        System.out.println(player.representation());
        

        File file = new File("highscores.txt");
        
        try {
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()){
                
            String st = scan.next();
            System.out.println(st);
            Highscores hs = Highscores.fromString(st);
//            System.out.println(Highscores.display());
            }
        } catch (FileNotFoundException e) {
            Highscores.makeEmpty();
            System.err.println("file cleared");
            
        }
        
        Highscores.addHighscore(player);   
//        Highscores.display();
        
        try {
            PrintWriter out = new PrintWriter(file);
            out.println(Highscores.display());
        } catch (FileNotFoundException ex) {
            System.err.println("fff");
        }
    }
}
