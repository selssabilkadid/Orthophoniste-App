package Controllers;

import Classes.Test;

import java.util.HashMap;
import java.util.Map;

public class SharedScoreDataModel {
    private static SharedScoreDataModel instance;
    private Map<Test, Integer> testScores;

    private SharedScoreDataModel() {
        testScores = new HashMap<>();
    }

    public static SharedScoreDataModel getInstance() {
        if (instance == null) {
            instance = new SharedScoreDataModel();
        }
        return instance;
    }

    public void setTestScore(Test test, int score) {
        testScores.put(test, score);
    }

    public Map<Test, Integer> getTestScores() {
        return new HashMap<>(testScores);
    }
}
