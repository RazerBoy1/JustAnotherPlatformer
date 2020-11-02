package com.meandi.justanotherplatformer.Interactables;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.physics.box2d.World;

public class Door extends Interactable {
    public Door(World world, TiledMap map, int layerID) {
        super(world, map, layerID);

        for (int i = 0; i < fixtures.size; i++)
            fixtures.get(i).setUserData(this);
    }

    public void onHeadHit() {
        Gdx.app.log("Door", "Collision");
    }
}
