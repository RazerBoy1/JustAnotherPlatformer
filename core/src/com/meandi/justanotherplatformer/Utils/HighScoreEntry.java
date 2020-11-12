package com.meandi.justanotherplatformer.Utils;

import java.util.*;

public class HighScoreEntry implements Comparable<HighScoreEntry> {
    private String entry;
    private String name;
    private int score;

    public HighScoreEntry(String name, int score) {
        this.name = name;
        this.score = score;

        entry = name + " - " + score;
    }

    public String getEntry() {
        return entry;
    }

    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }

    public void setEntry(String entry) {
        this.entry = entry;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static Comparator<HighScoreEntry> scoreComparator = new Comparator<HighScoreEntry>() {
        @Override
        public int compare(HighScoreEntry o1, HighScoreEntry o2) {
            return o1.compareTo(o2);
        }
    };

    @Override
    public int compareTo(HighScoreEntry entry) {
        return entry.getScore() - this.score;
    }
}
