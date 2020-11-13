package com.meandi.justanotherplatformer.Utils;

import com.badlogic.gdx.InputProcessor;
import com.meandi.justanotherplatformer.Characters.Hero;

public class MyInputProcessor implements InputProcessor {
    Hero hero;

    public MyInputProcessor(Hero hero) {
        this.hero = hero;
    }

    public boolean keyDown(int keycode) {
        if (hero.isAllowedToMove())
            switch (keycode) {
                case 19:
                    hero.setLeftMove(true);
                    break;
                case 20:
                    hero.setRightMove(true);
                    break;
                case 22:
                    hero.setJump(true);
                    break;
            }

        return false;
    }

    public boolean keyUp(int keycode) {
        switch (keycode) {
            case 19:
                hero.setLeftMove(false);
                break;
            case 20:
                hero.setRightMove(false);
                break;
        }

        return false;
    }

    public boolean keyTyped(char character) {
        return false;
    }

    public boolean touchDown(int x, int y, int pointer, int button) {
        return false;
    }

    public boolean touchUp(int x, int y, int pointer, int button) {
        return false;
    }

    public boolean touchDragged(int x, int y, int pointer) {
        return false;
    }

    public boolean mouseMoved(int x, int y) {
        return false;
    }

    public boolean scrolled(int amount) {
        return false;
    }
}
