/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.entities.creatures;

import game.Game;
import game.Handler;
import game.entities.Entity;
import game.entities.strategy.*;
import game.tiles.Tile;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mantvydas
 */
public abstract class Creature extends Entity{
    
    public static final int DEFAULT_HEALTH = 10;
    public static final float DEFAULT_SPEED = 3.0f;
    public static final int DEFAULT_CREATURE_WIDTH = 64;
    public static final int DEFAULT_CREATURE_HEIGHT = 64;
    
    protected int health;
    protected float speed;
    protected float xMove, yMove;
    
    //Strategy metodu sarasas
    public List<IAttackAlgorithm> attackList = new ArrayList<IAttackAlgorithm>();
    
    public Creature(Handler handler, float x, float y, int width, int height) {
        super(handler, x, y, width, height);
        health = DEFAULT_HEALTH;
        speed = DEFAULT_SPEED;
        xMove = 0;
        yMove = 0;
    }
    
    public void move(){
        moveX();
        moveY();
    }
    
    public void moveX(){
        if(xMove > 0)//moving right
        {
            int tx = (int) (x + xMove + bounds.x + bounds. width) / Tile.TILEWIDTH;
            if(!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT)
                    && !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT)){ //Collision check
               x += xMove; 
            }else{
                x = tx * Tile.TILEWIDTH - bounds.x - bounds. width - 1;
            }
        }else if(xMove < 0){//moving left
            int tx = (int) (x + xMove + bounds.x) / Tile.TILEWIDTH;
            if(!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT)
                    && !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT)){ //Collision check
               x += xMove; 
            }else{
                x = tx * Tile.TILEWIDTH +Tile.TILEWIDTH - bounds.x; 
            }
        }
    }
    public void moveY(){
        if(yMove < 0){
            int ty = (int) (y + yMove + bounds.y) / Tile.TILEHEIGHT;
            
            if(!collisionWithTile((int) (x + bounds.x) / Tile.TILEWIDTH, ty) 
                    && !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty)){
                y += yMove;
            }else{
                y = ty * Tile.TILEHEIGHT + Tile.TILEHEIGHT - bounds.y;
            }
        }else if(yMove > 0){
           int ty = (int) (y + yMove + bounds.y + bounds.height) / Tile.TILEHEIGHT;
            
            if(!collisionWithTile((int) (x + bounds.x) / Tile.TILEWIDTH, ty) 
                    && !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty)){
                y += yMove;
            }else{
                y = ty * Tile.TILEHEIGHT - bounds.y - bounds.height - 1;
            }
        }
    }
    
    //Atakos metodas, kuris realizuotas Strategy sablone
    public void attack()
    {
        for(IAttackAlgorithm a : attackList){
            a.attackType();
        }
    }
    
    protected boolean collisionWithTile(int x, int y){
        return handler.getWorld().getTile(x, y).isSolid();
    }
    
    public int getHealth(){
        return health;
    }
    public void setHealth(int health){
        this.health = health;
    }
    public float getSpeed(){
        return health;
    }
    public void setSpeed(float speed){
        this.speed = speed;
    }
    
    
}
