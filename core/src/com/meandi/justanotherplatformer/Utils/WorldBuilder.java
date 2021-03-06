package com.meandi.justanotherplatformer.Utils;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.*;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.meandi.justanotherplatformer.Characters.Slime;
import com.meandi.justanotherplatformer.Interactables.Coin;
import com.meandi.justanotherplatformer.Interactables.End;
import com.meandi.justanotherplatformer.Interactables.Moss;
import com.meandi.justanotherplatformer.JustAnotherPlatformer;
import com.meandi.justanotherplatformer.Screens.GameScreen;

public class WorldBuilder {
    private final GameScreen screen;
    private final World world;
    private final TiledMap map;
    private final Assets assets;

    private final Array<Slime> slimes;

    public WorldBuilder(GameScreen screen) {
        this.screen = screen;
        world = screen.getWorld();
        map = screen.getMap();
        assets = screen.getAssets();

        slimes = new Array<>();

        for (String layerName : JustAnotherPlatformer.WORLD_LAYERS)
            iterateObjects(layerName);
    }

    private void iterateObjects(String layerName) {
        for (MapObject object : map.getLayers().get(layerName).getObjects())
            switch (layerName) {
                case JustAnotherPlatformer.MOSS_LAYER:
                    if (object.getProperties().containsKey("Bouncy"))
                        createInteractables(object, layerName, 2f, false);
                    else
                        createInteractables(object, layerName, 0f, false);
                    break;
                case JustAnotherPlatformer.SLIME_LAYER:
                    createEnemies(object);
                    break;
                case JustAnotherPlatformer.COIN_LAYER:
                case JustAnotherPlatformer.END_LAYER:
                    createInteractables(object, layerName, 0f, true);
                    break;
                case JustAnotherPlatformer.GROUND_LAYER:
                default:
                    createInteractables(object, layerName, 0f, false);
                    break;
            }
    }

    private void createEnemies(MapObject object) {
        Rectangle rect = ((RectangleMapObject) object).getRectangle();
        slimes.add(new Slime(screen, assets.manager.get(Assets.SLIME_ATLAS).findRegion("slime_idle_anim_strip"),
                rect.getX() / JustAnotherPlatformer.PPT, rect.getY() / JustAnotherPlatformer.PPT));
    }

    private void createInteractables(MapObject object, String layerName, float restitution, boolean isSensor) {
        BodyDef bd = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fixDef = new FixtureDef();

        Rectangle rectangle = ((RectangleMapObject) object).getRectangle();

        bd.type = BodyDef.BodyType.StaticBody;
        bd.position.set((rectangle.getX() + rectangle.getWidth() / 2) / JustAnotherPlatformer.PPT, (rectangle.getY() + rectangle.getHeight() / 2) / JustAnotherPlatformer.PPT);

        shape.setAsBox(rectangle.getWidth() / 2 / JustAnotherPlatformer.PPT, rectangle.getHeight() / 2 / JustAnotherPlatformer.PPT);
        fixDef.shape = shape;
        fixDef.restitution = restitution;
        fixDef.isSensor = isSensor;

        Body body = world.createBody(bd);
        Fixture fixture = body.createFixture(fixDef);

        switch (layerName) {
            case JustAnotherPlatformer.COIN_LAYER:
                new Coin(screen, body, fixture, object);
                break;
            case JustAnotherPlatformer.MOSS_LAYER:
                new Moss(screen, body, fixture, object);
                break;
            case JustAnotherPlatformer.END_LAYER:
                new End(screen, body, fixture, object);
                break;
        }
    }

    public Array.ArrayIterator<Slime> getSlimes() {
        return new Array.ArrayIterator<>(slimes);
    }
}