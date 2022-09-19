package com.tomvosdev.fountains.fountain;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.FallingBlock;

import java.util.ArrayList;
import java.util.List;

public class Fountain {

    Location location;

    double data1, data2, data3, steps;

    List<FallingBlock> blocks;

    boolean on;

    public Fountain(Location location, double steps){

        this.location = location;
        this.data1 = 5;
        this.data2 = 5;
        this.data3 = 5;
        this.steps = steps;

        blocks = new ArrayList<>();

        on = false;

    }

    public void updateData1(double newData){

        data1 = newData;

    }
    public void updateData2(double newData){

        data2 = newData;

    }
    public void updateData3(double newData){

        data3 = newData;

    }

    public double getData1() {
        return data1;
    }

    public double getData2() {
        return data2;
    }

    public double getData3() {
        return data3;
    }
}
