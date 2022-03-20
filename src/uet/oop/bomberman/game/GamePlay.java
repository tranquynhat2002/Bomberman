package uet.oop.bomberman.game;

import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.entities.enemy.Enemy;
import uet.oop.bomberman.entities.dynamic.Bomb;
import uet.oop.bomberman.entities.dynamic.Flame;
import uet.oop.bomberman.entities.dynamic.FlameRay;
import uet.oop.bomberman.entities.statics.tile.Portal;
import uet.oop.bomberman.entities.entity.Entity;
import uet.oop.bomberman.entities.entity.EntityLayered;
import uet.oop.bomberman.message.Message;

import static uet.oop.bomberman.game.GameBomberman.HEIGHT;
import static uet.oop.bomberman.game.GameBomberman.WIDTH;


import java.util.ArrayList;
import java.util.List;

public class GamePlay {
    public static List<Bomber> bomberman = new ArrayList<>();
    public static List<Enemy> enemies = new ArrayList<>();
    public static List<Entity> stillObjects = new ArrayList<>();
    public static List<EntityLayered> entityLayereds = new ArrayList<>();
    public static List<Portal> portals = new ArrayList<>();
    public static List<Flame> flames = new ArrayList<>();
    public static List<Message> messages = new ArrayList<>();
    public static List<Bomb> bombs = new ArrayList<>();
    public static char[][] mapLevel = new char[HEIGHT][WIDTH];
    public Score scoreGame = new Score();

    // thời gian giới hạn một màn chơi.
    public static int timeLimit = 200;
    public static int lives = 3;
    //kiểm tra xem có đổi level không.
    public static boolean checkChangeLevel = false;

    public double startBomberX = 0;
    public double startBomberY = 0;

    //check vị trí của thực thể có thỏa mãn không (có nằm trong màn hình game không)
    public List<Entity> entityLocation(double x, double y) {
        List<Entity> entityList = new ArrayList<>();

        for (Enemy e : enemies) {
            if (e.getX() >= x + 32 || e.getX() + 32 <= x) {

            } else if (e.getY() >= y + 32 || e.getY() + 32 <= y) {

            } else {
                entityList.add(e);
            }
        }

        for (Bomb b : bombs) {
            if (b.getX() >= x + 32 || b.getX() + 32 <= x) {

            } else if (b.getY() >= y + 32 || b.getY() + 32 <= y) {

            } else {
                entityList.add(b);
            }
        }

        for (EntityLayered el : entityLayereds) {
            if (el.getX() >= x + 32 || el.getX() + 32 <= x) {

            } else if (el.getY() >= y + 32 || el.getY() + 32 <= y) {

            } else {
                entityList.add(el);
            }
        }

        for (Flame f : flames) {
            if (f.getX() >= x + 32 || f.getX() + 32 <= x) {

            } else if (f.getY() >= y + 32 || f.getY() + 32 <= y) {

            } else {
                entityList.add(f);
            }
        }

        for (Entity en : stillObjects) {
            if (en.getX() >= x + 32 || en.getX() + 32 <= x) {

            } else if (en.getY() >= y + 32 || en.getY() + 32 <= y) {

            } else {
                entityList.add(en);
            }
        }

        return entityList;
    }

    public void resetGame() {
        FlameRay.flameLength = 1;
        bomberman.clear();
        enemies.clear();
        stillObjects.clear();
        entityLayereds.clear();
        bombs.clear();
        flames.clear();
        messages.clear();
        timeLimit = 200;
    }


    public void update() {
        bomberman.get(0).update();
        for (int i = 0; i < enemies.size(); i++) {
            if (enemies.get(i).isCheckRemoved()) {
                enemies.remove(i);
            }
        }
        enemies.forEach(Enemy::update);
        stillObjects.forEach(Entity::update);
        for (int i = 0; i < entityLayereds.size(); i++) {
            if (entityLayereds.get(i).isCheckRemoved()) {
                entityLayereds.remove(i);
            }
        }
        entityLayereds.forEach(EntityLayered::update);
        for (int i = 0; i < bombs.size(); i++) {
            if (bombs.get(i).isCheckRemoved()) {
                bombs.remove(i);
            }
        }
        bombs.forEach(Bomb::update);
        for (int i = 0; i < flames.size(); i++) {
            if (flames.get(i).isCheckRemoved()) {
                flames.remove(i);
            }
        }
        for (int i = 0; i < messages.size(); i++) {
            if (messages.get(i).isCheckRemoved()) {
                messages.remove(i);
            }
        }
    }


    public void render() {
        GameBomberman.graphicsContext.clearRect(0, 0, GameBomberman.canvas.getWidth(), GameBomberman.canvas.getHeight());
        stillObjects.forEach(g -> g.render(GameBomberman.graphicsContext));
        entityLayereds.forEach(g -> g.render(GameBomberman.graphicsContext));
        bomberman.forEach(g -> g.render(GameBomberman.graphicsContext));
        enemies.forEach(g -> g.render(GameBomberman.graphicsContext));
        bombs.forEach(g -> g.render(GameBomberman.graphicsContext));
        messages.forEach(g -> g.render(GameBomberman.graphicsContext));
    }
}
