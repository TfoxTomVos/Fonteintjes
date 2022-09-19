package com.tomvosdev.fountains.commands.fountains;

import com.tomvosdev.fountains.fountain.JetFountain;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TestFountainCommand implements CommandExecutor {

    static boolean isTesting = false;
    JetFountain testFountain;
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) return false;

        if(!isTesting){

            testFountain = new JetFountain(((Player) sender).getLocation(), 20);
            testFountain.updateData1(3);
            testFountain.updateData2(5);
            testFountain.updateData3(3);
            testFountain.start();

            isTesting = true;

        }else{

            testFountain.stop();
            isTesting = false;

        }

        return true;
    }
}
