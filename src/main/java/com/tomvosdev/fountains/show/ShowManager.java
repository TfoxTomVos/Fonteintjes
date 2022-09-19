package com.tomvosdev.fountains.show;

import com.tomvosdev.fountains.Fountains;
import com.tomvosdev.fountains.fountain.FountainManager;
import com.tomvosdev.fountains.fountain.JetFountain;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class ShowManager {

    public static HashMap<String, Show> shows = new HashMap<>();
    public static HashMap<String, String> selectedShow = new HashMap<>();
    public static Fountains plugin = Fountains.getPlugin(Fountains.class);

    public static void loadAllShows() {

        if(plugin.getConfig().getConfigurationSection("shows")==null) plugin.getConfig().set("shows.placeholder", " "); plugin.saveConfig();

        ConfigurationSection showsInConfig = plugin.getConfig().getConfigurationSection("shows");
        for (String name : showsInConfig.getKeys(false)) {
            if(!name.equalsIgnoreCase("placeholder")) loadShow(name);

        }
    }

    public static void loadShow(String name) {
        ConfigurationSection shows = plugin.getConfig().getConfigurationSection("shows");
        Location showLocation = new Location(plugin.getServer().getWorld(shows.getString(name+".world")), shows.getDouble(name+".x"), shows.getDouble(name+".y"), shows.getDouble(name+".z"));

        HashMap<Integer, JetFountain> jetFountainHashMap = new HashMap<>();
        if(shows.getConfigurationSection("jet")!=null) {

            for (String index : shows.getConfigurationSection("jet").getKeys(false)) {

                ConfigurationSection fountainSection = shows.getConfigurationSection(index);
                Location fountainLocation = showLocation.add(fountainSection.getDouble("x"), fountainSection.getDouble("y"), fountainSection.getDouble("z"));
                JetFountain fountain = new JetFountain(fountainLocation, fountainSection.getDouble("steps"));

                jetFountainHashMap.put(Integer.parseInt(index), fountain);

            }
        }
        Show show = new Show(name, showLocation, jetFountainHashMap);

        ShowManager.shows.put(name, show);

        FountainManager.loadAllFountains(name);

    }
}
