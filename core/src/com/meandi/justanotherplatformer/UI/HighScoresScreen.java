package com.meandi.justanotherplatformer.UI;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.meandi.justanotherplatformer.JustAnotherPlatformer;

public class HighScoresScreen extends GeneralScreen {
    private final Stage stage;

    public HighScoresScreen(JustAnotherPlatformer jap) {
        super(jap);
        stage = new Stage(port, this.jap.batch);
    }

    @Override
    public void render(float delta) {
        clearScreen();
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
