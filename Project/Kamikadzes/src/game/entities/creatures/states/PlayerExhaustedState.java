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
public class PlayerExhaustedState implements IPlayerState{
    
    @Override
    public void checkAttacks(Player player, KeyManager keyManager) {
        //System.out.println("I can attack!");
        player.setAttackTimer(player.getAttackTimer() + System.currentTimeMillis() - player.getLastAttackTimer());
        player.setLastAttackTimer(System.currentTimeMillis());
        if(player.getAttackTimer() < player.getAttackCooldown())
            return;
        else if(keyManager.bomb)
            keyManager.buttonF.execute(player);
        else 
            return;
       player.setAttackTimer(0);
    }

    @Override
    public Player setState(Player player, Boolean input) {
        if(player.getHealth() > 5 && player.getRunningTime() < 0)
        {
            player.setNewState(walking);
            return player;
        }
        else
        {
            if(player.getRunningTime() >= 0){
            player.setRunningTime(player.getRunningTime() - 1);
            }
            setPlayerAnimation(player);
            setSpeed(player);
            return player;
        }
    }

    @Override
    public Player setPlayerAnimation(Player player) {
        player.setSkin(Assets.exhausted);
        return player;
    }

    @Override
    public Player setSpeed(Player player) {
        player.setSpeed(1);
        return player;
    }
    
}
