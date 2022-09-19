package com.tomvosdev.fountains.commands.shows;

import com.tomvosdev.fountains.Fountains;
import com.tomvosdev.fountains.show.ShowManager;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

public class CreateShowCommand implements CommandExecutor {

    Fountains plugin;

    public CreateShowCommand(Fountains plugin){

        this.plugin = plugin;

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) return false;

        if (args.length < 1) return false;

        ConfigurationSection shows = plugin.getConfig().getConfigurationSection("shows");
        if(shows.getKeys(false).contains(args[0].toLowerCase())){

            sender.sendMessage(ChatColor.RED + "SHOW ALREADY EXISTS");

        }

        Player player = (Player) sender;

        shows.set(args[0]+".x", player.getLocation().getBlockX());
        shows.set(args[0]+".y", player.getLocation().getBlockY()+1);
        shows.set(args[0]+".z", player.getLocation().getBlockZ());
        shows.set(args[0]+".world", player.getLocation().getWorld().getName());
        plugin.saveConfig();

        ShowManager.loadShow(args[0]);

        return true;
    }
}
