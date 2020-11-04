package com.meandi.justanotherplatformer.Interactables;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.meandi.justanotherplatformer.Helpers.Assets;
import com.meandi.justanotherplatformer.JustAnotherPlatformer;
import com.meandi.justanotherplatformer.UI.GameScreen;

public class Hero extends Sprite {
    private final World world;
    public Body body;

    private final Assets assets;

    private boolean moveLeft;
    private boolean moveRight;
    private boolean jump;

    private enum State {IDLING, DYING, RUNNING, JUMPING, DOUBLE_JUMPING, FALLING, PUSHING, ATTACKING, HIT}

    private State currentState, previousState;
    private Animation<TextureRegion> heroIdle, heroDeath, heroRun, heroJump, heroDoubleJump, heroFall, heroPush, heroAttack, heroHit;
    private boolean runningRight;
    private float stateTimer;

    public Hero(GameScreen screen) {
        super(screen.getAssets().manager.get(Assets.HERO_ATLAS).findRegion("herochar_idle_anim_strip"));
        currentState = State.IDLING;
        previousState = State.IDLING;
        stateTimer = 0;
        runningRight = true;

        createAnimations();

        this.assets = screen.getAssets();
        this.world = screen.getWorld();

        moveLeft = false;
        moveRight = false;
        jump = false;

        createPlayer();
        setBounds(0, 0, 16 / JustAnotherPlatformer.PPT, 16 / JustAnotherPlatformer.PPT);
    }

    public void createAnimations() {
        Array<TextureRegion> frames = new Array<>();

        heroIdle = createAnimation(frames, 36, 40, 0.1f);
        heroDeath = createAnimation(frames, 0, 8, 0.1f);
        heroRun = createAnimation(frames, 18, 24, 0.075f);
        heroJump = createAnimation(frames, 53, 56, 0.1f);
        heroDoubleJump = createAnimation(frames, 47, 50, 0.1f);
        heroFall = createAnimation(frames, 50, 54, 0.1f);
        heroPush = createAnimation(frames, 16, 22, 0.1f);
        heroAttack = createAnimation(frames, 8, 10, 0.1f);
        heroHit = createAnimation(frames, 44, 47, 0.1f);
    }

    private Animation<TextureRegion> createAnimation(Array<TextureRegion> frames, int startFrame, int endFrame, float frameDuration) {
        if (frames.notEmpty())
            frames.clear();

        for (int i = startFrame; i < endFrame; i++)
            frames.add(new TextureRegion(getTexture(), i * JustAnotherPlatformer.FRAME_SIZE, 0, JustAnotherPlatformer.FRAME_SIZE, JustAnotherPlatformer.FRAME_SIZE));

        return new Animation<>(frameDuration, frames);
    }

    public void updateSpritePosition(float delta) {
        setPosition((0.35f / JustAnotherPlatformer.PPT) + body.getPosition().x - getWidth() / 2, (2 / JustAnotherPlatformer.PPT) + body.getPosition().y - getHeight() / 2);
        setRegion(getFrame(delta));
    }

    public TextureRegion getFrame(float delta) {
        TextureRegion region;

        currentState = getState();
        switch (currentState) {
            case RUNNING:
                region = heroRun.getKeyFrame(stateTimer, true);
                break;
            case DYING:
                region = heroDeath.getKeyFrame(stateTimer);
                break;
            case JUMPING:
                region = heroJump.getKeyFrame(stateTimer);
                break;
            case DOUBLE_JUMPING:
                region = heroDoubleJump.getKeyFrame(stateTimer);
                break;
            case FALLING:
                region = heroFall.getKeyFrame(stateTimer);
                break;
            case PUSHING:
                region = heroPush.getKeyFrame(stateTimer);
                break;
            case ATTACKING:
                region = heroAttack.getKeyFrame(stateTimer);
                break;
            case HIT:
                region = heroHit.getKeyFrame(stateTimer);
                break;
            case IDLING:
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
        if (body.getLinearVelocity().y > 0)
            return State.JUMPING;
        else if (body.getLinearVelocity().y < 0)
            return State.FALLING;
        else if (body.getLinearVelocity().x != 0)
            return State.RUNNING;
        else
            return State.IDLING;
    }

    public void updateMotion() {
        if (moveRight && body.getLinearVelocity().x <= 1)
            body.applyLinearImpulse(new Vector2(0.1f, 0), body.getWorldCenter(), true);
        if (moveLeft && body.getLinearVelocity().x >= -1)
            body.applyLinearImpulse(new Vector2(-0.1f, 0), body.getWorldCenter(), true);

        if (jump) {
            if (body.getLinearVelocity().y == 0) {
                assets.manager.get(Assets.JUMP).play();
                body.applyLinearImpulse(new Vector2(0, 2.5f), body.getWorldCenter(), true);
            }
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
        fixDef.filter.categoryBits = JustAnotherPlatformer.HERO_BIT;
        fixDef.filter.maskBits = JustAnotherPlatformer.DEFAULT_BIT |
                JustAnotherPlatformer.COIN_BIT |
                JustAnotherPlatformer.DOOR_BIT |
                JustAnotherPlatformer.MOSS_BIT |
                JustAnotherPlatformer.ENEMY_BIT;

        body.createFixture(fixDef).setUserData("player_body");

        EdgeShape head = new EdgeShape();
        head.set(new Vector2(-3 / JustAnotherPlatformer.PPT, 6.5f / JustAnotherPlatformer.PPT), new Vector2(3 / JustAnotherPlatformer.PPT, 6.5f / JustAnotherPlatformer.PPT));
        fixDef.shape = head;
        fixDef.isSensor = true;

        body.createFixture(fixDef).setUserData("head");
    }
}
