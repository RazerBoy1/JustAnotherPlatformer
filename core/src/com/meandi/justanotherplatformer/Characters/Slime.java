package com.meandi.justanotherplatformer.Characters;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.utils.Array;
import com.meandi.justanotherplatformer.JustAnotherPlatformer;
import com.meandi.justanotherplatformer.UI.GameScreen;
import com.meandi.justanotherplatformer.Overlay.Hud;
import com.meandi.justanotherplatformer.Utils.Assets;

public class Slime extends Character {
    private final Hud hud;
    private final Assets assets;
    private final Vector2 velocity;
    private Animation<TextureRegion> slimeIdle, slimeRun, slimeHit, slimeDeath;

    public Slime(GameScreen screen, TextureRegion region, float x, float y) {
        super(screen, region, x, y);
        hud = screen.getHud();
        assets = screen.getAssets();
        velocity = new Vector2(0.25f, 0);

        currentState = previousState = State.RUNNING;

        createAnimations();
    }

    @Override
    protected void createCharacter() {
        BodyDef bodyDef = new BodyDef();
        FixtureDef fixDef = new FixtureDef();
        CircleShape circleShape = new CircleShape();

        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(getX(), getY());
        body = world.createBody(bodyDef);

        circleShape.setRadius(4.5f / JustAnotherPlatformer.PPT);
        fixDef.shape = circleShape;
        fixDef.filter.categoryBits = JustAnotherPlatformer.ENEMY_BIT;
        fixDef.filter.maskBits = JustAnotherPlatformer.DEFAULT_BIT |
                JustAnotherPlatformer.COIN_BIT |
                JustAnotherPlatformer.MOSS_BIT |
                JustAnotherPlatformer.ITEM_BIT |
                JustAnotherPlatformer.OBJECT_BIT |
                JustAnotherPlatformer.ENEMY_BIT |
                JustAnotherPlatformer.HERO_BIT;

        body.createFixture(fixDef).setUserData(this);

        PolygonShape head = new PolygonShape();
        head.set(new Vector2[]{
                new Vector2(-2.2f, 5.5f).scl(1 / JustAnotherPlatformer.PPT),
                new Vector2(2.2f, 5.5f).scl(1 / JustAnotherPlatformer.PPT),
                new Vector2(-2, 2).scl(1 / JustAnotherPlatformer.PPT),
                new Vector2(2, 2).scl(1 / JustAnotherPlatformer.PPT)
        });

        fixDef.shape = head;
        fixDef.restitution = 0.5f;
        fixDef.filter.categoryBits = JustAnotherPlatformer.ENEMY_HEAD_BIT;

        body.createFixture(fixDef).setUserData(this);
    }

    @Override
    public void updateSpritePosition(float delta) {
        if (setToDestroy && !destroyed) {
            world.destroyBody(body);
            destroyed = true;
            setRegion(getFrame());
            hud.addScore(50);
            stateTime = 0;
        } else if (!destroyed) {
            body.setLinearVelocity(velocity);
            setPosition(body.getPosition().x - getWidth() / 2, (3f / JustAnotherPlatformer.PPT) + body.getPosition().y - getHeight() / 2);
            setRegion(getFrame());
        }

        updateState(delta);
    }

    @Override
    protected TextureRegion getFrame() {
        TextureRegion region;

        currentState = getState();
        switch (currentState) {
            case RUNNING:
                region = slimeRun.getKeyFrame(stateTime, true);
                break;
            case DYING:
                region = slimeDeath.getKeyFrame(stateTime);
                break;
            case HIT:
                region = slimeHit.getKeyFrame(stateTime);
                break;
            case IDLING:
            default:
                region = slimeIdle.getKeyFrame(stateTime, true);
                break;
        }

        if (region.isFlipX() && body.getLinearVelocity().x < 0)
            region.flip(true, false);
        else if (body.getLinearVelocity().x > 0 && !region.isFlipX())
            region.flip(true, false);

        return region;
    }

    protected State getState() {
        if (destroyed)
            return State.DYING;
        else
            return State.RUNNING;
    }

    @Override
    protected void createAnimations() {
        Array<TextureRegion> frames = new Array<>();

        slimeIdle = createAnimation(frames, 0, 16, 16, 15, 20, 0.3f);
        slimeRun = createAnimation(frames, 8, 16, 24, 0, 15, 0.1f);
        slimeHit = createAnimation(frames, 0, 16, 16, 20, 23, 0.7f);
        slimeDeath = createAnimation(frames, 0, 16, 16, 15, 21, 1f);
    }

    @Override
    public void draw(Batch batch) {
        if (!destroyed || stateTime < 1)
            super.draw(batch);
    }

    public void reverseVelocity(boolean x, boolean y) {
        if (x)
            velocity.x = -velocity.x;
        if (y)
            velocity.y = -velocity.y;
    }

    public void hitOnHead() {
        assets.manager.get(Assets.BUMP_ENEMY_ON_HEAD).play();
        setToDestroy = true;

        hud.addScore(50);
    }
}
