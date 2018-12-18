/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.entities.creatures.states;

import game.entities.creatures.Player;
import game.input.KeyManager;

/**
 *
 * @author Mantvydas
 */
public interface IPlayerState {
    public static PlayerRunningState running = new PlayerRunningState();
    public static PlayerWalkingState walking = new PlayerWalkingState();
    public static PlayerExhaustedState exhausted = new PlayerExhaustedState();
    public static PlayerHyperState hyper = new PlayerHyperState();
    
    void checkAttacks(Player player, KeyManager keyManager);
    Player setState(Player player, Boolean input);
    Player setPlayerAnimation(Player player);
    Player setSpeed(Player player);
    
}
