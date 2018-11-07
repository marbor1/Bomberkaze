/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.entities.factories;

import game.entities.creatures.Creature;
import game.entities.creatures.Enemies.FastZombie;
import game.Handler;
import game.entities.builders.Director;
import game.entities.creatures.Enemies.SlowZombie;
/**
 *
 * @author nugal
 */
public class EnemyFactory implements IFactory{
    
    Director dir = new Director();
    
    @Override
    public Creature createEnemy(String type, Handler handler,int x, int y, String bombsType)
    {
        if(type.equals("FastZombie")){
            return dir.getFastZombie(handler, x, y, bombsType);
            //return new FastZombie(handler, x, y);
        }
        if(type.equals("SlowZombie")){
            return dir.getSlowZombie(handler, x, y, bombsType);
            //return new SlowZombie(handler, x, y);
        }
        return null;
    }
}
