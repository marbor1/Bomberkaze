/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.gfx;

import java.awt.image.BufferedImage;

/**
 *
 * @author Mantvydas
 */
public class Assets {
    
    private static final int width = 32, height = 32;
    
    public static BufferedImage player, player2, dirt, grass, stone, zombie, hyper, exhausted;
    
    public static void init(){
        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/sheet.png"));
        
        player = sheet.crop(0, 0, width, height);
        player2 = sheet.crop(width, height, width, height);
        dirt = sheet.crop(width, 0, width, height);
        grass = sheet.crop(width * 2, 0, width, height);
        stone = sheet.crop(0, height, width, height);
        zombie = sheet.crop(width * 2, height, width, height);
        hyper = sheet.crop(0, height * 2, width, height);
        exhausted = sheet.crop(width, height * 2, width, height);
    }
    
    
}
