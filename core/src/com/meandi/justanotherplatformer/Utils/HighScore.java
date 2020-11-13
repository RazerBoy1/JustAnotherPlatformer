package com.meandi.justanotherplatformer.Utils;

import java.util.*;

public class HighScore implements Comparable<HighScore> {
    private String entry;
    private String name;
    private int score;

    public HighScore(String name, int score) {
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

    public static Comparator<HighScore> scoreComparator = new Comparator<HighScore>() {
        @Override
        public int compare(HighScore o1, HighScore o2) {
            return o1.compareTo(o2);
        }
    };

    @Override
    public int compareTo(HighScore entry) {
        return entry.getScore() - this.score;
    }
}
