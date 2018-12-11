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
    public String talk(BlueSkin zombie);
    public String talk(RedSkin zombie);
    public String talk(BlueHat zombie);
}
