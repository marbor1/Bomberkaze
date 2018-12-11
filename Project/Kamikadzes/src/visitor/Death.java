/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visitor;

import game.entities.creatures.playerSkins.BlueHat;
import game.entities.creatures.playerSkins.BlueSkin;
import game.entities.creatures.playerSkins.RedSkin;

/**
 *
 * @author nugal
 */
public class Death implements Visitors {
    @Override
    public String talk(BlueSkin skin) {
       System.out.println(" i m " + skin.skinName + " and i m dead....");
       return "ok";
    }

    @Override
    public String talk(RedSkin skin) {
        System.out.println(" i m " + skin.skinName + " and i m dead....");
        return "ok";    
    }

    @Override
    public String talk(BlueHat skin) {
        System.out.println(" i have " + skin.skinName + " and i m dead....");
        return "ok";    
    }
}
