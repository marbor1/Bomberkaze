/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;

/**
 *
 * @author Mantvydas
 */
public class KeyManager implements KeyListener{

    private boolean[] keys;
    public boolean up, down, left, right, bomb, attack, a1, a2, a3, a4, a5, a6, a7, a8, a9, f1, f2;
    
    public LinkedList<Command> commands = new LinkedList<Command>();
    public Command buttonF = new PutBombCommand();
    public Command buttonG = new FireCommand();
    
    
    public KeyManager(){
        keys = new boolean[256];
        commands.add(buttonF);
        commands.add(buttonG);
    }
    
    public void tick(){
        up = keys[KeyEvent.VK_W];
        down = keys[KeyEvent.VK_S];
        left = keys[KeyEvent.VK_A];
        right = keys[KeyEvent.VK_D];
        bomb = keys[KeyEvent.VK_F];
        attack = keys[KeyEvent.VK_G];
        
        a1 = keys[KeyEvent.VK_1];
        a2 = keys[KeyEvent.VK_2];
        a3 = keys[KeyEvent.VK_3];
        a4 = keys[KeyEvent.VK_4];
        a5 = keys[KeyEvent.VK_5];   
        a6 = keys[KeyEvent.VK_6];
        a7 = keys[KeyEvent.VK_7];
        a8 = keys[KeyEvent.VK_8];
        a9 = keys[KeyEvent.VK_9];
        f1 = keys[KeyEvent.VK_F1];
        f2 = keys[KeyEvent.VK_F2];
    }
    
    @Override
    public void keyTyped(KeyEvent ke) {
        
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        keys[ke.getKeyCode()] = true;
      //  System.out.println("Pressed");
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        keys[ke.getKeyCode()] = false;
       
    }

}
