package com.meandi.justanotherplatformer.Items;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.meandi.justanotherplatformer.Characters.Hero;
import com.meandi.justanotherplatformer.JustAnotherPlatformer;
import com.meandi.justanotherplatformer.Screens.GameScreen;

public abstract class Item extends Sprite {
    protected GameScreen screen;
    protected World world;
    protected Body body;

    protected boolean setToDestroy;
    protected boolean destroyed;

    public Item(GameScreen screen, float x, float y) {
        this.screen = screen;
        world = screen.getWorld();

        setToDestroy = destroyed = false;

        setScale(JustAnotherPlatformer.ITEM_SCALE);
        setPosition(x, y);
        setBounds(getX(), getY(), 16 / JustAnotherPlatformer.PPT, 16 / JustAnotherPlatformer.PPT);

        defineItem();
    }

    protected abstract void defineItem();
    public abstract void useItem(Hero hero);

    public void update(float delta) {
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
