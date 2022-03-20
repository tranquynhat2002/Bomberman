package uet.oop.bomberman.entities.entity;

import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.game.GamePlay;

import java.util.ArrayList;
import java.util.List;

public class EntityLayered extends Entity {
    protected List<Entity> entitiesList = new ArrayList<>();

    public EntityLayered(double x, double y, Entity entity_1, GamePlay gamePlay) {
        super(x,y,gamePlay);
        entitiesList.add(entity_1);
        img = getTopEntity().getImg();
    }

    public EntityLayered(double x, double y, Entity entity_1, Entity entity_2, GamePlay gamePlay) {
        super(x,y,gamePlay);
        entitiesList.add(entity_1);
        entitiesList.add(entity_2);
        img = getTopEntity().getImg();
    }

    public Entity getTopEntity() {
        Entity topEntity = null;
        if (entitiesList.isEmpty()) {
            removeEntity();
        } else {
            topEntity = entitiesList.get(entitiesList.size() - 1);
        }
        return topEntity;
    }

    public void update() {
        Entity entityRemove = null;
        for (int i = 0; i < entitiesList.size(); i++) {
            entitiesList.get(i).update();
            if (entitiesList.get(i).isCheckRemoved()) {
                entityRemove = entitiesList.get(i);
            }
        }
        entitiesList.remove(entityRemove);
    }

    @Override
    public void collisionHandling() {
    }

    @Override
    public void render(GraphicsContext graphicsContext) {
        for (Entity e : entitiesList) {
            e.render(graphicsContext);
        }
    }
}

