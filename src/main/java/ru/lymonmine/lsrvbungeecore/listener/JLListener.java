package ru.lymonmine.lsrvbungeecore.listener;

import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import ru.lymonmine.lsrvbungeecore.database.SQLFind;
import ru.lymonmine.lsrvbungeecore.util.luckpermsapi;
import ru.lymonmine.lsrvbungeecore.util.tabUtil;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class JLListener implements Listener {
    private static SQLFind db;

    @EventHandler
    public void playerDisconnect(PlayerDisconnectEvent e) throws Exception {
        db = new SQLFind();
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat formater = new SimpleDateFormat("dd.MM.yyyy");
        Date date = cal.getTime();
        db.saveData(e.getPlayer().getDisplayName(), formater.format(date));
    }

    @EventHandler
    public void postLoginEvent(PostLoginEvent e) throws Exception {
        tabUtil.tabset(e.getPlayer());
        db = new SQLFind();
        db.saveDataJoin(e.getPlayer().getDisplayName(), luckpermsapi.getAPI().getUserManager().lookupUniqueId(e.getPlayer().getDisplayName())
                .get().toString());

    }
}