package com.meandi.justanotherplatformer.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.meandi.justanotherplatformer.*;
import com.meandi.justanotherplatformer.Characters.Character;
import com.meandi.justanotherplatformer.Characters.Hero;
import com.meandi.justanotherplatformer.Characters.Slime;
import com.meandi.justanotherplatformer.Items.HealthPotion;
import com.meandi.justanotherplatformer.Items.Item;
import com.meandi.justanotherplatformer.Items.ItemDefinition;
import com.meandi.justanotherplatformer.Utils.Assets;
import com.meandi.justanotherplatformer.Utils.MyInputProcessor;
import com.meandi.justanotherplatformer.Utils.WorldBuilder;
import com.meandi.justanotherplatformer.Utils.WorldContactListener;

public class GameScreen implements Screen {
    private final JustAnotherPlatformer jap;

    private final OrthographicCamera cam;
    private final Viewport port;

    private final Assets assets;

    private final Hud hud;

    private final TiledMap map;
    private final OrthogonalTiledMapRenderer renderer;

    private final World world;
    private final Box2DDebugRenderer boxDebugger;

    private final WorldBuilder worldBuilder;

    private final Hero hero;

    private final Array<Item> items;
    private final Array<ItemDefinition> itemsToSpawn;

    public GameScreen(JustAnotherPlatformer jap) {
        this.jap = jap;
        cam = new OrthographicCamera();
        port = new StretchViewport(JustAnotherPlatformer.WIDTH / JustAnotherPlatformer.PPT, JustAnotherPlatformer.HEIGHT / JustAnotherPlatformer.PPT, cam);

        assets = new Assets();
        assets.load();

        hud = new Hud(jap.batch, assets);

        map = new TmxMapLoader().load("world/levels/test_level.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1 / JustAnotherPlatformer.PPT);

        cam.position.set(port.getWorldWidth() / 2, port.getWorldHeight() / 2, 0);

        world = new World(new Vector2(0, JustAnotherPlatformer.GRAVITY), true);
        boxDebugger = new Box2DDebugRenderer();

        worldBuilder = new WorldBuilder(this);

        hero = new Hero(this, assets.manager.get(Assets.HERO_ATLAS).findRegion("herochar_idle_anim_strip"));

        Gdx.input.setInputProcessor(new MyInputProcessor(hero));

        world.setContactListener(new WorldContactListener());

        Music music = assets.manager.get(Assets.MUSIC);
        music.setLooping(true);
        music.play();

        items = new Array<>();
        itemsToSpawn = new Array<>();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        update(delta);
        clearScreen();

        renderer.render();
        boxDebugger.render(world, cam.combined);

        jap.batch.setProjectionMatrix(cam.combined);
        jap.batch.begin();

        hero.draw(jap.batch);

        for (Slime slime : worldBuilder.getSlimes())
            slime.draw(jap.batch);

        for (Item item : new Array.ArrayIterator<>(items))
            item.draw(jap.batch);

        jap.batch.end();

        jap.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();

        if (gameOver()) {
            jap.setScreen(new GameOverScreen(jap));
            dispose();
        }
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

        if (!hero.isDead())
            cam.position.x = hero.body.getPosition().x;

        cam.update();
        renderer.setView(cam);
    }

    private boolean gameOver() {
        return (hero.isDead() && hero.getStateTimer() > 2);
    }

    public void spawnItem(ItemDefinition itemDef) {
        itemsToSpawn.add(itemDef);
    }

    private void spawnItems() {
        if (!itemsToSpawn.isEmpty()) {
            ItemDefinition itemDef = itemsToSpawn.peek();
            itemsToSpawn.removeIndex(itemsToSpawn.size - 1);

            if (itemDef.type == HealthPotion.class)
                items.add(new HealthPotion(this, itemDef.position.x, itemDef.position.y));
        }
    }

    private void clearScreen() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    public World getWorld() {
        return world;
    }

    public TiledMap getMap() {
        return map;
    }

    public Assets getAssets() {
        return assets;
    }

    public Hud getHud() {
        return hud;
    }

    @Override
    public void resize(int width, int height) {
        port.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        map.dispose();
        renderer.dispose();
        world.dispose();
        boxDebugger.dispose();
        hud.dispose();
        assets.dispose();
    }
}
