package uet.oop.bomberman.entities.enemy.AI;

public class Point {
    protected int x;
    protected int y;
    public Point (int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean equals(Point obj) {
        if (this.x == obj.x && this.y == obj.y) {
            return true;
        } else return false;
    }
}
