package com.meandi.justanotherplatformer;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.meandi.justanotherplatformer.UI.GameScreen;

public class JustAnotherPlatformer extends Game {
    public static final int WIDTH = 301;
    public static final int HEIGHT = 152;

    public static final float GRAVITY = -10;
    public static final float PPT = 150;
    public static final float ITEM_SCALE = 0.5f;

    public static final String GRAPHICS_LAYER = "graphics_ground";
    public static final String SKY_LAYER = "sky";
    public static final String SLIME_LAYER = "slimes";
    public static final String MOSS_LAYER = "moss";
    public static final String DOOR_LAYER = "doors";
    public static final String COIN_LAYER = "coins";
    public static final String GROUND_LAYER = "ground";

    public static final String[] WORLD_LAYERS = {GRAPHICS_LAYER, SLIME_LAYER, MOSS_LAYER, DOOR_LAYER, COIN_LAYER, GROUND_LAYER};

    public static final short DEFAULT_BIT = 1;
    public static final short HERO_BIT = 2;
    public static final short HERO_HEAD_BIT = 4;
    public static final short ENEMY_BIT = 8;
    public static final short ENEMY_HEAD_BIT = 16;
    public static final short COIN_BIT = 32;
    public static final short MOSS_BIT = 64;
    public static final short DOOR_BIT = 128;
    public static final short ITEM_BIT = 256;
    public static final short OBJECT_BIT = 512;
    public static final short REMOVED_BIT = 1024;

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
