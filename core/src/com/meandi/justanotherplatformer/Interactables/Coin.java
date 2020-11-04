package com.meandi.justanotherplatformer.Interactables;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.meandi.justanotherplatformer.Helpers.Assets;
import com.meandi.justanotherplatformer.JustAnotherPlatformer;
import com.meandi.justanotherplatformer.UI.GameScreen;
import com.meandi.justanotherplatformer.UI.Hud;

public class Coin extends Interactable {
    private final int COIN = 295;

    public Coin(GameScreen screen, Body body, Fixture fixture) {
        super(screen, body, fixture);

        fixture.setUserData(this);

        setCategoryFilter(JustAnotherPlatformer.COIN_BIT);
    }

    public void onBodyHit() {
        if (getCell().getTile().getId() == COIN) {
            assets.manager.get(Assets.COIN).play();

            setCategoryFilter(JustAnotherPlatformer.REMOVED_BIT);

            getCell().setTile(null);

            hud.addScore(100);
        }
    }
}
