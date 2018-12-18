package Multiplayer;

import Interpreter.DeleteExpression;
import Interpreter.InsertExpression;
import Interpreter.SQLExpression;
import Interpreter.UserExpression;
import com.sun.xml.internal.bind.v2.runtime.reflect.Lister;
import game.Game;
import game.entities.creatures.Player;
import game.entities.creatures.PlayerMP;
import game.entities.creatures.playerSkins.BlueSkin;
import game.entities.creatures.playerSkins.IPlayerSkin;
import game.entities.creatures.playerSkins.RedSkin;

import javax.imageio.IIOException;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

public class GameServer extends Thread{
    private DatagramSocket socket;
    private Game game;
    private List<PlayerMP> connectedPlayers = new ArrayList<PlayerMP>();


    public GameServer(Game game)
    {
        this.game = game;
        try
        {
            this.socket = new DatagramSocket(1331);
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
//            System.out.println("CLIENT ["+packet.getAddress().getHostAddress()+":"+packet.getPort()+"] > " + message);
//            if(message.trim().equalsIgnoreCase("ping"))
//            {
//                System.out.println("returning pong");
//                sendData("pong".getBytes(), packet.getAddress(), packet.getPort());
//            }
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

                System.out.println("[" + address.getHostAddress() + ":" + port + "]"+((Packet00Login)packet).getUsername()+ " has connected");
                IPlayerSkin player1 = new BlueSkin(null);
                PlayerMP player = new PlayerMP(((Packet00Login)packet).getUsername(), player1.draw(), game.handler,100, 100,true, address, port);
                this.addConnection(player, ((Packet00Login)packet)); //When someone connects to the server, add connection to player
                new AzureConnectionProxy(((Packet00Login)packet).getUsername());

                SQLExpression user1 = new UserExpression(((Packet00Login)packet).getUsername());
                SQLExpression query = new InsertExpression(user1);
                try {
                    System.out.print(query.execute());
                } catch (Exception e) {
                    e.printStackTrace();
                }


                break;
            case DISCONNECT:
                packet = new Packet01Disconnect(data);
                System.out.println("[" + address.getHostAddress() + ":" + port + "] "
                        + ((Packet01Disconnect) packet).getUsername() + " has left...");
                this.removeConnection((Packet01Disconnect) packet);

                user1 = new UserExpression(((Packet00Login)packet).getUsername());
                query = new DeleteExpression(user1);
                try {
                    System.out.print(query.execute());
                } catch (Exception e) {
                    e.printStackTrace();
                }
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
        if (getPlayerMP(packet.getUsername()) != null) {
            int index = getPlayerMPIndex(packet.getUsername());
            this.connectedPlayers.get(index).x = packet.getX();
            this.connectedPlayers.get(index).y = packet.getY();
            packet.writeData(this);
        }
    }

    public void removeConnection(Packet01Disconnect packet) {
        this.connectedPlayers.remove(getPlayerMPIndex(packet.getUsername()));
        packet.writeData(this);
    }

    public PlayerMP getPlayerMP(String username) {
        for (PlayerMP player : this.connectedPlayers) {
            if (player.getName().equals(username)) {
                return player;
            }
        }
        return null;
    }

    public int getPlayerMPIndex(String username) {
        int index = 0;
        for (PlayerMP player : this.connectedPlayers) {
            if (player.getName().equals(username)) {
                break;
            }
            index++;
        }
        return index;
    }


    public void addConnection(PlayerMP player, Packet00Login packet) {
        boolean alreadyconnected = false;
        for(PlayerMP p: this.connectedPlayers)
        {
            if(player.getName().equalsIgnoreCase(p.getName()))
            {
                if(p.ipAdress == null)
                {
                    p.ipAdress = player.ipAdress;
                }
                if(p.port == -1)
                {
                    p.port = player.port;
                }
                alreadyconnected = true;
            }
            else {
                sendData(packet.getData(), p.ipAdress, p.port);
                packet = new Packet00Login(p.getName(), p.x, p.y);
                sendData(packet.getData(), player.ipAdress, player.port);
            }
        }
        if(!alreadyconnected) {
            this.connectedPlayers.add(player);
            //game.gameState.setPlayer(player);
        }
    }

    public void sendData(byte[] data, InetAddress ipAdress, int port) {
        DatagramPacket packet = new DatagramPacket(data, data.length, ipAdress, port);
        try {
            socket.send(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendDataToAllClients(byte[] data)
    {
        for (PlayerMP p : connectedPlayers)
        {
            sendData(data, p.ipAdress, p.port);
        }
    }
}
