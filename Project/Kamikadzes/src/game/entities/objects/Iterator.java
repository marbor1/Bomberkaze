/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.entities.objects;

import game.entities.creatures.Creature;

/**
 *
 * @author Kompas
 */
public interface Iterator {    
    public void add(Object obj);    
    public void remove(Object obj);    
    public Object first();    
    public Object current();    
    public Object next();    
    public boolean hasNext();  
}
