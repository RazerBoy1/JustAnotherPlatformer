package com.meandi.justanotherplatformer.Interactables;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.meandi.justanotherplatformer.Helpers.Assets;
import com.meandi.justanotherplatformer.UI.GameScreen;

public abstract class Enemy extends Sprite {
    protected GameScreen screen;
    protected World world;
    public Body body;

    public Enemy(GameScreen screen, float x, float y) {
        super(screen.getAssets().manager.get(Assets.SLIME_ATLAS).findRegion("slime-idle"));
        this.screen = screen;
        this.world = screen.getWorld();

        createEnemy();
        setPosition(x, y);
    }

    protected abstract void createEnemy();
}
