package ru.lymonmine.lsrvbungeecore.command;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import ru.lymonmine.lsrvbungeecore.util.onlineUtil;

public class onlinecmd extends Command {

    public onlinecmd() {
        super("online");
    }

    @Override
    public void execute(CommandSender commandSender, String[] args) {
        if (commandSender instanceof ProxiedPlayer) {
            ProxiedPlayer player = (ProxiedPlayer) commandSender;
            player.sendMessage(new TextComponent(onlineUtil.Online(args)));
        }
    }
}
