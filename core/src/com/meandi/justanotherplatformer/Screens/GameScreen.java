package com.meandi.justanotherplatformer.Screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.meandi.justanotherplatformer.*;
import com.meandi.justanotherplatformer.Characters.Hero;
import com.meandi.justanotherplatformer.Characters.Slime;
import com.meandi.justanotherplatformer.Items.HealthPotion;
import com.meandi.justanotherplatformer.Items.Item;
import com.meandi.justanotherplatformer.Items.ItemDefinition;
import com.meandi.justanotherplatformer.Overlays.GamePad;
import com.meandi.justanotherplatformer.Overlays.Hud;
import com.meandi.justanotherplatformer.Utils.Assets;
import com.meandi.justanotherplatformer.Utils.HeroKeyboardInputProcessor;
import com.meandi.justanotherplatformer.Utils.WorldBuilder;
import com.meandi.justanotherplatformer.Utils.WorldContactListener;

public class GameScreen extends GeneralScreen {
    private final Hud hud;

    private final TiledMap map;
    private final OrthogonalTiledMapRenderer renderer;

    private final World world;
    //private final Box2DDebugRenderer boxDebugger;

    private final WorldBuilder worldBuilder;

    private final Hero hero;

    private final Array<Item> items;
    private final Array<ItemDefinition> itemsToSpawn;

    private final GamePad gamePad;

    public GameScreen(JustAnotherPlatformer jap) {
        super(jap);
        port = new StretchViewport(JustAnotherPlatformer.WIDTH / JustAnotherPlatformer.PPT, JustAnotherPlatformer.HEIGHT / JustAnotherPlatformer.PPT, cam);

        hud = new Hud(jap.spriteBatch, assets);

        map = new TmxMapLoader().load("world/levels/level1.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1 / JustAnotherPlatformer.PPT);

        cam.position.set(port.getWorldWidth() / 2, port.getWorldHeight() / 2, 0);

        world = new World(new Vector2(0, JustAnotherPlatformer.GRAVITY), true);
        world.setContactListener(new WorldContactListener());
        //boxDebugger = new Box2DDebugRenderer();

        worldBuilder = new WorldBuilder(this);

        hero = new Hero(this, assets.manager.get(Assets.HERO_ATLAS).findRegion("herochar_idle_anim_strip"));

        assets.playMusic();

        items = new Array<>();
        itemsToSpawn = new Array<>();

        gamePad = new GamePad(this);

        handleUserInput();
    }

    private void handleUserInput() {
        InputMultiplexer multiplexer = new InputMultiplexer();

        // TODO: Uncomment this for production and comment the if statement below the commented one
        /*if (Gdx.app.getType() == Application.ApplicationType.Android || Gdx.app.getType() == Application.ApplicationType.iOS)
            multiplexer.addProcessor(gamePad.getStage());
        else if (Gdx.app.getType() == Application.ApplicationType.Desktop)
            multiplexer.addProcessor(new HeroKeyboardInputProcessor(hero));*/

        if (Gdx.app.getType() == Application.ApplicationType.Android || Gdx.app.getType() == Application.ApplicationType.iOS)
            multiplexer.addProcessor(gamePad.getStage());
        multiplexer.addProcessor(new HeroKeyboardInputProcessor(hero));

        Gdx.input.setInputProcessor(multiplexer);
    }

    @Override
    public void render(float delta) {
        update(delta);
        clearScreen();

        renderer.render();
        //boxDebugger.render(world, cam.combined);

        gamePad.draw();

        jap.spriteBatch.setProjectionMatrix(cam.combined);
        jap.spriteBatch.begin();

        hero.draw(jap.spriteBatch);

        for (Slime slime : worldBuilder.getSlimes())
            slime.draw(jap.spriteBatch);

        for (Item item : new Array.ArrayIterator<>(items))
            item.draw(jap.spriteBatch);

        jap.spriteBatch.end();

        jap.spriteBatch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();

        if (gameOver()) {
            jap.setScreen(new GameFinishedScreen(jap, hud, false));
            dispose();
        } else if (levelCompleted()) {
            jap.setScreen(new GameFinishedScreen(jap, hud, true));
            dispose();
        }
    }

    private boolean gameOver() {
        return (hero.isDead() && hero.getStateTimer() > 2);
    }

    private boolean levelCompleted() {
        return (hero.hasCompletedLevel() && hero.getStateTimer() > 1.5f);
    }

    public void update(float delta) {
        hero.updateMotion();
        spawnItems();

        world.step(1 / 60f, 6, 6);

        hero.updateSpritePosition(delta);
        for (Slime slime : worldBuilder.getSlimes()) {
            slime.updateSpritePosition(delta);

            if (slime.getX() < hero.getX() + 208 / JustAnotherPlatformer.PPT)
                slime.body.setActive(true);
        }

        for (Item item : new Array.ArrayIterator<>(items))
            item.update(delta);

        hud.update(delta);

        if (hero.isAllowedToMove())
            cam.position.x = hero.body.getPosition().x;

        cam.update();
        renderer.setView(cam);
    }

    private void spawnItems() {
        if (!itemsToSpawn.isEmpty()) {
            ItemDefinition itemDef = itemsToSpawn.peek();
            itemsToSpawn.removeIndex(itemsToSpawn.size - 1);

            if (itemDef.type == HealthPotion.class)
                items.add(new HealthPotion(this, itemDef.position.x, itemDef.position.y));
        }
    }

    public void spawnItem(ItemDefinition itemDef) {
        itemsToSpawn.add(itemDef);
    }

    public World getWorld() {
        return world;
    }

    public TiledMap getMap() {
        return map;
    }

    public Hud getHud() {
        return hud;
    }

    public Hero getHero() {
        return hero;
    }

    @Override
    public void resize(int width, int height) {
        port.update(width, height);
        gamePad.resized(width, height);
    }

    @Override
    public void dispose() {
        map.dispose();
        renderer.dispose();
        world.dispose();
        //boxDebugger.dispose();
        assets.dispose();
        hud.dispose();
        gamePad.dispose();
    }
}
