package uet.oop.bomberman.entities.enemy;

import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.entities.enemy.AI.AIlow;
import uet.oop.bomberman.entities.statics.item.ItemSpeed;
import uet.oop.bomberman.entities.entity.Entity;
import uet.oop.bomberman.entities.entity.EntityLayered;
import uet.oop.bomberman.game.GamePlay;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.sound.Sound;

import java.util.List;

public class Doll extends Enemy {
    //di chuyển xuyên Brick, Bom, di chuyển ngẫu nhiên
    // chỉ ăn được item speed và speed *2 sau khi ăn

    public Doll(double x, double y, GamePlay game) {
        super(x, y, game, 400);
        ai = new AIlow();
        img = Sprite.doll_left1.getFxImage();
        speed = (double) Sprite.SCALED_SIZE / 128;
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

    // ăn item Speed.
    @Override
    public void collisionHandling() {
        super.collisionHandling();
        List<Entity> entityList = gamePlay.entityLocation(x, y);
        for (Entity a : entityList) {
            if (a instanceof EntityLayered) {
                if (((EntityLayered) a).getTopEntity() instanceof ItemSpeed) {
                    if (direction == 0) {
                        y += speed;
                    } else if (direction == 1) {
                        x -= speed;
                    } else if (direction == 2) {
                        y -= speed;
                    } else {
                        x += speed;
                    }
                    speed *= 2;
                    a.removeEntity();
                    Sound.playGame("item");
                }
            }
        }
    }

    protected void chooseSprite() {
        switch (direction) {
            case 0:
            case 1:
                img = Sprite.movingSprite(Sprite.doll_right1, Sprite.doll_right2, Sprite.doll_right3, getAnimation(), 60).getFxImage();
                break;
            case 2:
            case 3:
                img = Sprite.movingSprite(Sprite.doll_left1, Sprite.doll_left2, Sprite.doll_left3, getAnimation(), 60).getFxImage();
                break;
        }
    }


    public void render(GraphicsContext gc) {
        chooseSprite();
        if (isCheckKilled()) {
            if (timeDie > -30) {
                if (getAnimation() % 60 < 20) {
                    img = Sprite.doll_dead.getFxImage();
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
