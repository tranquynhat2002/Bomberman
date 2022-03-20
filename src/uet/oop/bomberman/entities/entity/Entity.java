package uet.oop.bomberman.entities.entity;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.game.GamePlay;
import uet.oop.bomberman.graphics.Sprite;

public abstract class Entity {
    //Tọa độ X tính từ góc trái trên trong Canvas
    protected double x;

    //Tọa độ Y tính từ góc trái trên trong Canvas
    protected double y;

    //Kiểm tra thực thể có bị xóa hay không
    protected boolean checkRemoved = false;

    //Kiểm tra thực thể đã bị giết hay chưa
    protected boolean checkKilled = false;

    //thời gian từ khi giết thực thể đến khi biến mất
    protected int timeDie = 30;

    protected GamePlay gamePlay;

    protected Image img;
    public Entity() {}

    //Khởi tạo đối tượng, chuyển từ tọa độ đơn vị sang tọa độ trong canvas
    public Entity(double xUnit, double yUnit, GamePlay gamePlay) {
        this.x = xUnit * Sprite.SCALED_SIZE;
        this.y = yUnit * Sprite.SCALED_SIZE;
        this.gamePlay = gamePlay;
    }

    //xử lý khi va chạm với các thực thể
    public abstract void collisionHandling();

    //xóa thực thể
    public void removeEntity() {
        checkRemoved = true;
    }

    //kiểm tra thực thể đã bị xóa chưa
    public boolean isCheckRemoved() {
        return checkRemoved;
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(img, x, y);
    }

    public abstract void update();

    //giết thực thể
    public void killEntity() {
        checkKilled = true;
    }

    //kiểm tra xem thực thể có bị giết hay không
    public boolean isCheckKilled() {
        return checkKilled;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Image getImg() {
        return img;
    }
}
