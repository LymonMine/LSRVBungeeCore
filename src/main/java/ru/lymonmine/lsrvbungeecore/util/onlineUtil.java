package ru.lymonmine.lsrvbungeecore.util;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import ru.lymonmine.lsrvbungeecore.main;

public class onlineUtil {
    private static String OnlineServer(String server) {
        if (ProxyServer.getInstance().getServers().containsKey(server)) {
            int online = ProxyServer.getInstance().getServerInfo(server).getPlayers().size();
            String s = main.instanse.config.getString("online.onlineserver");
            s = s.replace("{plugin-prefix}", main.instanse.config.getString("lang.prefix"));
            s = s.replace("{server}", server);
            s = s.replace("{online}", "" + online);
            s = ChatColor.translateAlternateColorCodes('&', s);
            return s;
        } else {
            String s = main.instanse.config.getString("online.servetnotfound");
            s = s.replace("{plugin-prefix}", main.instanse.config.getString("lang.prefix"));
            s = ChatColor.translateAlternateColorCodes('&', s);
            return s;

        }
    }

    private static String onlineproject() {
        int online = ProxyServer.getInstance().getOnlineCount();
        String s = main.instanse.config.getString("online.online");
        s = s.replace("{plugin-prefix}", main.instanse.config.getString("lang.prefix"));
        s = ChatColor.translateAlternateColorCodes('&', s);
        s = s.replace("{online}", "" + online);
        return s;

    }

    public static String Online(String[] args) {
        if (args.length == 0) {
            return onlineproject();
        } else {
            return OnlineServer(args[0]);

        }
    }
}



