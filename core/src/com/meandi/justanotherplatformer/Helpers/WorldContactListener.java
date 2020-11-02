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

            if (object.getUserData() != null) {
                if (Coin.class.isAssignableFrom(object.getUserData().getClass()))
                    ((Coin) object.getUserData()).onHeadHit();
                if (Moss.class.isAssignableFrom(object.getUserData().getClass()))
                    ((Moss) object.getUserData()).onHeadHit();
            }
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
