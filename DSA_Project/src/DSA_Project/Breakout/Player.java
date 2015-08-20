package DSA_Project.Breakout;

/**
 *
 * @author olga
 */
public class Player implements Comparable<Player> {
    //FIELDS--------------------------------------------------------------------
    private String name;
    private int score;
    
    //CONSTRUCTOR---------------------------------------------------------------
    public Player(String name, int score) {
        this.name = name;
        this.score = score;
    }
    //GETTERS-------------------------------------------------------------------
    public String name() {
        return name;
    }

    public int score() {
        return score;
    }
    //SETTERS-------------------------------------------------------------------
    public void addToScore(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("Score must be 0 or greater!");
        }
        score += i;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    //METHODS-------------------------------------------------------------------
    public static Player fromInput(String s) {
        if (s == null) {
            throw new IllegalArgumentException("s cannot be null");
        }
        if (s.length() == 0) {
            throw new IllegalArgumentException("s cannot be empty");
        }
        return new Player(s, 0);
    }

    public static Player fromStringFile(String s) {
        if (s == null) {
            throw new IllegalArgumentException("s cannot be null");
        }
        if (s.length() == 0) {
            throw new IllegalArgumentException("s cannot be empty");
        }
        String[] split = s.split("\n");
        if (split.length != 2) {
            throw new IllegalArgumentException("s should be two strings separated by new line.");
        }
        if (split[0].equals("")) {
            throw new IllegalArgumentException("First part should not be empty.");
        }
        try {
            int i = Integer.parseInt(split[1]);
            if (i < 0) {
                throw new IllegalArgumentException("Second part must be a positive integer!");
            }
            return new Player(split[0], i);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Second part must be an integer!");
        }
    }

    @Override
    public int compareTo(Player p) {
        if (score > p.score) {
            return -1;
        }
        if (score < p.score) {
            return 1;
        }
        return 1;
    }

    public String representation() {
        return "Player: " + name() + ", score: " + score();
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Player) {
            Player ps = (Player) o;
            if (ps.score == score) {
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
        return name + "\n" + score;
    }
}
