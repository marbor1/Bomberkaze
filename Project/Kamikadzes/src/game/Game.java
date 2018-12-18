/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;


import Multiplayer.GameClient;
import Multiplayer.GameServer;
import Multiplayer.Packet00Login;
import Multiplayer.WindowHandler;
import game.display.Display;
import game.entities.creatures.Player;
import game.entities.creatures.PlayerMP;
import game.entities.creatures.playerSkins.BlueSkin;
import game.entities.creatures.playerSkins.IPlayerSkin;
import game.gfx.Assets;
import game.gfx.ImageLoader;
import game.gfx.SpriteSheet;
import game.input.KeyManager;
import game.states.GameState;
import game.states.MenuState;
import game.states.State;

import javax.swing.*;
import java.awt.*;

import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mantvydas
 */
public class Game implements Runnable {
    public static Game game;
    public JFrame frame;
    public WindowHandler windowHandler;

    private Display display;
    public int width, height;
    public String title;

    private boolean running = false;
    private Thread thread;

    private BufferStrategy bs;
    private Graphics g;

    //States
    public State gameState;
    private State menuState;

    //Input
    private KeyManager keyManager;

    //Handler
    public Handler handler;

    public Player player;

    //Network
    public GameServer server;
    public GameClient client;



    public Game(String title, int width, int height){
        game = this;
        this.width = width;
        this.height = height;
        this.title = title;
        keyManager = new KeyManager();
    }

   public void init(){
       client.sendData("ping".getBytes());
       display = new Display(title, width, height);
       display.getFrame().addKeyListener(keyManager);
       Assets.init();

       handler = new Handler(this);

       gameState = new GameState(handler);
       menuState = new MenuState(handler);
       State.setState(gameState);
       windowHandler = new WindowHandler(this);

       IPlayerSkin player1 = new BlueSkin(null);
        player =  new PlayerMP(JOptionPane.showInputDialog(null, "Please enter a username"), player1.draw(), handler,100, 100,true, null, -1);

       Packet00Login loginPacket = new Packet00Login(player.getName(), player.x, player.y);
       if(server != null)
       {
           server.addConnection((PlayerMP)player, loginPacket);
       }
       gameState.setPlayer(player);
        loginPacket.writeData(client);
   }



   private void tick(){
      keyManager.tick();
      if(State.getState() != null){
          State.getState().tick();
      }
   }

   private void render(){
       bs = display.getCanvas().getBufferStrategy();
       if(bs == null){
           display.getCanvas().createBufferStrategy(3);
           return;
       }
       g = bs.getDrawGraphics();
       //Clear Screen
       g.clearRect(0, 0, width, height);
       //Drawing
       if(State.getState() != null){
          State.getState().render(g);
        }
       //End of Drawing
       bs.show();
       g.dispose();
   }

   public void run(){
        //new Thread(this).start();

       init();

       int fps = 60;
       double timePerTick = 1000000000 / fps;
       double delta = 0;
       long now;
       long lastTime = System.nanoTime();
       long timer = 0;
       int ticks = 0;



       while(running){
           now = System.nanoTime();
           delta += (now - lastTime) / timePerTick;
           timer += now - lastTime;
           lastTime = now;

           if(delta >= 1){
                tick();
                render();
                ticks++;
                delta--;
           }

           if(timer >= 1000000000){
               //System.out.println("Ticks and Frames: " + ticks);
               ticks = 0;
               timer = 0;
           }
       }

       stop();
   }

   public KeyManager getKeyManager(){
       return keyManager;
   }

   public synchronized void start(){
       if(running)
           return;
       running = true;
       thread = new Thread(this);
       if(JOptionPane.showConfirmDialog(null, "Do you want to run the server?") == 0)
       {
           server = new GameServer(this);
           server.start();
       }

       client = new GameClient(this,"localhost");
       client.start();
       thread.start();
   }
   
   public synchronized void stop(){
       if(!running)
           return;
       running = false;
        try {
            thread.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
   
   public int getWidth(){
       return width;
   }
      public int getHeight(){
       return height;
   }
}
