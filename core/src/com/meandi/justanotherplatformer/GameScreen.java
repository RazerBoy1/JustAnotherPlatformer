package com.meandi.justanotherplatformer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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

public class GameScreen implements Screen {
    private final JustAnotherPlatformer jap;

    private final OrthographicCamera cam;
    private final Viewport port;
    private final Hud hud;

    private final TmxMapLoader mapLoader;
    private final TiledMap map;
    private final OrthogonalTiledMapRenderer renderer;

    private final World world;
    private final Box2DDebugRenderer boxDebugger;

    private final Player player;

    public GameScreen(JustAnotherPlatformer jap) {
        this.jap = jap;
        cam = new OrthographicCamera();
        port = new StretchViewport(JustAnotherPlatformer.WIDTH / JustAnotherPlatformer.PPT, JustAnotherPlatformer.HEIGHT / JustAnotherPlatformer.PPT, cam);
        hud = new Hud(jap.batch);

        mapLoader = new TmxMapLoader();
        map = mapLoader.load("world/level1.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1 / JustAnotherPlatformer.PPT);

        cam.position.set(port.getWorldWidth() / 2, port.getWorldHeight() / 2, 0);

        world = new World(new Vector2(0, -10), true);
        boxDebugger = new Box2DDebugRenderer();
        createMapBodies();

        player = new Player(world);
    }

    public void createMapBodies() {
        for (int layerID = 0; layerID < 4; layerID++)
            MapBodyBuilder.buildShapes(map, world, JustAnotherPlatformer.PPT, layerID);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        update();
        clearScreen();

        renderer.render();
        boxDebugger.render(world, cam.combined);

        jap.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
    }

    public void update() {
        handleInput();

        world.step(1 / 60f, 6, 6);
        cam.position.x = player.body.getPosition().x;

        cam.update();
        renderer.setView(cam);
    }

    public void handleInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) // Če dam tega DOWN tut ne prime !?
            player.body.applyLinearImpulse(new Vector2(0, 5f), player.body.getWorldCenter(), true);

        if (Gdx.input.isKeyPressed(Input.Keys.DOWN) && player.body.getLinearVelocity().x <= 1)
            player.body.applyLinearImpulse(new Vector2(0.1f, 0), player.body.getWorldCenter(), true);
        if (Gdx.input.isKeyPressed(Input.Keys.UP) && player.body.getLinearVelocity().x >= -1)
            player.body.applyLinearImpulse(new Vector2(-0.1f, 0), player.body.getWorldCenter(), true);
    }

    public void clearScreen() {
        Gdx.gl.glClearColor(1, 0, 0, 1);
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
    }
}
