package ru.lymonmine.lsrvbungeecore.util;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import ru.lymonmine.lsrvbungeecore.main;

import java.util.concurrent.TimeUnit;

public class tabUtil {

    private static String getString(String string) {
        int i = 0;
        String object2 = "";
        for (final String a : main.instanse.config.getStringList(string)) {
            if (++i > 1) {
                object2 = String.valueOf(object2) + "\n";
            }
            object2 = String.valueOf(object2) + a;
        }
        return object2;
    }

    public static void tabset(ProxiedPlayer p) {

        ProxyServer.getInstance().getScheduler().schedule(main.instanse, new Runnable() {
            @Override
            public void run() {
                String header = getString("visual.tab.header");
                String footer = getString("visual.tab.footer");
                String server;

                header = ChatColor.translateAlternateColorCodes('&', header);
                footer = ChatColor.translateAlternateColorCodes('&', footer);

                header = header.replace("%online%", ProxyServer.getInstance().getOnlineCount() + "");
                if (!(p.getServer().getInfo().getName() == null)) {
                    server = p.getServer().getInfo().getName();
                } else {
                    server = "Loading";
                }
                footer = footer.replace("%server%", server);
                p.setTabHeader(new ComponentBuilder(header).create(), new ComponentBuilder(footer).create());
            }

        }, 3, 1, TimeUnit.SECONDS);
    }
}



            



