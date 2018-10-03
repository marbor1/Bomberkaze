/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.entities.creatures;

import game.Game;
import game.Handler;
import game.gfx.Assets;
import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Mantvydas
 */
public class Player extends Creature{

    
    public Player(Handler handler, float x, float y){
        super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
    
        bounds.x = 19;
        bounds.y = 24;
        bounds.width = 25;
        bounds.height = 40;
    }

    @Override
    public void tick() {
        getInput();
        move();
    }

    private void getInput(){
        xMove = 0;
        yMove = 0;
        
        if(handler.getKeyManager().up)
            yMove = -speed;
        if(handler.getKeyManager().down)
            yMove = speed;
        if(handler.getKeyManager().left)
            xMove = -speed;
        if(handler.getKeyManager().right)
            xMove = speed;
    }
    
    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.player, (int) x, (int) y, width, height, null);
    
        //g.setColor(Color.red);
        //g.fillRect((int) (x + bounds.x), (int) (y + bounds.y), bounds.width, bounds.height);
    }
    
}
