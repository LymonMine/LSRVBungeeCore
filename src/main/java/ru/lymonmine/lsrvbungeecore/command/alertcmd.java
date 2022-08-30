package ru.lymonmine.lsrvbungeecore.command;

import java.util.Iterator;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import ru.lymonmine.lsrvbungeecore.main;

public class alertcmd extends Command {
    public alertcmd() {

        super("alert");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!sender.hasPermission("lsrvbungeecore.alert")) {
            sender.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', main.instanse.config.getString("lang.noperm")
                    .replace("{plugin-prefix}", main.instanse.config.getString("lang.prefix")))));
        } else {
            if (args.length == 0) {
                sender.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', main.instanse.config.getString("alert.usage")
                        .replace("{plugin-prefix}", main.instanse.config.getString("lang.prefix")))));
            } else {
                StringBuilder msg = new StringBuilder();
                String[] var4 = args;
                int var5 = args.length;

                for (int var6 = 0; var6 < var5; ++var6) {
                    String arg = var4[var6];
                    msg.append(arg + " ");
                }

                Iterator var8 = main.instanse.getProxy().getPlayers().iterator();

                while (var8.hasNext()) {
                    ProxiedPlayer players = (ProxiedPlayer) var8.next();

                    String mess = main.instanse.config.getString("alert.mess");
                    mess = ChatColor.translateAlternateColorCodes('&', mess);
                    mess = mess.replace("{message}", ChatColor.translateAlternateColorCodes('&', msg.toString()));

                    players.sendMessage(new TextComponent(mess));
                }
            }

        }
    }
}

