package com.tomvosdev.fountains.fountain;

import com.tomvosdev.fountains.Fountains;
import com.tomvosdev.fountains.show.ShowManager;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;

public class FountainManager {

    static Fountains plugin = Fountains.getPlugin(Fountains.class);

    public static void loadAllFountains(String showName){

        ConfigurationSection show = plugin.getConfig().getConfigurationSection("shows."+showName);

        if(show.get("jet") == null) show.set("jet.placeholder", " ");plugin.saveConfig();
        for (String key : show.getConfigurationSection("jet").getKeys(false)) {

            if(!(key.equalsIgnoreCase("placeholder"))) loadJetFountain(key, showName);

        }

    }

    public static void loadJetFountain(String key, String showName){

        ConfigurationSection show = plugin.getConfig().getConfigurationSection("shows."+showName);

        double x = show.getDouble("jet."+key+".x");
        double y =  show.getDouble("jet."+key+".y");
        double z =  show.getDouble("jet."+key+".z");
        double steps =  show.getDouble("jet."+key+".steps");
        Location loc = ShowManager.shows.get(showName).getCenter().clone().add(x, y, z);

        JetFountain fountain = new JetFountain(loc, steps);
        ShowManager.shows.get(showName).getJetFountains().putIfAbsent(Integer.parseInt(key), fountain);

    }

}
