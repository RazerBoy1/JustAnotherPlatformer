package com.meandi.justanotherplatformer.Interactables;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.physics.box2d.*;
import com.meandi.justanotherplatformer.Utils.Assets;
import com.meandi.justanotherplatformer.JustAnotherPlatformer;
import com.meandi.justanotherplatformer.Screens.GameScreen;
import com.meandi.justanotherplatformer.Overlays.Hud;

public abstract class Interactable {
    protected World world;
    protected TiledMap map;
    protected TiledMapTile tile;
    protected Body body;
    protected Fixture fixture;
    protected Hud hud;
    protected Assets assets;
    protected MapObject object;

    public Interactable(GameScreen screen, Body body, Fixture fixture, MapObject object) {
        world = screen.getWorld();
        map = screen.getMap();
        assets = screen.getAssets();
        hud = screen.getHud();
        this.object = object;

        this.body = body;
        this.fixture = fixture;
    }

    public void setCategoryFilter(short filterBit) {
        Filter filter = new Filter();

        filter.categoryBits = filterBit;
        fixture.setFilterData(filter);
    }

    public TiledMapTileLayer.Cell getCell() {
        TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(JustAnotherPlatformer.GRAPHICS_LAYER);

        return layer.getCell(
                (int) (body.getPosition().x * JustAnotherPlatformer.PPT / 8),
                (int) (body.getPosition().y * JustAnotherPlatformer.PPT / 8)
        );
    }
}
