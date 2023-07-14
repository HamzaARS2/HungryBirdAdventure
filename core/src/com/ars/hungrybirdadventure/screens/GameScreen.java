package com.ars.hungrybirdadventure.screens;

import static com.ars.hungrybirdadventure.Constants.HEIGHT;
import static com.ars.hungrybirdadventure.Constants.WIDTH;

import com.ars.hungrybirdadventure.HungryBirdGame;
import com.ars.hungrybirdadventure.sprites.Bird;
import com.ars.hungrybirdadventure.sprites.GameBackground;
import com.ars.hungrybirdadventure.sprites.Ground;
import com.ars.hungrybirdadventure.sprites.Tube;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameScreen implements Screen {

    private final HungryBirdGame game;
    private final GameBackground background;
    private final Ground ground;
    private final Bird bird;
    private OrthographicCamera cam;
    private Viewport viewport;
    private Array<Tube> tubes;


    public GameScreen(final HungryBirdGame game) {
        this.game = game;
        cam = new OrthographicCamera();
        viewport = new FillViewport(WIDTH, HEIGHT, cam);
        viewport.apply();
        background = new GameBackground(cam.position.x - cam.viewportWidth / 2);
        ground = new Ground(cam.position.x - cam.viewportWidth / 2);
        cam.setToOrtho(false, WIDTH, HEIGHT);
        bird = new Bird(cam.position.x, cam.position.y);
        tubes = new Array<>();
        for (int i = 0; i < 4; i++) {
            tubes.add(new Tube(ground.getTexture().getHeight()));
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(1, 1, 1, 1);
        update(delta);
        game.batch.setProjectionMatrix(cam.combined);
        game.batch.begin();
        drawBackground();
        drawBird();
        drawGround();
        drawTubes();
        game.batch.end();
    }

    private void update(float dt) {
        handleInput();
        bird.update(dt);
        background.update(dt);
        ground.update(dt);
        for (Tube tube: tubes) {
            tube.update(dt);
        }
        checkCollusion();
    }

    private void checkCollusion() {
        for (Tube tube: tubes) {
            if (bird.checkCollusion(tube.getTopTubeBounds()) || bird.checkCollusion(tube.getBotTubeBounds()))
                game.setScreen(new GameScreen(game));
        }
    }

    private void drawBird() {
        game.batch.draw(bird.getTexture(), bird.getPosition().x, bird.getPosition().y);
    }

    private void drawTubes() {
        for (Tube tube: tubes) {
            game.batch.draw(tube.getTexture(), tube.getTopTubePos().x , tube.getTopTubePos().y);
            game.batch.draw(tube.getTexture(), tube.getBotTubePos().x , tube.getBotTubePos().y);
        }
    }
    private void drawBackground() {
        game.batch.draw(background.getTexture(), background.getPosition().x, ground.getTexture().getHeight());
        if (background.getPosition().x < -(background.getTexture().getWidth() - cam.viewportWidth)) {
            game.batch.draw(background.getTexture(), background.getPosition().x + background.getTexture().getWidth(), ground.getTexture().getHeight());
        }

    }

    private void drawGround() {
        game.batch.draw(ground.getTexture(), ground.getPosition().x, 0);
        if (ground.getPosition().x < -(ground.getTexture().getWidth() - cam.viewportWidth))
            game.batch.draw(ground.getTexture(), ground.getPosition().x + ground.getTexture().getWidth(), 0);

    }

    private void handleInput() {
        if (Gdx.input.justTouched()) {
            bird.jump();
        }
    }

    @Override
    public void resize(int width, int height) {
        System.out.println("width = " + width + "height = " + height);
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
        background.dispose();
        ground.dispose();
        bird.dispose();
        for (Tube tube: tubes)
            tube.dispose();
    }
}
