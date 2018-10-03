/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import game.display.Display;

/**
 *
 * @author Mantvydas
 */
public class Launcher {
    public static void main(String[] args){
        Game game = new Game("Kamikadzes", 900, 900);
        game.start();
    }
}
