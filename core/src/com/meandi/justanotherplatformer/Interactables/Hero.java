package com.meandi.justanotherplatformer.Interactables;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.meandi.justanotherplatformer.Helpers.Assets;
import com.meandi.justanotherplatformer.JustAnotherPlatformer;

public class Hero extends Sprite {
    private final World world;
    public Body body;

    private boolean moveLeft;
    private boolean moveRight;
    private boolean jump;

    private enum State {FALLING, JUMPING, IDLE, RUNNING}
    private State currentState, previousState;
    private Animation<TextureRegion> heroIdle, heroRun, heroJump;
    private boolean runningRight;
    private float stateTimer;

    public Hero(World world, Assets assets) {
        super(assets.manager.get(Assets.HERO_ATLAS).findRegion("herochar_idle_anim_strip"));
        currentState = State.IDLE;
        previousState = State.IDLE;
        stateTimer = 0;
        runningRight = true;

        createAnimations();

        this.world = world;
        moveLeft = false;
        moveRight = false;
        jump = false;

        createPlayer();
        setBounds(0, 0, 16 / JustAnotherPlatformer.PPT, 16 / JustAnotherPlatformer.PPT);
    }

    public void createAnimations() {
        Array<TextureRegion> frames = new Array<>();

        createIdleAnimation(frames);
        createRunAnimation(frames);
        createJumpAnimation(frames);
    }

    private void createIdleAnimation(Array<TextureRegion> frames) {
        for (int i = 36; i < 40; i++)
            frames.add(new TextureRegion(getTexture(), i * 16, 0, 16, 16));

        heroIdle = new Animation<>(0.1f, frames);
        frames.clear();
    }

    private void createRunAnimation(Array<TextureRegion> frames) {
        for (int i = 18; i < 24; i++)
            frames.add(new TextureRegion(getTexture(), i * 16, 0, 16, 16));

        heroRun = new Animation<>(0.1f, frames);
        frames.clear();
    }

    private void createJumpAnimation(Array<TextureRegion> frames) {
        for (int i = 53; i < 56; i++)
            frames.add(new TextureRegion(getTexture(), i * 16, 0, 16, 16));

        heroJump = new Animation<>(0.1f, frames);
        frames.clear();
    }

    public void updateSpritePosition(float delta) {
        setPosition(body.getPosition().x - getWidth() / 2, body.getPosition().y - getHeight() / 2);
        setRegion(getFrame(delta));
    }

    public TextureRegion getFrame(float delta) {
        TextureRegion region;

        currentState = getState();
        switch (currentState) {
            case JUMPING:
                region = heroJump.getKeyFrame(stateTimer);
                break;
            case RUNNING:
                region = heroRun.getKeyFrame(stateTimer, true);
                break;
            case FALLING:
            case IDLE:
            default:
                region = heroIdle.getKeyFrame(stateTimer, true);
                break;
        }

        if ((body.getLinearVelocity().x < 0 || !runningRight) && !region.isFlipX()) {
            region.flip(true, false);
            runningRight = false;
        } else if ((body.getLinearVelocity().x > 0 || runningRight) && region.isFlipX()) {
            region.flip(true, false);
            runningRight = true;
        }

        stateTimer = currentState == previousState ? stateTimer + delta : 0;
        previousState = currentState;

        return region;
    }

    public State getState() {
        if (body.getLinearVelocity().y > 0 || (body.getLinearVelocity().y < 0 && previousState == State.JUMPING))
            return State.JUMPING;
        else if (body.getLinearVelocity().y < 0)
            return State.FALLING;
        else if (body.getLinearVelocity().x != 0)
            return State.RUNNING;
        else
            return State.IDLE;
    }

    public void updateMotion() {
        if (moveRight && body.getLinearVelocity().x <= 1)
            body.applyLinearImpulse(new Vector2(0.1f, 0), body.getWorldCenter(), true);
        if (moveLeft && body.getLinearVelocity().x >= -1)
            body.applyLinearImpulse(new Vector2(-0.1f, 0), body.getWorldCenter(), true);

        if (jump) {
            if (body.getLinearVelocity().y == 0)
                body.applyLinearImpulse(new Vector2(0, 2.5f), body.getWorldCenter(), true);
            else
                jump = false;
        }
    }

    public void setLeftMove(boolean t) {
        if (moveRight && t) moveRight = false;
        moveLeft = t;
    }

    public void setRightMove(boolean t) {
        if (moveLeft && t) moveLeft = false;
        moveRight = t;
    }

    public void setJump(boolean t) {
        jump = t;
    }

    public void createPlayer() {
        BodyDef bodyDef = new BodyDef();
        FixtureDef fixDef = new FixtureDef();
        CircleShape circleShape = new CircleShape();

        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(170 / JustAnotherPlatformer.PPT, 90 / JustAnotherPlatformer.PPT);
        body = world.createBody(bodyDef);

        circleShape.setRadius(6 / JustAnotherPlatformer.PPT);
        fixDef.shape = circleShape;

        body.createFixture(fixDef);
    }
}
