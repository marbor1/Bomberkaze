/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.entities.creatures.Enemies;

import singletones.MySingletone;

/**
 *
 * @author nugal
 */
public class EnemiesFacade {
    private MySingletone points = MySingletone.getInstance();
    private FastZombie fastZombie;
    private SlowZombie slowZombie;
    private RangerZombie rangerZombie;
    
    public EnemiesFacade()
    {
        fastZombie = new FastZombie();
        slowZombie = new SlowZombie();
        rangerZombie = new RangerZombie();
    }
    
    public void killFastZombie()
    {
        fastZombie.isKilled();
    }
    public void killSlowZombie()
    {
        slowZombie.isKilled();
    }
    public void killRangerZombie()
    {
        rangerZombie.isKilled();
    }
}
