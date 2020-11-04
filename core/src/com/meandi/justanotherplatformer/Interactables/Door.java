package com.meandi.justanotherplatformer.Interactables;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;
import com.meandi.justanotherplatformer.Helpers.Assets;
import com.meandi.justanotherplatformer.JustAnotherPlatformer;
import com.meandi.justanotherplatformer.UI.GameScreen;
import com.meandi.justanotherplatformer.UI.Hud;

public class Door extends Interactable {
    public Door(GameScreen screen, Body body, Fixture fixture, Assets assets, Hud hud)  {
        super(screen, body, fixture, assets, hud);

        fixture.setUserData(this);

        setCategoryFilter(JustAnotherPlatformer.DOOR_BIT);
    }
}
