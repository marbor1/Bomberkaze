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
public class Level3Handler extends LevelHandler{

    public Level3Handler(){
        this.level = 3;
    }


    @Override
    public void getRoomLayout(GameState state) {
        state.setWorld(new World(state.getHandler(), "res/worlds/world3.txt"));
        System.out.println("Changed to level 3");
    }

    @Override
    public void operate(GameState state) {
        if(this.level == state.getLevel())
        {
             getRoomLayout(state);
        }
        else
        {
              this.getNextHandler().operate(state);
        }
    }


    
}
