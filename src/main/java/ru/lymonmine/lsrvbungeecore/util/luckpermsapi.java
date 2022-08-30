package ru.lymonmine.lsrvbungeecore.util;

import java.util.UUID;

import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import ru.lymonmine.lsrvbungeecore.database.SQLFind;

public class luckpermsapi {
    private static SQLFind db;

    public static User getUser(final String username) throws Exception {
        db = new SQLFind();
        if (ProxyServer.getInstance().getPlayer(username) == null) {
            return loadOfflineUser(username);
        } else {
            final LuckPerms luckPerms = LuckPermsProvider.get();
            return luckPerms.getUserManager().getUser(username);
        }
    }

    public static LuckPerms getAPI() {

        return LuckPermsProvider.get();
    }

    private static User loadOfflineUser(String username) throws Exception {
        db = new SQLFind();
        User user = getAPI().getUserManager().loadUser(UUID.fromString(db.getUUID(username)), username).get();
        return user;
    }

    public static boolean hasPermission(final String username, final String permission) throws Exception {
        return getUser(username).getCachedData().getPermissionData().checkPermission(permission).asBoolean();
    }

    public static String getPrefix(final String username) throws Exception {
        if (!(getUser(username).getCachedData().getMetaData().getPrefix() == null)) {
            String prefix = getUser(username).getCachedData().getMetaData().getPrefix();
            return ChatColor.translateAlternateColorCodes('&', prefix);
        } else {
            return "";
        }
    }

    public static String getSuffix(final String username) throws Exception {
        if (!(getUser(username).getCachedData().getMetaData().getSuffix() == null)) {
            String suffix = getUser(username).getCachedData().getMetaData().getSuffix();
            return ChatColor.translateAlternateColorCodes('&', suffix);
        } else {
            return "";
        }
    }
}