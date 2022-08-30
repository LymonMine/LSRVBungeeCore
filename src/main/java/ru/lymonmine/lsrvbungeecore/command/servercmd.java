package ru.lymonmine.lsrvbungeecore.command;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import ru.lymonmine.lsrvbungeecore.main;
import ru.lymonmine.lsrvbungeecore.util.serverUtil;

public class servercmd extends Command {
    public servercmd() {

        super("server");
    }

    @Override
    public void execute(CommandSender commandSender, String[] args) {
        if (commandSender instanceof ProxiedPlayer) {

            ProxiedPlayer player = (ProxiedPlayer) commandSender;
            if (player.hasPermission("lsrvbungeecore.server")) {
                if (args.length == 0) {
                    player.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', main.instanse.config.getString("server.usage")
                            .replace("{plugin-prefix}", main.instanse.config.getString("lang.prefix")))));
                } else {
                    player.sendMessage(new TextComponent(serverUtil.server(player, args)));
                }
            } else {
                player.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', main.instanse.config.getString("lang.noperm")
                        .replace("{plugin-prefix}", main.instanse.config.getString("lang.prefix")))));
            }
        }
    }
}


