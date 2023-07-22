package com.ars.hungrybirdadventure.sprites;

import static com.ars.hungrybirdadventure.Constants.HEIGHT;
import static com.ars.hungrybirdadventure.Constants.WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import java.util.Random;

public class Tube {
    public static int TUBES_SPACING = WIDTH / 2;
    public static final int TUBES_COUNT = 4;
    private final int gap = 300;
    private int groundHeight;
    private final Texture topTube, botTube;
    private final Vector3 topTubePos, botTubePos;
    private final int velocity;
    private final Random random;
    private final Rectangle topTubeBounds, botTubeBounds;

    public Tube(float groundHeight, int tubeNum) {
        this.groundHeight = (int) groundHeight;
        random = new Random();
        velocity = 180;
        topTube = new Texture(Gdx.files.internal("blue_pipe.png"));
        botTube = topTube;
        topTubePos = new Vector3(WIDTH + (tubeNum * TUBES_SPACING) ,getRandomTubeY(), 0);
        botTubePos = new Vector3(WIDTH + (tubeNum * TUBES_SPACING) ,topTubePos.y - gap , 0);
        topTubeBounds = new Rectangle(topTubePos.x, topTubePos.y, topTube.getWidth(), topTube.getHeight());
        botTubeBounds = new Rectangle(botTubePos.x, botTubePos.y, botTube.getWidth(), botTube.getHeight());
    }

    public Rectangle getTopTubeBounds() {
        return topTubeBounds;
    }

    public Rectangle getBotTubeBounds() {
        return botTubeBounds;
    }

    public boolean update(float dt) {
        topTubePos.x -= velocity * dt;
        botTubePos.x -= velocity * dt;
        topTubeBounds.x = topTubePos.x;
        botTubeBounds.x = botTubePos.x;
        if (topTubePos.x + topTube.getWidth() < 0) {
            reposition();
            return true;
        }
        return false;
    }

    public void reposition() {
        topTubePos.set((TUBES_COUNT * TUBES_SPACING) - topTube.getWidth() , getRandomTubeY(), 0);
        botTubePos.set((TUBES_COUNT * TUBES_SPACING) - topTube.getWidth(), topTubePos.y - gap, 0);
        topTubeBounds.x = topTubePos.x;
        botTubeBounds.x = botTubePos.x;
        topTubeBounds.y = topTubePos.y;
        botTubeBounds.y = botTubePos.y;
    }

    private int getRandomTubeY() {
        return HEIGHT - topTube.getHeight() - random.nextInt(HEIGHT - topTube.getHeight() - gap  - this.groundHeight);
    }


    public int getGap() {
        return gap;
    }

    public Vector3 getBotTubePos() {
        return botTubePos;
    }

    public Texture getTexture() {
        return topTube;
    }

    public Vector3 getTopTubePos() {
        return topTubePos;
    }

    public void dispose() {
        topTube.dispose();
        botTube.dispose();
    }
}
