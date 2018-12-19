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
import game.mediator.Mediator;

/**
 *
 * @author nugal
 */
public class LevelFactory2 extends AbstractEnemyFactory {

    @Override
    public Melee createMelee(Handler handler, float x, float y, Mediator mediator) {
        return new SlowZombie(handler, x, y, mediator);
    }

    @Override
    public Range createRange(Handler handler, float x, float y, Mediator mediator) {
        return new RangerZombie(handler, x, y, mediator);
    }
    
}
