/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import game.input.KeyManager;
import game.worlds.World;

/**
 *
 * @author Mantvydas
 */
public class Handler {
    
    private Game game;
    private World world;
    
    public Handler(Game game){
        this.game = game; 
    }
    
    public int getWidth(){
        return game.getWidth();
    }
    public int getHeight(){
        return game.getHeight();
    }
    
    public Game getGame() {
        return game;
    }
    
    public void setGame(Game game) {
        this.game = game;
    }
    
    public World getWorld() {
        return world;
    }
    public void setWorld(World world) {
        this.world = world;
    }

    public KeyManager getKeyManager() {
        return game.getKeyManager();
    }
    
}
