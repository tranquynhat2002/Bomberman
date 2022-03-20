package uet.oop.bomberman.entities.statics.item;

import uet.oop.bomberman.game.GamePlay;
import uet.oop.bomberman.graphics.Sprite;

public class ItemFlame extends Item {
    public ItemFlame(double x, double y, GamePlay gamePlay) {
        super(x, y, gamePlay);
        img = Sprite.powerup_flames.getFxImage();
    }

    @Override
    public void update() {

    }
}
