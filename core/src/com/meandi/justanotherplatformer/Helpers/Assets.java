package com.meandi.justanotherplatformer.Helpers;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Disposable;

public class Assets implements Disposable {
    public AssetManager manager;

    private static final String HEARTS_PATH = "world/hearts.png";
    public static final AssetDescriptor<Texture> HEARTS = new AssetDescriptor<>(HEARTS_PATH, Texture.class);

    private static final String HERO_ATLAS_PATH = "hero/hero.pack";
    public static final AssetDescriptor<TextureAtlas> HERO_ATLAS = new AssetDescriptor<>(HERO_ATLAS_PATH, TextureAtlas.class);

    private static final String MUSIC_PATH = "audio/music.ogg";
    public static final AssetDescriptor<Music> MUSIC = new AssetDescriptor<>(MUSIC_PATH, Music.class);

    private static final String COIN_PATH = "audio/coin.wav";
    public static final AssetDescriptor<Sound> COIN = new AssetDescriptor<>(COIN_PATH, Sound.class);

    private static final String JUMP_PATH = "audio/jump.wav";
    public static final AssetDescriptor<Sound> JUMP = new AssetDescriptor<>(JUMP_PATH, Sound.class);

    public Assets() {
        manager = new AssetManager();
    }

    public void load() {
        manager.load(HEARTS);
        manager.load(HERO_ATLAS);
        manager.load(MUSIC);
        manager.load(COIN);
        manager.load(JUMP);

        manager.finishLoading();
    }

    @Override
    public void dispose() {
        manager.dispose();
    }
}
