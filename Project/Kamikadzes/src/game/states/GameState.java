/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.states;


import game.Handler;
import game.entities.creatures.*;
import game.entities.creatures.Enemies.*;
import game.entities.creatures.levels.*;
import game.entities.creatures.playerSkins.*;
import game.entities.factories.*;
import game.gfx.Assets;
import game.observer.Achievements;
import game.strategy.*;
import game.tiles.Physics;
import game.tiles.Tile;
import game.worlds.World;
import java.awt.Graphics;

/**
 *
 * @author Mantvydas
 */
public class GameState extends State{

    private Player player;
    private Creature slowZombie;
    private Creature fastZombie;
    private Creature rangerZombie;
    private World world;
    private IEnemyFactory factory = new EnemyFactory();
    private Creature sc;
    private Creature dc;
    
    
    
    public GameState(Handler handler){
        super(handler);
        world = new World(handler, "res/worlds/world1.txt");
        handler.setWorld(world);
        
        //Skin
        IPlayerSkin player1 = new RedSkin(null);
        player = new Player(player1.draw(), handler, 100, 100, true);
        
        //Factory
        slowZombie = factory.createEnemy("SlowZombie", handler, 65, 200);
        fastZombie = factory.createEnemy("FastZombie", handler, 100, 65); 
        EnemiesFacade enemyPoints = new EnemiesFacade();
        enemyPoints.killFastZombie();
        enemyPoints.killSlowZombie();
//        slowZombie = new SlowZombie(handler, 65, 200);
//        fastZombie = new FastZombie(handler, 200, 64);


        //Abstract factory
        AbstractEnemyFactory enemyFactor = new LevelFactory1();           
        slowZombie = enemyFactor.createMelee(handler, 65, 200);
        rangerZombie = enemyFactor.createRange(handler, 65, 200); 

        System.out.println("1) " + System.identityHashCode(fastZombie));
        sc = fastZombie.shallowCopy(); //ShallowCopy
        sc.setX(800); //ShallowCopy test
        System.out.println("2) " + System.identityHashCode(sc));
        dc = fastZombie.deepCopy();  //DeepCopy
        dc.setX(500); //DeepCopy test
        System.out.println("3) " + System.identityHashCode(dc));
        
        //Strategy
        rangerZombie.attackList.add(new Slash()); 
        //rangerZombie.attackList.remove(0);
    }   
    
    @Override
    public void tick() {
        world.tick();
        player.tick();
        rangerZombie.tick();
        //rangerZombie.attack(); //strategy
        fastZombie.tick();
        sc.tick();
        dc.tick();
        //fastZombie.attack(); //strategy
    }

    @Override
    public void render(Graphics g) {
        world.render(g);
        player.render(g);
        rangerZombie.render(g);
        fastZombie.render(g);
        sc.render(g);
        dc.render(g);
        
    }
    
}
