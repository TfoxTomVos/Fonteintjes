package com.tomvosdev.fountains.fountain;

import com.tomvosdev.fountains.Fountains;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.FallingBlock;
import org.bukkit.scheduler.BukkitRunnable;

public class JetFountain extends Fountain{

    public JetFountain(Location location, double steps){
        super(location,steps);
    }


    public void start() {

        blocks.clear();

        if(on) return;

        on = true;

        new FountainPlayTask(this, getData1(), getData2(), getData3()).runTaskTimer(Fountains.getPlugin(Fountains.class), 0, 1);

    }

    public void stop(){

        if(!on) return;

        on = false;

        new BukkitRunnable(){

            int maxTime = 8;
            int remainingTime = 8;
            int totalBlocks = blocks.size();

            @Override
            public void run() {


                for(int i = 0; i < totalBlocks/maxTime; i++){

                    blocks.get(0).remove();
                    blocks.remove(0);

                }

                remainingTime--;

                if(remainingTime <= 0){

                    this.cancel();

                }

            }
        }.runTaskTimer(Fountains.getPlugin(Fountains.class), 0, 1);


    }

}
