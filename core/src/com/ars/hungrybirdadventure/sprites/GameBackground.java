package com.ars.hungrybirdadventure.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;

public class GameBackground  {

    private final Texture background;
    private final int velocity;
    private final Vector3 position;
    public GameBackground(float x) {
        background = new Texture(Gdx.files.internal("game_background.png"));
        velocity = 80;
        position = new Vector3(x,0,0);
    }

    public void update(float dt) {
        position.x -= velocity * dt;

        if (position.x < - background.getWidth())
            position.x = 0;
    }

    public Texture getTexture() {
        return background;
    }

    public Vector3 getPosition() {
        return position;
    }

    public void dispose() {
        background.dispose();
    }
}
