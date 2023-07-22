package com.ars.hungrybirdadventure.game_states;

import com.ars.hungrybirdadventure.GameWorld;
import com.ars.hungrybirdadventure.HungryBirdGame;
import com.ars.hungrybirdadventure.screens.GameScreen;
import com.ars.hungrybirdadventure.sprites.Apple;
import com.ars.hungrybirdadventure.sprites.Bird;
import com.ars.hungrybirdadventure.sprites.GameBackground;
import com.ars.hungrybirdadventure.sprites.Ground;
import com.ars.hungrybirdadventure.sprites.Tube;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

public abstract class GameStateHandler {

    protected final GameScreen gameScreen;
    protected final HungryBirdGame game;
    protected final GameBackground background;
    protected final Ground ground;
    protected final Bird bird;
    protected OrthographicCamera cam;
    protected Array<Tube> tubes;
    protected Array<Apple> apples;

    public GameStateHandler(final GameWorld world) {
        this.gameScreen = world.getGameScreen();
        this.cam = world.getCam();
        this.game = gameScreen.getGame();
        world.loadWorldObjects();
        background = world.getBackground();
        ground = world.getGround();
        bird = world.getBird();
        tubes = world.getTubes();
        apples = world.getApples();

    }

    public abstract void render(float delta);
    public abstract void update(float delta);
    public abstract void handleInput();
}
