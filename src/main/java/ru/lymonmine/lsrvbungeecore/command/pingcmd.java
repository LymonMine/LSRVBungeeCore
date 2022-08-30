package ru.lymonmine.lsrvbungeecore.command;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import ru.lymonmine.lsrvbungeecore.main;
import ru.lymonmine.lsrvbungeecore.util.luckpermsapi;


public class pingcmd extends Command {

    public pingcmd() {
        super("ping");
    }

    @Override
    public void execute(CommandSender commandSender, String[] args) {
        if (commandSender instanceof ProxiedPlayer) {

            ProxiedPlayer player = (ProxiedPlayer) commandSender;

            int ping = player.getPing();
            String pingmsg = main.instanse.config.getString("ping");

            try {
                pingmsg = pingmsg.replace("{prefix}", luckpermsapi.getPrefix(player.getDisplayName()));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            pingmsg = pingmsg.replace("{nick}", player.getDisplayName());
            pingmsg = pingmsg.replace("{ping}", "" + ping);
            pingmsg = ChatColor.translateAlternateColorCodes('&', pingmsg);

            player.sendMessage(new TextComponent(pingmsg));
        }
    }
}
