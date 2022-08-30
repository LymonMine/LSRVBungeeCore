package ru.lymonmine.lsrvbungeecore.command;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;

import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import ru.lymonmine.lsrvbungeecore.main;
import ru.lymonmine.lsrvbungeecore.util.msgUtil;

public class msgcmd extends Command {
    public msgcmd() {

        super("msg");
    }

    @Override
    public void execute(CommandSender commandSender, String[] args) {
        if (commandSender instanceof ProxiedPlayer) {
            ProxiedPlayer player = (ProxiedPlayer) commandSender;
            if (!(args.length >= 2)) {
                String use = main.instanse.config.getString("message.usage");
                use = use.replace("{plugin-prefix}", main.instanse.config.getString("lang.prefix"));
                use = ChatColor.translateAlternateColorCodes('&', use);
                player.sendMessage(new TextComponent(use));
            } else {
                String rec = args[0];
                StringBuilder msgq = new StringBuilder();
                String[] var4 = args;
                int var5 = args.length;

                for (int var6 = 1; var6 < var5; ++var6) {
                    String arg = var4[var6];
                    msgq.append(arg + " ");
                }

                try {
                    msgUtil.SendPrivateMessage(player, rec, msgq.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

