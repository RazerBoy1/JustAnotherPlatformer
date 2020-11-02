package com.meandi.justanotherplatformer.Helpers;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.meandi.justanotherplatformer.Interactables.Hero;

public class MyInputProcessor implements InputProcessor {
    Hero hero;

    public MyInputProcessor(Hero hero) {
        this.hero = hero;
    }

    public boolean keyDown(int keycode) {
        //System.out.println("------------------------------------------------");
        //System.out.println("LEFT: " + Keys.LEFT);
        //System.out.println("RIGHT: " + Keys.RIGHT);
        //System.out.println("UP: " + Keys.UP);
        //System.out.println("DOWN: " + Keys.DOWN);
        //System.out.println("------------------------------------------------");
        //System.out.println();
        //System.out.println("------------------------------------------------");

        switch (keycode) {
            case 19:
                hero.setLeftMove(true);
                //System.out.println("LEFT: " + 19);
                break;
            case 20:
                hero.setRightMove(true);
                //System.out.println("RIGHT: " + 20);
                break;
            case 21:
                //System.out.println("DOWN: " + 21);
                break;
            case 22:
                hero.setJump(true);
                //System.out.println("UP: " + 22);
                break;
            default:
                //System.out.println("KEYCODE - KEYDOWN: " + keycode);
                break;
        }
        //System.out.println("------------------------------------------------");

        return false;
    }

    public boolean keyUp(int keycode) {
        switch (keycode) {
            case 19:
                hero.setLeftMove(false);
                //System.out.println("LEFT: " + 19);
                break;
            case 20:
                hero.setRightMove(false);
                //System.out.println("RIGHT: " + 20);
                break;
            case 21:
                //System.out.println("DOWN: " + 21);
                break;
            case 22:
                //System.out.println("UP: " + 22);
                break;
            default:
                //System.out.println("KEYCODE - KEYDOWN: " + keycode);
                break;
        }

        //System.out.println("************************************************");
        //System.out.println("KEYCODE - KEYUP: " + keycode);
        //System.out.println("************************************************");

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
