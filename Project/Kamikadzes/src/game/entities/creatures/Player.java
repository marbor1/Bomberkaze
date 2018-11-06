/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.entities.creatures;

import game.Game;
import game.Handler;
import game.entities.creatures.playerSkins.IPlayerSkin;
import game.gfx.Assets;
import game.input.Command;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import singletones.MySingletone;

/**
 *
 * @author Mantvydas
 */
public class Player extends Creature implements IPlayerSkin{

    protected BufferedImage skin;
    private Command command;
    
    private long lastAttackTimer, attackCooldown = 800, attackTimer = attackCooldown;
    
    
    public Player(String name, BufferedImage skin, Handler handler, float x, float y, boolean hero){
        super(name, handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT, hero);
    
        if (skin == null )
        {
            this.skin = Assets.player;
        }
        this.skin = skin;
        bounds.x = 19;
        bounds.y = 24;
        bounds.width = 25;
        bounds.height = 40;
        hero = true;

    }

    @Override
    public void tick() {
        getInput();
        move();
        MySingletone points = MySingletone.getInstance();
        points.activity((int)yMove);
        points.activity((int)xMove);
        checkAttacks();
//        checkAlgorithms();
       // System.out.println(points.getPoints());
       
    }
    
    private void checkAttacks()
    {
        attackTimer += System.currentTimeMillis() - lastAttackTimer;
        lastAttackTimer = System.currentTimeMillis();
        if(attackTimer < attackCooldown)
            return;
        
        if(handler.getKeyManager().attack)
            handler.getKeyManager().buttonG.execute(this);
        else if(handler.getKeyManager().bomb)
            handler.getKeyManager().buttonF.execute(this);
        else 
            return;
        
        attackTimer = 0;
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
        g.drawImage(this.skin, (int) x, (int) y, width, height, null);
    
        //g.setColor(Color.red);
        //g.fillRect((int) (x + bounds.x), (int) (y + bounds.y), bounds.width, bounds.height);
    }

    //Player ataka nesutampa su prieso igyvendintu metodu
    @Override
    public void attack() {
        System.out.println(this.name + " atakuoja");
    }
    
    public void putBomb(){
        System.out.println(this.name + " padejo bomba");
    }

    @Override
    public BufferedImage draw() {
        return null;
    }
    
}
