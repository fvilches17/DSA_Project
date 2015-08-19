package DSA_Project.Breakout;

import java.util.*;

/**
 *
 * @author olga
 */
public class Highscores {

	private static SortedSet<Player> scoreboard;

	private Highscores() {}

	public static Highscores makeEmpty() {
		Highscores h = new Highscores();
		h.scoreboard = new TreeSet<>();
		return h;
	}

	public static Highscores fromString (String s) {
		if (s == null) {
			throw new IllegalArgumentException ("s cannot be null");
		}
		if (s.length() == 0) {
			throw new IllegalArgumentException ("s cannot be empty");
		}
                /////this one causes problem, I'll fix tomorrow
		Highscores h = Highscores.makeEmpty();
		String[] split = s.split("\n");
		if (split.length %2 == 0) {
			throw new IllegalArgumentException ("s should have an even number of lines.");
		}
		if (split.length > 20) {
			throw new IllegalArgumentException ("s has too many lines.");
		}
		String buff = "";
		for (int i = 0; i < split.length; i++) {
			if(i %2 == 1) {
				buff = split[i];
			}
			else {
				h.scoreboard.add(Player.fromStringFile(buff + split[i].trim()));
			}
		}
		return h;
	}

	public static Player[] scores() {
		Player[] ps = new Player[scoreboard.size()];
		int i = 0;
		for (Player p : scoreboard) {
			ps[i] = p;
			i++;
		}
		return ps;		
	}

	public static void addHighscore (Player p) {
		scoreboard.add(p);
		if (scoreboard.size() == 11) {
			scoreboard.remove(scoreboard.last());
		}
	}

        public static String display() {
		StringBuilder sb = new StringBuilder();
		for (Player p : scoreboard) {
			sb.append(p.toString());
                        sb.append("\n");
		}
		return sb.toString();
        }
        
	@Override
	public String toString () {
		StringBuilder sb = new StringBuilder();
		for (Player p : scoreboard) {
			sb.append(p.toString());
                        sb.append("\n");
		}
		return sb.toString();
	}
}
