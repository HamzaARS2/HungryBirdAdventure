package com.ars.hungrybirdadventure.sprites;

import com.ars.hungrybirdadventure.Animation;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Bird {

    private final static int GRAVITY = -60;
    private static final int MAX_DOWN_VELOCITY = -900;
    private Vector3 position;
    private Vector3 velocity;
    private final Texture bird;
    private final Rectangle birdBounds;
    private Animation birdAnim;

    float initX, initY;


    public Bird(float x, float y) {
        bird = new Texture(Gdx.files.internal("bird_frames.png"));
        birdAnim = new Animation(bird, 4, 0.3f);
        this.initX = x - (birdAnim.getFrame().getRegionWidth()) / 2f;
        this.initY = y;
        position = new Vector3(initX, y, 0);
        velocity = new Vector3(0, 0, 0);
        birdBounds = new Rectangle(position.x, position.y, bird.getWidth() / 4f, bird.getHeight());

    }

    public void update(float dt) {
        birdAnim.update(dt);
        velocity.add(0, GRAVITY, 0);
        if (velocity.y <= MAX_DOWN_VELOCITY)
            velocity.y = MAX_DOWN_VELOCITY;
        velocity.scl(dt);
        position.add(0, velocity.y, 0);
        birdBounds.y = position.y;
        velocity.scl(1 / dt);
        if (position.y < 0) position.y = 0;
    }

    public void animate(float delta) {
        birdAnim.update(delta);
    }



    public boolean checkCollusion(Rectangle bounds) {
        return birdBounds.overlaps(bounds);
    }

    public void jump() {
        velocity.y = 900;
    }

    public Vector3 getPosition() {
        return position;
    }

    public TextureRegion getTexture() {
        return birdAnim.getFrame();
    }

    public void dispose() {
        bird.dispose();
    }

    public void reposition() {
        position.set(initX, initY, 0);
    }
}
