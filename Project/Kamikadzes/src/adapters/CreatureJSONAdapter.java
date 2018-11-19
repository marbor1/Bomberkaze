/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adapters;

import game.entities.creatures.Creature;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author a.pocius
 */
public class CreatureJSONAdapter implements JSONParser {
    
    Creature creature;
    JSONObject json;
    
    public CreatureJSONAdapter(Creature creature)
    {
        this.creature = creature;
        json = new JSONObject();
    }

    @Override
    public void getandsetHealth() {
       try {
            json.put("health", creature.getHealth());
        } catch (JSONException e) {
            //some exception handler code.
        }  //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void getandsetSpeed() {
        try {
            json.put("speed", creature.getSpeed());
        } catch (JSONException e) {
            //some exception handler code.
        } 
    }

    @Override
    public void setHealth(int health) {
        try {
            json.put("health", health);
        } catch (JSONException e) {
            //some exception handler code.
        } 
    }

    @Override
    public void setSpeed(float speed) {
        try {
            json.put("speed", speed);
        } catch (JSONException e) {
            //some exception handler code.
        }  
    }

    @Override
    public String showJSONobject() {
        return json.toString();
    }
    
   
    
}
