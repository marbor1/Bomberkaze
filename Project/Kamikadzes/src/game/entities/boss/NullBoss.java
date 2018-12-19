/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.entities.boss;
import game.Handler;
import game.entities.creatures.Creature;
import game.entities.creatures.Melee;
import game.gfx.Assets;
import game.mediator.Mediator;
import java.awt.Graphics;
import singletones.MySingletone;
import visitor.Visitors;

/**
 *
 * @author nugal
 */
public class NullBoss extends Melee{
     public NullBoss(Handler handler, float x, float y, Mediator mediator){
        super(handler, x, y, mediator);
    }
    public NullBoss(){
        super(null,0,0, null);
    }
     
     @Override
    public void tick() {

    }

    private void getMovement(){
    }
    
    @Override
    public void render(Graphics g) {
    }
    public void isKilled()
    {
    }
}
