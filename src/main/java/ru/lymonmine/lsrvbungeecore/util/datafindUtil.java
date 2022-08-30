package ru.lymonmine.lsrvbungeecore.util;


import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import ru.lymonmine.lsrvbungeecore.database.SQLFind;
import ru.lymonmine.lsrvbungeecore.main;

public class datafindUtil {
    private static SQLFind db;

    private static String getDataLeave(String PlayerName) throws Exception {
        db = new SQLFind();
        return db.getData(PlayerName);
    }

    private static boolean getProject(String PlayerName) throws Exception {
        db = new SQLFind();
        if (db.getData(PlayerName) == null) {
            return false;
        } else {
            return true;
        }
    }

    private static boolean getFindOnline(String PlayerName) throws Exception {
        db = new SQLFind();
        ProxiedPlayer player = ProxyServer.getInstance().getPlayer(PlayerName);
        if (player == null || player.getServer() == null) {
            return false;
        } else {
            return true;
        }
    }

    private static String getPDL(String PlayerName) throws Exception {
        db = new SQLFind();
        if (!getProject(PlayerName)) {

            String playernotfound = main.instanse.config.getString("find.playernotfound");
            playernotfound = ChatColor.translateAlternateColorCodes('&', playernotfound);

            return playernotfound;
        } else {
            String data = getDataLeave(PlayerName);

            String leavetime = main.instanse.config.getString("find.leavetime");
            leavetime = leavetime.replace("{nick}", PlayerName);
            leavetime = leavetime.replace("{data}", data);
            leavetime = leavetime.replace("{prefix}", luckpermsapi.getPrefix(PlayerName));
            leavetime = ChatColor.translateAlternateColorCodes('&', leavetime);

            return leavetime;
        }
    }

    public static String Find(String PlayerName) throws Exception {
        db = new SQLFind();
        if (getFindOnline(PlayerName)) {
            ProxiedPlayer player = ProxyServer.getInstance().getPlayer(PlayerName);
            String server = player.getServer().getInfo().getName();

            String online = main.instanse.config.getString("find.online");
            online = ChatColor.translateAlternateColorCodes('&', online);
            online = online.replace("{nick}", PlayerName);
            online = online.replace("{server}", server);
            online = online.replace("{prefix}", luckpermsapi.getPrefix(PlayerName));

            return online;
        } else {
            return getPDL(PlayerName);

        }
    }
}







