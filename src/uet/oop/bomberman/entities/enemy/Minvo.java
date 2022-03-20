package uet.oop.bomberman.entities.enemy;

import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.entities.enemy.AI.AIlow;
import uet.oop.bomberman.entities.statics.item.ItemBomb;
import uet.oop.bomberman.entities.statics.item.ItemFlame;
import uet.oop.bomberman.entities.statics.item.ItemSpeed;
import uet.oop.bomberman.entities.statics.tile.Portal;
import uet.oop.bomberman.entities.entity.Entity;
import uet.oop.bomberman.entities.entity.EntityLayered;
import uet.oop.bomberman.game.GamePlay;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.sound.Sound;

import java.util.List;

public class Minvo extends Enemy {
    //có thể ăn item, không thể di chuyển qua brick và wall
    public Minvo(double x, double y, GamePlay game) {
        super(x, y, game, 800);
        ai = new AIlow();
        img = Sprite.minvo_right2.getFxImage();
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

    // ăn item.
    public void collisionHandling() {
        List<Entity> entityList = gamePlay.entityLocation(x,y);
        for (Entity e : entityList) {
            if (e instanceof EntityLayered) {
                if (((EntityLayered) e).getTopEntity() instanceof ItemSpeed) {
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
                    e.removeEntity();
                    Sound.playGame("item");
                } else if (((EntityLayered) e).getTopEntity() instanceof ItemBomb) {
                    e.removeEntity();
                    Sound.playGame("item");
                } else if (((EntityLayered) e).getTopEntity() instanceof ItemFlame) {
                    e.removeEntity();
                    Sound.playGame("item");
                } else if (((EntityLayered) e).getTopEntity() instanceof Portal) {
                    if (GamePlay.enemies.isEmpty()) {
                        e.removeEntity();
                        Sound.playGame("item");
                    }
                }
            }
        }
    }

    protected void chooseSprite() {
        switch (direction) {
            case 0:
            case 1:
                img = Sprite.movingSprite(Sprite.minvo_right1, Sprite.minvo_right2, Sprite.minvo_right3, getAnimation(), 60).getFxImage();
                break;
            case 2:
            case 3:
                img = Sprite.movingSprite(Sprite.minvo_left1, Sprite.minvo_left2, Sprite.minvo_left3, getAnimation(), 60).getFxImage();
                break;
        }
    }

    @Override
    public void render(GraphicsContext gc) {
        chooseSprite();
        if (isCheckKilled()) {
            if (timeDie > -30) {
                if (getAnimation() % 60 < 20) {
                    img = Sprite.minvo_dead.getFxImage();
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
