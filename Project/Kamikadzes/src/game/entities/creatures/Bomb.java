/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.entities.creatures;

import game.entities.creatures.Memento.Memento;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mantvydas
 */
public class Bomb implements Cloneable{
    String type;
    private int lifeTime;
    private boolean exploded;
    private boolean planted;

    public Bomb(String type) {
        this.type = type;
        lifeTime = 5;
        exploded = false;
        planted = false;
    }
    
    public Bomb clone(){
        try {
            return (Bomb) super.clone();
        } catch (CloneNotSupportedException ex) {
           ex.printStackTrace();
        }
        return null;
    }
    

    public void setExploded(boolean newExploded){
       exploded = newExploded;
    }
    public void setPlanted(boolean newPlanted){
       planted = newPlanted;
    }
    public void setLifetime(int newLifeTime){
       lifeTime = newLifeTime;
    }
    public void setType(String newType){
       type = newType;
    }
    public boolean getExploded(){
       return exploded;
    }
    public boolean getPlanted(){
       return planted;
    }
    public int getLifetime(){
       return lifeTime;
    }
    public String getType(){
       return type;
    }
}
