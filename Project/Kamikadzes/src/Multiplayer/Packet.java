package Multiplayer;

import com.sun.xml.internal.bind.v2.runtime.reflect.Lister;

public abstract class Packet {

    public static enum PacketTypes {
        INVALID(-1), LOGIN(00), DISCONNECT(01), MOVE(02);

        private int packetId;

        private PacketTypes(int packetid)
        {
            this.packetId = packetid;
        }

        public int getId()
        {
            return packetId;
        }
    }

    public byte packetId;

    public Packet(int packetId)
    {
        this.packetId = (byte) packetId;
    }

    public abstract void writeData(GameClient client);
    public abstract void writeData(GameServer server);
    public abstract byte[] getData();

    public String readData(byte[] data)
    {
        String message = new String(data).trim();
        return  message.substring(2);
    }
    public static PacketTypes lookupPacket(String packetid)
    {
        try{
            return  lookupPacket(Integer.parseInt(packetid));
        }catch (NumberFormatException e)
        {
            return  PacketTypes.INVALID;
        }
    }

    public static PacketTypes lookupPacket(int id)
    {
        for (PacketTypes p : PacketTypes.values())
        {
            if (p.getId() == id)
            {
                return  p;
            }
        }
        return PacketTypes.INVALID;
    }


}
