/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.states;


import game.Handler;
import game.entities.creatures.*;
import game.entities.creatures.levels.*;
import game.entities.creatures.playerSkins.*;
import game.entities.factories.*;
import game.gfx.Assets;
import game.observer.Achievements;
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
    
    
    public GameState(Handler handler){
        super(handler);
        world = new World(handler, "res/worlds/world1.txt");
        handler.setWorld(world);
        IPlayerSkin player1 = new RedSkin(null);
        player = new Player(player1.draw(), handler, 100, 100, true);
//        slowZombie = factory.createEnemy("SlowZombie", handler, 65, 200);
        //fastZombie = factory.createEnemy("FastZombie", handler, 100, 65); //buvo sitas
//        slowZombie = new SlowZombie(handler, 65, 200);
//        fastZombie = new FastZombie(handler, 200, 64);

        AbstractEnemyFactory enemyFactor = new LevelFactory1();
        //slowZombie = enemyFactor.createMelee(handler, 65, 200);
      //  rangerZombie = enemyFactor.createRange(handler, 65, 200); //buvo sitas
     //   rangerZombie.attackList.add(new Slash()); //buvo sitas
        //rangerZombie.attackList.remove(0);
        
    }
    
    @Override
    public void tick() {
        world.tick();
        player.tick();
      //  rangerZombie.tick();
      //  rangerZombie.attack();
        //fastZombie.tick();
        //fastZombie.attack();
    }

    @Override
    public void render(Graphics g) {
        world.render(g);
        player.render(g);
       // rangerZombie.render(g);
        //fastZombie.render(g);
    }
    
}
