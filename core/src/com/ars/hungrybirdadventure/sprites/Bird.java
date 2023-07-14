package com.ars.hungrybirdadventure.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Bird {

    private static final int GRAVITY = -40;
    private static final int MAX_DOWN_VELOCITY = -600;
    private Vector3 position;
    private Vector3 velocity;
    private final Texture bird;
    private final Rectangle birdBounds;



    public Bird(float x, float y) {
        bird = new Texture(Gdx.files.internal("bird/bird_frame_3.png"));
        position = new Vector3(x - bird.getWidth() , y, 0);
        velocity = new Vector3(0, 0, 0);
        birdBounds = new Rectangle(position.x, position.y, bird.getWidth(), bird.getHeight());

    }

    public void update(float dt) {
        velocity.add(0, GRAVITY, 0);
        if (velocity.y <= MAX_DOWN_VELOCITY)
            velocity.y = MAX_DOWN_VELOCITY;
        velocity.scl(dt);
        position.add(0, velocity.y, 0);
        birdBounds.y = position.y;
        velocity.scl(1 / dt);
        if (position.y < 0) position.y = 0;
    }

    public boolean checkCollusion(Rectangle bounds) {
        return birdBounds.overlaps(bounds);
    }

    public void jump() {
        velocity.y = 700;
    }

    public Vector3 getPosition() {
        return position;
    }

    public Texture getTexture() {
        return bird;
    }

    public void dispose() {
        bird.dispose();
    }
}
