/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.worlds;

import game.Game;
import game.Handler;
import game.tiles.*;
import game.tiles.Tile;
import game.utils.Utils;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Mantvydas
 */
public class World {
    
    private Handler handler;
    private int width, height;
    private int spawnX, spawnY;
  //  private GrassTile grass = new GrassTile(0);
   // private RockTile rock = new RockTile(1);
    
    private int[][] tiles;
    private ArrayList<ArrayList<String>> mapList;
    
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
//        List<List<String>> aaa = Arrays.asList(tiles);
        
        for(int y = yStart; y < yEnd; y++){
            for(int x = xStart; x < xEnd; x++){
                getTile(x,y).render(g, x * Tile.TILEWIDTH, y * Tile.TILEHEIGHT);
            }
        }
    }
    public void render2(Graphics g){
        int xStart = 0;
        int xEnd = width;
        int yStart = 0;
        int yEnd = height;
//        List<List<String>> aaa = Arrays.asList(tiles);
        
        for(int y = yStart; y < yEnd; y++){
            for(int x = xStart; x < xEnd; x++){
                if (tiles[x][y] == 0)
                {
                    GrassTile grass = new GrassTile(0);
                    grass.render(g, x* Tile.TILEWIDTH, y* Tile.TILEHEIGHT);
                }
                else
                {
                    RockTile rock = new RockTile(1);
                    rock.render(g, x* Tile.TILEWIDTH, y* Tile.TILEHEIGHT);
                }
               // getTile(x,y).render(g, x * Tile.TILEWIDTH, y * Tile.TILEHEIGHT);
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
        mapList = new ArrayList<ArrayList<String>>();
        for(int y = 0; y < height; y++){
            ArrayList<String> line = new ArrayList<String>();
            for(int x = 0; x < width; x++){
                tiles[x][y] = Utils.parseInt(tokens[(x + y * width) + 4]);
                line.add(tokens[(x + y * width) + 4]);
            }
            mapList.add(line);
        }
    }
    
}
