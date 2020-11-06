package com.meandi.justanotherplatformer.Interactables;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.meandi.justanotherplatformer.JustAnotherPlatformer;
import com.meandi.justanotherplatformer.UI.GameScreen;

public class Moss extends Interactable {
    public Moss(GameScreen screen, Body body, Fixture fixture) {
        super(screen, body, fixture);

        fixture.setUserData(this);

        setCategoryFilter(JustAnotherPlatformer.MOSS_BIT);
    }

    public void onHeadHit() {
        setCategoryFilter(JustAnotherPlatformer.REMOVED_BIT);

        getCell().setTile(null);

        hud.addScore(100);
    }
}
