package com.meandi.justanotherplatformer.Helpers;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.*;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;
import com.meandi.justanotherplatformer.Interactables.Coin;
import com.meandi.justanotherplatformer.Interactables.Door;
import com.meandi.justanotherplatformer.Interactables.Moss;
import com.meandi.justanotherplatformer.JustAnotherPlatformer;
import com.meandi.justanotherplatformer.UI.Hud;

public class WorldBuilder {
    private final World world;
    private final TiledMap map;
    private final Hud hud;
    private final Assets assets;

    public WorldBuilder(World world, TiledMap map, Hud hud, Assets assets) {
        this.world = world;
        this.map = map;
        this.hud = hud;
        this.assets = assets;

        for (int layerId : JustAnotherPlatformer.WORLD_LAYERS)
            iterateObjects(layerId);
    }

    private void iterateObjects(int layerId) {
        for (MapObject object : map.getLayers().get(layerId).getObjects()) {
            switch (layerId) {
                case JustAnotherPlatformer.COIN_LAYER:
                    createInteractables(object, layerId, true);
                    break;
                case JustAnotherPlatformer.DOOR_LAYER:
                case JustAnotherPlatformer.MOSS_LAYER:
                    createInteractables(object, layerId, false);
                    break;
                case JustAnotherPlatformer.GROUND_LAYER:
                default:
                    createNonInteractables(object);
                    break;
            }
        }
    }

    private void createInteractables(MapObject object, int layerId, boolean isSensor) {
        BodyDef bd = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fixDef = new FixtureDef();

        Rectangle rectangle = ((RectangleMapObject) object).getRectangle();

        bd.type = BodyDef.BodyType.StaticBody;
        bd.position.set((rectangle.getX() + rectangle.getWidth() / 2) / JustAnotherPlatformer.PPT, (rectangle.getY() + rectangle.getHeight() / 2) / JustAnotherPlatformer.PPT);

        Body body = world.createBody(bd);

        shape.setAsBox(rectangle.getWidth() / 2 / JustAnotherPlatformer.PPT, rectangle.getHeight() / 2 / JustAnotherPlatformer.PPT);
        fixDef.shape = shape;
        fixDef.isSensor = isSensor;
        Fixture fixture = body.createFixture(fixDef);

        switch (layerId) {
            case JustAnotherPlatformer.COIN_LAYER:
                new Coin(world, map, body, fixture, hud, assets);
                break;
            case JustAnotherPlatformer.DOOR_LAYER:
                new Door(world, map, body, fixture, hud, assets);
                break;
            case JustAnotherPlatformer.MOSS_LAYER:
                new Moss(world, map, body, fixture, hud, assets);
                break;
        }
    }

    private void createNonInteractables(MapObject object) {
        BodyDef bd = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fixDef = new FixtureDef();

        Rectangle rectangle = ((RectangleMapObject) object).getRectangle();

        bd.type = BodyDef.BodyType.StaticBody;
        bd.position.set((rectangle.getX() + rectangle.getWidth() / 2) / JustAnotherPlatformer.PPT, (rectangle.getY() + rectangle.getHeight() / 2) / JustAnotherPlatformer.PPT);

        Body body = world.createBody(bd);

        shape.setAsBox(rectangle.getWidth() / 2 / JustAnotherPlatformer.PPT, rectangle.getHeight() / 2 / JustAnotherPlatformer.PPT);
        fixDef.shape = shape;
        body.createFixture(fixDef);
    }
}