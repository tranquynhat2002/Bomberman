package uet.oop.bomberman.entities.statics.item;

import uet.oop.bomberman.entities.statics.Static;
import uet.oop.bomberman.game.GamePlay;

public abstract class Item extends Static {
    public Item(double x, double y, GamePlay gamePlay) {
        super(x, y, gamePlay);
    }

    @Override
    public void collisionHandling() {

    }
}
