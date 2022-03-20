package uet.oop.bomberman.entities.enemy;

import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.entities.character.Character;
import uet.oop.bomberman.entities.enemy.AI.AI;
import uet.oop.bomberman.message.Message;
import uet.oop.bomberman.game.GamePlay;

public abstract class Enemy extends Character {
    protected AI ai;
    protected int score;

    public Enemy(double x, double y, GamePlay gamePlay, int score) {
        super(x, y, gamePlay);
        this.score = score;
    }

    @Override
    public void collisionHandling() {}

    protected abstract void chooseSprite();

    public abstract void moveEnemy();

    public void rightMove() {
        direction = 1;
        if (checkMove(direction)) {
            x += speed;
        }
    }

    public void leftMove() {
        direction = 3;
        if (checkMove(direction)) {
            x -= speed;
        }
    }

    public void upMove() {
        direction = 0;
        if (checkMove(direction)) {
            y -= speed;
        }
    }

    public void downMove() {
        direction = 2;
        if (checkMove(direction)) {
            y += speed;
        }
    }


    public void removeEnemy () {
        gamePlay.scoreGame.addScore(score);
        checkRemoved = true;
        Message message = new Message("+" + String.valueOf(score) , x, y, 1);
        GamePlay.messages.add(message);
    }

    @Override
    public void update() {
        animate();
        if (isCheckKilled()) {
            timeDie = timeDie - 1;
        }
        collisionHandling();
        moveEnemy();
    }
}
