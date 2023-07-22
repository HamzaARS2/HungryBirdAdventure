package com.ars.hungrybirdadventure;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;

public class SettingsWindow extends DialogMenu  {
    private Label settingsLb, soundLb, gpLb;
    private TextButton gpBtn;
    private CheckBox soundCB;
    private ImageButton closeBtn;

    public SettingsWindow(Stage stage, Skin skin) {
        super(stage, skin);
        initialize();
    }

    private void initialize() {
        closeBtn = new ImageButton(skin, "close");
        settingsLb = new Label("Settings", skin,"title");
        soundLb = new Label("Sound", skin, "white");
        soundCB = new CheckBox("", skin );
        gpLb = new Label("Google Play\nSign-in", skin, "white");
        gpBtn = new TextButton("Connected", skin,"settings");
        setupListeners();
        addContent(closeBtn)
                .colspan(2)
                .expandX()
                .right().padRight(40).height(20)
                .row();
        addContent(settingsLb)
                .expandX().align(Align.center).center().top().colspan(2).row();
        addContent(soundLb)
                .expandX().left().padLeft(40).padTop(20);
        addContent(soundCB)
                .expandX().center().padTop(20).row();
        addContent(gpLb)
                .left().padLeft(40).padTop(40);
        addContent(gpBtn)
                .expandX().right().padRight(40).padTop(40).width(145).height(60);
    }
    private void setupListeners() {
        closeBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                hide();
            }
        });

        soundCB.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                // Handle sound checkbox state change
                boolean soundEnabled = soundCB.isChecked();
                // Update sound settings accordingly
            }
        });

        gpBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                boolean connected = gpBtn.isChecked();
                if (!connected) gpBtn.setText("Connected");
                else  gpBtn.setText("Disconnected");
            }
        });
    }
}



