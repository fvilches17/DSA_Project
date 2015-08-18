package DSA_Project.Breakout;

/**
 *
 * @author olga
 */
public class PlayerScore implements Comparable<PlayerScore> {

	private final String name;
	private final int score;
	//enum
	

	private PlayerScore (String name, int score) {
		this.name = name;
		this.score = score;
	}

	public static PlayerScore fromPlayer (Player p) {
		if (p == null) {
			throw new IllegalArgumentException ("P cannot be null.");
		}
		return new PlayerScore(p.name(), p.score());
	}

	public static PlayerScore fromString (String s) {
		if (s == null) {
			throw new IllegalArgumentException ("s cannot be null");
		}
		if (s.length() == 0) {
			throw new IllegalArgumentException ("s cannot be empty");
		}
		String[] split = s.split("\n");
		if (split.length != 2) {
			throw new IllegalArgumentException ("s should be two strings separated by new line.");
		}
		if (split[0].equals("")) {
			throw new IllegalArgumentException ("First part should not be empty.");
		}
		try {
			int i = Integer.parseInt(split[1]);
			if (i <= 0) {
				throw new IllegalArgumentException ("Second part must be a positive integer!");
			}
			return new PlayerScore(split[0], i);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException ("Second part must be an integer!");
		}
	}

        @Override
	public int compareTo(PlayerScore ps) {
		if (score < ps.score) {
            return -1;
        }
        if (score > ps.score) {
            return 1;
        }
        return 0;
	}

	public String representation() {
		return "";
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof PlayerScore) {
			PlayerScore ps = (PlayerScore) o;
			if (ps.name.equals(name) && ps.score == score) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 23 * hash + name.hashCode();
		hash = 23 * hash + score;
		return hash;
	}

	@Override
	public String toString() {
		return  name + "\n" + score;
	}
}
