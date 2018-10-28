/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.states;


import game.Handler;
import game.entities.creatures.Creature;
import game.entities.creatures.Player;
import game.gfx.Assets;
import game.tiles.Tile;
import game.worlds.World;
import java.awt.Graphics;
import game.entities.creatures.Enemies.SlowZombie;
import game.entities.creatures.Enemies.FastZombie;
import game.entities.creatures.levels.LevelFactory1;
import game.entities.creatures.playerSkins.*;
import game.entities.creatures.playerSkins.IPlayerSkin;
import game.entities.factories.AbstractEnemyFactory;
import game.entities.factories.IEnemyFactory;
import game.entities.factories.EnemyFactory;
/**
 *
 * @author Mantvydas
 */
public class GameState extends State{

    private Player player;
    private Creature slowZombie;
    private Creature fastZombie;
    private World world;
    private IEnemyFactory factory = new EnemyFactory();
    
    
    public GameState(Handler handler){
        super(handler);
        world = new World(handler, "res/worlds/world1.txt");
        handler.setWorld(world);
        
        
        IPlayerSkin player1 = new RedSkin(null);
        player = new Player(player1.draw(), handler, 100, 100);
        
        
//         player1 = player1.draw(handler);
//        player = new Player(handler, 100, 100);
//        slowZombie = factory.createEnemy("SlowZombie", handler, 65, 200);
        fastZombie = factory.createEnemy("FastZombie", handler, 100, 65);
//        slowZombie = new SlowZombie(handler, 65, 200);
//        fastZombie = new FastZombie(handler, 200, 64);

        AbstractEnemyFactory enemyFactor = new LevelFactory1();
        slowZombie = enemyFactor.createMelee(handler, 65, 200);
        
        
    }
    
    @Override
    public void tick() {
        world.tick();
        player.tick();
        slowZombie.tick();
        fastZombie.tick();
    }

    @Override
    public void render(Graphics g) {
        world.render(g);
        player.render(g);
        slowZombie.render(g);
        fastZombie.render(g);
    }
    
}
