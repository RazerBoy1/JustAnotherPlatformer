package com.meandi.justanotherplatformer.Helpers;

import com.badlogic.gdx.physics.box2d.*;
import com.meandi.justanotherplatformer.Interactables.Coin;
import com.meandi.justanotherplatformer.Interactables.Moss;

public class WorldContactListener implements ContactListener {

    @Override
    public void beginContact(Contact contact) {
        Fixture a = contact.getFixtureA();
        Fixture b = contact.getFixtureB();

        if (a.getUserData() == "head" || b.getUserData() == "head") {
            Fixture head = a.getUserData() == "head" ? a : b;
            Fixture object = head == a ? b : a;

            if (object.getUserData() != null && Moss.class.isAssignableFrom(object.getUserData().getClass()))
                ((Moss) object.getUserData()).onHeadHit();
        }

        if (a.getUserData() == "body" || b.getUserData() == "body") {
            Fixture body = a.getUserData() == "body" ? a : b;
            Fixture object = body == a ? b : a;

            if (object.getUserData() != null && Coin.class.isAssignableFrom(object.getUserData().getClass()))
                ((Coin) object.getUserData()).onBodyHit();
        }
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
