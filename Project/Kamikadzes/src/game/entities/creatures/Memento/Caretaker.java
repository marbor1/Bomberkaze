/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.entities.creatures.Memento;

import java.util.ArrayList;

/**
 *
 * @author Mantvydas
 */
public class Caretaker {
    
    ArrayList<Memento> statesList;
    
    public Caretaker(){
        statesList = new ArrayList<Memento>();
    }
    
    public void add(Memento state){
       statesList.add(state);
    }
    
    public Memento get(int index){
        Memento restoreState = statesList.get(index);
        //statesList.remove(index); We don't need to remove saves if we don't want to.
        return restoreState;
    }
    public void remove(int index){
        statesList.remove(index);
    }
    public int getSize(){
        return statesList.size();
    }
    
}
