package uet.oop.bomberman.entities.entity;

import uet.oop.bomberman.game.GamePlay;

public abstract class EntityAnimation extends Entity {
    private int animation = 0;

    private final int maxAnimation = 7500;

    public EntityAnimation (double x, double y, GamePlay gamePlay) {
        super(x,y,gamePlay);
    }

    public void animate() {
        if (animation < maxAnimation) {
            animation++;
        } else {
            animation = 0;
        }
    }

    public int getAnimation() {
        return animation;
    }

    @Override
    public void collisionHandling() {

    }

    @Override
    public void update() {

    }
}
