package com.meandi.justanotherplatformer.Interactables;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.meandi.justanotherplatformer.Items.HealthPotion;
import com.meandi.justanotherplatformer.Items.ItemDefinition;
import com.meandi.justanotherplatformer.JustAnotherPlatformer;
import com.meandi.justanotherplatformer.UI.GameScreen;
import com.meandi.justanotherplatformer.Utils.Assets;

public class Moss extends Interactable {
    GameScreen screen;

    public Moss(GameScreen screen, Body body, Fixture fixture, MapObject object) {
        super(screen, body, fixture, object);
        this.screen = screen;

        fixture.setUserData(this);

        setCategoryFilter(JustAnotherPlatformer.MOSS_BIT);
    }

    public void onFeetHit() {
        if (object.getProperties().containsKey("Bouncy")) {
            assets.manager.get(Assets.BLOCK_BREAK).play();
            setCategoryFilter(JustAnotherPlatformer.REMOVED_BIT);
            getCell().setTile(null);
        }
    }

    public void onHeadHit() {
        assets.manager.get(Assets.BUMP_HERO_HEAD).play();
        setCategoryFilter(JustAnotherPlatformer.REMOVED_BIT);

        if (object.getProperties().containsKey("HealthPotion"))
            screen.spawnItem(new ItemDefinition(new Vector2(body.getPosition().x, body.getPosition().y + 16 / JustAnotherPlatformer.PPT), HealthPotion.class));

        getCell().setTile(null);

        hud.addScore(100);
    }
}
