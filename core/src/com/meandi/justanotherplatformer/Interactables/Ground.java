package com.meandi.justanotherplatformer.Interactables;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.physics.box2d.World;

public class Ground extends Interactable {
    public Ground(World world, TiledMap map, int layerID) {
        super(world, map, layerID);
    }
}
