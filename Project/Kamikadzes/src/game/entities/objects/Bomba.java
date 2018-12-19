/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.entities.objects;

import game.Handler;
import game.entities.creatures.Creature;
import game.gfx.Assets;
import game.tiles.Tile;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Marius
 */
public class Bomba extends StaticObject{
    protected int aliveTime = 100;
    protected int damage = 100;
    protected int range = 32*5;
    protected List<Integer> zonaX;
    protected List<Integer> zonaY;
    
    public Bomba(Handler handler, float x, float y) {
        super("Bomba", handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT, false);
        zonaX = new ArrayList<Integer>();
        zonaY = new ArrayList<Integer>();
    }
    
    public void explosionArea(int enemyX, int enemyY)
    {
        if((Math.abs(enemyX - this.x) < range && enemyY == this.y) 
                || (Math.abs(enemyY - this.y) < range && enemyX == this.x))
            System.out.println("Matau zombi");
    }
    
    public int getTime()
    {
        return aliveTime;
    }
    
    public int getDamage()
    {
        return damage;
    }
    
    public List<Integer> getZonaX(){
        return zonaX;
    }
    
    public List<Integer> getZonaY(){
        return zonaY;
    }

    @Override
    public void tick() {
        
        aliveTime = aliveTime - 1;
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.dirt, (int) x, (int) y, width, height, null);
    }    

    @Override
    public Iterator createIterator() {
        IteratorArea result = new IteratorArea();        
        return result;
    }
}
