package uet.oop.bomberman.entities.dynamic;

import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.entities.enemy.Enemy;
import uet.oop.bomberman.entities.statics.tile.Wall;
import uet.oop.bomberman.entities.entity.Entity;
import uet.oop.bomberman.entities.entity.EntityLayered;
import uet.oop.bomberman.game.GamePlay;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.sound.Sound;

import java.util.List;

public class Flame extends Dynamic {
    protected int direction;
    public boolean checkRender = true;

    public Flame(double x, double y, int direction, GamePlay gamePlay) {
        super(x, y, gamePlay);
        this.direction = direction;
    }


    //xử lý va chạm khi bomber va chạm với thực thể.
    public void collisionHandling () {
        List<Entity> entityList = gamePlay.entityLocation(x,y);
        double bomberX = GamePlay.bomberman.get(0).getX();
        double bomberY = GamePlay.bomberman.get(0).getY();

        if (timeExplode <= 0) {
            for (Entity e : entityList) {
                if (e instanceof EntityLayered) {
                    if (((EntityLayered) e).getTopEntity() instanceof Brick) {
                        ((EntityLayered) e).getTopEntity().killEntity();
                        Sound.playGame("bom_no");
                        checkRender = false;
                    }
                }
                if (e instanceof Enemy) {
                    e.killEntity();
                    Sound.playGame("keu");
                }
            }
        }

        if (timeExplode <= 0) {
            if (x + 32 < bomberX) {
                return;
            } else if (x > bomberX + 32) {
                return;
            } else if (y > bomberY + 32) {
                return;
            } else if (y + 32 < bomberY) {
                return;
            } else {
                gamePlay.bomberman.get(0).killEntity();
            }
        }
    }

    //kiểm tra xem có thể nổ được ở vị trí hiện tại ko
    public boolean checkExplode() {
        List<Entity> entityList = gamePlay.entityLocation(x, y);
        for (Entity e : entityList) {
            if (e instanceof Wall) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void render(GraphicsContext graphicsContext) {
        if (checkExplode()) {
            if (timeDie > 0 && timeExplode <= 0) {
                if (getAnimation() % 30 < 10) {
                    Sound.playGame("bomb_bang");
                    switch (direction) {
                        case 0:
                            img = Sprite.explosion_vertical_top_last.getFxImage();
                            break;
                        case 2:
                            img = Sprite.explosion_vertical_down_last.getFxImage();
                            break;
                        case 3:
                            img = Sprite.explosion_horizontal_left_last.getFxImage();
                            break;
                        case 1:
                            img = Sprite.explosion_horizontal_right_last.getFxImage();
                            break;
                        case 4:
                            img = Sprite.explosion_vertical.getFxImage();
                            break;
                        case 5:
                            img = Sprite.explosion_horizontal.getFxImage();
                    }
                } else if (getAnimation() % 30 < 20) {
                    switch (direction) {
                        case 0:
                            img = Sprite.explosion_vertical_top_last1.getFxImage();
                            break;
                        case 2:
                            img = Sprite.explosion_vertical_down_last1.getFxImage();
                            break;
                        case 3:
                            img = Sprite.explosion_horizontal_left_last1.getFxImage();
                            break;
                        case 1:
                            img = Sprite.explosion_horizontal_right_last1.getFxImage();
                            break;
                        case 4:
                            img = Sprite.explosion_vertical1.getFxImage();
                            break;
                        case 5:
                            img = Sprite.explosion_horizontal1.getFxImage();
                    }
                } else {
                    switch (direction) {
                        case 0:
                            img = Sprite.explosion_vertical_top_last2.getFxImage();
                            break;
                        case 2:
                            img = Sprite.explosion_vertical_down_last2.getFxImage();
                            break;
                        case 3:
                            img = Sprite.explosion_horizontal_left_last2.getFxImage();
                            break;
                        case 1:
                            img = Sprite.explosion_horizontal_right_last2.getFxImage();
                            break;
                        case 4:
                            img = Sprite.explosion_vertical2.getFxImage();
                            break;
                        case 5:
                            img = Sprite.explosion_horizontal2.getFxImage();
                    }
                }
                if (checkRender) {
                    graphicsContext.drawImage(img, x, y);
                }
            } else {
                removeEntity();
            }
            animate();
        } else {
            removeEntity();
        }
    }
}
