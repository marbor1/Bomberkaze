/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.entities.creatures;

import game.strategy.ShootGun;
import game.Handler;
import game.gfx.Assets;
import java.awt.Graphics;
import singletones.MySingletone;

/**
 *
 * @author nugal
 */
public abstract class Range extends Creature{
     public Range(Handler handler, float x, float y){
        super("Ranger", handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT, false);
    
        bounds.x = 59;
        bounds.y = 24;
        bounds.width = 25;
        bounds.height = 40;
        
        //Strategy sablono ataka
        this.attackList.add(new ShootGun());
    }

//    @Override
//    public void tick() {
//       
//    }

    private void getInput(){
        xMove = 1;
        yMove = 1;
        

    }
    
    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.player, (int) x, (int) y, width, height, null);
    
        //g.setColor(Color.red);
        //g.fillRect((int) (x + bounds.x), (int) (y + bounds.y), bounds.width, bounds.height);
    }
      @Override
    public void upgradeHp()
    {
        System.out.println("This zombie got +100Hp");
    }
    @Override
    public void upgradeSpeed()
    {
        System.out.println("This zombie got +7 speed");
    }
    @Override
    public void upgradeDmg()
    {
        System.out.println("This zombie deals 50dmg more");
    }
     @Override
    public void upgradeRange()
    {
        System.out.println("Player range increased by 10");
    }
}
