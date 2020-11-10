package com.meandi.justanotherplatformer.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.meandi.justanotherplatformer.JustAnotherPlatformer;

public class MenuScreen extends GeneralScreen {
    private final Stage stage;
    private final Skin skin;

    public MenuScreen(final JustAnotherPlatformer jap) {
        super(jap);
        stage = new Stage(port, this.jap.batch);

        skin = new Skin(Gdx.files.internal("menu/craftacular-ui.json"));

        TextButton playButton = new TextButton("Play", skin);
        TextButton highScoresButton = new TextButton("High scores", skin);
        TextButton exitButton = new TextButton("Exit", skin);

        playButton.getLabel().setFontScale(0.5f);
        highScoresButton.getLabel().setFontScale(0.5f);
        exitButton.getLabel().setFontScale(0.5f);

        playButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                jap.setScreen(new GameScreen(jap));
                return true;
            }
        });

        highScoresButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                jap.setScreen(new HighScoresScreen(jap));
                return true;
            }
        });

        exitButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.exit();
                return true;
            }
        });

        Table t = new Table();
        t.center();
        t.setFillParent(true);

        t.add(playButton).height(35).width(120);
        t.row();
        t.add(highScoresButton).height(35).width(120);
        t.row();
        t.add(exitButton).height(35).width(120);

        stage.addActor(t);

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        clearScreen();
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
    }
}
