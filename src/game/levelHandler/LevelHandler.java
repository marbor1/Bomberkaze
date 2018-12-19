/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.levelHandler;

import game.Handler;
import game.states.GameState;
import game.states.State;
import game.worlds.World;

/**
 *
 * @author Mantvydas
 */
public abstract class LevelHandler {
    public LevelHandler nextHandler;
    public int level;
    
    public void setNextHandler(LevelHandler next)
    {
        nextHandler = next;
    }
        public LevelHandler getNextHandler()
    {
        return nextHandler;
    }
    public abstract void getRoomLayout(GameState state);
    public abstract void operate(GameState state);

}
