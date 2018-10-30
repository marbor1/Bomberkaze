/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.entities.creatures.Enemies;
import game.Handler;
import game.entities.creatures.Creature;
import game.entities.creatures.Melee;
import game.gfx.Assets;
import java.awt.Graphics;
import singletones.MySingletone;

/**
 *
 * @author nugal
 */
public class FastZombie extends Melee{
    
     public FastZombie(Handler handler, float x, float y){
        super(handler, x, y);
        
        speed = 2;
        bounds.x = 19;
        bounds.y = 24;
        bounds.width = 25;
        bounds.height = 40;
    
    }
    public FastZombie(){
        super(null,0,0);
    }
     
     @Override
    public void tick() {
        getMovement();
        move();

    }

    private void getMovement(){
        xMove = speed;
        yMove = speed;
        
    }
    
    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.player, (int) x, (int) y, width, height, null);
  
    }
    public void isKilled()
    {
        MySingletone points = MySingletone.getInstance();
        points.activity(100);
        System.out.println(points.getPoints());
    }

}
