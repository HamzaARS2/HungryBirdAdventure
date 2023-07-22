package com.ars.hungrybirdadventure;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.hide;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.show;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;

public class DialogMenu  {
    private Table dialogTable;
    protected Stage stage;
    protected Skin skin;
    private Actor overlay;

    public DialogMenu(Stage stage, Skin skin) {
        this.stage = stage;
        this.skin = skin;

        dialogTable = new Table(skin);
        dialogTable.setBackground("window");
        dialogTable.setDebug(false);
        // Set the size and position of the dialog
        dialogTable.setSize(480, 300);
        dialogTable.setPosition(
                (stage.getWidth() - dialogTable.getWidth()) / 2,
                (stage.getHeight() - dialogTable.getHeight()) / 2
        );

        overlay = new Image();
        overlay.setSize(stage.getWidth(), stage.getHeight());
        overlay.addListener(new ClickListener());

        stage.addActor(overlay);
        stage.addActor(dialogTable);
    }

    public void show() {
        dialogTable.setVisible(true);
        overlay.setVisible(true);
    }

    public void hide() {
        dialogTable.setVisible(false);
        overlay.setVisible(false);
    }

    public <T extends Actor> Cell<T> addContent(T actor) {
        return dialogTable.add(actor);
    }

}
