package uet.oop.bomberman.entities.character;

import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.entities.dynamic.Bomb;
import uet.oop.bomberman.entities.dynamic.FlameRay;
import uet.oop.bomberman.entities.enemy.Enemy;
import uet.oop.bomberman.entities.statics.tile.Portal;
import uet.oop.bomberman.entities.entity.Entity;
import uet.oop.bomberman.entities.entity.EntityLayered;
import uet.oop.bomberman.message.Message;
import uet.oop.bomberman.game.GamePlay;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.entities.statics.item.ItemSpeed;
import uet.oop.bomberman.entities.statics.item.ItemBomb;
import uet.oop.bomberman.entities.statics.item.ItemFlame;


import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import uet.oop.bomberman.sound.Sound;

import java.util.List;

public class Bomber extends Character {
    public int maxTime = 8;
    public int numberOfBombs = 1;

    private int leftKey = 0;
    private int rightKey = 0;
    private int upKey = 0;
    private int downKey = 0;


    public Bomber(double x, double y, GamePlay gamePlay) {
        super(x, y, gamePlay);
        img = Sprite.player_down.getFxImage();
        this.gamePlay = gamePlay;
        this.speed = Sprite.SCALED_SIZE / 8;
    }

    @Override
    public void collisionHandling() {
        List<Entity> entityList = gamePlay.entityLocation(x, y);
        for (Entity e : entityList) {
            if (e instanceof EntityLayered) {
                if (((EntityLayered) e).getTopEntity() instanceof ItemSpeed) {
                    Message mes = new Message("+ Speed power", x, y, 1);
                    GamePlay.messages.add(mes);
                    speed = speed * 2;
                    e.removeEntity();
                    Sound.playGame("CRYST_UP");
                } else if (((EntityLayered) e).getTopEntity() instanceof ItemBomb) {
                    Message mes = new Message("+ Bomb power", x, y, 1);
                    GamePlay.messages.add(mes);
                    numberOfBombs = numberOfBombs + 1;
                    e.removeEntity();
                    Sound.playGame("CRYST_UP");
                } else if (((EntityLayered) e).getTopEntity() instanceof ItemFlame) {
                    Message mes = new Message("+ Item power", x, y, 1);
                    GamePlay.messages.add(mes);
                    FlameRay.flameLength = FlameRay.flameLength + 1;
                    e.removeEntity();
                    Sound.playGame("CRYST_UP");
                } else if (((EntityLayered) e).getTopEntity() instanceof Portal) {
                    if (GamePlay.enemies.isEmpty()) {
                        Message mes = new Message("Pass level", x, y, 2);
                        GamePlay.messages.add(mes);
                        e.removeEntity();
                        Sound.playGame("CRYST_UP");
                        GamePlay.checkChangeLevel = true;
                    }
                }
            }
            if (e instanceof Enemy) {
                gamePlay.bomberman.get(0).killEntity();
            }
        }
    }

    public void moveLeft() {
        img = Sprite.movingSprite(Sprite.player_left, Sprite.player_left_1, Sprite.player_left_2, leftKey).getFxImage();
        if (leftKey == maxTime) {
            leftKey = 0;
        } else {
            leftKey++;
        }
        if (checkMove(3)) {
            x = x - speed;
        }
    }

    public void moveRight() {
        img = Sprite.movingSprite(Sprite.player_right, Sprite.player_right_1, Sprite.player_right_2, rightKey).getFxImage();
        if (rightKey == maxTime) {
            rightKey = 0;
        } else {
            rightKey++;
        }
        if (checkMove(1)) {
            x = x + speed;
        }
    }

    public void moveUp() {
        img = Sprite.movingSprite(Sprite.player_up, Sprite.player_up_1, Sprite.player_up_2, upKey).getFxImage();
        if (upKey == maxTime) {
            upKey = 0;
        } else {
            upKey++;
        }
        if (checkMove(0)) {
            y = y - speed;
        }
    }

    public void moveDown() {
        img = Sprite.movingSprite(Sprite.player_down, Sprite.player_down_1, Sprite.player_down_2, downKey).getFxImage();
        if (downKey == maxTime) {
            downKey = 0;
        } else {
            downKey++;
        }
        if (checkMove(2)) {
            y = y + speed;
        }
    }

    public void keyBoard (KeyEvent event) {
        if (event.getCode().equals(KeyCode.UP) == true) {
            direction = 0;
            moveUp();
        }
        if (event.getCode().equals(KeyCode.RIGHT) == true) {
            direction = 1;
            moveRight();
        }
        if (event.getCode().equals(KeyCode.DOWN) == true) {
            direction = 2;
            moveDown();
        }
        if (event.getCode().equals(KeyCode.LEFT) == true) {
            direction = 3;
            moveLeft();
        }
        if (event.getCode().equals(KeyCode.SPACE) == true) {
            setBomb();
        }
    }

    public void setBomb() {
        if (GamePlay.bombs.size() < numberOfBombs) {
            Sound.playGame("bom_dat");
            Bomb bom;

            if (direction == 0) {
                bom = new Bomb(Math.round(x / Sprite.SCALED_SIZE), Math.floor(y / Sprite.SCALED_SIZE), gamePlay);
            }
            if (direction == 2) {
                bom = new Bomb(Math.round(x / Sprite.SCALED_SIZE), Math.ceil(y / Sprite.SCALED_SIZE), gamePlay);
            }
            if (direction == 1) {
                bom = new Bomb(Math.ceil(x / Sprite.SCALED_SIZE), Math.round(y / Sprite.SCALED_SIZE), gamePlay);
            } else {
                bom = new Bomb(Math.floor(x / Sprite.SCALED_SIZE), Math.round(y / Sprite.SCALED_SIZE), gamePlay);
            }

            for (int i = 0; i < FlameRay.flameLength; i++) {
                GamePlay.flames.add(bom.flameRayDown.flameList.get(i));
                GamePlay.flames.add(bom.flameRayLeft.flameList.get(i));
                GamePlay.flames.add(bom.flameRayUp.flameList.get(i));
                GamePlay.flames.add(bom.flameRayRight.flameList.get(i));
            }
            GamePlay.bombs.add(bom);
        }
    }

    @Override
    public void render(GraphicsContext graphicsContext) {
        if (isCheckKilled()) {
            if (timeDie > 20) {
                Sound.playGame("end_game");
            }
            if (timeDie > -30) {
                if (getAnimation() % 60 < 20) {
                    img = Sprite.player_dead1.getFxImage();
                } else if (getAnimation() % 60 < 40) {
                    img = Sprite.player_dead2.getFxImage();
                } else {
                    img = Sprite.player_dead3.getFxImage();
                }
                graphicsContext.drawImage(img, x, y);
            } else {
                removeEntity();
            }
            Message mes = new Message("-1 LIVES", x, y, 2);
            GamePlay.messages.add(mes);
            animate();
        } else {
            super.render(graphicsContext);
        }
    }
}
