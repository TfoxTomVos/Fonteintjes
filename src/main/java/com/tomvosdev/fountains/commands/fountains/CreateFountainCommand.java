package com.tomvosdev.fountains.commands.fountains;

import com.tomvosdev.fountains.Fountains;
import com.tomvosdev.fountains.fountain.FountainManager;
import com.tomvosdev.fountains.fountain.JetFountain;
import com.tomvosdev.fountains.show.Show;
import com.tomvosdev.fountains.show.ShowManager;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

public class CreateFountainCommand implements CommandExecutor {

    Fountains plugin;

    public CreateFountainCommand(Fountains plugin) {

        this.plugin = plugin;

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) return false;

        if(args.length < 2) return false;

        String showName = ShowManager.selectedShow.get(((Player) sender).getUniqueId().toString());
        Show show = ShowManager.shows.get(showName);

        if(args[0].equalsIgnoreCase("jet")){

            double x = (args[2]!=null) ? Double.parseDouble(args[2]) : 0;
            double y = (args[3]!=null) ? Double.parseDouble(args[3]) : 0;
            double z = (args[4]!=null) ? Double.parseDouble(args[4]) : 0;
            double steps = (args[5]!=null) ? Double.parseDouble(args[5]) : 20;

            ConfigurationSection jets = plugin.getConfig().getConfigurationSection("shows."+showName+".jet");
            jets.set(args[1]+".x" , x);
            jets.set(args[1]+".y" , y);
            jets.set(args[1]+".z" , z);
            jets.set(args[1]+".steps" , steps);

            plugin.saveConfig();
            sender.sendMessage("You made a JET FOUNTAIN!");
            FountainManager.loadJetFountain(args[1], showName);

        }

        return true;

    }
}
