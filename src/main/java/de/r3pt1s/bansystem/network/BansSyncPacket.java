package de.r3pt1s.bansystem.network;

import alemiz.stargate.StarGate;
import alemiz.stargate.handler.PacketHandler;
import alemiz.stargate.handler.StarGatePacketHandler;
import alemiz.stargate.protocol.StarGatePacket;
import alemiz.stargate.protocol.types.PacketHelper;
import alemiz.stargate.server.ServerSession;
import io.netty.buffer.ByteBuf;

public class BansSyncPacket extends StarGatePacket {

    private String bansJson;

    @Override
    public void encodePayload(ByteBuf byteBuf) {
        PacketHelper.writeString(byteBuf, bansJson);
    }

    @Override
    public void decodePayload(ByteBuf byteBuf) {
        bansJson = PacketHelper.readString(byteBuf);
    }

    @Override
    public byte getPacketId() {
        return BanSystemPackets.BANS_SYNC_PACKET;
    }

    @Override
    public boolean handle(StarGatePacketHandler handler) {
        ServerSession excludingSession = null;
        if (handler instanceof alemiz.stargate.handler.PacketHandler) excludingSession = ((PacketHandler) handler).getSession();
        for (ServerSession serverSession : StarGate.getInstance().getServer().getSessions().values()) {
            if (excludingSession != null && serverSession.getAddress().equals(excludingSession.getAddress())) continue;
            serverSession.sendPacket(this);
        }
        return true;
    }
}
