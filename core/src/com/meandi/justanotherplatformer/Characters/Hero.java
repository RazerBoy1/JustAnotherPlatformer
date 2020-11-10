package com.meandi.justanotherplatformer.Characters;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.meandi.justanotherplatformer.Overlay.Hud;
import com.meandi.justanotherplatformer.Utils.Assets;
import com.meandi.justanotherplatformer.JustAnotherPlatformer;
import com.meandi.justanotherplatformer.UI.GameScreen;

public class Hero extends Character {
    private final Assets assets;
    private final Hud hud;

    private boolean moveLeft;
    private boolean moveRight;
    private boolean jump;
    private boolean heroDied;

    private Animation<TextureRegion> heroIdle, heroDeath, heroRun, heroJump, heroDoubleJump, heroFall, heroPush, heroAttack, heroHit;
    private boolean runningRight;

    public Hero(GameScreen screen, TextureRegion region) {
        super(screen, region);

        currentState = previousState = State.IDLING;
        runningRight = true;

        createAnimations();

        assets = screen.getAssets();
        hud = screen.getHud();

        moveLeft = moveRight = jump = false;
    }

    @Override
    protected void createCharacter() {
        BodyDef bodyDef = new BodyDef();
        FixtureDef fixDef = new FixtureDef();
        CircleShape circleShape = new CircleShape();

        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(560 / JustAnotherPlatformer.PPT, 90 / JustAnotherPlatformer.PPT);
        body = world.createBody(bodyDef);

        circleShape.setRadius(5.5f / JustAnotherPlatformer.PPT);
        fixDef.shape = circleShape;
        fixDef.filter.categoryBits = JustAnotherPlatformer.HERO_BIT;
        fixDef.filter.maskBits = JustAnotherPlatformer.DEFAULT_BIT |
                JustAnotherPlatformer.COIN_BIT |
                JustAnotherPlatformer.MOSS_BIT |
                JustAnotherPlatformer.ITEM_BIT |
                JustAnotherPlatformer.OBJECT_BIT |
                JustAnotherPlatformer.ENEMY_BIT |
                JustAnotherPlatformer.ENEMY_HEAD_BIT;

        body.createFixture(fixDef).setUserData(this);

        EdgeShape head = new EdgeShape();
        head.set(new Vector2(-3 / JustAnotherPlatformer.PPT, 6.5f / JustAnotherPlatformer.PPT), new Vector2(3 / JustAnotherPlatformer.PPT, 6.5f / JustAnotherPlatformer.PPT));
        fixDef.shape = head;
        fixDef.isSensor = true;
        fixDef.filter.categoryBits = JustAnotherPlatformer.HERO_HEAD_BIT;

        body.createFixture(fixDef).setUserData(this);

        EdgeShape feet = new EdgeShape();
        feet.set(new Vector2(3 / JustAnotherPlatformer.PPT, -6.5f / JustAnotherPlatformer.PPT), new Vector2(-3 / JustAnotherPlatformer.PPT, -6.5f / JustAnotherPlatformer.PPT));
        fixDef.shape = feet;
        fixDef.isSensor = true;
        fixDef.filter.categoryBits = JustAnotherPlatformer.HERO_FEET_BIT;

        body.createFixture(fixDef).setUserData(this);
    }

    @Override
    protected void createAnimations() {
        Array<TextureRegion> frames = new Array<>();

        heroIdle = createAnimation(frames, 0, 16, 16, 36, 40, 0.1f);
        heroDeath = createAnimation(frames, 0, 16, 16, 0, 8, 0.1f);
        heroRun = createAnimation(frames, 0, 16, 16, 18, 24, 0.075f);
        heroJump = createAnimation(frames, 0, 16, 16, 53, 56, 0.1f);
        heroDoubleJump = createAnimation(frames, 0, 16, 16, 47, 50, 0.1f);
        heroFall = createAnimation(frames, 0, 16, 16, 50, 54, 0.1f);
        heroPush = createAnimation(frames, 0, 16, 16, 16, 22, 0.1f);
        heroAttack = createAnimation(frames, 0, 16, 16, 8, 10, 0.1f);
        heroHit = createAnimation(frames, 0, 16, 16, 44, 47, 0.1f);
    }

    @Override
    public void updateSpritePosition(float delta) {
        setPosition((0.35f / JustAnotherPlatformer.PPT) + body.getPosition().x - getWidth() / 2, (2 / JustAnotherPlatformer.PPT) + body.getPosition().y - getHeight() / 2);
        setRegion(getFrame());

        updateState(delta);
    }

    @Override
    protected TextureRegion getFrame() {
        TextureRegion region;

        currentState = getState();
        switch (currentState) {
            case RUNNING:
                region = heroRun.getKeyFrame(stateTime, true);
                break;
            case DYING:
                region = heroDeath.getKeyFrame(stateTime);
                break;
            case JUMPING:
                region = heroJump.getKeyFrame(stateTime);
                break;
            case DOUBLE_JUMPING:
                region = heroDoubleJump.getKeyFrame(stateTime);
                break;
            case FALLING:
                region = heroFall.getKeyFrame(stateTime);
                break;
            case PUSHING:
                region = heroPush.getKeyFrame(stateTime);
                break;
            case ATTACKING:
                region = heroAttack.getKeyFrame(stateTime);
                break;
            case HIT:
                region = heroHit.getKeyFrame(stateTime);
                break;
            case IDLING:
            default:
                region = heroIdle.getKeyFrame(stateTime, true);
                break;
        }

        if ((body.getLinearVelocity().x < 0 || !runningRight) && !region.isFlipX()) {
            region.flip(true, false);
            runningRight = false;
        } else if ((body.getLinearVelocity().x > 0 || runningRight) && region.isFlipX()) {
            region.flip(true, false);
            runningRight = true;
        }

        return region;
    }

    protected State getState() {
        if (heroDied)
            return State.DYING;
        else if (body.getLinearVelocity().y > 0)
            return State.JUMPING;
        else if (body.getLinearVelocity().y < 0)
            return State.FALLING;
        else if (body.getLinearVelocity().x != 0)
            return State.RUNNING;
        else
            return State.IDLING;
    }

    public void updateMotion() {
        if (body.getPosition().y < -10 / JustAnotherPlatformer.PPT && !heroDied) {
            assets.manager.get(Assets.MUSIC).stop();
            assets.manager.get(Assets.DEATH).play();

            hud.removeAllHearths();
            heroDied = true;
        }

        if (body.getLinearVelocity().y > 2.75f)
            body.setLinearVelocity(new Vector2(body.getLinearVelocity().x, 2.75f));

        if (moveRight && body.getLinearVelocity().x <= 1)
            body.applyLinearImpulse(new Vector2(0.1f, 0), body.getWorldCenter(), true);
        if (moveLeft && body.getLinearVelocity().x >= -1)
            body.applyLinearImpulse(new Vector2(-0.1f, 0), body.getWorldCenter(), true);

        if (jump) {
            if (body.getLinearVelocity().y == 0) {
                assets.manager.get(Assets.JUMP).play();
                body.applyLinearImpulse(new Vector2(0, 2.5f), body.getWorldCenter(), true);
            } else
                jump = false;
        }
    }

    public void hit() {
        assets.manager.get(Assets.BUMP_WITH_ENEMY).play();
        hud.removeHearth();

        if (hud.getHearthCount() == 0) {
            assets.manager.get(Assets.MUSIC).stop();
            assets.manager.get(Assets.DEATH).play();

            heroDied = true;
        }
    }

    public boolean isDead() {
        return heroDied;
    }

    public float getStateTimer() {
        return stateTime;
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
}
