package com.meandi.justanotherplatformer.Interactables;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.meandi.justanotherplatformer.Utils.Assets;
import com.meandi.justanotherplatformer.JustAnotherPlatformer;
import com.meandi.justanotherplatformer.UI.GameScreen;

public class Coin extends Interactable {
    public Coin(GameScreen screen, Body body, Fixture fixture, MapObject object) {
        super(screen, body, fixture, object);

        fixture.setUserData(this);

        setCategoryFilter(JustAnotherPlatformer.COIN_BIT);
    }

    public void onBodyHit() {
        assets.manager.get(Assets.COIN).play();

        setCategoryFilter(JustAnotherPlatformer.REMOVED_BIT);
        getCell().setTile(null);

        hud.addScore(100);
    }
}
