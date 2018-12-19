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
    public void talk(BlueSkin skin) {
       System.out.println(" i m " + skin.skinName + " and i m ready to go!!!");
    }

    @Override
    public void talk(RedSkin skin) {
        System.out.println(" i m " + skin.skinName + " and i m ready to go!!!");
    }

    @Override
    public void talk(BlueHat skin) {
        System.out.println(" i have" + skin.skinName + " and i m ready to go!!!");
    }

    @Override
    public void dropItem(BlueSkin zombie) {
        System.out.println("Zombie cant drop item on spawn.");
    }

    @Override
    public void dropItem(RedSkin zombie) {
        System.out.println("Zombie cant drop item on spawn.");
    }

    @Override
    public void dropItem(BlueHat zombie) {
         System.out.println("Zombie cant drop item on spawn.");
    }
    
}
