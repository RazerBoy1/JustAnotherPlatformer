package com.meandi.justanotherplatformer.UI;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.meandi.justanotherplatformer.JustAnotherPlatformer;

public class GameOverScreen implements Screen {
    private final Viewport port;
    private final Stage stage;

    private final Game jap;

    public GameOverScreen(Game jap) {
        this.jap = jap;
        port = new StretchViewport(JustAnotherPlatformer.WIDTH / JustAnotherPlatformer.PPT, JustAnotherPlatformer.HEIGHT / JustAnotherPlatformer.PPT, new OrthographicCamera());
        stage = new Stage(port, ((JustAnotherPlatformer) this.jap).batch);

        Label.LabelStyle font = new Label.LabelStyle(new BitmapFont(), Color.WHITE);

        Table t = new Table();
        t.center();
        t.setFillParent(true);

        Label gameOverLabel = new Label("GAME OVER", font);
        Label playAgain = new Label("Play again", font);

        t.add(gameOverLabel).expandX();
        t.row();
        t.add(playAgain).expandX().padTop(20);

        stage.addActor(t);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        if (Gdx.input.justTouched()) {
            jap.setScreen(new GameScreen((JustAnotherPlatformer) jap));
            dispose();
        }

        clearScreen();
        stage.draw();
    }

    private void clearScreen() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose()
        ;
    }
}
