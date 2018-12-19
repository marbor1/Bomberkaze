/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.entities.creatures.Enemies;
import game.Handler;
import game.entities.creatures.Creature;
import game.entities.creatures.Range;
import game.gfx.Assets;
import game.mediator.Mediator;
import java.awt.Graphics;
import singletones.MySingletone;
import visitor.Visitors;

/**
 *
 * @author nugal
 */
public class RangerZombie extends Range{
    public String name = "trowPo" + Math.random()*1000;
     public RangerZombie(Handler handler, float x, float y, Mediator mediator){
        super(handler, x, y, mediator);
        
        speed = 2;
        bounds.x = 19;
        bounds.y = 24;
        bounds.width = 25;
        bounds.height = 40;
    
    }
    public RangerZombie(){
        super(null,0,0, null);
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
    
        //g.setColor(Color.red);
        //g.fillRect((int) (x + bounds.x), (int) (y + bounds.y), bounds.width, bounds.height);
    }    
    public void isKilled()
    {
        MySingletone points = MySingletone.getInstance();
        points.activity(100);
    }
     

}
