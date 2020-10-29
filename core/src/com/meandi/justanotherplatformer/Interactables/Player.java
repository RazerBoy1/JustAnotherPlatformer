package com.meandi.justanotherplatformer.Interactables;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.meandi.justanotherplatformer.JustAnotherPlatformer;

public class Player extends Sprite {
    public World world;
    public Body body;

    private boolean moveLeft;
    private boolean moveRight;
    private boolean jump;

    public Player(World world) {
        this.world = world;
        moveLeft = false;
        moveRight = false;
        jump = false;

        createPlayer();
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

        circleShape.setRadius(5 / JustAnotherPlatformer.PPT);
        fixDef.shape = circleShape;

        body.createFixture(fixDef);
    }
}
