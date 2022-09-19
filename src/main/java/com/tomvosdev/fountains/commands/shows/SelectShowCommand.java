package com.tomvosdev.fountains.commands.shows;

import com.tomvosdev.fountains.show.ShowManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SelectShowCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) return false;

        if(!ShowManager.shows.containsKey(args[0].toLowerCase())) return false;

        ShowManager.selectedShow.put(((Player) sender).getUniqueId().toString(), args[0].toLowerCase());
        sender.sendMessage("You have selected show " + args[0].toUpperCase());

        return true;
    }
}
