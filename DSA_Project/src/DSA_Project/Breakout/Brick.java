package DSA_Project.Breakout;

import java.awt.Color;

public class Brick extends Block {

    //FIELDS--------------------------------------------------------------------

    private int score;
    private boolean isSpecial;

    //CONSTRUCTOR---------------------------------------------------------------
    public Brick(int locationX, int locationY, int width, int height, Color color, int score) {
        super(locationX, locationY, width, height, color);
        this.score = score;
        this.isSpecial = false;
    }

    //GETTERS-------------------------------------------------------------------
    public int getScore() {
        return score;
    }

    public boolean isSpecial() {
        return isSpecial;
    }

    //SETTERS-------------------------------------------------------------------
    public void setScore(int score) {
        this.score = score;
    }

    public void setIsSpecial(boolean isSpecial) {
        this.isSpecial = isSpecial;
    }

    //METHODS
    @Override
    public String toString() {
        return "Brick{" + "score=" + score + ", isSpecial=" + isSpecial + '}';
    }

}
