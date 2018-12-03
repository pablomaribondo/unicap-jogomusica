package game.play;

import java.io.Serializable;

public class Player implements Serializable, Comparable<Player> {
    
    private static final long serialVersionUID = 7046110786340411747L;
    private String name;
    private int score;

    public Player(String name, int highscore) {
        this.name = name;
        this.score = highscore;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int highscore) {
        this.score = highscore;
    }

    @Override
    public int compareTo(Player p) {
        if(this.score < p.getScore()){
            return 1;
        }else if(this.score > p.getScore()){
            return -1;
        }
        return 0;
    }

    @Override
    public String toString() {
        return "Player{" + "name=" + name + ", score=" + score + '}';
    }
    
    
    
       
}
