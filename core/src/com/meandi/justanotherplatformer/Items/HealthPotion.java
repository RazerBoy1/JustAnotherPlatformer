package com.meandi.justanotherplatformer.Items;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.meandi.justanotherplatformer.Characters.Hero;
import com.meandi.justanotherplatformer.JustAnotherPlatformer;
import com.meandi.justanotherplatformer.UI.GameScreen;
import com.meandi.justanotherplatformer.UI.Hud;
import com.meandi.justanotherplatformer.Utils.Assets;

public class HealthPotion extends Item {
    Hud hud;
    Assets assets;

    public HealthPotion(GameScreen screen, float x, float y) {
        super(screen, x, y);
        hud = screen.getHud();
        assets = screen.getAssets();

        setRegion(screen.getAssets().manager.get(Assets.HEALTH_POTION));
    }

    @Override
    protected void defineItem() {
        BodyDef bodyDef = new BodyDef();
        FixtureDef fixDef = new FixtureDef();
        CircleShape circleShape = new CircleShape();

        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(getX(), getY());
        body = world.createBody(bodyDef);

        circleShape.setRadius(3f / JustAnotherPlatformer.PPT);
        fixDef.shape = circleShape;
        fixDef.filter.categoryBits = JustAnotherPlatformer.ITEM_BIT;
        fixDef.filter.maskBits = JustAnotherPlatformer.DEFAULT_BIT |
                JustAnotherPlatformer.MOSS_BIT |
                JustAnotherPlatformer.ITEM_BIT |
                JustAnotherPlatformer.OBJECT_BIT |
                JustAnotherPlatformer.HERO_BIT |
                JustAnotherPlatformer.ENEMY_BIT;

        body.createFixture(fixDef).setUserData(this);
    }

    @Override
    public void useItem(Hero hero) {
        if (hud.getHearthCount() != 3) {
            assets.manager.get(Assets.HEALTH_POTION_SOUND).stop();
            assets.manager.get(Assets.HEALTH_POTION_SOUND).play();

            destroy();
            hud.addHearth();
        }
    }

    public void update(float delta) {
        super.update(delta);
        setPosition(body.getPosition().x - (getWidth() * JustAnotherPlatformer.ITEM_SCALE) / 2, body.getPosition().y - (getHeight() * JustAnotherPlatformer.ITEM_SCALE) / 2);
    }
}
