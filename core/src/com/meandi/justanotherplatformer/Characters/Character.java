package com.meandi.justanotherplatformer.Characters;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.meandi.justanotherplatformer.JustAnotherPlatformer;
import com.meandi.justanotherplatformer.UI.GameScreen;

public abstract class Character extends Sprite {
    protected final GameScreen screen;
    protected final World world;
    public Body body;

    protected float stateTime;
    public State currentState;
    protected State previousState;

    protected boolean setToDestroy;
    protected boolean destroyed;

    public enum State {IDLING, DYING, RUNNING, JUMPING, DOUBLE_JUMPING, FALLING, PUSHING, ATTACKING, HIT}

    public Character(GameScreen screen, TextureRegion region) {
        this.screen = screen;
        world = screen.getWorld();

        stateTime = 0;
        setToDestroy = false;
        destroyed = false;

        setSprite(region);
        createCharacter();
        setBounds(0, 0, 16 / JustAnotherPlatformer.PPT, 16 / JustAnotherPlatformer.PPT);
    }

    public Character(GameScreen screen, TextureRegion region, float x, float y) {
        this.screen = screen;
        world = screen.getWorld();

        stateTime = 0;
        setToDestroy = false;
        destroyed = false;

        setPosition(x, y);
        setSprite(region);
        createCharacter();
        setBounds(0, 0, 16 / JustAnotherPlatformer.PPT, 16 / JustAnotherPlatformer.PPT);

        body.setActive(false);
    }

    protected abstract void createCharacter();
    protected abstract void createAnimations();
    protected abstract void updateSpritePosition(float delta);
    protected abstract TextureRegion getFrame();

    protected void setSprite(TextureRegion region) {
        setRegion(region);
        setColor(1, 1, 1, 1);
        setSize(region.getRegionWidth(), region.getRegionHeight());
        setOrigin(region.getRegionWidth() / 2f, region.getRegionHeight() / 2f);
    }

    protected Animation<TextureRegion> createAnimation(Array<TextureRegion> frames, int startY, int frameWidth, int frameHeight, int startFrame, int endFrame, float frameDuration) {
        if (frames.notEmpty())
            frames.clear();

        for (int i = startFrame; i < endFrame; i++)
            frames.add(new TextureRegion(getTexture(), i * frameWidth, startY, frameWidth, frameHeight));

        return new Animation<>(frameDuration, frames);
    }

    protected void updateState(float delta) {
        stateTime = currentState == previousState ? stateTime + delta : 0;
        previousState = currentState;
    }
}