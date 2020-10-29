package com.meandi.justanotherplatformer.Interactables;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.physics.box2d.*;
import com.meandi.justanotherplatformer.Helpers.BodyBuilder;

public class Interactable {
    protected World world;
    protected TiledMap map;
    protected TiledMapTile tile;
    protected Body body;

    public Interactable(World world, TiledMap map, int layerID) {
        this.world = world;
        this.map = map;

        new BodyBuilder(world, map, layerID);
    }
}
