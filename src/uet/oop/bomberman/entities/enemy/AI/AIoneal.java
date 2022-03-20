package uet.oop.bomberman.entities.enemy.AI;

import uet.oop.bomberman.entities.dynamic.Bomb;
import uet.oop.bomberman.entities.dynamic.Brick;
import uet.oop.bomberman.entities.dynamic.Flame;
import uet.oop.bomberman.entities.enemy.Oneal;
import uet.oop.bomberman.entities.statics.tile.Wall;
import uet.oop.bomberman.entities.entity.Entity;
import uet.oop.bomberman.entities.entity.EntityLayered;
import uet.oop.bomberman.game.GameBomberman;
import uet.oop.bomberman.game.GamePlay;

import java.util.LinkedList;
import java.util.List;

public class AIoneal extends AI {
    protected Oneal oneal;
    protected GamePlay game;
    protected boolean[][] visited = new boolean[GameBomberman.WIDTH][GameBomberman.HEIGHT];
    String[][] path = new String[GameBomberman.WIDTH][GameBomberman.HEIGHT];
    public AIoneal(Oneal oneal, GamePlay game) {
        this.oneal = oneal;
        this.game = game;
    }

    public int calculateDirection() {
        Point onealPoint = new Point((int) (oneal.getX() / 32), (int) (oneal.getY() / 32));
        Point point = new Point((int) GamePlay.bomberman.get(0).getX() / 32, (int) GamePlay.bomberman.get(0).getY() / 32);
        String eTob = resultPath(point, onealPoint);
        if (eTob.equals("l")) {
            return 3;
        } else if (eTob.equals("r")) {
            return 1;
        } else if (eTob.equals("u")) {
            return 0;
        } else if (eTob.equals("d")) {
            return 2;
        } else {
            return random.nextInt(4);
        }
    }

    public boolean nextPoint(int X, int Y) {
        List<Entity> entityList = game.entityLocation(X * 32, Y * 32);
        for (Entity entity : entityList) {
            if (entity instanceof Wall || entity instanceof Brick || entity instanceof Bomb || entity instanceof Flame) {
                return false;
            }
            if (entity instanceof EntityLayered) {
                if (((EntityLayered) entity).getTopEntity() instanceof Brick) {
                    return false;
                }
            }
        }
        return true;
    }

    public void bfs(Point point) {
        for (int i = 0; i < GameBomberman.WIDTH; i++) {
            for (int j = 0; j < GameBomberman.HEIGHT; j++) {
                visited[i][j] = false;
                path[i][j] = " ";
            }
        }
        LinkedList<Point> linkedList = new LinkedList<>();
        visited[point.x][point.y] = true;
        linkedList.add(point);
        boolean check = true;
        while (linkedList.isEmpty() != true) {
            Point point1 = linkedList.pollFirst();
            int X, Y;
            X = point1.x + 1;
            Y = point1.y;
            if (nextPoint(X, Y)) {
                if (visited[X][Y] != true) {
                    visited[X][Y] = true;
                    Point newPoint = new Point(X, Y);
                    linkedList.add(newPoint);
                    path[X][Y] = "r";
                }
            }
            X = point1.x - 1;
            Y = point1.y;
            if (nextPoint(X, Y)) {
                if (visited[X][Y] != true) {
                    visited[X][Y] = true;
                    Point newPoint = new Point(X, Y);
                    linkedList.add(newPoint);
                    path[X][Y] = "l";
                }
            }
            X = point1.x;
            Y = point1.y + 1;
            if (nextPoint(X, Y)) {
                if (visited[X][Y] != true) {
                    visited[X][Y] = true;
                    Point newPoint = new Point(X, Y);
                    linkedList.add(newPoint);
                    path[X][Y] = "d";
                }
            }
            X = point1.x;
            Y = point1.y - 1;
            if (nextPoint(X, Y)) {
                if (visited[X][Y] != true) {
                    visited[X][Y] = true;
                    Point newPoint = new Point(X, Y);
                    linkedList.add(newPoint);
                    path[X][Y] = "u";
                }
            }
        }
    }

    public String resultPath(Point point1, Point point2) {
        String result = " ";
        bfs(point2);
        if (visited[point1.x][point1.y] == false) return " ";

        String direction = "";
        System.out.println(point2.x + " " + point2.y);
        while (point1.equals(point2) != true) {
            System.out.println(point1.x + " " + point1.y);
            direction = path[point1.x][point1.y];
            result = direction + result;
            if (direction == "l") {
                point1.x++;
            } else if (direction == "r") {
                point1.x--;
            } else if (direction == "d") {
                point1.y--;
            } else if (direction == "u") {
                point1.y++;
            }
        }
        System.out.println(result);
        return direction;
    }
}
