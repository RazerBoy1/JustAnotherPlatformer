package com.meandi.justanotherplatformer.UI;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.meandi.justanotherplatformer.Helpers.Assets;
import com.meandi.justanotherplatformer.JustAnotherPlatformer;

import java.util.Locale;

public class Hud implements Disposable {
    public Stage stage;

    private float timeCount;
    private Integer worldTimer;
    private Integer score;

    Label countdownLabel;
    Label scoreLabel;

    public Hud(SpriteBatch sb, Assets assets) {
        stage = new Stage(new StretchViewport(JustAnotherPlatformer.WIDTH, JustAnotherPlatformer.HEIGHT), sb);

        worldTimer = 0;
        score = 0;
        countdownLabel = new Label(String.format(Locale.ENGLISH, "%d", worldTimer), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        scoreLabel = new Label(String.format(Locale.ENGLISH, "%d", score), new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        Texture hearth = assets.manager.get(Assets.HEARTS);
        Image heartImage1 = new Image(hearth);
        Image heartImage2 = new Image(hearth);
        Image heartImage3 = new Image(hearth);

        Table t1 = new Table();
        t1.top().left();
        t1.setFillParent(true);

        t1.add(heartImage1).padTop(3).padLeft(5);
        t1.add(heartImage2).padTop(3).padLeft(5);
        t1.add(heartImage3).padTop(3).padLeft(5);

        Table t2 = new Table();
        t2.top();
        t2.setFillParent(true);

        t2.add(countdownLabel).padTop(3).center();

        Table t3 = new Table();
        t3.top().right();
        t3.setFillParent(true);

        t3.add(scoreLabel).padTop(3).padRight(5);

        stage.addActor(t1);
        stage.addActor(t2);
        stage.addActor(t3);
    }

    public void update(float delta) {
        timeCount += delta;

        if (timeCount >= 1) {
            worldTimer++;
            timeCount--;
            countdownLabel.setText(String.format(Locale.ENGLISH, "%d", worldTimer));
        }
    }

    public void addScore(int value) {
        score += value;
        scoreLabel.setText(String.format(Locale.ENGLISH, "%d", score));
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
