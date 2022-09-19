package com.tomvosdev.fountains;

import com.tomvosdev.fountains.commands.fountains.CreateFountainCommand;
import com.tomvosdev.fountains.commands.shows.CreateShowCommand;
import com.tomvosdev.fountains.commands.fountains.TestFountainCommand;
import com.tomvosdev.fountains.commands.shows.StartShowCommand;
import com.tomvosdev.fountains.commands.shows.DebugShowCommand;
import com.tomvosdev.fountains.commands.shows.SelectShowCommand;
import com.tomvosdev.fountains.show.Show;
import com.tomvosdev.fountains.show.ShowManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Fountains extends JavaPlugin {

    @Override
    public void onEnable() {

        saveDefaultConfig();
        getConfig().options().copyDefaults(true);
        saveConfig();
        registerCommands();

        ShowManager.loadAllShows();

    }

    @Override
    public void onDisable() {
        for(Show show : ShowManager.shows.values()){

            show.stopDebugging();
            show.setDebugging(false);

        }
    }

    private void registerCommands() {

        getCommand("createShow").setExecutor(new CreateShowCommand(this));
        getCommand("selectShow").setExecutor(new SelectShowCommand());
        getCommand("debugShow").setExecutor(new DebugShowCommand());
        getCommand("startShow").setExecutor(new StartShowCommand(this));


        getCommand("testFountain").setExecutor(new TestFountainCommand());
        getCommand("createFountain").setExecutor(new CreateFountainCommand(this));

    }

}
