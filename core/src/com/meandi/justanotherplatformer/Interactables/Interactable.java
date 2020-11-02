package com.meandi.justanotherplatformer.Interactables;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.meandi.justanotherplatformer.Helpers.BodyBuilder;
import com.meandi.justanotherplatformer.JustAnotherPlatformer;

public abstract class Interactable {
    protected World world;
    protected TiledMap map;
    protected TiledMapTile tile;
    protected Body body;
    protected Array<Fixture> fixtures;

    public Interactable(World world, TiledMap map, int layerID) {
        this.world = world;
        this.map = map;

        BodyBuilder bb = new BodyBuilder();
        fixtures = bb.buildShapes(map, world, JustAnotherPlatformer.PPT, layerID);
    }

    public void setCategoryFilter(short filterBit) {
        Filter filter = new Filter();
        filter.categoryBits = filterBit;

        for (Fixture f : fixtures)
            f.setFilterData(filter);
    }

    public TiledMapTileLayer.Cell getCell() {
        TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(JustAnotherPlatformer.GRAPHICS_LAYER);
        return layer.getCell(
                (int) (body.getPosition().x * JustAnotherPlatformer.PPT / JustAnotherPlatformer.TILE_SIZE),
                (int) (body.getPosition().y * JustAnotherPlatformer.PPT / JustAnotherPlatformer.TILE_SIZE)
        );
    }
}
