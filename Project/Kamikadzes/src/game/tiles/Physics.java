/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.tiles;

import game.Handler;
import game.entities.Entity;
import game.observer.Event;
import game.observer.Subject;

/**
 *
 * @author Kompas
 */
public class Physics extends Subject{
      
    public Physics()
    {
        super();
    }

       // public boolean collisionWithTile(int x, int y, Entity entity){
    public boolean collisionWithTile(int x, int y, Handler handler){
        return handler.getWorld().getTile(x, y).isSolid();
    }
    
    public void collisionNotify(Entity entity)
    {
        notify(entity, Event.KOLIZIJA);
    }
    
}
