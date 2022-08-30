package ru.lymonmine.lsrvbungeecore.command;


import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import ru.lymonmine.lsrvbungeecore.main;
import ru.lymonmine.lsrvbungeecore.util.datafindUtil;

public class findcmd extends Command {

    public findcmd() {

        super("find");
    }

    @Override
    public void execute(CommandSender commandSender, String[] args) {
        if (commandSender instanceof ProxiedPlayer) {
            ProxiedPlayer player = (ProxiedPlayer) commandSender;
            if (args.length == 0) {

                String usage = main.instanse.config.getString("find.usage");
                usage = usage.replace("{plugin-prefix}", main.instanse.config.getString("lang.prefix"));
                usage = ChatColor.translateAlternateColorCodes('&', usage);

                player.sendMessage(new TextComponent(usage));
            } else {
                try {

                    String find = datafindUtil.Find(args[0]);

                    player.sendMessage(new TextComponent(find));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

