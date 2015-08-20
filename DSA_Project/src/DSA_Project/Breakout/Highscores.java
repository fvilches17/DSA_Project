package DSA_Project.Breakout;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

/**
 *
 * @author olga
 */
public class Highscores {

    private static SortedSet<Player> scoreboard;

    public Highscores() {
    }

    public static Highscores makeEmpty() {
        Highscores h = new Highscores();
        h.scoreboard = new TreeSet<>();
        return h;
    }

    public static Highscores fromString(String s) {
        if (s == null) {
            throw new IllegalArgumentException("s cannot be null");
        }
        if (s.length() == 0) {
            throw new IllegalArgumentException("s cannot be empty");
        }
        //System.out.println("Validated!");
        Highscores h = Highscores.makeEmpty();
        String[] split = s.split("\n");
        if (split.length % 2 != 0) {
            throw new IllegalArgumentException("s should have an even number of lines.");
        }
        if (split.length > 20) {
            throw new IllegalArgumentException("s has too many lines.");
        }
        //System.out.println("Division worked!");
        String buff = "";
      
        ///////
        for (int i = 0; i < split.length; i++) {
            //System.out.println(i);
            if (i % 2 == 0) {
                
                buff = split[i] + "\n";
            } else {
                h.scoreboard.add(Player.fromStringFile(buff + split[i].trim()));
            }
        }
        //System.out.println("Scoreboard filled.");
        return h;
    }

	public Player[] scores() {
		Player[] ps = new Player[scoreboard.size()];
		int i = 0;
		for (Player p : scoreboard) {
			ps[i] = p;
			i++;
		}
		return ps;		
	}
        
    public void addHighscore(Player p) {
//        for (Player pl: scoreboard) {
//            System.out.printf("%s %s\n", pl.name(), pl.score());
//        }
        scoreboard.add(p);
//        for (Player pl: scoreboard) {
//            System.out.printf("%s %s\n", pl.name(), pl.score());
//        }
        if (scoreboard.size() == 11) {
            scoreboard.remove(scoreboard.last());
        }
    }

    public static String[] display(int spaceCount) {
        String[] output = new String[scoreboard.size()];
        int count = 0;
        for (Player p : scoreboard) {
            StringBuilder sb = new StringBuilder();
            sb.append(p.name());
            for (int i = 0; i < spaceCount; i++) {
                sb.append(" ");
            }
            sb.append(p.score());
            output[count] = sb.toString();
            count++;
        }
        return output;
    }
    
    public static int getLowestScore() {
        File file = new File("highscores.txt");
        String text = "";
        try {
            Scanner scan = new Scanner(file);
            int x = 0;
            while (true) {
                if(scan.hasNextLine() == false && x==0)
                    return 0;
                
                if(scan.hasNextLine() == false)
                    break;
                
                text = scan.nextLine();
                x++;
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found!");
        }
        if(text.length()<1)
            return 0;
            
        return Integer.parseInt(text);
        
    }
    
    public static void saveHighScore(Player player) {
        Highscores h = null;
        File file = new File("highscores.txt");
        try {
            Scanner scan = new Scanner(file);
            String text = "";
            while (scan.hasNextLine()) {
                text += scan.nextLine() + "\n";
            }
            h = h.fromString(text.trim());

        } catch (FileNotFoundException e) {
            System.err.println("File not found!");
        }

        if (h == null) {
            h = Highscores.makeEmpty();
        }
        
        h.addHighscore(player);

        try {
            FileWriter fw = null;
            try {
                fw = new FileWriter(file);
            } catch (IOException ex) {
                Logger.getLogger(Breakout.class.getName()).log(Level.SEVERE, null, ex);
            }
            try (BufferedWriter bw = new BufferedWriter(fw)) {
                bw.write(h.toString());
            }
        } catch (IOException ex) {
            System.err.println("file not found");
        }
    }
    
    public static void paintHighScores(Graphics g, JPanel panel) {
        Highscores h = new Highscores();
        g.clearRect(0, 0, panel.getWidth(), panel.getHeight());
        File file = new File("highscores.txt");
        try {
            Scanner scan = new Scanner(file);
            String text = "";
            while (scan.hasNextLine()) {
                text += scan.nextLine() + "\n";
            }
            h = Highscores.fromString(text.trim());

        } catch (FileNotFoundException e) {
            System.err.println("File not found!");
        }

        if (h == null) {
            h = Highscores.makeEmpty();
        }

        //use number of spaces you need as the argument to h.display
        g.setColor(Color.green);
        Font font = new Font("Arial", 1, 40);
        FontMetrics fontMetrics = g.getFontMetrics(font);
        g.setFont(font);
        int fontLength = 0;
        fontLength = fontMetrics.stringWidth("HIGHSCORES");
        g.drawString("HIGHSCORES", (panel.getWidth() / 2) - fontLength / 2, 70);
        font = new Font("Arial", 1, 25);
        g.setFont(font);
        int spacing = 10;
        for (String s : h.display(1)) {
            fontLength = fontMetrics.stringWidth(s);
            g.drawString(s, (panel.getWidth() / 2) - fontLength/3, 100 + spacing);
            spacing += 35;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Iterator it = scoreboard.iterator();
        int x = 0;
        while(it.hasNext()) {
            if(x == 10)
                break;
            sb.append(it.next().toString());
            sb.append("\n");
            x++;
        }
        /*
        for (Player p : scoreboard) {
            sb.append(p.toString());
            sb.append("\n");
        }
        */
        return sb.toString();
    }
    
    
}
