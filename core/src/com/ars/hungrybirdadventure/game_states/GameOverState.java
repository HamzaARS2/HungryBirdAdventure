package com.ars.hungrybirdadventure.game_states;

import com.ars.hungrybirdadventure.GameWorld;
import com.ars.hungrybirdadventure.screens.MainMenuScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameOverState extends GameStateHandler implements EventListener {

    private final Stage stage;
    private final Table root;
    private final Table windowTable;
    private final Skin skin;
    private Image gameOverImage;

    private TextButton restartBtn, mainMenuBtn;
    public GameOverState(GameWorld world, Skin skin, Viewport viewport) {
        super(world);
        stage = new Stage(viewport);
        root = new Table();
        windowTable = new Table(skin);
        windowTable.setBackground("window");
        stage.addActor(root);
        stage.addActor(windowTable);
        root.setFillParent(true);
        this.skin = skin;
        Gdx.input.setInputProcessor(stage);
        root.align(Align.center);

        gameOverImage = new Image(skin,"game_over");
        restartBtn = new TextButton("Restart", skin,"light_orange");
        mainMenuBtn = new TextButton("Main Menu", skin,"dark_orange");
        addTableContent();
        setListeners();

    }

    private void setListeners() {
        restartBtn.addListener(this);
        mainMenuBtn.addListener(this);
    }

    private void addTableContent() {
        root.defaults().width(480);
        root.add(gameOverImage).colspan(2).row();
        root.add(windowTable).colspan(2).padTop(10).height(300).row();
        root.defaults().width(220).height(80);
        root.add(restartBtn);
        root.add(mainMenuBtn);

        windowTable.defaults().padLeft(40).padRight(40);
        windowTable.add(new Label("Time",skin,"title")).expandX().left();
        windowTable.add(new Label("Score",skin, "title")).expandX().right().row();

        windowTable.add(new Label("00:59",skin,"medium_white")).expandX().padTop(10).left();
        windowTable.add(new Label("156",skin,"medium_white")).expandX().padTop(10).center().row();

        windowTable.add(new Label("Best",skin,"title")).colspan(2).padTop(80).center().row();
        windowTable.add(new Label("237",skin,"medium_white")).colspan(2).padTop(10).center();
    }

    @Override
    public void render(float delta) {
        drawBackground();
        drawBird();
        drawGround();
        stage.act();
        stage.draw();
    }

    @Override
    public void update(float delta) {
    }

    private void drawGround() {
        game.batch.draw(ground.getTexture(), ground.getPosition().x, 0);
        if (ground.getPosition().x < -(ground.getTexture().getWidth() - cam.viewportWidth))
            game.batch.draw(ground.getTexture(), ground.getPosition().x + ground.getTexture().getWidth(), 0);

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

    @Override
    public void handleInput() {
//        if (Gdx.input.justTouched())
//            gameScreen.setGameState(GameState.IDLE);
    }


    @Override
    public boolean handle(Event event) {
        if (event.getTarget() == restartBtn) {
            gameScreen.setGameState(GameState.IDLE);
            restartBtn.removeListener(this);
            mainMenuBtn.removeListener(this);
        } else if (event.getTarget() == mainMenuBtn) {
            game.setScreen(new MainMenuScreen(game));
            restartBtn.removeListener(this);
            mainMenuBtn.removeListener(this);
        }

        return false;
    }
}
