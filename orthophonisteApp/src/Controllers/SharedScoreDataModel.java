package Controllers;
import Classes.Test;

import java.util.HashMap;
import java.util.Map;

public class SharedScoreDataModel {
    private static SharedScoreDataModel instance = new SharedScoreDataModel();
    private Map<Test, Integer> testScores = new HashMap<>();

    private SharedScoreDataModel() {}

    public static SharedScoreDataModel getInstance() {
        return instance;
    }

    public Map<Test, Integer> getTestScores() {
        return new HashMap<>(testScores);
    }

    public void setTestScore(Test test, int score) {
        testScores.put(test, score);
    }
}

