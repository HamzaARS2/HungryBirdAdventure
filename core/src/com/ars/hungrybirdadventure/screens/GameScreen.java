package com.ars.hungrybirdadventure.screens;

import static com.ars.hungrybirdadventure.Constants.HEIGHT;
import static com.ars.hungrybirdadventure.Constants.WIDTH;

import com.ars.hungrybirdadventure.GameWorld;
import com.ars.hungrybirdadventure.HungryBirdGame;
import com.ars.hungrybirdadventure.game_states.GameOverState;
import com.ars.hungrybirdadventure.game_states.GameState;
import com.ars.hungrybirdadventure.game_states.GameStateHandler;
import com.ars.hungrybirdadventure.game_states.IdleState;
import com.ars.hungrybirdadventure.game_states.PlayingState;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameScreen implements Screen {
    public static final int V_WIDTH = 480;
    public static final int V_HEIGHT = 800;
    private final HungryBirdGame game;
    private GameStateHandler currentState;
    private final GameWorld world;
    private OrthographicCamera cam;
    private Viewport viewport;
    private final Skin skin;



    public GameScreen(final HungryBirdGame game) {
        this.game = game;
        skin = new Skin(Gdx.files.internal("skins/yellow_skin/flat_yellow_skin.json"));

        cam = new OrthographicCamera();
        viewport = new FillViewport(WIDTH, HEIGHT, cam);
        viewport.apply();
        cam.setToOrtho(false, WIDTH, HEIGHT);

        world = new GameWorld(this, cam);
        setGameState(GameState.IDLE);
    }
    public HungryBirdGame getGame() {
        return game;
    }

    public void setGameState(GameState state) {
        changeState(state);
    }
    private void changeState(GameState state) {
        switch (state) {
            case IDLE:
                currentState = new IdleState(world, skin);
                break;
            case PLAYING:
                currentState = new PlayingState(world);
                break;
            case OVER:
                currentState = new GameOverState(world, skin, viewport);
                break;
        }
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(1, 1, 1, 1);
        game.batch.setProjectionMatrix(cam.combined);
        update(delta);
        game.batch.begin();
        currentState.render(delta);
        game.batch.end();
    }

    private void update(float dt) {
        currentState.update(dt);
        currentState.handleInput();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        cam.position.set(cam.viewportWidth / 2, cam.viewportHeight / 2, 0);
    }


    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        world.dispose();
    }
}
