package ru.lymonmine.lsrvbungeecore.util;


import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import ru.lymonmine.lsrvbungeecore.database.SQLMaintenance;
import ru.lymonmine.lsrvbungeecore.main;

import java.sql.SQLException;
import java.util.concurrent.TimeUnit;


public class maintenanceUtil {
    private static SQLMaintenance db;

    public static void kickOnlinePlayers() throws Exception {
        ProxyServer.getInstance().getScheduler().schedule(main.instanse, new Runnable() {
            @Override
            public void run() {
                try {
                    db = new SQLMaintenance();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                for (final ProxiedPlayer p : ProxyServer.getInstance().getPlayers()) {
                    try {
                        if (!db.isPlayerContains(p.getDisplayName()) && db.getEnabled() == 1) {
                            String kick_message = main.instanse.config.getString("visual.motd.maintenance.kick-message");
                            kick_message = ChatColor.translateAlternateColorCodes('&', kick_message);
                            p.disconnect(kick_message);

                        }
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }

            }
        }, 1, 15, TimeUnit.SECONDS);
    }

    public static String add(String[] args){
        try {
            db = new SQLMaintenance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if (!db.isPlayerContains(args[1])) {
            db.addPlayerMaintenance(args[1]);
            String add_mess = main.instanse.config.getString("lang.maintenance.add");
            add_mess = add_mess.replace("{playername}", args[1]);
            add_mess = ChatColor.translateAlternateColorCodes('&', add_mess);
            return add_mess;
        } else {
            String add_fail_mess = main.instanse.config.getString("lang.maintenance.add-fail");
            add_fail_mess = add_fail_mess.replace("{playername}", args[1]);
            add_fail_mess = ChatColor.translateAlternateColorCodes('&', add_fail_mess);
            return add_fail_mess;
        }
    }
    public static String remove(String[] args){
        try {
            db = new SQLMaintenance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if (db.isPlayerContains(args[1])) {
            db.removePlayerMaintenance(args[1]);
            String remove_mess = main.instanse.config.getString("lang.maintenance.remove");
            remove_mess = remove_mess.replace("{playername}", args[1]);
            remove_mess = ChatColor.translateAlternateColorCodes('&', remove_mess);
            return remove_mess;
        } else {
            String remove_fail_mess = main.instanse.config.getString("lang.maintenance.remove-fail");
            remove_fail_mess = remove_fail_mess.replace("{playername}", args[1]);
            remove_fail_mess = ChatColor.translateAlternateColorCodes('&', remove_fail_mess);
            return remove_fail_mess;
        }
    }
}

