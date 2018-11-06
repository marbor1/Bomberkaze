/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adapters;

/**
 *
 * @author a.pocius
 */
public interface JSONParser {
    public void getandsetHealth();
    public void getandsetSpeed();
    public void setHealth(int health);
    public void setSpeed(float speed);
    public String showJSONobject();
    
}
