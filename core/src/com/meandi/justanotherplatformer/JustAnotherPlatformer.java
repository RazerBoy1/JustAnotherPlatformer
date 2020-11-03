package com.meandi.justanotherplatformer;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.meandi.justanotherplatformer.UI.GameScreen;

public class JustAnotherPlatformer extends Game {
    public static final int WIDTH = 301;
    public static final int HEIGHT = 152;

    public static final float GRAVITY = -10;

    public static final float PPT = 150;
    public static final float TILE_SIZE = 8;
    public static final int FRAME_SIZE = 16;

    public static final int GROUND_LAYER = 0;
    public static final int COIN_LAYER = 1;
    public static final int DOOR_LAYER = 2;
    public static final int MOSS_LAYER = 3;
    public static final int GRAPHICS_LAYER = 5;
    public static final int[] WORLD_LAYERS = {GROUND_LAYER, COIN_LAYER, DOOR_LAYER, MOSS_LAYER};

    public static final short DEFAULT_BIT = 1;
    public static final short HERO_BIT = 2;
    public static final short COIN_BIT = 4;
    public static final short MOSS_BIT = 8;
    public static final short DOOR_BIT = 16;
    public static final short REMOVED_BIT = 32;

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
