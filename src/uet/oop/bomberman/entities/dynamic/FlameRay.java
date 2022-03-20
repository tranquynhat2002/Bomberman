package uet.oop.bomberman.entities.dynamic;

import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.game.GamePlay;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;

public class FlameRay extends Dynamic {
    public static int flameLength = 1;
    protected int direction;
    public List<Flame> flameList = new ArrayList<>();

    public FlameRay(double xUnit, double yUnit, int direction, GamePlay gamePlay) {
        super(xUnit, yUnit, gamePlay);
        this.direction = direction;

        for (int i = 0; i < flameLength - 1; i++) {
            switch (direction) {
                case 0: {
                    flameList.add(new Flame(x / Sprite.SCALED_SIZE, y / Sprite.SCALED_SIZE - i, 4, gamePlay));
                    break;
                }
                case 1: {
                    flameList.add(new Flame(x / Sprite.SCALED_SIZE + i, y / Sprite.SCALED_SIZE, 5, gamePlay));
                    break;
                }
                case 2: {
                    flameList.add(new Flame(x / Sprite.SCALED_SIZE, y / Sprite.SCALED_SIZE + i, 4, gamePlay));
                    break;
                }
                case 3: {
                    flameList.add(new Flame(x / Sprite.SCALED_SIZE - i, y / Sprite.SCALED_SIZE, 5, gamePlay));
                    break;
                }
            }
        }

        switch (direction) {
            case 0: {
                flameList.add(new Flame(x / Sprite.SCALED_SIZE, y / Sprite.SCALED_SIZE - (flameLength - 1), 0, gamePlay));
                break;
            }
            case 1: {
                flameList.add(new Flame(x / Sprite.SCALED_SIZE + (flameLength - 1), y / Sprite.SCALED_SIZE, 1, gamePlay));
                break;
            }
            case 2: {
                flameList.add(new Flame(x / Sprite.SCALED_SIZE, y / Sprite.SCALED_SIZE + (flameLength - 1), 2, gamePlay));
                break;
            }
            case 3: {
                flameList.add(new Flame(x / Sprite.SCALED_SIZE - (flameLength - 1), y / Sprite.SCALED_SIZE, 3, gamePlay));
                break;
            }
        }
    }

    @Override
    public void collisionHandling() {
        flameList.get(0).collisionHandling();
        int i = 1;
        while (i < flameList.size()) {
            if (!flameList.get(i - 1).checkExplode() || !flameList.get(i - 1).checkRender) {
                break;
            } else {
                flameList.get(i).collisionHandling();
                i++;
            }
        }
    }

    public void render(GraphicsContext graphicsContext) {
        int i = 0;
        while (i < flameList.size()) {
            if (flameList.get(i).checkExplode() && flameList.get(i).checkRender) {
                flameList.get(i).render(graphicsContext);
                i++;
            } else {
                break;
            }
        }
    }

    @Override
    public void update() {
        for (Flame f : flameList) {
            f.update();
        }
    }
}
