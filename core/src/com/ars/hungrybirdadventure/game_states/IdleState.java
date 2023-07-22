package com.ars.hungrybirdadventure.game_states;

import com.ars.hungrybirdadventure.GameWorld;
import com.ars.hungrybirdadventure.HungryBirdGame;
import com.ars.hungrybirdadventure.screens.GameScreen;
import com.ars.hungrybirdadventure.sprites.Bird;
import com.ars.hungrybirdadventure.sprites.GameBackground;
import com.ars.hungrybirdadventure.sprites.Ground;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;

public class IdleState extends GameStateHandler {

    private Label tapToStartLabel;
    public IdleState(final GameWorld world, Skin skin) {
        super(world);
        bird.reposition();
        tapToStartLabel = new Label("Tap to start",skin);
        tapToStartLabel.setPosition(cam.position.x, cam.position.y - bird.getTexture().getRegionHeight() * 2, Align.center);
    }
    @Override
    public void render(float delta) {
        drawBackground();
        drawBird();
        drawGround();
        tapToStartLabel.draw(game.batch, 1f);
    }

    @Override
    public void update(float delta) {
        background.update(delta);
        ground.update(delta);
        bird.animate(delta);
    }

    @Override
    public void handleInput() {
        if (Gdx.input.justTouched()) {
            bird.jump();
            gameScreen.setGameState(GameState.PLAYING);
        }
    }

    private void drawBird() {
        game.batch.draw(bird.getTexture(), bird.getPosition().x, bird.getPosition().y);
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
