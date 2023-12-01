package org.p3rsonal1ty.p3rsogb.math;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.p3rsonal1ty.p3rsogb.P3rsoGB;

import java.util.Random;

public class LocationGoldBlock {
    public void cord(){
        int min = 500;
        int max = 1750;
        max-=min;
        int quadrant = new Random().nextInt(4);
        switch (quadrant) {
            case 0: // Positive X and Z
                P3rsoGB.GoldBlockx = new Random().nextInt(max) + min;
                P3rsoGB.GoldBlockz = new Random().nextInt(max) + min;
                break;
            case 1: // Negative X and Z
                P3rsoGB.GoldBlockx = -1*(new Random().nextInt(max) + min);
                P3rsoGB.GoldBlockz = -1*(new Random().nextInt(max) + min);
                break;
            case 2: // Negative X and Positive Z
                P3rsoGB.GoldBlockx = -1*(new Random().nextInt(max) + min);
                P3rsoGB.GoldBlockz = new Random().nextInt(max) + min;
                break;
            default: // Positive X and Negative Z
                P3rsoGB.GoldBlockx = new Random().nextInt(max) + min;
                P3rsoGB.GoldBlockz = -1*(new Random().nextInt(max) + min);
                break;
        }
        World world = Bukkit.getWorld("world");
        P3rsoGB.GoldBlocky=cordy(P3rsoGB.GoldBlockx,P3rsoGB.GoldBlockz,world);
    }
    public static int cordy(int x,int z,World world){
        Block b = world.getHighestBlockAt(x,z);
        Location loca = new Location(world,x,b.getY(),z);
        Block b1 = loca.getBlock();
        int r = b.getY();
        if (b1.getType() !=Material.WATER) {
            r = b.getY()+1;
        }
        else{
            while(b1.getType()==Material.WATER){
                r=b1.getY()-1;
                Location location = new Location(world,x,r,z);
                b1 = location.getBlock();
            }
            r=b1.getY()+1;
        }
        return r;
    }
}
