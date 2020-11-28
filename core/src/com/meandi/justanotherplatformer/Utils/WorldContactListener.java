package com.meandi.justanotherplatformer.Utils;

import com.badlogic.gdx.physics.box2d.*;
import com.meandi.justanotherplatformer.Characters.Hero;
import com.meandi.justanotherplatformer.Characters.Slime;
import com.meandi.justanotherplatformer.Interactables.Coin;
import com.meandi.justanotherplatformer.Interactables.Moss;
import com.meandi.justanotherplatformer.Items.HealthPotion;
import com.meandi.justanotherplatformer.JustAnotherPlatformer;

public class WorldContactListener implements ContactListener {
    @Override
    public void beginContact(Contact contact) {
        Fixture a = contact.getFixtureA();
        Fixture b = contact.getFixtureB();

        int collisionDefinition = a.getFilterData().categoryBits | b.getFilterData().categoryBits;

        switch (collisionDefinition) {
            case JustAnotherPlatformer.HERO_HEAD_BIT | JustAnotherPlatformer.MOSS_BIT:
                if (a.getFilterData().categoryBits == JustAnotherPlatformer.MOSS_BIT)
                    ((Moss) a.getUserData()).onHeadHit();
                else
                    ((Moss) b.getUserData()).onHeadHit();
                break;
            case JustAnotherPlatformer.HERO_FEET_BIT | JustAnotherPlatformer.MOSS_BIT:
                if (a.getFilterData().categoryBits == JustAnotherPlatformer.MOSS_BIT)
                    ((Moss) a.getUserData()).onFeetHit();
                else
                    ((Moss) b.getUserData()).onFeetHit();
                break;
            case JustAnotherPlatformer.HERO_FEET_BIT | JustAnotherPlatformer.ENEMY_HEAD_BIT:
                if (a.getFilterData().categoryBits == JustAnotherPlatformer.ENEMY_HEAD_BIT)
                    ((Slime) a.getUserData()).hitOnHead();
                else
                    ((Slime) b.getUserData()).hitOnHead();
                break;
            case JustAnotherPlatformer.HERO_BIT | JustAnotherPlatformer.ENEMY_BIT:
                if (a.getFilterData().categoryBits == JustAnotherPlatformer.HERO_BIT)
                    heroAndEnemyCollision(a, b);
                else
                    heroAndEnemyCollision(b, a);
                break;
            case JustAnotherPlatformer.HERO_BIT | JustAnotherPlatformer.COIN_BIT:
                if (a.getFilterData().categoryBits == JustAnotherPlatformer.COIN_BIT)
                    ((Coin) a.getUserData()).onBodyHit();
                else
                    ((Coin) b.getUserData()).onBodyHit();
                break;
            case JustAnotherPlatformer.HERO_BIT | JustAnotherPlatformer.END_BIT:
                if (a.getFilterData().categoryBits == JustAnotherPlatformer.HERO_BIT)
                    ((Hero) a.getUserData()).onEndHit();
                else
                    ((Hero) b.getUserData()).onEndHit();
                break;
            case JustAnotherPlatformer.HERO_BIT | JustAnotherPlatformer.ITEM_BIT:
                if (a.getFilterData().categoryBits == JustAnotherPlatformer.ITEM_BIT)
                    ((HealthPotion) a.getUserData()).useItem((Hero) b.getUserData());
                else
                    ((HealthPotion) b.getUserData()).useItem((Hero) a.getUserData());
                break;
            case JustAnotherPlatformer.ENEMY_BIT | JustAnotherPlatformer.MOSS_BIT:
            case JustAnotherPlatformer.ENEMY_BIT | JustAnotherPlatformer.ITEM_BIT:
            case JustAnotherPlatformer.ENEMY_BIT | JustAnotherPlatformer.DEFAULT_BIT:
                if (a.getFilterData().categoryBits == JustAnotherPlatformer.ENEMY_BIT && ((Slime) a.getUserData()).body.getLinearVelocity().y == 0)
                    ((Slime) a.getUserData()).reverseVelocity(true, false);
                else if (((Slime) b.getUserData()).body.getLinearVelocity().y == 0)
                    ((Slime) b.getUserData()).reverseVelocity(true, false);
                break;
            case JustAnotherPlatformer.ENEMY_BIT:
                ((Slime) a.getUserData()).reverseVelocity(true, false);
                ((Slime) b.getUserData()).reverseVelocity(true, false);
                break;
        }
    }

    private void heroAndEnemyCollision(Fixture a, Fixture b) {
        if (((Slime) b.getUserData()).body.getLinearVelocity().y == 0 &&
                (((Hero) a.getUserData()).body.getLinearVelocity().x > 0 && ((Slime) b.getUserData()).body.getLinearVelocity().x < 0) ||
                (((Hero) a.getUserData()).body.getLinearVelocity().x > 0 && ((Slime) b.getUserData()).body.getLinearVelocity().x < 0))
            ((Slime) b.getUserData()).reverseVelocity(true, false);

        ((Hero) a.getUserData()).hit();
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
