/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visitor;
import game.entities.creatures.playerSkins.*;
/**
 *
 * @author nugal
 */
public interface Visitors {
    public void talk(BlueSkin zombie);
    public void talk(RedSkin zombie);
    public void talk(BlueHat zombie);
    public void dropItem(BlueSkin zombie);
    public void dropItem(RedSkin zombie);
    public void dropItem(BlueHat zombie);
}
