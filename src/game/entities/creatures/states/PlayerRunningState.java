/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.entities.creatures.states;

import game.entities.creatures.Player;
import game.gfx.Assets;
import game.input.KeyManager;

/**
 *
 * @author Mantvydas
 */
public class PlayerRunningState implements IPlayerState{
    @Override
    public void checkAttacks(Player player, KeyManager keyManager) {
        
    }

    @Override
    public Player setState(Player player, Boolean input) {
        if(player.getHealth() <= 5)
        {
            player.setNewState(exhausted);
            return player;
        }
        else if(!input)
        {
            player.setNewState(walking);
            return player;
        }
        else if(player.getRunningTime() > 250)
        {
            player.setNewState(hyper);
            return player;
        }
        else
        {
            player.setRunningTime(player.getRunningTime() + 1);
            setPlayerAnimation(player);
            setSpeed(player);
            return player; 
        }
    }

    @Override
    public Player setPlayerAnimation(Player player) {
        player.setSkin(Assets.player2);
        return player;
    }

    @Override
    public Player setSpeed(Player player) {
        player.setSpeed(5);
        return player;
    }
    
}
