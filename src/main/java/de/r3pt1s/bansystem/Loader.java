package de.r3pt1s.bansystem;

import alemiz.stargate.StarGate;
import alemiz.stargate.codec.ProtocolCodec;
import de.r3pt1s.bansystem.network.*;
import dev.waterdog.waterdogpe.plugin.Plugin;

public class Loader extends Plugin {

    @Override
    public void onEnable() {
        ProtocolCodec protocolCodec = StarGate.getInstance().getServer().getProtocolCodec();
        protocolCodec.registerPacket(BanSystemPackets.BANS_SYNC_PACKET, BansSyncPacket.class);
        protocolCodec.registerPacket(BanSystemPackets.MUTES_SYNC_PACKET, MutesSyncPacket.class);
        protocolCodec.registerPacket(BanSystemPackets.PLAYER_KICK_PACKET, PlayerKickPacket.class);
        protocolCodec.registerPacket(BanSystemPackets.NOTIFY_PACKET, NotifyPacket.class);
        this.getLogger().info("§bRegistered 3 packets for StarGate §8(§cBansSyncPacket§8, §cMutesSyncPacket§8, §cPlayerKickPacket§8)");
    }
}