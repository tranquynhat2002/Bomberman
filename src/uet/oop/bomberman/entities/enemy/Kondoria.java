package uet.oop.bomberman.entities.enemy;

import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.entities.enemy.AI.AImedium;
import uet.oop.bomberman.entities.enemy.Enemy;
import uet.oop.bomberman.game.GameBomberman;
import uet.oop.bomberman.game.GamePlay;
import uet.oop.bomberman.graphics.Sprite;

public class Kondoria extends Enemy {
    //có thể di chuyển xuyên tường
    public Kondoria(double x, double y, GamePlay game) {
        super(x, y, game, 1000);
        speed = (double) Sprite.SCALED_SIZE / 128;
        ai = new AImedium(GamePlay.bomberman.get(0), this);
        img = Sprite.kondoria_right1.getFxImage();
        direction = ai.calculateDirection();
    }

    public void moveEnemy() {
        int d = direction;
        if (!checkMoved) {
            d = ai.calculateDirection();
        }
        if (d == 0) {
            y -= speed;
        } else if (d == 1) {
            x -= speed;
        } else if (d == 2) {
            y += speed;
        } else {
            x -= speed;
        }
    }


    protected void chooseSprite() {
        switch (direction) {
            case 0:
            case 1:
                img = Sprite.movingSprite(Sprite.kondoria_right1, Sprite.kondoria_right2, Sprite.kondoria_right3, getAnimation(), 60).getFxImage();
                break;
            case 2:
            case 3:
                img = Sprite.movingSprite(Sprite.kondoria_left1, Sprite.kondoria_left2, Sprite.kondoria_left3, getAnimation(), 60).getFxImage();
                break;
        }
    }

    @Override
    public void render(GraphicsContext gc) {
        chooseSprite();
        if (isCheckKilled()) {
            if (timeDie > -30) {
                if (getAnimation() % 60 < 20) {
                    img = Sprite.kondoria_dead.getFxImage();
                } else if (getAnimation() % 60 < 40) {
                    img = Sprite.mob_dead2.getFxImage();
                } else {
                    img = Sprite.mob_dead3.getFxImage();
                }
                gc.drawImage(img, x, y);
            } else {
                removeEnemy();
            }
            animate();
        } else {
            super.render(gc);
        }

    }
}
