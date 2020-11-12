package com.meandi.justanotherplatformer.Utils;

import java.io.Serializable;
import java.util.Arrays;

public class HighScoresController implements Serializable {
    private final int scoresCount;
    private final HighScoreEntry[] highScoreEntries;

    public HighScoresController() {
        scoresCount = 11;
        highScoreEntries = new HighScoreEntry[scoresCount];

        init();
    }

    private void init() {
        for (int i = 0; i < scoresCount; i++)
            highScoreEntries[i] = new HighScoreEntry("---" , 0);
    }

    public boolean isHighScore(int score) {
        return score > highScoreEntries[scoresCount - 1].getScore();
    }

    public void addHighScore(String name, int newScore) {
        highScoreEntries[scoresCount - 1].setScore(newScore);
        highScoreEntries[scoresCount - 1].setName(name);
        highScoreEntries[scoresCount - 1].setEntry(name + " - " + newScore);

        Arrays.sort(highScoreEntries, HighScoreEntry.scoreComparator);


    }

    public HighScoreEntry[] getHighScoreEntries() {
        return highScoreEntries;
    }
}
