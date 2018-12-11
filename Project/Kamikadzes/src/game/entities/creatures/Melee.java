/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.entities.creatures;

import game.strategy.Slash;
import game.Handler;
import game.gfx.Assets;
import java.awt.Graphics;
import singletones.MySingletone;

/**
 *
 * @author nugal
 */
public abstract class Melee extends Creature{
     public Melee(Handler handler, float x, float y){
        super("Melee", handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT, false);
    
        bounds.x = 59;
        bounds.y = 24;
        bounds.width = 25;
        bounds.height = 40;
        
        this.attackList.add(new Slash());
        
    }
    private void getInput(){
        xMove = 1;
        yMove = 1; 
    }
    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.player, (int) x, (int) y, width, height, null);
    }
//    public double accaptVisitor()
//    {
//        
//    }
}
