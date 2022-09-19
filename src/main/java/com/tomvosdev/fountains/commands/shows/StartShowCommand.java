package com.tomvosdev.fountains.commands.shows;

import com.tomvosdev.fountains.Fountains;
import com.tomvosdev.fountains.show.Show;
import com.tomvosdev.fountains.show.ShowManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.File;

public class StartShowCommand implements CommandExecutor {

    Fountains plugin;

    public StartShowCommand(Fountains plugin) {

        this.plugin = plugin;

    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!(sender instanceof Player)) return false;
        if(!ShowManager.selectedShow.containsKey(((Player) sender).getUniqueId().toString())) return false;

        if(args.length < 2) return false;
        if(!(new File(plugin.getDataFolder(), args[0]+".mid").exists())) return false;
        if(!(new File(plugin.getDataFolder(), args[0]+".mid").isFile())) return false;

        String showName = ShowManager.selectedShow.get(((Player) sender).getUniqueId().toString());
        Show show = ShowManager.shows.get(showName);

        show.playShow(args[0], Integer.parseInt(args[1]));

        return true;
    }
}
