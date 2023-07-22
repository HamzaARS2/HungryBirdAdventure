package com.ars.hungrybirdadventure.screens;

import com.ars.hungrybirdadventure.DialogMenu;
import com.ars.hungrybirdadventure.HungryBirdGame;
import com.ars.hungrybirdadventure.SettingsWindow;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class MainMenuScreen implements Screen, EventListener {
    private static final String TAG = "MainMenuScreen";
    private static final int MENU_SCREEN_WIDTH = 600;
    private static final int MENU_SCREEN_HEIGHT = 800;
    final HungryBirdGame game;

    private final Viewport viewport;
    private OrthographicCamera cam;

    private final Texture background;
    private final Texture ground;
    private final Texture logo;
    private  Stage stage;
    private  Table table;
    private  Skin skin;
    private Button playBtn, levelsBtn, leaderboardBtn, settingsBtn, exitBtn;


    public MainMenuScreen(final HungryBirdGame game) {
        this.game = game;
        cam = new OrthographicCamera();
        viewport = new ExtendViewport(MENU_SCREEN_WIDTH  , MENU_SCREEN_HEIGHT , cam);
        viewport.apply();
        cam.setToOrtho(false, cam.viewportWidth  , cam.viewportHeight);
        background = new Texture(Gdx.files.internal("game_background.png"));
        ground = new Texture(Gdx.files.internal("ground.png"));
        logo = new Texture(Gdx.files.internal("title_logo.png"));

        initUi();
    }
    private void initUi() {
        stage = new Stage(viewport);
        Gdx.input.setInputProcessor(stage);
        skin = new Skin(Gdx.files.internal("skins/yellow_skin/flat_yellow_skin.json"));
        table = new Table(skin);
        stage.addActor(table);
        table.setFillParent(true);
        table.align(Align.center|Align.bottom);
        table.setPosition(table.getX(), ground.getHeight());

        table.add(new Image(logo)).expandY().bottom().row();
        table.add(new Label("Best Score",skin,"title")).expandY().bottom().row();
        table.add(new Label("148",skin,"title")).padBottom(50).padTop(20).row();

        playBtn = addButton("Play", 20);
        levelsBtn = addButton("Levels", 20);
        leaderboardBtn = addButton("Leaderboard",20);
        settingsBtn = addButton("Settings", 20);
        exitBtn = addButton("Exit", 0);

        setButtonsClickListener();
    }

    private Button addButton(String title, int padBot) {
        TextButton btn = new TextButton(title, skin);
        table.add(btn).padBottom(padBot).fillX().row();
        return btn;
    }

    private void setButtonsClickListener() {
        playBtn.addListener(this);
        levelsBtn.addListener(this);
        leaderboardBtn.addListener(this);
        settingsBtn.addListener(this);
        exitBtn.addListener(this);

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(1, 1, 0, 1);
        game.batch.setProjectionMatrix(cam.combined);
        game.batch.begin();
        game.batch.draw(background, 0,0, cam.viewportWidth, cam.viewportHeight);
        game.batch.draw(ground, 0, 0, cam.viewportWidth, ground.getHeight());
        game.batch.end();
        stage.act();
        stage.draw();
        cam.update();

    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        cam.setToOrtho(false, cam.viewportWidth , cam.viewportHeight);
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
        removeListeners();
    }

    private void removeListeners() {
        playBtn.removeListener(this);
        levelsBtn.removeListener(this);
        leaderboardBtn.removeListener(this);
        settingsBtn.removeListener(this);
        exitBtn.removeListener(this);
    }

    @Override
    public boolean handle(Event event) {
            if (event.getTarget() == playBtn) {
                // Play button clicked
                game.setScreen(new GameScreen(game));
                dispose();
            } else if (event.getTarget() == levelsBtn) {
                // Levels button clicked
                Gdx.app.log(TAG, "Levels Clicked");
            } else if (event.getTarget() == leaderboardBtn) {
                // Leaderboard button clicked
                Gdx.app.log(TAG, "Leaderboard Clicked");
            } else if (event.getTarget() == settingsBtn) {
                // Settings button clicked
                showDialog();
                Gdx.app.log(TAG, "Settings Clicked");
            } else if (event.getTarget() == exitBtn) {
                // Exit button clicked
                Gdx.app.log(TAG, "Exit Clicked");
                Gdx.app.exit();
            }


        return false;
    }

    private void showDialog() {
        SettingsWindow sw = new SettingsWindow(stage, skin);
        sw.show();
    }
}
