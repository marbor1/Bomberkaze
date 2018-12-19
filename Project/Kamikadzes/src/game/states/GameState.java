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
import game.entities.creatures.Memento.Caretaker;
import game.entities.creatures.Memento.Memento;
import game.entities.objects.Bomba;
import game.entities.objects.Box;
import game.entities.objects.Iterator;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import static java.lang.Math.round;
import game.levelHandler.Level1Handler;
import game.levelHandler.Level2Handler;
import game.levelHandler.Level3Handler;
import game.levelHandler.LevelHandler;
import game.mediator.EnemyRadio;
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
public class GameState extends State {

    private Player player;
    private Box box;
    private Creature slowZombie;
    private Creature slowZombieTest, slowZombieTest2, slowZombieTest3;
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
    private EnemyRadio radio;
    private Iterator checkIterator;
    private Bomba bomba;

    AbstractEnemyFactory enemyFactor;

    private long lastTimer, cooldown = 800, timer = cooldown;

    public GameState(Handler handler) {
        super(handler);
        long startTime = System.nanoTime();
        world = new World(handler, "res/worlds/world1.txt");
        //world = new World(handler,"res/worlds/bigWorld2.txt");
        handler.setWorld(world);
        // handler.setWorld(bigWorld);
        long endTime = System.nanoTime();
        long totalTime = endTime - startTime;
        System.out.println((double) totalTime / 1000000000);
        //Skin
        IPlayerSkin player1 = new BlueSkin(null);
        levelHandler = getChainOfLevelHandlers();
        player = new Player("Bombermenas", player1.draw(), handler, 100, 100, true, null);
        radio = new EnemyRadio();
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public int getLevel() {
        return this.level;
    }

    @Override
    public void tick() {
        world.tick();
        player.tick();

        checkAlgorithms();
        if (slowZombieTest != null) {
            slowZombieTest.tick();
            slowZombieTest.searchPlayer(player);
        }
        if (slowZombieTest2 != null) {
            slowZombieTest2.tick();
            slowZombieTest2.searchPlayer(player);
        }
        if (slowZombieTest3 != null) {
            slowZombieTest3.tick();
        }
        if (sc != null) {
            sc.tick();
        }
        if (dc != null) {
            dc.tick();
        }
        if (box != null) {
            box.tick();
        }
        if (bomba != null) {
            bomba.tick();
            if (bomba.getTime() <= 0) {
                while (checkIterator.hasNext()) {
                    Creature n = (Creature) checkIterator.next();
                    bomba.explosionArea((int) n.getX(), (int) n.getY());
                    System.out.println("Name : " + n.getName());
                }
                bomba = null;
            }
        }
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
        if (slowZombieTest != null) {
            slowZombieTest.render(g);
        }
        if (slowZombieTest2 != null) {
            slowZombieTest2.render(g);
        }
        if (slowZombieTest3 != null) {
            slowZombieTest3.render(g);
        }
        if (sc != null) {
            sc.render(g);
        }
        if (dc != null) {
            dc.render(g);
        }
        if (box != null) {
            box.render(g);
        }
        if (bomba != null) {
            bomba.render(g);
        }

    }

    private void checkAlgorithms() {
        MySingletone points = MySingletone.getInstance();
        timer += System.currentTimeMillis() - lastTimer;
        lastTimer = System.currentTimeMillis();
        if (timer < cooldown) {
            return;
        }

        if (handler.getKeyManager().a1) {
            level = 1;
            levelHandler.operate(this);

            System.out.println("\n  Memento TEST! \n");
            System.out.println("\n  init ");
            Caretaker ct = new Caretaker();
            Memento state1 = player.saveEntityState();
            ct.add(state1);

            System.out.println("\n  change 1 ");
            player.setEntityState("123");
            Memento state2 = player.saveEntityState();
            ct.add(state2);
            System.out.println("\n change 2 ");
            player.setEntityState("555");
            System.out.println("Before changes: " + "X: " + player.getX() + " " + "Y: " + player.getY() + " " + "name: " + player.getName() + " " + "state: " + player.getEntityState());
            player.setX(200);
            player.setY(200);
            Memento state3 = player.saveEntityState();
            ct.add(state3);
            System.out.println("After changes: " + "X: " + player.getX() + " " + "Y: " + player.getY() + " " + "name: " + player.getName() + " " + "state: " + player.getEntityState());
            System.out.println("\n  restore 1 ");
            Memento restoreState = ct.get(ct.getSize() - 2);
            player.restoreEntityState(restoreState);
            System.out.println("After restore: " + "X: " + player.getX() + " " + "Y: " + player.getY() + " " + "name: " + player.getName() + " " + "state: " + player.getEntityState());
            System.out.println(player.getEntityState());
            System.out.println("\n restore to after changes ");
            Memento restoreState2 = ct.get(ct.getSize() - 1);
            player.restoreEntityState(restoreState2);
            System.out.println("After restore: " + "X: " + player.getX() + " " + "Y: " + player.getY() + " " + "name: " + player.getName() + " " + "state: " + player.getEntityState());
            System.out.println(player.getEntityState());
            System.out.println("============================================");
        } else if (handler.getKeyManager().a2) {
            level = 2;
            levelHandler.operate(this);
            //levelHandler.operate(this);
            System.out.println("Abstract factory demo");
            enemyFactor = new LevelFactory2();
            slowZombieTest = enemyFactor.createRange(handler, 300, 200, radio);
            slowZombieTest.upgrade();
            System.out.println("Sukurtas creature: " + slowZombieTest.getName() + " " + slowZombieTest.getX() + " " + slowZombieTest.getY());
            slowZombieTest2 = enemyFactor.createMelee(handler, 65, 200, radio);
            slowZombieTest2.upgrade();
            System.out.println("Sukurtas creature: " + slowZombieTest2.getName() + " " + slowZombieTest2.getX() + " " + slowZombieTest2.getY());
            System.out.println("============================================");
            player.upgrade();
        } else if (handler.getKeyManager().a3) {
            level = 3;
            levelHandler.operate(this);
            //levelHandler.operate(this);
            System.out.println("Factory demo");
            ObjectFactory objFact = new ObjectFactory();
            box = (Box) objFact.createEnemy("Box", handler, 100, 400, "");
            System.out.println("Sukurtas object: " + box.getName() + " " + box.getX() + " " + box.getY());
            System.out.println("============================================");
        } else if (handler.getKeyManager().a4) {
            System.out.println("Mediator demo");
            enemyFactor = new LevelFactory2();
            slowZombieTest = enemyFactor.createRange(handler, 300, 200, radio);
            System.out.println("Sukurtas creature: " + slowZombieTest.getName() + " " + slowZombieTest.getX() + " " + slowZombieTest.getY());
            slowZombieTest2 = enemyFactor.createMelee(handler, 65, 200, radio);
            System.out.println("Sukurtas creature: " + slowZombieTest2.getName() + " " + slowZombieTest2.getX() + " " + slowZombieTest2.getY());
            radio.add(slowZombieTest);
            //radio.add(slowZombieTest2);
        } else if (handler.getKeyManager().a5) {
            System.out.println("Builder demo");

            Director dir = new Director();
            slowZombieTest3 = dir.getFastZombie(handler, 200, 200, "SimpleBomb");
            System.out.println("Sukurtas creature: " + slowZombieTest3.getName() + " " + slowZombieTest3.getX() + " " + slowZombieTest3.getY() + " " + slowZombieTest3.getBomb());
            // slowZombieTest3 = builder.addSlow().buildEnemy();

            // slowZombieTest3 = factory.createEnemy("SlowZombie", handler, 250, 250, "SimpleBomb");
            System.out.println("============================================");
        } else if (handler.getKeyManager().a6) {
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

        } else if (handler.getKeyManager().a7) {
            System.out.println("Decorator and visitor demo");
            IPlayerSkin player1 = new RedSkin(null);
            player1.accaptVisitor(new Spawn());
            player1.accaptVisitor(new Death());
            player = new Player("Bombermenas", player1.draw(), handler, player.getX(), player.getY(), true, null);
            System.out.println("============================================");
        } else if (handler.getKeyManager().a8) {
            System.out.println("Adapter demo");
            JSONParser jsonobj = new CreatureJSONAdapter(player);

            jsonobj.getandsetHealth();
            jsonobj.getandsetSpeed();

            System.out.println(jsonobj.showJSONobject());
            System.out.println("============================================");
        } else if (handler.getKeyManager().bomb) {
            if (bomba == null) {
                bomba = player.getBomba();
                checkIterator = bomba.createIterator();
                checkIterator.add(slowZombieTest);
                checkIterator.add(slowZombieTest2);
            }
        } else {
            return;
        }

        timer = 0;
    }

    private LevelHandler getChainOfLevelHandlers() {

        LevelHandler level1Handler = new Level1Handler();
        LevelHandler level2Handler = new Level2Handler();
        LevelHandler level3Handler = new Level3Handler();

        level1Handler.setNextHandler(level2Handler);
        level2Handler.setNextHandler(level3Handler);
        return level1Handler;
    }
}
