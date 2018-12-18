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
public class MenuState extends State{

    public MenuState(Handler handler){
        super(handler);
        
    }
    
    @Override
    public void tick() {
        
    }

    @Override
    public void render(Graphics g) {
        
    }

    @Override
    public void setPlayer(Player player) {

    }

    @Override
    public void removePlayerMP(String username) {

    }

    @Override
    public int getPlayerMPIndex(String username) {
        return 0;
    }

    @Override
    public void movePlayer(String username, float x, float y) {

    }
}
