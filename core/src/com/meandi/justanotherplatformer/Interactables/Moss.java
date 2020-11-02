package com.meandi.justanotherplatformer.Interactables;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.physics.box2d.World;
import com.meandi.justanotherplatformer.JustAnotherPlatformer;

public class Moss extends Interactable {
    public Moss(World world, TiledMap map, int layerID) {
        super(world, map, layerID);

        for (int i = 0; i < fixtures.size; i++)
            fixtures.get(i).setUserData(this);

        setCategoryFilter(JustAnotherPlatformer.MOSS_BIT);
    }

    public void onHeadHit() {
        Gdx.app.log("Moss", "Collision");

        setCategoryFilter(JustAnotherPlatformer.REMOVED_BIT);
    }
}
