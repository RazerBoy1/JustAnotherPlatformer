package com.meandi.justanotherplatformer.Interactables;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.utils.Array;
import com.meandi.justanotherplatformer.JustAnotherPlatformer;
import com.meandi.justanotherplatformer.UI.GameScreen;

public class Slime extends Enemy {
    private float stateTime;
    private Animation<TextureRegion> slimeIdle, slimeWalk, slimeHurt, slimeDie;

    public Slime(GameScreen screen, float x, float y) {
        super(screen, x, y);
        stateTime = 0;

        createAnimations();

        setBounds(getX(), getY(), 16 / JustAnotherPlatformer.PPT, 16 / JustAnotherPlatformer.PPT);
    }

    public void update(float delta) {
        stateTime += delta;

        setPosition(body.getPosition().x - getWidth() / 2, (2.5f / JustAnotherPlatformer.PPT) + body.getPosition().y - getHeight() / 2);
        setRegion(slimeWalk.getKeyFrame(stateTime, true));
    }

    @Override
    protected void createEnemy() {
        BodyDef bodyDef = new BodyDef();
        FixtureDef fixDef = new FixtureDef();
        CircleShape circleShape = new CircleShape();

        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(150 / JustAnotherPlatformer.PPT, 90 / JustAnotherPlatformer.PPT);
        body = world.createBody(bodyDef);

        circleShape.setRadius(3.75f / JustAnotherPlatformer.PPT);
        fixDef.shape = circleShape;
        fixDef.filter.categoryBits = JustAnotherPlatformer.ENEMY_BIT;
        fixDef.filter.maskBits = JustAnotherPlatformer.DEFAULT_BIT |
                JustAnotherPlatformer.COIN_BIT |
                JustAnotherPlatformer.DOOR_BIT |
                JustAnotherPlatformer.MOSS_BIT |
                JustAnotherPlatformer.ENEMY_BIT |
                JustAnotherPlatformer.HERO_BIT;

        body.createFixture(fixDef).setUserData("slime_enemy_body");
    }

    public void createAnimations() {
        Array<TextureRegion> frames = new Array<>();

        slimeIdle = createAnimation(frames, 8, 12, 0.3f);
        slimeWalk = createAnimation(frames, 12, 16, 0.3f);
        slimeHurt = createAnimation(frames, 4, 8, 0.3f);
        slimeDie = createAnimation(frames, 0, 4, 0.3f);
    }

    private Animation<TextureRegion> createAnimation(Array<TextureRegion> frames, int startFrame, int endFrame, float frameDuration) {
        if (frames.notEmpty())
            frames.clear();

        for (int i = startFrame; i < endFrame; i++)
            frames.add(new TextureRegion(getTexture(), i * 32, 0, 32, 25));

        return new Animation<>(frameDuration, frames);
    }
}
