package uet.oop.bomberman.entities.enemy.AI;

import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.entities.enemy.Enemy;

public class AImedium  extends AI {
    protected Bomber _bomber;
    protected Enemy _e;

    public AImedium(Bomber bomber, Enemy e) {
        _bomber = bomber;
        _e = e;
    }

    @Override
    public int calculateDirection() {
        if(_bomber == null)
            return random.nextInt(4);

        int vertical = random.nextInt(2);

        if(vertical == 1) {
            int v = calculateRowDirection();
            if(v != -1)
                return v;
            else
                return calculateColDirection();

        } else {
            int h = calculateColDirection();

            if(h != -1)
                return h;
            else
                return calculateRowDirection();
        }
    }
    protected int calculateColDirection() {
        if(_bomber.getX() < _e.getX())
            return 3;
        else if(_bomber.getX() > _e.getX())
            return 1;

        return -1;
    }

    protected int calculateRowDirection() {
        if(_bomber.getY() < _e.getY())
            return 0;
        else if(_bomber.getY() > _e.getY())
            return 2;
        return -1;
    }
}
