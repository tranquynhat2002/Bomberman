package uet.oop.bomberman.entities.statics.tile;

import uet.oop.bomberman.entities.statics.Static;
import uet.oop.bomberman.game.GamePlay;
import uet.oop.bomberman.graphics.Sprite;

public class Portal extends Static {
    public Portal (double x, double y, GamePlay gamePlay) {
        super(x, y, gamePlay);
        img = Sprite.portal.getFxImage();
    }

    @Override
    public void collisionHandling() {

    }

    @Override
    public void update() {

    }
}
