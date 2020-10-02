package net.tererun.plugin.trans.trans;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Calendar;

public final class Trans extends JavaPlugin {

    public static String string = "[" + ChatColor.GREEN + "Trans" + ChatColor.WHITE + "]: ";
    public static FileConfiguration config;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        config = getConfig();
        getCommand("tl").setExecutor(new Command());
    }

}
