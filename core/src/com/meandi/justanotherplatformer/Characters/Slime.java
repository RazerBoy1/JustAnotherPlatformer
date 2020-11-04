package com.meandi.justanotherplatformer.Characters;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.utils.Array;
import com.meandi.justanotherplatformer.JustAnotherPlatformer;
import com.meandi.justanotherplatformer.UI.GameScreen;

public class Slime extends Character {
    private Animation<TextureRegion> slimeIdle, slimeWalk, slimeHit, slimeDie;

    public Slime(GameScreen screen, TextureRegion region) {
        super(screen, region);

        createAnimations();
    }

    @Override
    protected void createCharacter() {
        BodyDef bodyDef = new BodyDef();
        FixtureDef fixDef = new FixtureDef();
        CircleShape circleShape = new CircleShape();

        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(150 / JustAnotherPlatformer.PPT, 90 / JustAnotherPlatformer.PPT);
        body = world.createBody(bodyDef);

        circleShape.setRadius(4.5f / JustAnotherPlatformer.PPT);
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

    @Override
    public void updateSpritePosition(float delta) {
        stateTimer += delta;

        setPosition(body.getPosition().x - getWidth() / 2, (3f / JustAnotherPlatformer.PPT) + body.getPosition().y - getHeight() / 2);
        setRegion(slimeWalk.getKeyFrame(stateTimer, true));
    }

    @Override
    public void createAnimations() {
        Array<TextureRegion> frames = new Array<>();

        slimeIdle = createAnimation(frames, 0, 16, 16, 15, 20, 0.3f);
        slimeWalk = createAnimation(frames,8, 16, 24, 0, 15, 0.1f);
        slimeHit = createAnimation(frames, 0, 16, 16, 20, 23, 0.3f);
        slimeDie = createAnimation(frames, 0, 16, 16, 15, 21, 0.3f);
    }
}
