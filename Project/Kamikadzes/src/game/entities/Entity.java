/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.entities;

import game.Game;
import game.Handler;
import game.entities.creatures.Memento.Memento;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author Mantvydas
 */
public abstract class Entity {
    
    protected Handler handler;
    protected float x, y;
    protected int width, height;
    protected Rectangle bounds;
    
    protected final boolean hero;
    protected String name;
    protected String state;
    
    public Entity(String name, Handler handler, float x, float y, int width, int height, boolean hero){
        this.handler = handler;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.hero = hero;
        this.name = name;
        this.state = "Created";
        bounds = new Rectangle(0,0, width, height);
        
    }
    
    public boolean isHero()
    {
        return hero;
    }
       
        public void restoreEntityState(Memento restoreState){
        restoreState.getState(this);
        System.out.println("restore state: " + this.getEntityState());
    }
    
    public abstract void tick();
    public abstract void render(Graphics g);
    
    public float getX(){
        return x;
    }
    public void setX(float x){
        this.x = x;
    }
    public float getY(){
        return y;
    }
    public void setY(float y){
        this.y = y;
    }
    public int getWidth(){
        return width;
    }
    public void setWidth(int width){
        this.width = width;
    }
    public int getHeight(){
        return height;
    }
    public void setHeight(int height){
        this.height = height;
    }
        
    public String getName(){
        return name;
    }
        public void setName(String name){
        this.name = name;
    }
    public String getEntityState(){
        return state;
    }
        public void setEntityState(String state){
        System.out.println("set state: " + state);
        this.state = state;
    }
        public Memento saveEntityState(){
        System.out.println("saved state: " + state);
        return new Memento(state, this);
    }

}
