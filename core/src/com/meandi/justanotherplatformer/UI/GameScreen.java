package com.meandi.justanotherplatformer.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.meandi.justanotherplatformer.*;
import com.meandi.justanotherplatformer.Helpers.Assets;
import com.meandi.justanotherplatformer.Helpers.MyInputProcessor;
import com.meandi.justanotherplatformer.Helpers.WorldBuilder;
import com.meandi.justanotherplatformer.Helpers.WorldContactListener;
import com.meandi.justanotherplatformer.Interactables.Hero;

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

    private final Hero hero;

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
        new WorldBuilder(world, map);

        hero = new Hero(world, assets);

        Gdx.input.setInputProcessor(new MyInputProcessor(hero));

        world.setContactListener(new WorldContactListener());
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
        jap.batch.end();

        jap.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
    }

    public void update(float delta) {
        hero.updateMotion();

        world.step(1 / 60f, 6, 6);
        hero.updateSpritePosition(delta);
        cam.position.x = hero.body.getPosition().x;

        cam.update();
        renderer.setView(cam);
    }

    public void clearScreen() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
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
