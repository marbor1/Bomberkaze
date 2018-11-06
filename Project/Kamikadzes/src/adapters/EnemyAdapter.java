/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adapters;

import game.entities.creatures.Enemies.FastZombie;

/**
 *
 * @author a.pocius
 */
public class EnemyAdapter implements Enemy {
    
    FastZombie fastzombie;
    
    @Override
    public void attack()
    {
        
    }
    @Override
    public void move()
    {
       fastzombie.tick();
    }
    @Override
    public void destroy()
    {
       
    }
}
