package com.tomvosdev.fountains.fountain;

import org.bukkit.Material;
import org.bukkit.entity.FallingBlock;
import org.bukkit.scheduler.BukkitRunnable;

public class FountainPlayTask extends BukkitRunnable {

    int maxTime = 8;
    int remainingTime = 8;
    double currentStep = 0;

    Fountain fountain;

    double data1,data2, data3;

    public FountainPlayTask(Fountain fountain, double data1, double data2, double data3) {

        this.fountain = fountain;
        this.data1 = data1;
        this.data2 = data2;
        this.data3 = data3;

    }


    @Override
    public void run() {

        for(int i = 0; i < fountain.steps/maxTime; i++){

            double distance = (Math.sqrt(Math.pow(data1, 2) + Math.pow(data2, 2)));
            double xMod = (data1/fountain.steps)*currentStep;
            double zMod = (data2/fountain.steps)*currentStep;
            double yMod = (-data3/Math.pow(-distance,2) * Math.pow((-distance/2)+(distance/fountain.steps*currentStep), 2)) * 4 + data3;

            FallingBlock block = fountain.location.getWorld().spawnFallingBlock(fountain.location.clone().add(xMod, yMod, zMod), Material.BLUE_CONCRETE.createBlockData());
            block.setGravity(false);
            fountain.blocks.add(block);

            currentStep++;
        }

        remainingTime--;

        if(remainingTime <= 0){

            this.cancel();

        }

    }
}

