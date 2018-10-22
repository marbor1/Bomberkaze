/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.entities.creatures.levels;

import game.Handler;
import game.entities.creatures.Enemies.FastZombie;
import game.entities.creatures.Enemies.RangerZombie;
import game.entities.creatures.Enemies.SlowZombie;
import game.entities.creatures.Melee;
import game.entities.creatures.Range;
import game.entities.factories.AbstractEnemyFactory;

/**
 *
 * @author nugal
 */
public class LevelFactory1 extends AbstractEnemyFactory {

    @Override
    public Melee createMelee(Handler handler, float x, float y) {
        return new SlowZombie(handler, x, y);
    }

    @Override
    public Range createRange(Handler handler, float x, float y) {
        return new RangerZombie(handler, x, y);
    }
    
}
