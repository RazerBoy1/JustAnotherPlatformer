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

public class GameOverScreen extends GeneralScreen {
    private final Stage stage;
    private final Skin skin;
    private final int score;
    private final int worldTimer;

    public GameOverScreen(final JustAnotherPlatformer jap, Hud hud) {
        super(jap);
        stage = new Stage(port, this.jap.spriteBatch);
        this.score = hud.getScore();
        this.worldTimer = hud.getWorldTimer();

        skin = new Skin(Gdx.files.internal("menu/craftacular-ui.json"));

        Table t = new Table();
        t.center();
        t.setFillParent(true);

        Label gameOverLabel = new Label("GAME OVER", skin);
        Label scoreLabel = new Label(String.format(Locale.ENGLISH, "SCORE: %d", score), skin);

        t.add(gameOverLabel).expandX();
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
