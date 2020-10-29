package com.meandi.justanotherplatformer.Interactables;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.physics.box2d.World;

public class Moss extends Interactable {
    public Moss(World world, TiledMap map, int layerID) {
        super(world, map, layerID);
    }
}
