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
public interface IBuilder {
    
    IBuilder startNew(Handler handler, int x, int y); //Takes handler and location of enemy
    
    IBuilder addSlow();
    
    IBuilder addFast();
    
    Creature buildEnemy();
    
}
