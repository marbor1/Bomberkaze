/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.entities.builders;

import game.Handler;
import game.entities.creatures.Creature;
import game.entities.creatures.Enemies.*;

/**
 *
 * @author Mantvydas
 */
public class ZombieBuilder implements IBuilder{
    
    Creature e = null; 
    int x;
    int y;
    Handler handler;
    
    @Override
    public IBuilder startNew(Handler handler, int x, int y) {
         e = null;
         this.handler = handler;
         this.x = x;
         this.y = y;
         return this;
    }
    
    @Override
    public IBuilder addSlow() {
        e = new SlowZombie(handler, x, y);
        return this;
    }
    
    @Override
    public IBuilder addFast() {
        e = new FastZombie(handler, x, y);
        return this;
    }
    
    @Override
    public Creature buildEnemy() {
        return e;
    }
}
