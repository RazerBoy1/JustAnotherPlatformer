package com.meandi.justanotherplatformer;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Hud implements Disposable {
    public Stage stage;
    private Viewport viewport;

    private Integer worldTimer;
    private Integer score;

    Label countdownLabel;
    Texture hearth;

    public Hud(SpriteBatch sb) {
        viewport = new FitViewport(JustAnotherPlatformer.WIDTH, JustAnotherPlatformer.HEIGHT);
        stage = new Stage(viewport, sb);

        worldTimer = 0;
        score = 0;

        countdownLabel = new Label(String.format("%d", worldTimer), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        hearth = new Texture("world/hearts.png");
        Image heartImage1 = new Image(hearth);
        Image heartImage2 = new Image(hearth);
        Image heartImage3 = new Image(hearth);

        Table t1 = new Table();
        t1.top().left();
        t1.setFillParent(true);

        t1.add(heartImage1).padTop(10).padLeft(4);
        t1.add(heartImage2).padTop(10).padLeft(4);
        t1.add(heartImage3).padTop(10).padLeft(4);

        Table t2 = new Table();
        t2.top();
        t2.setFillParent(true);
        t2.add(countdownLabel).padTop(10).center();

        stage.addActor(t1);
        stage.addActor(t2);
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
