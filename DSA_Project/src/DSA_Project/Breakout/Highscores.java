package DSA_Project.Breakout;

import java.util.*;

/**
 *
 * @author olga
 */
public class Highscores {

	private SortedSet<PlayerScore> scoreboard;

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
		Highscores h = Highscores.makeEmpty();
		String[] split = s.split("\n");
		if (split.length %2 == 1) {
			throw new IllegalArgumentException ("s should have an even number of lines.");
		}
		if (split.length > 20) {
			throw new IllegalArgumentException ("s has too many lines.");
		}
		String buff = "";
		for (int i = 0; i < split.length; i++) {
			if(i %2 == 0) {
				buff = split[i];
			}
			else {
				h.scoreboard.add(PlayerScore.fromString(buff + split[i].trim()));
			}
		}
		return h;
	}

	public PlayerScore[] scores() {
		PlayerScore[] ps = new PlayerScore[scoreboard.size()];
		int i = 0;
		for (PlayerScore p : scoreboard) {
			ps[i] = p;
			i++;
		}
		return ps;		
	}

	public void addHighscore (Player p) {
		PlayerScore ps = PlayerScore.fromPlayer(p);
		scoreboard.add(ps);
		if (scoreboard.size() == 11) {
			scoreboard.remove(scoreboard.last());
		}
	}

	@Override
	public String toString () {
		StringBuilder sb = new StringBuilder();
		for (PlayerScore ps : scoreboard) {
			sb.append(ps.toString() + "\n");
		}
		return sb.toString();
	}
}
