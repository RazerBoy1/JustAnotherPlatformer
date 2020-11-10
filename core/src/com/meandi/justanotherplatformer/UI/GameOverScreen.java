package com.meandi.justanotherplatformer.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.meandi.justanotherplatformer.JustAnotherPlatformer;

public class GameOverScreen extends GeneralScreen {
    private final Stage stage;

    public GameOverScreen(JustAnotherPlatformer jap) {
        super(jap);
        stage = new Stage(port, this.jap.batch);

        Label.LabelStyle font = new Label.LabelStyle(new BitmapFont(), Color.WHITE);

        Table t = new Table();
        t.center();
        t.setFillParent(true);

        Label gameOverLabel = new Label("GAME OVER", font);

        t.add(gameOverLabel).expandX();

        stage.addActor(t);
    }

    @Override
    public void render(float delta) {
        if (Gdx.input.justTouched()) {
            jap.setScreen(new GameScreen(jap));
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
