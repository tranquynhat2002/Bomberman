package uet.oop.bomberman.entities.enemy.AI;

public class AIlow extends AI {
    @Override
    public int calculateDirection() {
        return random.nextInt(4);
    }
}