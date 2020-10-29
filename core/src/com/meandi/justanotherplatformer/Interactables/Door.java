package com.meandi.justanotherplatformer.Interactables;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.physics.box2d.World;

public class Door extends Interactable {
    public Door(World world, TiledMap map, int layerID) {
        super(world, map, layerID);
    }
}
