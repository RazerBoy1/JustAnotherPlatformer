package com.meandi.justanotherplatformer;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.*;

public class Player extends Sprite {
    public World world;
    public Body body;

    public Player(World world) {
        this.world = world;
        createPlayer();
    }

    public void createPlayer() {
        BodyDef bodyDef = new BodyDef();
        FixtureDef fixDef = new FixtureDef();
        CircleShape circleShape = new CircleShape();

        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(170 / JustAnotherPlatformer.PPT, 90 / JustAnotherPlatformer.PPT);
        body = world.createBody(bodyDef);

        circleShape.setRadius(5 / JustAnotherPlatformer.PPT);
        fixDef.shape = circleShape;

        body.createFixture(fixDef);
    }
}
