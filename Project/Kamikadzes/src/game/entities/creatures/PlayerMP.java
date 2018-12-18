package game.entities.creatures;

import game.Handler;

import java.awt.image.BufferedImage;
import java.net.InetAddress;

public class PlayerMP extends Player{
    public InetAddress ipAdress;
    public int port;

    public PlayerMP(String name, BufferedImage skin, Handler handler, float x, float y, boolean hero, InetAddress ipAdress, int port)
    {
        super(name, skin, handler, x,y, hero);
        this.ipAdress = ipAdress;
        this.port = port;
    }

    @Override
    public void tick() {
        super.tick();
    }
}
