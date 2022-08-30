package ru.lymonmine.lsrvbungeecore.command;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import ru.lymonmine.lsrvbungeecore.main;

public class hubcmd extends Command {
    public hubcmd() {
        super("hub", null, "lobby");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof ProxiedPlayer) {
            String server = main.instanse.config.getString("hub");
            ((ProxiedPlayer) sender).connect(ProxyServer.getInstance().getServerInfo(server));
            String mess = main.instanse.config.getString("server.sendserver").replace("{server}", server);
            sender.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&',mess
                    .replace("{plugin-prefix}", main.instanse.config.getString("lang.prefix")))));
        }
    }
}
