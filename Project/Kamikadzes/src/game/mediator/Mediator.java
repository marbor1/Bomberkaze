/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.mediator;

import game.entities.creatures.Creature;

/**
 *
 * @author Kompas
 */
public interface Mediator {
    	public void add(Creature enemy);	
	public void broadcastMessage(Creature sender, String msg);
}
