/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.worlds;

import game.Game;
import game.Handler;
import game.tiles.Tile;
import game.utils.Utils;
import java.awt.Graphics;

/**
 *
 * @author Mantvydas
 */
public class World {
    
    private Handler handler;
    private int width, height;
    private int spawnX, spawnY;
    private int[][] tiles;
    
    public World(Handler handler, String path){
        this.handler = handler;
        loadWorld(path);
        
    }
    
    public void tick(){
        
    }
    
    public void render(Graphics g){
        int xStart = 0;
        int xEnd = width;
        int yStart = 0;
        int yEnd = height;
        
        
        for(int y = yStart; y < yEnd; y++){
            for(int x = xStart; x < xEnd; x++){
                getTile(x,y).render(g, x * Tile.TILEWIDTH, y * Tile.TILEHEIGHT);
            }
        }
    }
    public Tile getTile(int x, int y){
        if(x < 0 || y < 0 || x >= width || y >= height)
            return Tile.grassTile; // To prevent errors
        
        Tile t = Tile.tiles[tiles[x][y]];
        if(t == null)
            return Tile.grassTile;
        return t;
    }
    
    private void loadWorld(String path){
        String file = Utils.loadFileAsString(path);
        String[] tokens = file.split("\\s+");
        width = Utils.parseInt(tokens[0]);
        height = Utils.parseInt(tokens[1]);
        spawnX = Utils.parseInt(tokens[2]);
        spawnY = Utils.parseInt(tokens[3]);
        tiles = new int[width][height];
        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                tiles[x][y] = Utils.parseInt(tokens[(x + y * width) + 4]);
            }
        }
    }
    
}
