package uet.oop.bomberman.entities.statics.item;

import uet.oop.bomberman.game.GamePlay;
import uet.oop.bomberman.graphics.Sprite;

public class ItemSpeed extends Item {
    public ItemSpeed(double x, double y, GamePlay gamePlay) {
        super(x, y, gamePlay);
        img = Sprite.powerup_speed.getFxImage();
    }

    @Override
    public void update() {
    }
}
