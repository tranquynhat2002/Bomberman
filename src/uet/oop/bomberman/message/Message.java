package uet.oop.bomberman.message;

import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.entities.entity.Entity;

public class Message extends Entity {
    protected String _message;
    protected int _duration;

    public Message(String message, double x_, double y_, int duration) {
        x = x_;
        y = y_;
        _message = message;
        _duration = duration * 60; //seconds
    }

    public int getDuration() {
        return _duration;
    }

    public void setDuration(int _duration) {
        this._duration = _duration;
    }

    public String getMessage() {
        return _message;
    }


    @Override
    public void update() {
        Message m;
        int time = 0;
        for (int i = 0; i < gamePlay.messages.size(); i++) {
            m = gamePlay.messages.get(i);
            time = m.getDuration();
            if(time > 0) {
                m.setDuration(--time);
            }
            else {
                checkRemoved = true;
            }
        }
    }


    public void render(GraphicsContext gc) {
        Message m;
        for (int i = 0; i < gamePlay.messages.size(); i++) {
            m = gamePlay.messages.get(i);
            String tmp = m.getMessage();
            gc.fillText(tmp, x, y);
            m.update();
        }

    }

    @Override
    public void collisionHandling() {

    }

}
