package com.meandi.justanotherplatformer.Utils;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.*;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;
import com.meandi.justanotherplatformer.Interactables.Coin;
import com.meandi.justanotherplatformer.Interactables.Door;
import com.meandi.justanotherplatformer.Interactables.Moss;
import com.meandi.justanotherplatformer.JustAnotherPlatformer;
import com.meandi.justanotherplatformer.UI.GameScreen;

public class WorldBuilder {
    private final GameScreen screen;
    private final World world;
    private final TiledMap map;

    public WorldBuilder(GameScreen screen) {
        this.screen = screen;
        world = screen.getWorld();
        map = screen.getMap();

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
                    createBodyAndFixture(object, false);
                    break;
            }
        }
    }

    private void createInteractables(MapObject object, int layerId, boolean isSensor) {
        Object[] bodyAndFixture = createBodyAndFixture(object, isSensor);

        Body body = (Body) bodyAndFixture[0];
        Fixture fixture = (Fixture) bodyAndFixture[1];

        switch (layerId) {
            case JustAnotherPlatformer.COIN_LAYER:
                new Coin(screen, body, fixture);
                break;
            case JustAnotherPlatformer.DOOR_LAYER:
                new Door(screen, body, fixture);
                break;
            case JustAnotherPlatformer.MOSS_LAYER:
                new Moss(screen, body, fixture);
                break;
        }
    }

    private Object[] createBodyAndFixture(MapObject object, boolean isSensor) {
        BodyDef bd = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fixDef = new FixtureDef();

        Rectangle rectangle = ((RectangleMapObject) object).getRectangle();

        bd.type = BodyDef.BodyType.StaticBody;
        bd.position.set((rectangle.getX() + rectangle.getWidth() / 2) / JustAnotherPlatformer.PPT, (rectangle.getY() + rectangle.getHeight() / 2) / JustAnotherPlatformer.PPT);

        shape.setAsBox(rectangle.getWidth() / 2 / JustAnotherPlatformer.PPT, rectangle.getHeight() / 2 / JustAnotherPlatformer.PPT);
        fixDef.shape = shape;
        fixDef.isSensor = isSensor;

        Body body = world.createBody(bd);
        Fixture fixture = body.createFixture(fixDef);

        return new Object[]{body, fixture};
    }
}