package org.p3rsonal1ty.p3rsogb.math;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.p3rsonal1ty.p3rsogb.P3rsoGB;

import java.util.Random;

public class ItemsToGoldBlock {
    int[] invclear = new int[54];
    public void Inv(){
        for(int i=0;i<54;i++){
            ItemStack air = new ItemStack(Material.AIR);
            P3rsoGB.GoldBlockInventrory.setItem(i,air);
        }
        int itemsInGoldBlock=new Random().nextInt(6)+8;
        for(int i =0;i<itemsInGoldBlock;i++){
            getIndex();
            int Check = new Random().nextInt(1001);
            for(int j = 0;j<P3rsoGB.NumItems;j++){
                if(P3rsoGB.ItemsChanceResult.get(j)<=Check && P3rsoGB.ItemsChanceResult.get(j+1)>Check){
                    ItemStack item = new ItemStack(P3rsoGB.ItemsType.get(j));
                    item.setAmount(new Random().nextInt(P3rsoGB.ItemsMax.get(j))+P3rsoGB.ItemsMin.get(j));
                    P3rsoGB.GoldBlockInventrory.setItem(P3rsoGB.Index,item);
                }
            }
        }
    }
    private void getIndex(){
        int k =0;
        while(k==0){
            P3rsoGB.Index = new Random().nextInt(54);
            if (invclear[P3rsoGB.Index]!=1){
                k=1;
                invclear[P3rsoGB.Index]=1;
            }
        }
    }
}
