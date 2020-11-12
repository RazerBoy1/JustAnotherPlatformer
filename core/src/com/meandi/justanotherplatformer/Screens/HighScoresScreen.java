package com.meandi.justanotherplatformer.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.meandi.justanotherplatformer.JustAnotherPlatformer;
import com.meandi.justanotherplatformer.Utils.HighScoresController;
import com.meandi.justanotherplatformer.Utils.HighScoreEntry;
import com.meandi.justanotherplatformer.Utils.Storage;

import java.util.Locale;

public class HighScoresScreen extends GeneralScreen {
    private final Stage stage;
    private final Skin skin;

    private final HighScoreEntry[] highScoreEntries;
    private final Storage storage;
    private final HighScoresController highScoresController;

    public HighScoresScreen(final JustAnotherPlatformer jap) {
        super(jap);
        stage = new Stage(port, this.jap.spriteBatch);
        skin = new Skin(Gdx.files.internal("menu/craftacular-ui.json"));
        skin.getFont("font").getData().setScale(0.5f);

        storage = new Storage();
        storage.load();

        highScoresController = storage.getHighScoresController();
        highScoreEntries = highScoresController.getHighScoreEntries();

        TextButton backButton = new TextButton("Back", skin);
        backButton.getLabel().setFontScale(0.25f);
        backButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                jap.setScreen(new MainMenuScreen(jap));
                return true;
            }
        });

        Table t1 = new Table();
        t1.left().bottom();
        t1.setFillParent(true);

        t1.add(backButton).height(30).width(45);

        Table t2 = new Table(skin);
        t2.center().left();
        t2.setFillParent(true);

        for (int i = 0; i < highScoreEntries.length - 1; i++) {
            t2.add(String.format(Locale.ENGLISH, "%2d. %-3s", i + 1, highScoreEntries[i].getName())).height(15).width(100).padLeft(75);
            t2.row();
        }

        Table t3 = new Table(skin);
        t3.center().right();
        t3.setFillParent(true);

        for (int i = 0; i < highScoreEntries.length - 1; i++) {
            t3.add(String.format(Locale.ENGLISH, "%10s", highScoreEntries[i].getScore())).height(15).width(110);
            t3.row();
        }


        stage.addActor(t1);
        stage.addActor(t2);
        stage.addActor(t3);

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
    }
}
