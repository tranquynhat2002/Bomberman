package uet.oop.bomberman.game;

public class Score {
    private static int scoreTotal = 0;
    private static int scoreLevel = 0;

    public int getScoreTotal() {
        return scoreTotal;
    }

    public void setScoreTotal(int scoreTotal) {
        this.scoreTotal = scoreTotal;
    }

    public void setScoreLevel(int scoreLevel) {
        this.scoreLevel = scoreLevel;
    }

    public void addScore (int score) {
        this.scoreLevel = this.scoreLevel + score;
        this.scoreTotal = this.scoreTotal + score;
    }
}
