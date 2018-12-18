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
import Multiplayer.AzureConnection;
import adapters.CreatureJSONAdapter;
import adapters.JSONParser;
import game.entities.builders.Director;
import game.entities.objects.Box;
import game.levelHandler.Level1Handler;
import game.levelHandler.Level2Handler;
import game.levelHandler.Level3Handler;
import game.levelHandler.LevelHandler;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import static java.lang.Math.round;
import java.util.Random;
import singletones.MySingletone;
import visitor.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.NumberFormat;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
/**
 *
 * @author Mantvydas
 */
public class GameState extends State{

    private Player player;
    private Box box;
    private Creature slowZombie;
    private Creature slowZombieTest, slowZombieTest2,slowZombieTest3;
    private Creature fastZombie;
    private Creature rangerZombie;
    private World world, bigWorld;
    private IFactory factory = new EnemyFactory();
    private Creature sc;
    private Creature dc;
    private AzureConnection con;
        private int level;
    private String path;
    private LevelHandler levelHandler;
    
    AbstractEnemyFactory enemyFactor ;
            
    private long lastTimer, cooldown = 800, timer = cooldown;
    
    
    public GameState(Handler handler){
        super(handler);
        long startTime = System.nanoTime();
        world = new World(handler, "res/worlds/world1.txt");
        //world = new World(handler,"res/worlds/bigWorld2.txt");
        handler.setWorld(world);
       // handler.setWorld(bigWorld);
        long endTime   = System.nanoTime();
        long totalTime = endTime - startTime;
        System.out.println((double)totalTime/1000000000);
        //Skin
        IPlayerSkin player1 = new BlueSkin(null);
        levelHandler = getChainOfLevelHandlers();
        player1.accaptVisitor(new Spawn());
        player1.accaptVisitor(new Death());
        player = new Player("Bombermenas", player1.draw(), handler, 100, 60, true);
    }   
    
    @Override
    public void tick() {
        world.tick();
        player.tick();
        
       /* rangerZombie.tick();
        //rangerZombie.attack(); //strategy
        fastZombie.tick();
        sc.tick();
        dc.tick();*/
        checkAlgorithms();
                if(slowZombieTest != null)
            slowZombieTest.tick();
        if(slowZombieTest2 != null)
            slowZombieTest2.tick();
        if(slowZombieTest3 != null)
            slowZombieTest3.tick();
        if(sc != null)
            sc.tick();
        if(dc != null)
            dc.tick();
        if(box != null)
            box.tick();
        //fastZombie.attack(); //strategy
    }

