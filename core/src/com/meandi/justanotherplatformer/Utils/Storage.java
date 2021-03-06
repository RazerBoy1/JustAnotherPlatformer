package com.meandi.justanotherplatformer.Utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class Storage {
    private final Preferences prefs;
    private final HighScoresController highScoresController;

    public Storage() {
        prefs = Gdx.app.getPreferences("High scores");
        highScoresController = new HighScoresController();
    }

    public void save(HighScore[] highScores) {
        for (int i = 0; i < highScores.length; i++)
            prefs.putString(Integer.toString(i), highScores[i].getEntry());

        prefs.flush();
    }

    public void load() {
        for (int i = 0; i < highScoresController.getHighScores().length; i++) {
            String preferenceName = Integer.toString(i);
            String entry = prefs.getString(preferenceName);

            if (!entry.equals("")) {
                String[] nameAndScore = entry.split(" - ");

                String name = nameAndScore[0];
                int score = Integer.parseInt(nameAndScore[1]);

                highScoresController.addHighScore(name, score);
            }
            else
                highScoresController.addHighScore("---", 0);
        }
    }

    public HighScoresController getHighScoresController() {
        return highScoresController;
    }
}
