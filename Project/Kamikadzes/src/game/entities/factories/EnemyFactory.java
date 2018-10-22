/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.entities.factories;

import game.entities.creatures.Creature;
import game.entities.creatures.Enemies.FastZombie;
import game.Handler;
import game.entities.creatures.Enemies.SlowZombie;
/**
 *
 * @author nugal
 */
public class EnemyFactory implements IEnemyFactory{
    
    public Creature createEnemy(String type, Handler handler,int x, int y)
    {
        if(type.equals("FastZombie")){
            return new FastZombie(handler, x, y);
        }
        if(type.equals("SlowZombie")){
            return new SlowZombie(handler, x, y);
        }
        return null;
    }
}
