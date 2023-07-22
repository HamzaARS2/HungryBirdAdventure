package com.ars.hungrybirdadventure.sprites;

import com.ars.hungrybirdadventure.Animation;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Apple {
    private Vector3 position;
    private int velocity;
    private final Texture apple;
    private final Rectangle appleBounds;
    private Animation appleAnim;
    private Tube tube;


    public Apple(Tube tube) {
        this.tube = tube;
        apple = new Texture(Gdx.files.internal("apple_sprite.png"));
        appleAnim = new Animation(apple, 5, 0.5f);
        position = new Vector3(tube.getTopTubePos().x  + tube.getTexture().getWidth() / 2f - appleAnim.getFrame().getRegionWidth() / 2f, tube.getTopTubePos().y
                + getTexture().getRegionHeight() - tube.getGap() / 2f , 0);
        velocity = 180;
        appleBounds = new Rectangle(position.x, position.y, apple.getWidth() / 5f, apple.getHeight());

    }

    public void update(float dt,boolean repositioned,  Vector3 tubePos) {
        animate(dt);
        position.x -= velocity * dt;
        appleBounds.x = position.x;
        if (repositioned) {
            reposition(tubePos.x, tubePos.y);
        }
    }

    public Vector3 getPosition() {
        return position;
    }

    public TextureRegion getTexture() {
        return appleAnim.getFrame();
    }

    public void reposition(float x, float y) {
        position.set(x  + tube.getTexture().getWidth() / 2f - appleAnim.getFrame().getRegionWidth() / 2f , tube.getTopTubePos().y
                + getTexture().getRegionHeight() - tube.getGap() / 2f , 0);
        appleBounds.x = position.x;
        appleBounds.y = position.y;
    }
    public void animate(float delta) {
        appleAnim.update(delta);
    }

    public Rectangle getAppleBounds() {
        return appleBounds;
    }
}
