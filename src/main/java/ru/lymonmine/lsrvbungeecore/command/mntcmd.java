package ru.lymonmine.lsrvbungeecore.command;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Command;
import ru.lymonmine.lsrvbungeecore.database.SQLMaintenance;
import ru.lymonmine.lsrvbungeecore.main;
import ru.lymonmine.lsrvbungeecore.util.maintenanceUtil;

import java.util.*;

public class mntcmd extends Command {
    private static SQLMaintenance db;

    public mntcmd() {

        super("mnt");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {

        List<String> cmds = new ArrayList<String>();
        cmds.add("add");
        cmds.add("remove");
        cmds.add("on");
        cmds.add("off");

        try {
            db = new SQLMaintenance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (sender.hasPermission("lsrvbungeecore.maintenance")) {
            if (args.length == 0 || !cmds.contains(args[0])) {
                for (String help_mess : main.instanse.config.getStringList("lang.maintenance.help")) {
                    help_mess = help_mess.replace("{plugin-prefix}",
                            main.instanse.config.getString("lang.prefix"));
                    help_mess = ChatColor.translateAlternateColorCodes('&', help_mess);
                    sender.sendMessage(new TextComponent(help_mess));
                }
            } else {
                if (args[0].equals("on")) {
                    String on_mess = main.instanse.config.getString("lang.maintenance.enabled");
                    on_mess = on_mess.replace("{plugin-prefix}", main.instanse.config.getString("lang.prefix"));
                    on_mess = ChatColor.translateAlternateColorCodes('&', on_mess);
                    sender.sendMessage(new TextComponent(on_mess));
                    db.saveEnable();
                }
                if (args[0].equals("off")) {
                    String off_mess = main.instanse.config.getString("lang.maintenance.disabled");
                    off_mess = off_mess.replace("{plugin-prefix}", main.instanse.config.getString("lang.prefix"));
                    off_mess = ChatColor.translateAlternateColorCodes('&', off_mess);
                    sender.sendMessage(new TextComponent(off_mess));
                    db.saveDisable();
                }
                if (args.length == 2 && args[0].equals("add")) {
                    sender.sendMessage(new TextComponent(maintenanceUtil.add(args)));
                } else if (args[0].equals("add")) {
                    String add_usage_mess = main.instanse.config.getString("lang.maintenance.usage-add");
                    add_usage_mess = ChatColor.translateAlternateColorCodes('&', add_usage_mess);
                    sender.sendMessage(new TextComponent(add_usage_mess));
                }
                if (args.length == 2 && args[0].equals("remove")) {
                    sender.sendMessage(new TextComponent(maintenanceUtil.remove(args)));
                } else if (args[0].equals("remove")) {
                    String remove_usage_mess = main.instanse.config.getString("lang.maintenance.usage-remove");
                    remove_usage_mess = ChatColor.translateAlternateColorCodes('&', remove_usage_mess);
                    sender.sendMessage(new TextComponent(remove_usage_mess));
                }
            }


        } else {
            sender.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', main.instanse.config.getString("lang.noperm")
                    .replace("{plugin-prefix}", main.instanse.config.getString("lang.prefix")))));
        }

    }
}
