package com.meandi.justanotherplatformer.Items;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.meandi.justanotherplatformer.JustAnotherPlatformer;
import com.meandi.justanotherplatformer.UI.GameScreen;

public abstract class Item extends Sprite {
    protected GameScreen screen;
    protected World world;
    protected Body body;

    protected Vector2 velocity;

    protected boolean setToDestroy;
    protected boolean destroyed;

    public Item(GameScreen screen, float x, float y) {
        this.screen = screen;
        world = screen.getWorld();

        setToDestroy = destroyed = false;

        setPosition(x, y);
        setBounds(getX(), getY(), 16 / JustAnotherPlatformer.PPT, 16 / JustAnotherPlatformer.PPT);

        defineItem();
    }

    protected abstract void defineItem();
    protected abstract void useItem();

    public void updateSpritePosition(float delta) {
        if (setToDestroy && !destroyed) {
            world.destroyBody(body);
            destroyed = true;
        }
    }

    public void destroy() {
        setToDestroy = true;
    }

    @Override
    public void draw(Batch batch) {
        if (!destroyed)
            super.draw(batch);
    }
}
