package uet.oop.bomberman.entities.character;

import uet.oop.bomberman.entities.dynamic.Bomb;
import uet.oop.bomberman.entities.dynamic.Brick;
import uet.oop.bomberman.entities.entity.EntityAnimation;
import uet.oop.bomberman.entities.statics.tile.Wall;
import uet.oop.bomberman.entities.entity.Entity;
import uet.oop.bomberman.entities.entity.EntityLayered;
import uet.oop.bomberman.game.GamePlay;

import java.util.List;

public abstract class Character extends EntityAnimation {
    //tốc độ của game
    protected double speed;

    protected GamePlay gamePlay = new GamePlay();

    //kiểm tra xem khi thực hiện hướng đó thì có thể di chuyển được không
    protected boolean checkMoved;
    //hướng đi của thực thể
    protected int direction = -1;

    public Character(double x, double y, GamePlay gamePlay) {
        super(x,y,gamePlay);
    }

    public boolean checkMove(int direction) {
        checkMoved = true;
        if (direction == 0) {
            List<Entity> entityList = gamePlay.entityLocation(x,y - speed);
            for (Entity e : entityList) {
                if ((e instanceof Wall || e instanceof Bomb) && y > e.getY()) {
                    checkMoved = false;
                } else if (e instanceof EntityLayered && y > e.getY()) {
                    if (((EntityLayered) e).getTopEntity() instanceof Brick) {
                        checkMoved = false;
                    }
                }
            }
        }

        if (direction == 1) {
            List<Entity> entityList = gamePlay.entityLocation(x + speed, y);
            for (Entity e : entityList) {
                if ((e instanceof Wall || e instanceof Bomb) && x < e.getX()) {
                    checkMoved = false;
                } else if (e instanceof EntityLayered && x < e.getX()) {
                    if (((EntityLayered) e).getTopEntity() instanceof Brick) {
                        checkMoved = false;
                    }
                }
            }
        }

        if (direction == 2) {
            List<Entity> entityList = gamePlay.entityLocation(x, y + speed);
            for (Entity e : entityList) {
                if ((e instanceof Wall || e instanceof Bomb) && y < e.getY()) {
                    checkMoved = false;
                } else if (e instanceof EntityLayered && y < e.getY()) {
                    if (((EntityLayered) e).getTopEntity() instanceof Brick) {
                        checkMoved = false;
                    }
                }
            }
        }

        if (direction == 3) {
            List<Entity> entityList = gamePlay.entityLocation(x - speed, y);
            for (Entity e : entityList) {
                if ((e instanceof Wall || e instanceof Bomb) && x > e.getX()) {
                    checkMoved = false;
                } else if (e instanceof EntityLayered && x > e.getX()) {
                    if (((EntityLayered) e).getTopEntity() instanceof Brick) {
                        checkMoved = false;
                    }
                }
            }
        }

        if (isCheckKilled()) {
            checkMoved = false;
        }
        return checkMoved;
    }

    public void update() {
        if (isCheckKilled()) {
            timeDie = timeDie - 1;
        }
        collisionHandling();
    }
}
