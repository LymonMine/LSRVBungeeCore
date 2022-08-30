package ru.lymonmine.lsrvbungeecore;

import net.md_5.bungee.api.plugin.*;

import java.nio.file.*;

import net.md_5.bungee.config.*;
import ru.lymonmine.lsrvbungeecore.command.*;
import ru.lymonmine.lsrvbungeecore.listener.*;
import ru.lymonmine.lsrvbungeecore.util.maintenanceUtil;

import java.io.*;

public class main extends Plugin {
    public Configuration config;
    public File f;
    public static main instanse;


    public void onEnable() {
        //Конфиг
        getDataFolder().mkdirs();
        f = new File(getDataFolder(), "config.yml");
        if (!f.exists()) {
            try {
                Files.copy(this.getResourceAsStream("config.yml"), f.toPath(), new CopyOption[0]);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        try {
            config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(f);
        } catch (IOException ex2) {
            ex2.printStackTrace();
        }
        //Конфиг

        instanse = this;
        int pluginId = 16299;
        Metrics metrics = new Metrics(this, pluginId);
        try {
            maintenanceUtil.kickOnlinePlayers();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        //Команды, листенеры
        getProxy().getPluginManager().registerCommand(this, new pingcmd());
        getProxy().getPluginManager().registerCommand(this, new onlinecmd());
        getProxy().getPluginManager().registerCommand(this, new alertcmd());
        getProxy().getPluginManager().registerCommand(this, new servercmd());
        getProxy().getPluginManager().registerCommand(this, new findcmd());
        getProxy().getPluginManager().registerCommand(this, new msgcmd());
        getProxy().getPluginManager().registerCommand(this, new hubcmd());
        getProxy().getPluginManager().registerListener(this, new JLListener());
        getProxy().getPluginManager().registerListener(this, new motdListener());
        getProxy().getPluginManager().registerCommand(this, new mntcmd());
        //Команды, листенеры
    }
}




