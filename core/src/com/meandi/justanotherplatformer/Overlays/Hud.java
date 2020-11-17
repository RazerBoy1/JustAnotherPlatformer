package com.meandi.justanotherplatformer.Overlays;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.meandi.justanotherplatformer.Screens.GameScreen;
import com.meandi.justanotherplatformer.Utils.Assets;
import com.meandi.justanotherplatformer.JustAnotherPlatformer;

import java.util.Locale;

public class Hud implements Disposable {
    public Stage stage;

    private final GameScreen screen;

    private float timeCount;
    private int worldTimer;
    private int score;

    private final Label countdownLabel;
    private final Label scoreLabel;

    Image heartImage1, heartImage2, heartImage3;
    int hearthCount;

    Table t1;

    public Hud(GameScreen screen) {
        stage = new Stage(new StretchViewport(JustAnotherPlatformer.WIDTH, JustAnotherPlatformer.HEIGHT), screen.getJustAnotherPlatformer().spriteBatch);

        this.screen = screen;

        Skin skin = new Skin(Gdx.files.internal("skin/craftacular-ui.json"));
        skin.getFont("font").getData().setScale(0.5f);

        worldTimer = 0;
        score = 0;
        hearthCount = 3;
        countdownLabel = new Label(String.format(Locale.ENGLISH, "%d", worldTimer), skin);
        scoreLabel = new Label(String.format(Locale.ENGLISH, "%d", score), skin);

        Texture hearth = screen.getAssets().manager.get(Assets.HEARTS);
        heartImage1 = new Image(hearth);
        heartImage2 = new Image(hearth);
        heartImage3 = new Image(hearth);

        t1 = new Table();
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

        if (timeCount >= 1 && screen.getHero().isDone()) {
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

    public int getScore() { return score; }

    public int getWorldTimer() {
        return worldTimer;
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
