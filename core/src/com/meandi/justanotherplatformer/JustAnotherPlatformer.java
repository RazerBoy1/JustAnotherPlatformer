package com.meandi.justanotherplatformer;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.meandi.justanotherplatformer.UI.GameScreen;

public class JustAnotherPlatformer extends Game {
    public static final int WIDTH = 301;
    public static final int HEIGHT = 152;
    public static final float PPT = 150;
    public SpriteBatch batch;

    @Override
    public void create() {
        batch = new SpriteBatch();
        setScreen(new GameScreen(this));
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
