package uet.oop.bomberman.entities.statics;

import uet.oop.bomberman.entities.entity.Entity;
import uet.oop.bomberman.game.GamePlay;

public abstract class Static extends Entity {
    protected GamePlay gamePlay;

    public Static(double x, double y, GamePlay gamePlay) {
        super(x, y, gamePlay);
    }
}
