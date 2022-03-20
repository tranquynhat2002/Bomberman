package uet.oop.bomberman.entities.dynamic;

import uet.oop.bomberman.entities.entity.Entity;
import uet.oop.bomberman.entities.entity.EntityAnimation;
import uet.oop.bomberman.game.GamePlay;

public abstract class Dynamic extends EntityAnimation {
    protected double timeExplode = 180;

    public Dynamic(double x, double y, GamePlay gamePlay) {
        super(x, y, gamePlay);
    }

    @Override
    public void update() {
        if (timeExplode > 0) {
            timeExplode--;
        } else {
            timeDie--;
        }
    }
}