    @Override
    public void render(Graphics g) {
      /*  Runtime runtime = Runtime.getRuntime();
        NumberFormat format = NumberFormat.getInstance();
        StringBuilder sb = new StringBuilder();
        long freeMemory = runtime.freeMemory();
        long startTime = System.nanoTime();*/
        world.render(g);
      /*  long freeMemory2 = runtime.freeMemory();
        long endTime   = System.nanoTime();
        long totalTime = endTime - startTime;
        System.out.println("flyWeight:" + (double)totalTime/1000000000);
        System.out.println("how much memory have been used: " + format.format((freeMemory - freeMemory2) / 1024)+ "mb");
       // System.out.println("flyWeight memory:" + (beforeUsedMem - afterUsedMem) +" " + (beforeUsedMem - afterUsedMem)/1000000 + "Mb");*/
   
        player.render(g);
       /* rangerZombie.render(g);
        fastZombie.render(g);
        sc.render(g);
        dc.render(g);*/
        if(slowZombieTest != null)
            slowZombieTest.render(g);
        if(slowZombieTest2 != null)
            slowZombieTest2.render(g);
        if(slowZombieTest3 != null)
            slowZombieTest3.render(g);
        if(sc != null)
            sc.render(g);
        if(dc != null)
            dc.render(g);
        if(box != null)
            box.render(g);
        
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
           System.out.println("Singletone demo");
           System.out.println("Taskai pries pridejima " + points.getPoints());
           points.activity(100);
           System.out.println("Taskai po " + points.getPoints()); 
           System.out.println("============================================");
        }     
        else if(handler.getKeyManager().a2)
        {
            System.out.println("Abstract factory demo");
            enemyFactor = new LevelFactory2();   
            slowZombieTest = enemyFactor.createRange(handler, 300, 200); 
            slowZombieTest.upgrade();
            System.out.println("Sukurtas creature: " + slowZombieTest.getName() + " " + slowZombieTest.getX() + " " + slowZombieTest.getY());
            slowZombieTest2 = enemyFactor.createMelee(handler, 65, 200);  
            slowZombieTest2.upgrade();
            System.out.println("Sukurtas creature: " + slowZombieTest2.getName() + " " + slowZombieTest2.getX() + " " + slowZombieTest2.getY());
            System.out.println("============================================");
            player.upgrade();
        }  
        else if(handler.getKeyManager().a3)
        {
            System.out.println("Factory demo");
            ObjectFactory objFact = new ObjectFactory();
            box = (Box) objFact.createEnemy("Box", handler, 100, 400, "");
            System.out.println("Sukurtas object: " + box.getName() + " " + box.getX() + " " + box.getY());
            System.out.println("============================================");
        }
        else if(handler.getKeyManager().a4)
        {
            System.out.println("Strategy");
            System.out.println("Atlieka atakas po sukurimo " + slowZombieTest.getName() + " " + slowZombieTest.getX() + " " + slowZombieTest.getY());
            slowZombieTest.attack();
            System.out.println("Atlieka atakas po papildomos atakos pridejimo " + slowZombieTest.getName() + " " + slowZombieTest.getX() + " " + slowZombieTest.getY());
            slowZombieTest.attackList.add(new Slash());
            slowZombieTest.attack();
            System.out.println("Atlieka atakas po pirmos atakos pasalinimo " + slowZombieTest.getName() + " " + slowZombieTest.getX() + " " + slowZombieTest.getY());
            slowZombieTest.attackList.remove(0);
            slowZombieTest.attack();
            System.out.println("============================================");
        }
        else if(handler.getKeyManager().a5)
        {
            System.out.println("Builder demo");
            
            Director dir = new Director();
            slowZombieTest3 = dir.getFastZombie(handler, 200, 200, "SimpleBomb");
            System.out.println("Sukurtas creature: " + slowZombieTest3.getName() + " " + slowZombieTest3.getX() + " " + slowZombieTest3.getY() + " " + slowZombieTest3.getBomb());
           // slowZombieTest3 = builder.addSlow().buildEnemy();
           
            // slowZombieTest3 = factory.createEnemy("SlowZombie", handler, 250, 250, "SimpleBomb");
        
            
            System.out.println("============================================");
        }
        else if(handler.getKeyManager().a6)
        {
            System.out.println("Prototype demo");
            Random r = new Random();
            System.out.println("1) Original zombie: " + slowZombieTest3.getName() + " " + System.identityHashCode(slowZombieTest) + ", Zombie bomb: " + System.identityHashCode(slowZombieTest3.getBombs()));
            sc = slowZombieTest3.shallowCopy(); //ShallowCopy
            sc.setX(r.nextInt(800)); //ShallowCopy test
            System.out.println("2) zombie shallow copy: " + slowZombieTest3.getName() + " " + System.identityHashCode(sc) + ", Zombie bomb: " + System.identityHashCode(sc.getBombs()));
            dc = slowZombieTest3.deepCopy();  //DeepCopy
            dc.setX(r.nextInt(800)); //DeepCopy test        
            System.out.println("2) zombie deep copy: " + slowZombieTest3.getName() + " " + System.identityHashCode(dc) + ", Zombie bomb: " + System.identityHashCode(dc.getBombs()));
            System.out.println("============================================");
            
        }
        else if(handler.getKeyManager().a7)
        {
            System.out.println("Decorator and visitor demo");
            IPlayerSkin player1 = new RedSkin(null);
            player1.accaptVisitor(new Spawn());
            player1.accaptVisitor(new Death());
            player = new Player("Bombermenas", player1.draw(), handler, player.getX(), player.getY(), true);            
            System.out.println("============================================");
        }
        else if(handler.getKeyManager().a8)
        {
            System.out.println("Adapter demo");
            JSONParser jsonobj = new CreatureJSONAdapter(player);

            jsonobj.getandsetHealth();
            jsonobj.getandsetSpeed();
            
            System.out.println(jsonobj.showJSONobject());
            System.out.println("============================================");
            
        }
        else
            return;
        
        timer = 0;
    }
     private LevelHandler getChainOfLevelHandlers(){

      LevelHandler level1Handler = new Level1Handler();
      LevelHandler level2Handler = new Level2Handler();
      LevelHandler level3Handler = new Level3Handler();

     
      level1Handler.setNextHandler(level2Handler);
      level2Handler.setNextHandler(level3Handler);
      return level1Handler;	
   }
         public void setWorld(World world)
    {
        this.world = world;
    }
        public int getLevel()
    {
        return this.level;
    }
}
