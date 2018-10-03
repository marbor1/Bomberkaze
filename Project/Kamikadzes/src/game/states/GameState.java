/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.states;


import game.Handler;
import game.entities.creatures.Player;
import game.gfx.Assets;
import game.tiles.Tile;
import game.worlds.World;
import java.awt.Graphics;

/**
 *
 * @author Mantvydas
 */
public class GameState extends State{

    private Player player;
    private World world;

    public GameState(Handler handler){
        super(handler);
        world = new World(handler, "res/worlds/world1.txt");
        handler.setWorld(world);
        player = new Player(handler, 100, 100);
        
    }
    
    @Override
    public void tick() {
        world.tick();
        player.tick();
    }

    @Override
    public void render(Graphics g) {
        world.render(g);
        player.render(g);
    }
    
}
