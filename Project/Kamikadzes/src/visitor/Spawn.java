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
public class Spawn implements Visitors {

    @Override
    public String talk(BlueSkin skin) {
       System.out.println(" i m " + skin.skinName + " and i m ready to go!!!");
       return "ok";
    }

    @Override
    public String talk(RedSkin skin) {
        System.out.println(" i m " + skin.skinName + " and i m ready to go!!!");
        return "ok";    
    }

    @Override
    public String talk(BlueHat skin) {
        System.out.println(" i have" + skin.skinName + " and i m ready to go!!!");
        return "ok";    
    }
    
}
