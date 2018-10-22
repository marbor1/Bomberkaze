/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.entities.factories;

import game.Handler;
import game.entities.creatures.Melee;
import game.entities.creatures.Range;

/**
 *
 * @author nugal
 */
public abstract class AbstractEnemyFactory {
    public abstract Melee createMelee(Handler handler, float x, float y);
    public abstract Range createRange(Handler handler, float x, float y);
}
