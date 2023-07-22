package com.ars.hungrybirdadventure.game_states;

import com.ars.hungrybirdadventure.GameWorld;
import com.ars.hungrybirdadventure.HungryBirdGame;
import com.ars.hungrybirdadventure.screens.GameScreen;
import com.ars.hungrybirdadventure.sprites.Bird;
import com.ars.hungrybirdadventure.sprites.GameBackground;
import com.ars.hungrybirdadventure.sprites.Ground;
import com.ars.hungrybirdadventure.sprites.Tube;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.Array;

public class PlayingState extends GameStateHandler {
    public PlayingState(final GameWorld world) {
        super(world);
    }
    @Override
    public void render(float delta) {
        drawBackground();
        drawBird();
        drawGround();
        drawTubes();
        drawApples();
    }

    @Override
    public void update(float delta) {
        bird.update(delta);
        background.update(delta);
        ground.update(delta);

        for (int i = 0; i < tubes.size; i++) {
            boolean repositioned = tubes.get(i).update(delta);
            apples.get(i).update(delta, repositioned, tubes.get(i).getTopTubePos());
        }

        checkCollusion();
    }

    @Override
    public void handleInput() {
        if (Gdx.input.justTouched()) {
            bird.jump();
        }
    }
    private void checkCollusion() {
        if (bird.getPosition().y + bird.getTexture().getTexture().getHeight() <= ground.getPosition().y + ground.getTexture().getHeight())
            gameScreen.setGameState(GameState.OVER);
        for (int i = 0; i < tubes.size; i++) {
            if (bird.checkCollusion(tubes.get(i).getTopTubeBounds()) || bird.checkCollusion(tubes.get(i).getBotTubeBounds()))
                gameScreen.setGameState(GameState.OVER);
        }
    }
    private void drawBird() {
        game.batch.draw(bird.getTexture(), bird.getPosition().x, bird.getPosition().y);
    }

    private void drawTubes() {
        for (int i = 0; i < tubes.size; i++) {
            game.batch.draw(tubes.get(i).getTexture(), tubes.get(i).getTopTubePos().x , tubes.get(i).getTopTubePos().y);
            game.batch.draw(tubes.get(i).getTexture(), tubes.get(i).getBotTubePos().x , tubes.get(i).getBotTubePos().y);
        }
    }
    private void drawApples() {
        for (int i = 0; i < apples.size; i++) {
            game.batch.draw(apples.get(i).getTexture(), apples.get(i).getPosition().x
                    , apples.get(i).getPosition().y);
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
}
