package com.meandi.justanotherplatformer.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.meandi.justanotherplatformer.JustAnotherPlatformer;
import com.meandi.justanotherplatformer.Utils.Storage;

import java.util.Locale;

import static com.meandi.justanotherplatformer.JustAnotherPlatformer.SKIN_PATH;

public class GameFinishedScreen extends GeneralScreen {
    private final Stage stage;
    private final int finalScore;

    public GameFinishedScreen(final JustAnotherPlatformer jap, int score, boolean isCompleted) {
        super(jap);
        stage = new Stage(port, this.jap.spriteBatch);
        finalScore = Math.max(score, 0);

        Skin skin = new Skin(Gdx.files.internal(SKIN_PATH));
        skin.getFont("font").getData().setScale(0.5f);

        Table t = new Table();
        t.center();
        t.setFillParent(true);

        Label finishLabel;
        Label scoreLabel = new Label(String.format(Locale.ENGLISH, "SCORE: %d", finalScore), skin);

        if (isCompleted)
            finishLabel = new Label("LEVEL COMPLETED", skin);
        else
            finishLabel = new Label("GAME OVER", skin);

        t.add(finishLabel).expandX();
        t.row();
        t.add(scoreLabel).padTop(50);

        stage.addActor(t);
    }

    @Override
    public void render(float delta) {
        Storage storage = new Storage();
        storage.load();

        if (Gdx.input.justTouched()) {
            if (storage.getHighScoresController().isHighScore(finalScore))
                jap.setScreen(new InputHighScoreScreen(jap, storage, finalScore));
            else
                jap.setScreen(new MainMenuScreen(jap));

            dispose();
        }

        clearScreen();
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
