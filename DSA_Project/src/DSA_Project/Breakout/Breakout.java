
package DSA_Project.Breakout;

import java.util.Scanner;

/**
 *
 * @author olga
 */
public class Breakout  {


    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        Player p;
        do {
            try {
                String name = s.nextLine();
                p = Player.fromString(name);
            } catch (IllegalArgumentException e) {
                System.out.println("Name cannot be null or empty.");
            }
        } while (p == null);
    }
    
    Game game = new Game();
}