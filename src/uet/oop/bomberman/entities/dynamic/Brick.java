package uet.oop.bomberman.entities.dynamic;

import uet.oop.bomberman.game.GamePlay;
import uet.oop.bomberman.graphics.Sprite;

public class Brick extends Dynamic {

    public Brick(double x, double y, GamePlay gamePlay) {
        super(x, y, gamePlay);
        img = Sprite.brick.getFxImage();
    }

    public void collisionHandling() {

    }

    //cập nhật trạng thái sau khi brick vỡ
    public void killedAfter() {
        if (timeDie > 0) {
            if (getAnimation() % 30 < 10) {
                img = Sprite.brick_exploded.getFxImage();
            } else if (getAnimation() % 30 < 20) {
                img = Sprite.brick_exploded1.getFxImage();
            } else {
                img = Sprite.brick_exploded2.getFxImage();
            }
        } else {
            removeEntity();
        }
        animate();
    }

    @Override
    public void update() {
        if (isCheckKilled()) {
            timeDie--;
            killedAfter();
        }
    }
}
