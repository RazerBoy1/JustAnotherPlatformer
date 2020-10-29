package com.meandi.justanotherplatformer.Helpers;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.physics.box2d.World;
import com.meandi.justanotherplatformer.Interactables.Coin;
import com.meandi.justanotherplatformer.Interactables.Door;
import com.meandi.justanotherplatformer.Interactables.Ground;
import com.meandi.justanotherplatformer.Interactables.Moss;

/*
    layerID 0 = ground
    layerID 1 = coin
    layerID 2 = door
    layerID 3 = moss
*/
public class WorldBuilder {
    public WorldBuilder(World world, TiledMap map) {
        new Ground(world, map, 0);
        new Coin(world, map, 1);
        new Door(world, map, 2);
        new Moss(world, map, 3);
    }
}
