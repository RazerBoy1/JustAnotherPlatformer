package com.meandi.justanotherplatformer.Interactables;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.meandi.justanotherplatformer.JustAnotherPlatformer;
import com.meandi.justanotherplatformer.UI.GameScreen;

public class Door extends Interactable {
    public Door(GameScreen screen, Body body, Fixture fixture, MapObject object)  {
        super(screen, body, fixture, object);

        fixture.setUserData(this);

        setCategoryFilter(JustAnotherPlatformer.OBJECT_BIT);
    }
}
