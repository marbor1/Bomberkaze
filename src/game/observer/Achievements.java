/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.observer;

import game.entities.Entity;
import game.entities.creatures.Player;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kompas
 */
public class Achievements implements IObserver{

    private enum Achievement{
        IVYKO_KOLIZIJA;
    }
    
    private final List<Achievement> achievements;
    
    public Achievements()
    {
        achievements = new ArrayList<Achievement>();
    }
    
    @Override
    public void update(Entity entity, Event event) {
        switch(event)
        {
            case KOLIZIJA:
                if(entity.isHero())
                {
                    unlock(Achievement.IVYKO_KOLIZIJA, entity);
                }
        }
    }
    
    private void unlock(Achievement achievement, Entity player)
    {
        if(!achievements.contains(achievement))
        {
            achievements.add(achievement);
            System.out.println(player.getName() + " Gavo achievementa " + achievement.name());
        }
    }
    
}
