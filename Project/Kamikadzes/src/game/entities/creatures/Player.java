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
import game.entities.creatures.states.IPlayerState;
import game.entities.creatures.states.PlayerWalkingState;
import game.entities.objects.Bomba;
import game.gfx.Assets;
import game.input.Command;
import game.mediator.Mediator;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import singletones.MySingletone;
import visitor.Visitors;

/**
 *
 * @author Mantvydas
 */
public class Player extends Creature implements IPlayerSkin {

    protected BufferedImage skin;
    private Command command;
    private IPlayerState state;
    private int runningTime;
    private long lastAttackTimer, attackCooldown = 800, attackTimer = attackCooldown;

    public Player(String name, BufferedImage skin, Handler handler, float x, float y, boolean hero, Mediator mediator) {
        super(name, handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT, hero, mediator);

        if (skin == null) {
            this.skin = Assets.player;
        }
        this.skin = skin;
        state = IPlayerState.walking;
        bounds.x = 19;
        bounds.y = 24;
        bounds.width = 25;
        bounds.height = 40;
        hero = true;
        runningTime = 0;
    }

    @Override
    public void tick() {
        getInput();
        move();
        MySingletone points = MySingletone.getInstance();
        points.activity((int) yMove);
        points.activity((int) xMove);
        checkAttacks();
//        checkAlgorithms();
        // System.out.println(points.getPoints());

    }

    private void checkAttacks() {
        /*attackTimer += System.currentTimeMillis() - lastAttackTimer;
        lastAttackTimer = System.currentTimeMillis();
        if(attackTimer < attackCooldown)
            return;
         */
        state.checkAttacks(this, handler.getKeyManager());

        /*
        if(handler.getKeyManager().attack)
            handler.getKeyManager().buttonG.execute(this);
        else if(handler.getKeyManager().bomb)
            handler.getKeyManager().buttonF.execute(this);
        else 
            return;
         */
        //attackTimer = 0;
    }

    private void getInput() {
        xMove = 0;
        yMove = 0;

        if (handler.getKeyManager().up) {
            yMove = -speed;
        }
        if (handler.getKeyManager().down) {
            yMove = speed;
        }
        if (handler.getKeyManager().left) {
            xMove = -speed;
        }
        if (handler.getKeyManager().right) {
            xMove = speed;
        }
        state.setState(this, handler.getKeyManager().run);

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
        System.out.println("Command");
        System.out.println(this.name + " atakuoja");
    }

    @Override
    public BufferedImage draw() {
        return null;
    }

    @Override
    public void accaptVisitor(Visitors visitor) {

    }

    @Override
    void upgradeHp() {
        health += 2000;
        System.out.println("Player got 2000hp bonus");
    }

    @Override
    void upgradeSpeed() {
        this.speed += 1;
        System.out.println("Player speed increased by 1");
    }

    @Override
    void upgradeDmg() {
        damage += 500;
        System.out.println("Player deals 500dmg more");
    }

    @Override
    void upgradeRange() {
        System.out.println("Player range increased by 15");
    }

    public void setSkin(BufferedImage skin) {
        this.skin = skin;
    }

    public void setNewState(IPlayerState state) {
        this.state = state;
    }

    public IPlayerState getState() {
        return this.state;
    }

    public void setAttackTimer(long timer) {
        this.attackTimer = timer;
    }

    public void setLastAttackTimer(long timer) {
        this.lastAttackTimer = timer;
    }

    public long getAttackTimer() {
        return this.attackTimer;
    }

    public long getLastAttackTimer() {
        return this.lastAttackTimer;
    }

    public long getAttackCooldown() {
        return this.attackCooldown;
    }

    public int getRunningTime() {
        return this.runningTime;
    }

    public void setRunningTime(int runningTime) {
        this.runningTime = runningTime;
    }

    public Bomba getBomba() {
        return new Bomba(handler, this.x, this.y);
    }

}
