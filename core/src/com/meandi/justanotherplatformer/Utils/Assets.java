package com.meandi.justanotherplatformer.Utils;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Disposable;

public class Assets implements Disposable {
    public AssetManager manager;

    private static final String BACKGROUND_PATH = "background.jpg";
    private static final String HEARTS_PATH = "world/hearts.png";
    private static final String HEALTH_POTION_PATH = "items/health_potion.png";
    private static final String BUTTON_RIGHT_PATH = "gamePad/button_right.png";
    private static final String BUTTON_LEFT_PATH = "gamePad/button_left.png";
    private static final String BUTTON_UP_PATH = "gamePad/button_up.png";
    private static final String BUTTON_DOWN_PATH = "gamePad/button_down.png";
    private static final String HERO_ATLAS_PATH = "hero/hero.pack";
    private static final String SLIME_ATLAS_PATH = "slime/slime.pack";
    private static final String MUSIC_PATH = "audio/music.ogg";
    private static final String COIN_PATH = "audio/coin.wav";
    private static final String JUMP_PATH = "audio/jump.wav";
    private static final String BUMP_WITH_ENEMY_PATH = "audio/bump_with_enemy.wav";
    private static final String BUMP_HERO_HEAD_PATH = "audio/bump_hero_head.wav";
    private static final String BUMP_ENEMY_ON_HEAD_PATH = "audio/bump_enemy_on_head.mp3";
    private static final String BLOCK_BREAK_PATH = "audio/block_break.wav";
    private static final String DEATH_PATH = "audio/death.wav";
    private static final String HEALTH_POTION_SOUND_PATH = "audio/health_potion.wav";
    private static final String LEVEL_COMPLETE_PATH = "audio/level_complete.wav";

    public static final AssetDescriptor<Texture> BACKGROUND = new AssetDescriptor<>(BACKGROUND_PATH, Texture.class);
    public static final AssetDescriptor<Texture> HEARTS = new AssetDescriptor<>(HEARTS_PATH, Texture.class);
    public static final AssetDescriptor<Texture> HEALTH_POTION = new AssetDescriptor<>(HEALTH_POTION_PATH, Texture.class);
    public static final AssetDescriptor<Texture> BUTTON_RIGHT = new AssetDescriptor<>(BUTTON_RIGHT_PATH, Texture.class);
    public static final AssetDescriptor<Texture> BUTTON_LEFT = new AssetDescriptor<>(BUTTON_LEFT_PATH, Texture.class);
    public static final AssetDescriptor<Texture> BUTTON_UP = new AssetDescriptor<>(BUTTON_UP_PATH, Texture.class);
    public static final AssetDescriptor<Texture> BUTTON_DOWN = new AssetDescriptor<>(BUTTON_DOWN_PATH, Texture.class);
    public static final AssetDescriptor<TextureAtlas> HERO_ATLAS = new AssetDescriptor<>(HERO_ATLAS_PATH, TextureAtlas.class);
    public static final AssetDescriptor<TextureAtlas> SLIME_ATLAS = new AssetDescriptor<>(SLIME_ATLAS_PATH, TextureAtlas.class);
    public static final AssetDescriptor<Music> MUSIC = new AssetDescriptor<>(MUSIC_PATH, Music.class);
    public static final AssetDescriptor<Sound> COIN = new AssetDescriptor<>(COIN_PATH, Sound.class);
    public static final AssetDescriptor<Sound> JUMP = new AssetDescriptor<>(JUMP_PATH, Sound.class);
    public static final AssetDescriptor<Sound> BUMP_WITH_ENEMY = new AssetDescriptor<>(BUMP_WITH_ENEMY_PATH, Sound.class);
    public static final AssetDescriptor<Sound> BUMP_HERO_HEAD = new AssetDescriptor<>(BUMP_HERO_HEAD_PATH, Sound.class);
    public static final AssetDescriptor<Sound> BUMP_ENEMY_ON_HEAD = new AssetDescriptor<>(BUMP_ENEMY_ON_HEAD_PATH, Sound.class);
    public static final AssetDescriptor<Sound> BLOCK_BREAK = new AssetDescriptor<>(BLOCK_BREAK_PATH, Sound.class);
    public static final AssetDescriptor<Sound> DEATH = new AssetDescriptor<>(DEATH_PATH, Sound.class);
    public static final AssetDescriptor<Sound> HEALTH_POTION_SOUND = new AssetDescriptor<>(HEALTH_POTION_SOUND_PATH, Sound.class);
    public static final AssetDescriptor<Sound> LEVEL_COMPLETE = new AssetDescriptor<>(LEVEL_COMPLETE_PATH, Sound.class);

    public Assets() {
        manager = new AssetManager();
    }

    public void load() {
        manager.load(BACKGROUND);
        manager.load(HEARTS);
        manager.load(HEALTH_POTION);
        manager.load(BUTTON_RIGHT);
        manager.load(BUTTON_LEFT);
        manager.load(BUTTON_UP);
        manager.load(BUTTON_DOWN);
        manager.load(HERO_ATLAS);
        manager.load(SLIME_ATLAS);
        manager.load(MUSIC);
        manager.load(COIN);
        manager.load(JUMP);
        manager.load(BUMP_WITH_ENEMY);
        manager.load(BUMP_HERO_HEAD);
        manager.load(BUMP_ENEMY_ON_HEAD);
        manager.load(BLOCK_BREAK);
        manager.load(DEATH);
        manager.load(HEALTH_POTION_SOUND);
        manager.load(LEVEL_COMPLETE);

        manager.finishLoading();
    }

    public void playMusic() {
        Music music = manager.get(MUSIC);
        music.setLooping(true);
        music.setVolume(0.4f);
        music.play();
    }

    @Override
    public void dispose() {
        manager.dispose();
    }
}
