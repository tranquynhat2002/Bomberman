package uet.oop.bomberman.entities.enemy;

import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.entities.enemy.AI.AIlow;
import uet.oop.bomberman.game.GamePlay;
import uet.oop.bomberman.graphics.Sprite;

public class Balloom extends Enemy {
    //di chuyển ngẫu nhiên với tốc độ cố định
    public Balloom(double x, double y, GamePlay gamePlay) {
        super(x, y, gamePlay, 100);
        img = Sprite.balloom_left1.getFxImage();
        ai = new AIlow();
        speed = (double) Sprite.SCALED_SIZE / 64;
    }

    public void moveEnemy(){
        int d = direction;
        if (!checkMoved) {
            d = ai.calculateDirection();
        }
        if (d == 0) {
            upMove();
        } else if (d == 1) {
            rightMove();
        } else if (d == 2) {
            downMove();
        } else {
            leftMove();
        }
    }

    protected void chooseSprite() {
        switch (direction) {
            case 0:
            case 1:
                img = Sprite.movingSprite(Sprite.balloom_right1, Sprite.balloom_right2,
                        Sprite.balloom_right3, getAnimation(), 60).getFxImage();
                break;

            case 2:
            case 3:
                img = Sprite.movingSprite(Sprite.balloom_left1, Sprite.balloom_left2,
                        Sprite.balloom_left3, getAnimation(), 60).getFxImage();
                break;
        }
    }

    @Override
    public void render(GraphicsContext graphicsContext) {
        chooseSprite();
        if (isCheckKilled()) {
            if (timeDie > -60) {
                img = Sprite.movingSprite(Sprite.mob_dead1, Sprite.mob_dead2,
                        Sprite.mob_dead3, getAnimation(), 60).getFxImage();
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
