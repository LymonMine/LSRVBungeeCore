package ru.lymonmine.lsrvbungeecore.listener;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ServerPing;
import net.md_5.bungee.api.event.PreLoginEvent;
import net.md_5.bungee.api.event.ProxyPingEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import ru.lymonmine.lsrvbungeecore.database.SQLMaintenance;
import ru.lymonmine.lsrvbungeecore.main;

import java.util.UUID;

public class motdListener implements Listener {
    private static SQLMaintenance db;

    @EventHandler
    public void onPreLoginEvent(PreLoginEvent e) throws Exception {
        db = new SQLMaintenance();
        if (db.getEnabled() == 1 && !db.isPlayerContains(e.getConnection().getName())) {
            String kick_message = main.instanse.config.getString("visual.motd.maintenance.kick-message");
            kick_message = ChatColor.translateAlternateColorCodes('&', kick_message);

            e.getConnection().disconnect(kick_message);
        }
    }

    @EventHandler
    public void onPing(ProxyPingEvent e) throws Exception {
        db = new SQLMaintenance();
        ServerPing ping = e.getResponse();
        ServerPing.Players player = ping.getPlayers();

        if (db.getEnabled() == 0) {
            normalMOTD(ping, player);
        } else {
            maintenanceMOTD(ping, player);
        }
        e.setResponse(ping);
    }

    private void normalMOTD(ServerPing ping, ServerPing.Players player) {
        String playerInfo = main.instanse.config.getString("visual.motd.playerinfo");
        playerInfo = ChatColor.translateAlternateColorCodes('&', playerInfo);

        player.setSample(new ServerPing.PlayerInfo[]{new ServerPing.PlayerInfo(playerInfo, UUID.randomUUID())});

        String header = main.instanse.config.getString("visual.motd.header");
        String footer = main.instanse.config.getString("visual.motd.footer");

        header = ChatColor.translateAlternateColorCodes('&', header);
        footer = ChatColor.translateAlternateColorCodes('&', footer);
        ping.setDescription(header + "\n" + footer);
    }

    private void maintenanceMOTD(ServerPing ping, ServerPing.Players player) {
        String playerInfo = main.instanse.config.getString("visual.motd.maintenance.playerinfo");
        playerInfo = ChatColor.translateAlternateColorCodes('&', playerInfo);

        player.setSample(new ServerPing.PlayerInfo[]{new ServerPing.PlayerInfo(playerInfo, UUID.randomUUID())});

        String header = main.instanse.config.getString("visual.motd.maintenance.header");
        String footer = main.instanse.config.getString("visual.motd.maintenance.footer");

        header = ChatColor.translateAlternateColorCodes('&', header);
        footer = ChatColor.translateAlternateColorCodes('&', footer);

        ping.setDescription(header + "\n" + footer);
    }
}
