package Multiplayer;

import com.mysql.jdbc.authentication.MysqlClearPasswordPlugin;
import game.Game;
import game.entities.creatures.PlayerMP;
import game.entities.creatures.playerSkins.BlueSkin;
import game.entities.creatures.playerSkins.IPlayerSkin;
import game.entities.creatures.playerSkins.RedSkin;

import javax.imageio.IIOException;
import java.io.IOException;
import java.net.*;

public class GameClient extends Thread{

    private InetAddress ipAdress;
    private DatagramSocket socket;
    private Game game;
    private AzureConnectionProxy proxy;

    public GameClient(Game game, String ipadress)
    {
        this.game = game;
        try
        {
            this.socket = new DatagramSocket();
            try {
                this.ipAdress = InetAddress.getByName(ipadress);
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }

        }catch (SocketException e)
        {
            e.printStackTrace();
        }
    }

    public void run()
    {
        while (true)
        {
            byte[] data = new byte[1024];
            DatagramPacket packet = new DatagramPacket(data, data.length);
            try{
                socket.receive(packet);
            }catch (IOException e)
            {
                e.printStackTrace();
            }
            this.parsePacket(packet.getData(), packet.getAddress(), packet.getPort());
//            String message = new String(packet.getData());
//            System.out.println("SERVER > " + message);
        }
    }
    private void parsePacket(byte[] data, InetAddress address, int port)
    {
        String message = new String(data).trim();
        Packet.PacketTypes type = Packet.lookupPacket(message.substring(0,2));
        Packet packet;
        switch (type)
        {
            default:
            case LOGIN:
                packet = new Packet00Login(data);
                System.out.println("[" + address.getHostAddress() + ":" + port + "]"+((Packet00Login)packet).getUsername()+ " has joined the game");
                IPlayerSkin player1 = new RedSkin(null);
                PlayerMP player = new PlayerMP(((Packet00Login)packet).getUsername(), player1.draw(), game.handler,500, 100,true, address, port);
                game.gameState.setPlayer(player);
                player.setIndex(game.gameState.getPlayerMPIndex(player.getName()));
                new AzureConnectionProxy(((Packet00Login)packet).getUsername());
                break;
            case DISCONNECT:
                packet = new Packet01Disconnect(data);
                System.out.println("[" + address.getHostAddress() + ":" + port + "] "
                        + ((Packet01Disconnect) packet).getUsername() + " has left the world...");
                game.gameState.removePlayerMP(((Packet01Disconnect) packet).getUsername());
                //game.level.removePlayerMP(((Packet01Disconnect) packet).getUsername());
                break;
            case INVALID:
                break;
            case MOVE:
                packet = new Packet02Move(data);
                handleMove((Packet02Move) packet);
        }
        //Packet.PacketTypes

    }

    private void handleMove(Packet02Move packet) {
        this.game.gameState.movePlayer(packet.getUsername().trim(), packet.getX(), packet.getY());
    }

    public void sendData(byte[] data) {
        DatagramPacket packet = new DatagramPacket(data, data.length, ipAdress, 1331);
        try {
            socket.send(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
