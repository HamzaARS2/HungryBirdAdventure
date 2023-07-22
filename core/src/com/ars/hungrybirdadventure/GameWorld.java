package com.ars.hungrybirdadventure;

import com.ars.hungrybirdadventure.screens.GameScreen;
import com.ars.hungrybirdadventure.sprites.Apple;
import com.ars.hungrybirdadventure.sprites.Bird;
import com.ars.hungrybirdadventure.sprites.GameBackground;
import com.ars.hungrybirdadventure.sprites.Ground;
import com.ars.hungrybirdadventure.sprites.Tube;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.Array;

public class GameWorld {

    private final GameScreen gameScreen;
    private final OrthographicCamera cam;
    private final GameBackground background;
    private final Ground ground;
    private final Bird bird;
    private Array<Tube> tubes;
    private Array<Apple> apples;

    public GameWorld(GameScreen gameScreen, OrthographicCamera camera) {
        this.gameScreen = gameScreen;
        this.cam = camera;
        this.background = new GameBackground(0);
        this.ground = new Ground(0);
        this.bird = new Bird(camera.position.x, camera.position.y);



    }

    public void loadWorldObjects() {
        tubes = new Array<>();
        apples = new Array<>();
        for (int i = 0; i < 4; i++) {
            tubes.add(new Tube(ground.getTexture().getHeight(), i));
            apples.add(new Apple(tubes.get(i)));
        }
    }
    public GameScreen getGameScreen() {
        return gameScreen;
    }

    public OrthographicCamera getCam() {
        return cam;
    }

    public GameBackground getBackground() {
        return background;
    }

    public Ground getGround() {
        return ground;
    }

    public Bird getBird() {
        return bird;
    }

    public Array<Tube> getTubes() {
        return tubes;
    }

    public Array<Apple> getApples() {
        return apples;
    }

    public void dispose() {
        background.dispose();
        ground.dispose();
        bird.dispose();
        for (int i = 0; i < tubes.size; i++)
            tubes.get(i).dispose();
    }
}
