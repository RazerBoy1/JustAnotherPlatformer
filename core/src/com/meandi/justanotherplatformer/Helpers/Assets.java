package com.meandi.justanotherplatformer.Helpers;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Disposable;

public class Assets implements Disposable {
    public AssetManager manager;

    private static final String HEARTS_PATH = "world/hearts.png";
    public static final AssetDescriptor<Texture> HEARTS = new AssetDescriptor<>(HEARTS_PATH, Texture.class);

    private static final String HERO_ATLAS_PATH = "hero/hero.pack";
    public static final AssetDescriptor<TextureAtlas> HERO_ATLAS = new AssetDescriptor<>(HERO_ATLAS_PATH, TextureAtlas.class);

    public Assets() {
        manager = new AssetManager();
    }

    public void load() {
        manager.load(HEARTS);
        manager.load(HERO_ATLAS);
        manager.finishLoading();
    }

    @Override
    public void dispose() {
        manager.dispose();
    }
}
