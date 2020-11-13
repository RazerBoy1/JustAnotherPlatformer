package com.meandi.justanotherplatformer.Utils;

import java.util.Arrays;

public class HighScoresController {
    private final int scoresCount;
    private final HighScore[] highScores;

    public HighScoresController() {
        scoresCount = 11;
        highScores = new HighScore[scoresCount];

        init();
    }

    private void init() {
        for (int i = 0; i < scoresCount; i++)
            highScores[i] = new HighScore("---" , 0);
    }

    public boolean isHighScore(int score) {
        return score > highScores[scoresCount - 1].getScore();
    }

    public void addHighScore(String name, int newScore) {
        highScores[scoresCount - 1].setScore(newScore);
        highScores[scoresCount - 1].setName(name);
        highScores[scoresCount - 1].setEntry(name + " - " + newScore);

        Arrays.sort(highScores, HighScore.scoreComparator);
    }

    public HighScore[] getHighScores() {
        return highScores;
    }
}
