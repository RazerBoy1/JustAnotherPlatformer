package com.meandi.justanotherplatformer.Overlays;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.meandi.justanotherplatformer.Characters.Hero;
import com.meandi.justanotherplatformer.JustAnotherPlatformer;
import com.meandi.justanotherplatformer.Screens.GameScreen;
import com.meandi.justanotherplatformer.Utils.Assets;

public class GamePad implements Disposable {
    Viewport port;
    Stage stage;
    OrthographicCamera cam;

    Assets assets;
    Hero hero;

    public GamePad(GameScreen screen) {
        cam = new OrthographicCamera();
        port = new StretchViewport(JustAnotherPlatformer.WIDTH, JustAnotherPlatformer.HEIGHT, cam);
        stage = new Stage();
        assets = screen.getAssets();
        hero = screen.getHero();

        Image imgRight = new Image(assets.manager.get(Assets.BUTTON_RIGHT));
        Image imgLeft = new Image(assets.manager.get(Assets.BUTTON_LEFT));
        Image imgLeftUp = new Image(assets.manager.get(Assets.BUTTON_UP));
        Image imgRightUp = new Image(assets.manager.get(Assets.BUTTON_UP));

        imgRight.setSize(125, 125);
        imgLeft.setSize(125, 125);
        imgLeftUp.setSize(125, 125);
        imgRightUp.setSize(125, 125);

        imgRight.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (hero.isDead())
                    hero.setRightMove(true);

                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (hero.isDead())
                    hero.setRightMove(false);
            }
        });
        imgLeft.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (hero.isDead())
                    hero.setLeftMove(true);

                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (hero.isDead())
                    hero.setLeftMove(false);
            }
        });
        imgLeftUp.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (hero.isDead())
                    hero.setJump(true);
                return true;
            }
        });
        imgRightUp.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (hero.isDead())
                    hero.setJump(true);
                return true;
            }
        });

        Table t1 = new Table();
        Table t2 = new Table();

        t1.debugAll();
        t2.debugAll();

        t1.left().bottom();
        t2.right().bottom();

        t1.setFillParent(true);
        t2.setFillParent(true);

        t1.add();
        t1.add(imgLeftUp).size(imgLeftUp.getWidth(), imgLeftUp.getHeight());
        t1.row().pad(5, 5, 5, 5);
        t1.add(imgLeft).size(imgLeft.getWidth(), imgLeft.getHeight());
        t1.add();

        t2.add();
        t2.add(imgRightUp).size(imgRightUp.getWidth(), imgRightUp.getHeight());
        t2.add();
        t2.row().pad(5, 5, 5, 5);
        t2.add();
        t2.add();
        t2.add(imgRight).size(imgRight.getWidth(), imgRight.getHeight());

        stage.addActor(t1);
        stage.addActor(t2);
    }

    public void draw() {
        stage.draw();
    }

    public void resized(int width, int height) {
        port.update(width, height);
        cam.position.set(cam.viewportWidth / 2f, cam.viewportHeight / 2f, 0);
        cam.update();
    }

    public Stage getStage() {
        return stage;
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
