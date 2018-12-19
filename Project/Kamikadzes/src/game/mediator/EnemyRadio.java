/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.mediator;

import game.entities.creatures.Creature;
import game.entities.creatures.Enemies.RangerZombie;
import game.entities.creatures.Enemies.SlowZombie;
import game.entities.creatures.Melee;
import game.entities.creatures.Range;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kompas
 */
public class EnemyRadio implements Mediator {

    private List<Creature> enemyList = new ArrayList<Creature>();

    @Override
    public void add(Creature enemy) {
        System.out.print(enemy.getName());
        enemyList.add(enemy);
    }

    @Override
    public void broadcastMessage(Creature sender, String msg) {
        for (Creature e : enemyList) {
            if (e != sender) {
                e.receiveMessage(msg);
            }
        }
    }

    public void notifyMelee(Creature sender, String msg) {
        for (Creature e : enemyList) {
            if (e != sender && e.getClass().equals(Melee.class)) {
                e.receiveMessage(msg);
            }
        }
    }

    public void notifyRange(Creature sender, String msg) {
        for (Creature e : enemyList) {
            if (e != sender && e.getClass().equals(Range.class)) {
                e.receiveMessage(msg);
            }
        }
    }
}
