package uet.oop.bomberman.entities.statics.item;

import uet.oop.bomberman.game.GamePlay;
import uet.oop.bomberman.graphics.Sprite;

public class ItemBomb extends Item {
    public ItemBomb(double x, double y, GamePlay gamePlay) {
        super(x, y, gamePlay);
        img = Sprite.powerup_bombs.getFxImage();
    }

    @Override
    public void update() {

    }
}



