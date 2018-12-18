/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.entities.creatures.Memento;

import game.entities.Entity;
import game.entities.creatures.Bomb;
import java.time.*;

/**
 *
 * @author Mantvydas
 */
public class Memento {
    String state;
    float x, y;
    String name;
    LocalDateTime time;
    
    public Memento(String newState, Entity entity){
        state = newState;
        x = entity.getX();
        y = entity.getY();
        name = entity.getName();
        time = time.now();
    }
    
    public void getState(Entity entity){
        entity.setEntityState(this.state);
        entity.setX(this.x);
        entity.setY(this.y);
        entity.setName(this.name);
    }
    
    
}
