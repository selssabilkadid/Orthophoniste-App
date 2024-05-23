package Classes;

import java.util.ArrayList;

public class ObjectifEvalue extends Objectif {
    private static final long serialVersionUID = 1L;
    private ArrayList<Integer> scores;

    public ObjectifEvalue(String nom, TypeObjectif typeObjectif) {
        super(nom, typeObjectif);
        scores = new ArrayList<>();
    }

    public ArrayList<Integer> getScores() {
        return scores;
    }

    public void addScore(int score) {
        scores.add(score);
    }
}
