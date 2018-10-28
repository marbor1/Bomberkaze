/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.observer;

import game.entities.Entity;
import java.util.Observable;

/**
 *
 * @author Kompas
 */
public interface IObserver {
    
    //o - the observable object.
    //arg - an argument passed to the notifyObservers method.
    public void update(final Entity entity, Event event);
    
}
