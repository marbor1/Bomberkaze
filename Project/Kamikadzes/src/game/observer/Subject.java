/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.observer;

import game.entities.Entity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kompas
 */
//Konkretus subject yra Physics
public abstract class Subject {
    
    private List<IObserver> observers = new ArrayList<IObserver>();
    
    public void add(IObserver obs)
    {
        observers.add(obs);
    }	
	
    public void remove(IObserver obs)
    {
        observers.remove(obs);
    }
	
    public void notify(final Entity entity, Event event)
    {
        for(IObserver o : observers)
        {
            o.update(entity, event);
        }
    }
	   
}
