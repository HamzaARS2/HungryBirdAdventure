package com.ars.hungrybirdadventure.screens;

import static com.ars.hungrybirdadventure.Constants.HEIGHT;
import static com.ars.hungrybirdadventure.Constants.WIDTH;

import com.ars.hungrybirdadventure.HungryBirdGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.Viewport;

public class MainMenuScreen implements Screen  {


    final HungryBirdGame game;

    private final Stage stage;
    private final Table table;
    private Texture background;
    private Texture titleLogo;

    private TextButton playBtn;
    public MainMenuScreen(final HungryBirdGame game) {
        this.game = game;
        game.font.setColor(Color.RED);
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        table = new Table();
        table.setFillParent(true);
        stage.addActor(table);
        table.setDebug(true);

        background = new Texture(Gdx.files.internal("menu_background.jpeg"));
        titleLogo = new Texture(Gdx.files.internal("title_logo.png"));
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(1, 1, 0, 1);
        update(delta);
        game.batch.begin();
        game.batch.draw(background, 0, 0);
        game.font.draw(game.batch, "Tap to start", WIDTH / 2F - 40, HEIGHT / 2F);
        game.batch.end();

    }

    private void update(float dt) {
        handleInput();
    }

    private void handleInput() {
        if (Gdx.input.isTouched()) {
            game.setScreen(new GameScreen(game));
            dispose();
        }
    }

    @Override
    public void resize(int width, int height) {

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

    }
}
