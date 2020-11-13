package com.meandi.justanotherplatformer.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.meandi.justanotherplatformer.JustAnotherPlatformer;
import com.meandi.justanotherplatformer.Overlays.Hud;
import com.meandi.justanotherplatformer.Utils.Storage;

import java.util.Locale;

public class GameFinishedScreen extends GeneralScreen {
    private final Stage stage;
    private final Skin skin;
    private final int score;
    private final int worldTimer;

    public GameFinishedScreen(final JustAnotherPlatformer jap, Hud hud, boolean isCompleted) {
        super(jap);
        stage = new Stage(port, this.jap.spriteBatch);
        this.score = hud.getScore();
        this.worldTimer = hud.getWorldTimer();

        skin = new Skin(Gdx.files.internal("skin/craftacular-ui.json"));
        skin.getFont("font").getData().setScale(0.5f);

        Table t = new Table();
        t.center();
        t.setFillParent(true);

        Label finishLabel;
        Label scoreLabel = new Label(String.format(Locale.ENGLISH, "SCORE: %d", score), skin);

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
            if (storage.getHighScoresController().isHighScore(score))
                jap.setScreen(new InputHighScoreScreen(jap, storage, score - (worldTimer * 5)));
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
