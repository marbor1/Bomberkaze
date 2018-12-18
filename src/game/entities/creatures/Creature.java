/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.entities.creatures;

import game.strategy.IAttackAlgorithm;
import game.Game;
import game.Handler;
import game.entities.Entity;
import game.observer.Achievements;
import game.tiles.Tile;
import game.tiles.Physics;
import java.util.ArrayList;
import java.util.List;
import visitor.Visitors;

/**
 *
 * @author Mantvydas
 */
public abstract class Creature extends Entity implements Cloneable {
    
    public static final int DEFAULT_HEALTH = 10;
    public static final float DEFAULT_SPEED = 3.0f;
    public static final int DEFAULT_CREATURE_WIDTH = 64;
    public static final int DEFAULT_CREATURE_HEIGHT = 64;
    
    protected int health;
    protected float speed;
    protected int damage;
    protected int range;
    protected float xMove, yMove;
    protected Bomb bombs = null;
    protected Physics physic = new Physics();
    Achievements achievements = new Achievements();

    abstract void upgradeHp();

    abstract void upgradeSpeed();

    abstract void upgradeDmg();
    
    abstract void upgradeRange();
    
    public final void upgrade()
    {
        upgradeHp();
        upgradeSpeed();
        upgradeDmg();
        upgradeRange();
    }
    public Creature shallowCopy(){  //Shallow copy
        try {
            return (Creature) super.clone();
        } catch (CloneNotSupportedException e){
            e.printStackTrace();
        }
        return null;
    }
    
    public Creature deepCopy(){  //Deep copy
        try {
            Creature clone = (Creature) super.clone();
            clone.bombs = clone.bombs.clone();
            return clone;
        } catch (CloneNotSupportedException e){
            e.printStackTrace();
        }
        return null;
    }
    
    //Strategy metodu sarasas
    public List<IAttackAlgorithm> attackList = new ArrayList<IAttackAlgorithm>();
    
    public Creature(String name, Handler handler, float x, float y, int width, int height, boolean hero) {
        super(name, handler, x, y, width, height, hero);
        health = DEFAULT_HEALTH;
        speed = DEFAULT_SPEED;
        xMove = 0;
        yMove = 0;
        physic.add(achievements);
    }
    
    public void move(){
        moveX();
        moveY();
    }
    
    public void moveX(){    
        if(xMove > 0)//moving right
        {
            int tx = (int) (x + xMove + bounds.x + bounds. width) / Tile.TILEWIDTH;
            if(!physic.collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT, handler)
                    && !physic.collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT, handler)){ //Collision check
               x += xMove; 
            }else{
                x = tx * Tile.TILEWIDTH - bounds.x - bounds. width - 1;
                physic.collisionNotify(this);
            }
        }else if(xMove < 0){//moving left
            int tx = (int) (x + xMove + bounds.x) / Tile.TILEWIDTH;
            if(!physic.collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT, handler)
                    && !physic.collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT, handler)){ //Collision check
               x += xMove; 
            }else{
                x = tx * Tile.TILEWIDTH +Tile.TILEWIDTH - bounds.x;
                physic.collisionNotify(this);
            }
        }
    }
    public void moveY(){
        if(yMove < 0){
            int ty = (int) (y + yMove + bounds.y) / Tile.TILEHEIGHT;
            
            if(!physic.collisionWithTile((int) (x + bounds.x) / Tile.TILEWIDTH, ty, handler) 
                    && !physic.collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty, handler)){
                y += yMove;
            }else{
                y = ty * Tile.TILEHEIGHT + Tile.TILEHEIGHT - bounds.y;
                physic.collisionNotify(this);
            }
        }else if(yMove > 0){
           int ty = (int) (y + yMove + bounds.y + bounds.height) / Tile.TILEHEIGHT;
            
            if(!physic.collisionWithTile((int) (x + bounds.x) / Tile.TILEWIDTH, ty, handler) 
                    && !physic.collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty, handler)){
                y += yMove;
            }else{
                y = ty * Tile.TILEHEIGHT - bounds.y - bounds.height - 1;
                physic.collisionNotify(this);
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
    
   /* protected boolean collisionWithTile(int x, int y){
        return handler.getWorld().getTile(x, y).isSolid();
    }*/
    
    public int getHealth(){
        return health;
    }
    public void setHealth(int health){
        this.health = health;
    }
    public float getSpeed(){
        return speed;
    }
    public void setSpeed(float speed){
        this.speed = speed;
    }
    
    public void addBombs(String type){
        this.bombs = new Bomb(type);
    }
    public Bomb getBombs(){
        return this.bombs;
    }
    
        public String getBomb(){
        return this.bombs.type;
    }
    
    public void putBomb(){
        System.out.println("Command");
        System.out.println(this.name + " padejo bomba");
    }
    

    

}
