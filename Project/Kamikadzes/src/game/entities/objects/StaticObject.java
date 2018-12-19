/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.entities.objects;

import game.Handler;
import game.entities.Entity;

/**
 *
 * @author Marius
 */
public abstract class StaticObject extends Entity {
    
    public StaticObject(String name, Handler handler, float x, float y, int width, int height, boolean hero) {
        super(name, handler, x, y, width, height, hero);
    }
    
    public abstract Iterator createIterator();
}
