package uet.oop.bomberman.entities.enemy;

import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.entities.enemy.AI.AIoneal;
import uet.oop.bomberman.game.GamePlay;
import uet.oop.bomberman.graphics.Sprite;

public class Oneal extends Enemy {
    //có tốc độ di chuyển thay đổi và di chuyển thông minh (có thể đuổi theo Bomber)
    public Oneal(double x, double y, GamePlay gamePlay) {
        super(x, y, gamePlay,200);
        img = Sprite.oneal_left1.getFxImage();
        ai = new AIoneal(this, gamePlay);
        speed = (double) Sprite.SCALED_SIZE / 64;
    }

    public void moveEnemy() {
        if (this.x % 32 == 0 && this.y % 32 == 0) {
            direction = ai.calculateDirection();
        }
        if (direction == 0) {
            upMove();
        } else if (direction == 2) {
            downMove();
        } else if (direction == 3) {
            leftMove();
        } else {
            rightMove();
        }
    }

    protected void chooseSprite() {
        switch (direction) {
            case 0:
            case 1:
                img = Sprite.movingSprite(Sprite.oneal_right1, Sprite.oneal_right2,
                        Sprite.oneal_right3, getAnimation(), 60).getFxImage();
                break;
            case 2:
            case 3:
                img = Sprite.movingSprite(Sprite.oneal_left1, Sprite.oneal_left2,
                        Sprite.oneal_left3, getAnimation(), 60).getFxImage();
                break;
        }
    }
    public void render(GraphicsContext graphicsContext) {
        chooseSprite();
        if (isCheckKilled()) {
            if (timeDie > -30) {
                if (getAnimation() % 60 < 20) {
                    img = Sprite.oneal_dead.getFxImage();
                } else if (getAnimation() % 60 < 40) {
                    img = Sprite.mob_dead2.getFxImage();
                } else {
                    img = Sprite.mob_dead3.getFxImage();
                }
                graphicsContext.drawImage(img, x, y);
            } else {
                removeEnemy();
            }
            animate();
        } else {
            super.render(graphicsContext);
        }
    }
}
