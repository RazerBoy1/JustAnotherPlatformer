package com.meandi.justanotherplatformer.Overlay;

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
import com.meandi.justanotherplatformer.Utils.Assets;
import com.meandi.justanotherplatformer.JustAnotherPlatformer;

import java.util.Locale;

public class Hud implements Disposable {
    public Stage stage;

    private float timeCount;
    private Integer worldTimer;
    private Integer score;

    Label countdownLabel;
    Label scoreLabel;

    Image heartImage1, heartImage2, heartImage3;
    int hearthCount;

    Table t1;

    public Hud(SpriteBatch sb, Assets assets) {
        stage = new Stage(new StretchViewport(JustAnotherPlatformer.WIDTH, JustAnotherPlatformer.HEIGHT), sb);

        worldTimer = 0;
        score = 0;
        hearthCount = 3;
        Label.LabelStyle font = new Label.LabelStyle(new BitmapFont(), Color.WHITE);
        countdownLabel = new Label(String.format(Locale.ENGLISH, "%d", worldTimer), font);
        scoreLabel = new Label(String.format(Locale.ENGLISH, "%d", score), font);

        Texture hearth = assets.manager.get(Assets.HEARTS);
        heartImage1 = new Image(hearth);
        heartImage2 = new Image(hearth);
        heartImage3 = new Image(hearth);

        t1 = new Table();
        t1.top().left();
        t1.setFillParent(true);
        t1.debugAll();

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

    public void addHearth() {
        switch (hearthCount) {
            case 1:
                t1.addActorAt(1, heartImage2);
                break;
            case 2:
                t1.addActorAt(2, heartImage3);
                break;
        }

        hearthCount++;
    }

    public void removeHearth() {
        switch (hearthCount) {
            case 1:
                t1.removeActorAt(0, true);
                break;
            case 2:
                t1.removeActorAt(1, true);
                break;
            case 3:
                t1.removeActorAt(2, true);
                break;
        }

        hearthCount--;
    }

    public void removeAllHearths() {
        switch (hearthCount) {
            case 1:
                t1.removeActorAt(0, true);
                break;
            case 2:
                t1.removeActorAt(1, true);
                t1.removeActorAt(0, true);
                break;
            case 3:
                t1.removeActorAt(2, true);
                t1.removeActorAt(1, true);
                t1.removeActorAt(0, true);
                break;
        }

        hearthCount = 0;
    }

    public int getHearthCount() {
        return hearthCount;
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
