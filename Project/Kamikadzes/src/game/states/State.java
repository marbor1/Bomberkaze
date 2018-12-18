/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.states;

import game.Game;
import game.Handler;
import game.entities.creatures.Player;

import java.awt.Graphics;

/**
 *
 * @author Mantvydas
 */
public abstract class State {
    
    private static State currentState = null;
    
    public static void setState(State state){
        currentState = state;
    }
    
    public static State getState(){
        return currentState;
    }
    protected Handler handler;
    
    public State(Handler handler){
        this.handler = handler;
    }
    //Class
    public abstract void tick();
    
    public abstract void render(Graphics g);

    public abstract void setPlayer(Player player);

    public abstract void removePlayerMP(String username);

    public abstract int getPlayerMPIndex(String username);

    public  abstract  void movePlayer(String username, float x, float y);
}
