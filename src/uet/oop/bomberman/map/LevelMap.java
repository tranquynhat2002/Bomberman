package uet.oop.bomberman.map;

import uet.oop.bomberman.entities.character.*;
import uet.oop.bomberman.entities.dynamic.Brick;
import uet.oop.bomberman.entities.enemy.*;
import uet.oop.bomberman.entities.statics.item.ItemBomb;
import uet.oop.bomberman.entities.statics.item.ItemFlame;
import uet.oop.bomberman.entities.statics.item.ItemSpeed;
import uet.oop.bomberman.entities.statics.tile.Grass;
import uet.oop.bomberman.entities.statics.tile.Portal;
import uet.oop.bomberman.entities.statics.tile.Wall;
import uet.oop.bomberman.entities.entity.EntityLayered;
import uet.oop.bomberman.game.GamePlay;

import java.io.BufferedReader;
import java.io.FileReader;


import static uet.oop.bomberman.game.GameBomberman.*;

public class LevelMap {
    private GamePlay gamePlay;

    public LevelMap (GamePlay gamePlay) {
        this.gamePlay = gamePlay;
    }

    public void createMap(int level) {
        readMapFromFile(level);
        for (int i = 0; i< HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                Grass grass = new Grass(j, i, gamePlay);
                GamePlay.stillObjects.add(grass);
                if (GamePlay.mapLevel[i][j] == '#') {
                    Wall wall = new Wall(j, i, gamePlay);
                    GamePlay.stillObjects.add(wall);
                }
                loadEntities(GamePlay.mapLevel[i][j], j, i);
            }
        }
    }

    public void readMapFromFile(int level) {
        try {
            FileReader fileMapReader = new FileReader("res\\levels\\Level" + level + ".txt");
            BufferedReader buffer_read = new BufferedReader(fileMapReader);
            String s = buffer_read.readLine();
            String num[] = s.split(" ");
            Integer level1 = Integer.parseInt(num[0]);
            Integer height = Integer.parseInt(num[1]);
            Integer width = Integer.parseInt(num[2]);
            String enter = "";
            for (int i = 0; i < HEIGHT; i++) {
                enter = buffer_read.readLine();
                for (int j = 0; j < WIDTH; j++) {
                    GamePlay.mapLevel[i][j] = enter.charAt(j);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadEntities(char c, int x, int y) {
        switch (c) {
            case 'x': {
                Portal portal = new Portal(x, y, gamePlay);
                Brick brick_ = new Brick(x, y, gamePlay);
                EntityLayered entityLayered_x = new EntityLayered(x, y, portal, brick_, gamePlay);
                GamePlay.entityLayereds.add(entityLayered_x);
                GamePlay.portals.add(portal);
                break;
            }
            case 'p': {
                Bomber newBomber = new Bomber(x, y, gamePlay);
                gamePlay.bomberman.add(newBomber);
                gamePlay.startBomberX = x;
                gamePlay.startBomberY = y;
                break;
            }

            case '*': {
                Brick brick = new Brick(x, y, gamePlay);
                EntityLayered layeredEntity_bb = new EntityLayered(x, y, brick, gamePlay);
                GamePlay.entityLayereds.add(layeredEntity_bb);
                break;
            }

            case '1': {
                Balloom balloom = new Balloom(x, y, gamePlay);
                GamePlay.enemies.add(balloom);
                break;
            }
            case '2': {
                Oneal oneal = new Oneal(x, y, gamePlay);
                GamePlay.enemies.add(oneal);
                break;
            }
            case '3': {
                Kondoria kondoria = new Kondoria(x, y, gamePlay);
                GamePlay.enemies.add(kondoria);
                break;
            }
            case '4': {
                Doll doll = new Doll(x, y, gamePlay);
                GamePlay.enemies.add(doll);
                break;
            }
            case '5': {
                Minvo minvo = new Minvo(x, y, gamePlay);
                GamePlay.enemies.add(minvo);
                break;
            }

            case 'f': {
                ItemFlame itemFlame = new ItemFlame(x, y, gamePlay);
                Brick brick_f = new Brick(x, y, gamePlay);
                EntityLayered entityLayered_f = new EntityLayered(x, y, itemFlame, brick_f, gamePlay);
                GamePlay.entityLayereds.add(entityLayered_f);
                break;
            }

            case 'b': {
                ItemBomb itemBomb = new ItemBomb(x, y, gamePlay);
                Brick brick_b = new Brick(x, y, gamePlay);
                EntityLayered entityLayered_b = new EntityLayered(x, y, itemBomb, brick_b, gamePlay);
                GamePlay.entityLayereds.add(entityLayered_b);
                break;
            }

             case 's': {
                ItemSpeed itemSpeed = new ItemSpeed(x, y, gamePlay);
                Brick brick_s = new Brick(x, y, gamePlay);
                EntityLayered entityLayered_s = new EntityLayered(x, y, itemSpeed, brick_s, gamePlay);
                GamePlay.entityLayereds.add(entityLayered_s);
                break;
            }
        }
    }
}
