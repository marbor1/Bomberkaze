/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.entities.builders;

import game.Handler;
import game.entities.creatures.Creature;

/**
 *
 * @author Mantvydas
 */
public class Director {
    private IBuilder builder;
    
    public Creature getSlowZombie(Handler handler, int x, int y)
    {
        builder = new ZombieBuilder();
        return builder.startNew(handler, x, y).addSlow().buildEnemy();
        //return null;
    }
    
    public Creature getFastZombie(Handler handler, int x, int y)
    {
        builder = new ZombieBuilder();
        return builder.startNew(handler, x, y).addFast().buildEnemy();
    }
}
