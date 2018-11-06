/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.states;


import game.Handler;
import game.entities.builders.ZombieBuilder;
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
import singletones.MySingletone;

/**
 *
 * @author Mantvydas
 */
public class GameState extends State{

    private Player player;
    private Creature slowZombie;
    private Creature slowZombieTest, slowZombieTest2,slowZombieTest3;
    private Creature fastZombie;
    private Creature rangerZombie;
    private World world;
    private IEnemyFactory factory = new EnemyFactory();
    private Creature sc;
    private Creature dc;
    
    AbstractEnemyFactory enemyFactor ;
            
    private long lastTimer, cooldown = 800, timer = cooldown;
    
    
    public GameState(Handler handler){
        super(handler);
        world = new World(handler, "res/worlds/world1.txt");
        handler.setWorld(world);
        
        //Skin
        IPlayerSkin player1 = new RedSkin(null);
        player = new Player("Bombermenas", player1.draw(), handler, 100, 100, true);
        
        //Factory
        slowZombie = factory.createEnemy("SlowZombie", handler, 0, 0, "SimpleBomb");
        fastZombie = factory.createEnemy("FastZombie", handler, 100, 65, "SimpleBomb"); 
        EnemiesFacade enemyPoints = new EnemiesFacade();
        enemyPoints.killFastZombie();
        enemyPoints.killSlowZombie();
//        slowZombie = new SlowZombie(handler, 65, 200);
//        fastZombie = new FastZombie(handler, 200, 64);


        //Abstract factory
        enemyFactor = new LevelFactory1();           
//        slowZombie = enemyFactor.createMelee(handler, 0, 0);
        rangerZombie = enemyFactor.createRange(handler, 65, 200); 

        System.out.println("1) Original Fast zombie: " + System.identityHashCode(fastZombie) + ", Zombie bomb: " + System.identityHashCode(fastZombie.getBombs()));
        sc = fastZombie.shallowCopy(); //ShallowCopy
        sc.setX(800); //ShallowCopy test
        System.out.println("2) Fast zombie shallow copy: " + System.identityHashCode(sc) + ", Zombie bomb: " + System.identityHashCode(sc.getBombs()));
        dc = fastZombie.deepCopy();  //DeepCopy
        dc.setX(500); //DeepCopy test
        System.out.println("3) Fast zombie deep copy: " + System.identityHashCode(dc) + ", Zombie bomb: " + System.identityHashCode(dc.getBombs()));
        
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
        checkAlgorithms();
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
        if(slowZombieTest != null)
            slowZombieTest.render(g);
        if(slowZombieTest2 != null)
            slowZombieTest2.render(g);
        if(slowZombieTest3 != null)
            slowZombieTest3.render(g);
        
    }
    
      private void checkAlgorithms()
    {
        MySingletone points = MySingletone.getInstance();
        timer += System.currentTimeMillis() - lastTimer;
        lastTimer = System.currentTimeMillis();
        if(timer < cooldown)
            return;
        
        if(handler.getKeyManager().a1)
        {
           points.activity(100);
           System.out.println("Singletone");
           System.out.println(points.getPoints()); 
        }     
        else if(handler.getKeyManager().a2)
        {
          slowZombieTest = enemyFactor.createMelee(handler, 65, 200);  
          System.out.println("Factory new zombie");
        }  
        else if(handler.getKeyManager().a3)
        {
            enemyFactor = new LevelFactory2();   
            slowZombieTest2 = enemyFactor.createRange(handler, 0, 0); 
            System.out.println("Abstract factory new zombie");
        }
        else if(handler.getKeyManager().a4)
        {
            fastZombie.attack();
            System.out.println("Strategy");
        }
        else if(handler.getKeyManager().a5)
        {
            System.out.println("Observer");
        }
        else if(handler.getKeyManager().a6)
        {
            ZombieBuilder builder = new ZombieBuilder();
            slowZombieTest3 = builder.addSlow().buildEnemy();
            System.out.println("Builder");
        }
        else if(handler.getKeyManager().a7)
        {
            System.out.println("Prototype");
        }
        else if(handler.getKeyManager().a8)
        {
            System.out.println("Decorator");
        }
        else
            return;
        
        timer = 0;
    }
}
