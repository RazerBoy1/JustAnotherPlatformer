package com.meandi.justanotherplatformer.Helpers;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.physics.box2d.World;
import com.meandi.justanotherplatformer.Interactables.Coin;
import com.meandi.justanotherplatformer.Interactables.Door;
import com.meandi.justanotherplatformer.Interactables.Ground;
import com.meandi.justanotherplatformer.Interactables.Moss;
import com.meandi.justanotherplatformer.JustAnotherPlatformer;

public class WorldBuilder {
    public WorldBuilder(World world, TiledMap map) {
        new Ground(world, map, JustAnotherPlatformer.GROUND_LAYER);

        new Coin(world, map, JustAnotherPlatformer.COIN_LAYER);
        new Door(world, map, JustAnotherPlatformer.DOOR_LAYER);
        new Moss(world, map, JustAnotherPlatformer.MOSS_LAYER);
    }
}
