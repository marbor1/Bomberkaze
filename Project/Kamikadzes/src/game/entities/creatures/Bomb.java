/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.entities.creatures;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mantvydas
 */
public class Bomb implements Cloneable{
    String type;

    public Bomb(String type) {
        this.type = type;
    }
    
    public Bomb clone(){
        try {
            return (Bomb) super.clone();
        } catch (CloneNotSupportedException ex) {
           ex.printStackTrace();
        }
        return null;
    }
}
