/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.entities.creatures;

import bridge.Bomb;
import game.Game;
import game.Handler;
import game.entities.creatures.playerSkins.IPlayerSkin;
import game.gfx.Assets;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import singletones.MySingletone;

/**
 *
 * @author Mantvydas
 */
public class Player extends Creature implements IPlayerSkin{

    protected BufferedImage skin;
    
    public Player(BufferedImage skin, Handler handler, float x, float y, boolean hero, Bomb bomb){
        super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT, hero, bomb);
    
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
       // System.out.println(points.getPoints());
       
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

    @Override
    public void attack() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BufferedImage draw() {
        return null;
    }
    
    public void addBomb(){
        bomb.addBomb();
    }
    
}
