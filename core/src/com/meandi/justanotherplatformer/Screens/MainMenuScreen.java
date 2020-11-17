package com.meandi.justanotherplatformer.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Scaling;
import com.meandi.justanotherplatformer.JustAnotherPlatformer;
import com.meandi.justanotherplatformer.Utils.Assets;

public class MainMenuScreen extends GeneralScreen {
    private final Stage stage;
    private final Skin skin;

    public MainMenuScreen(final JustAnotherPlatformer jap) {
        super(jap);
        stage = new Stage(port, this.jap.spriteBatch);
        skin = new Skin(Gdx.files.internal("skin/craftacular-ui.json"));
        Image background = new Image(assets.manager.get(Assets.BACKGROUND));
        background.setScaling(Scaling.fit);

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
                dispose();

                return true;
            }
        });

        highScoresButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                jap.setScreen(new HighScoresScreen(jap));
                dispose();

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

        Table t1 = new Table();
        t1.center();
        t1.setFillParent(true);

        t1.add(background);

        Table t2 = new Table();
        t2.center();
        t2.setFillParent(true);

        t2.add(playButton).height(45).width(120);
        t2.row();
        t2.add(highScoresButton).height(45).width(120).padTop(5);
        t2.row();
        t2.add(exitButton).height(45).width(120).padTop(5);

        stage.addActor(t1);
        stage.addActor(t2);

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
