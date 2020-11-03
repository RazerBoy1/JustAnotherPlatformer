package com.meandi.justanotherplatformer.Interactables;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;
import com.meandi.justanotherplatformer.Helpers.Assets;
import com.meandi.justanotherplatformer.JustAnotherPlatformer;
import com.meandi.justanotherplatformer.UI.Hud;

public class Coin extends Interactable {
    private final int COIN = 295;

    public Coin(World world, TiledMap map, Body body, Fixture fixture, Hud hud, Assets assets) {
        super(world, map, body, fixture, hud, assets);

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
