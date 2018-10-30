/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.input;

import game.entities.creatures.Player;

/**
 *
 * @author Kompas
 */
public abstract class Command {
    
    public abstract void execute(Player actor);
}
