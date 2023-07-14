package com.ars.hungrybirdadventure.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;

public class Ground {
    private final Texture ground;
    private final int velocity;
    private final Vector3 position;

    public Ground(float x) {
        ground = new Texture(Gdx.files.internal("ground.png"));
        velocity = 150;
        position = new Vector3(x,0,0);
    }

    public void update(float dt) {
        position.x -= velocity * dt;
        if (position.x < -ground.getWidth())
            position.x = 0;
    }

    public Texture getTexture() {
        return ground;
    }

    public Vector3 getPosition() {
        return position;
    }

    public void dispose() {
        ground.dispose();
    }
}
