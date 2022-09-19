package com.tomvosdev.fountains.commands.shows;

import com.tomvosdev.fountains.show.Show;
import com.tomvosdev.fountains.show.ShowManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DebugShowCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!(sender instanceof Player)) return false;

        if(!ShowManager.selectedShow.containsKey(((Player) sender).getUniqueId().toString())) return false;

        String showName = ShowManager.selectedShow.get(((Player) sender).getUniqueId().toString());
        Show show = ShowManager.shows.get(showName);
        if(show.isDebugging()){

            show.setDebugging(false);
            show.stopDebugging();
            sender.sendMessage("Debugging " + showName.toUpperCase() + " Off");

        }else{
            show.setDebugging(true);
            show.initDebugging();
            sender.sendMessage("Debugging " + showName.toUpperCase() + " On");
        }
        return true;
    }
}
