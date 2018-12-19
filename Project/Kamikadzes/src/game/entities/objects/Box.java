/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.entities.objects;

import game.Handler;
import game.gfx.Assets;
import game.tiles.Tile;
import java.awt.Graphics;

/**
 *
 * @author Marius
 */
public class Box extends StaticObject{

    public Box(Handler handler, float x, float y) {
        super("Box", handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT, false);
    }


    @Override
    public void tick() {
        
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.dirt, (int) x, (int) y, width, height, null);
    }

    @Override
    public Iterator createIterator() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
