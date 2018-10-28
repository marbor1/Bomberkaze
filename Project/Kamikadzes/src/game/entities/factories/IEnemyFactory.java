/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.entities.factories;
import game.Handler;
import game.entities.creatures.Creature;
/**
 *
 * @author nugal
 */
public interface IEnemyFactory {
    
    public Creature createEnemy(String type, Handler handler, int x, int y);
    
}
