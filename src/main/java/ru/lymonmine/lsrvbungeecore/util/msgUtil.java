package ru.lymonmine.lsrvbungeecore.util;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import ru.lymonmine.lsrvbungeecore.main;


public class msgUtil {
    public static void SendPrivateMessage(ProxiedPlayer sender, String nickrecipient, String message) throws Exception {
        ProxiedPlayer player = ProxyServer.getInstance().getPlayer(nickrecipient);
        if (player == null || player.getServer() == null) {
            String noonline = main.instanse.config.getString("message.noonline");
            noonline = noonline.replace("{plugin-prefix}", main.instanse.config.getString("lang.prefix"));
            noonline = ChatColor.translateAlternateColorCodes('&', noonline);
            sender.sendMessage(new TextComponent(noonline));
        } else {
            String my = main.instanse.config.getString("message.my");
            my = ChatColor.translateAlternateColorCodes('&', my);
            my = my.replace("{prefix}", luckpermsapi.getPrefix(nickrecipient));
            my = my.replace("{nick}", nickrecipient);
            my = my.replace("{message}", message);
            String from = main.instanse.config.getString("message.from");
            from = ChatColor.translateAlternateColorCodes('&', from);
            from = from.replace("{prefix}", luckpermsapi.getPrefix(sender.getDisplayName()));
            from = from.replace("{nick}", sender.getDisplayName());
            from = from.replace("{message}", message);
            sender.sendMessage(new TextComponent(my));
            player.sendMessage(new TextComponent(from));
        }

    }
}
