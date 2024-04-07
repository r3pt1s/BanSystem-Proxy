package de.r3pt1s.bansystem.network;

import alemiz.stargate.handler.StarGatePacketHandler;
import alemiz.stargate.protocol.StarGatePacket;
import alemiz.stargate.protocol.types.PacketHelper;
import dev.waterdog.waterdogpe.ProxyServer;
import dev.waterdog.waterdogpe.player.ProxiedPlayer;
import io.netty.buffer.ByteBuf;

public class PlayerKickPacket extends StarGatePacket {

    private String player;
    private String reason;

    @Override
    public void encodePayload(ByteBuf byteBuf) {
        PacketHelper.writeString(byteBuf, player);
        PacketHelper.writeString(byteBuf, reason);
    }

    @Override
    public void decodePayload(ByteBuf byteBuf) {
        player = PacketHelper.readString(byteBuf);
        reason = PacketHelper.readString(byteBuf);
    }

    @Override
    public byte getPacketId() {
        return BanSystemPackets.PLAYER_KICK_PACKET;
    }

    @Override
    public boolean handle(StarGatePacketHandler handler) {
        ProxiedPlayer proxiedPlayer = ProxyServer.getInstance().getPlayer(player);
        if (proxiedPlayer != null) proxiedPlayer.disconnect(this.reason);
        return true;
    }
}
