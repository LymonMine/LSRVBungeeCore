package ru.lymonmine.lsrvbungeecore.util;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import ru.lymonmine.lsrvbungeecore.main;

public class serverUtil {
    public static String server(ProxiedPlayer player, String[] args) {
        if (ProxyServer.getInstance().getServers().containsKey(args[0])) {
            player.connect(ProxyServer.getInstance().getServerInfo(args[0]));
            return ChatColor.translateAlternateColorCodes('&', main.instanse.config.getString("server.sendserver").replace("{server}", args[0])
                    .replace("{plugin-prefix}", main.instanse.config.getString("lang.prefix")));
        } else {
            return ChatColor.translateAlternateColorCodes('&', main.instanse.config.getString("server.servetnotfound")
                    .replace("{plugin-prefix}", main.instanse.config.getString("lang.prefix")));
        }
    }
}
