package DSA_Project.Breakout;

/**
 *
 * @author olga
 */
public class Player {

	private final String name;
	private int score;

	private Player (String name) {
		this.name = name;
		score = 0;
	}

	public static Player fromString (String name) {
		if (name == null) {
			throw new IllegalArgumentException ("Name cannot be null");
		}
		if (name.length() == 0) {
			throw new IllegalArgumentException ("Name cannot be empty");
		}
		return new Player (name);
	}

	public String name() {
		return name;
	}

	public int score() {
		return score;
	}

	public void addToScore (int i) {
		if (i <= 0) {
			throw new IllegalArgumentException ("I must be positive!");
		}
		score += i;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Player) {
			Player p = (Player) o;
			if (p.name.equals(name)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int hashCode() {
		return name.hashCode();
	}

	@Override
	public String toString() {
		return "Player name: " + name + ", score: " + score;
	}

}
