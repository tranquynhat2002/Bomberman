package uet.oop.bomberman.entities.dynamic;
import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.entities.entity.EntityAnimation;
import uet.oop.bomberman.game.GamePlay;
import uet.oop.bomberman.graphics.Sprite;
public class Bomb extends Dynamic {

    public FlameRay flameRayUp = new FlameRay(x / Sprite.SCALED_SIZE, y / Sprite.SCALED_SIZE - 1, 0, gamePlay);
    public FlameRay flameRayDown = new FlameRay(x / Sprite.SCALED_SIZE, y / Sprite.SCALED_SIZE + 1, 2, gamePlay);
    public FlameRay flameRayLeft = new FlameRay(x / Sprite.SCALED_SIZE - 1, y / Sprite.SCALED_SIZE, 3, gamePlay);
    public FlameRay flameRayRight = new FlameRay(x / Sprite.SCALED_SIZE + 1, y / Sprite.SCALED_SIZE, 1, gamePlay);

    public Bomb(double x, double y, GamePlay gamePlay) {
        super(x, y, gamePlay);
        img = Sprite.bomb.getFxImage();
    }

    @Override
    public void collisionHandling() {
        flameRayUp.collisionHandling();
        flameRayDown.collisionHandling();
        flameRayLeft.collisionHandling();
        flameRayRight.collisionHandling();
    }

    @Override
    public void render(GraphicsContext graphicsContext) {
        if (timeExplode > 0) {
            if (getAnimation() % 60 < 20) {
                img = Sprite.bomb_2.getFxImage();
            } else if (getAnimation() % 60 < 40) {
                img = Sprite.bomb_1.getFxImage();
            } else {
                img = Sprite.bomb.getFxImage();
            }
            graphicsContext.drawImage(img, x, y);
        } else {
            if (timeDie > 0) {
                if (getAnimation() % 30 < 10) {
                    img = Sprite.bomb_exploded.getFxImage();
                } else if (getAnimation() % 30 < 20) {
                    img = Sprite.bomb_exploded1.getFxImage();
                } else {
                    img = Sprite.bomb_exploded2.getFxImage();
                }
                graphicsContext.drawImage(img, x, y);
            } else {
                removeEntity();
            }
        }

        flameRayUp.render(graphicsContext);
        flameRayDown.render(graphicsContext);
        flameRayLeft.render(graphicsContext);
        flameRayRight.render(graphicsContext);

        animate();
    }

    @Override
    public void update() {
        super.update();

        flameRayUp.update();
        flameRayDown.update();
        flameRayLeft.update();
        flameRayRight.update();

        collisionHandling();
    }
}