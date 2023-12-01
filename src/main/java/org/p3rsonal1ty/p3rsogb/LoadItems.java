package org.p3rsonal1ty.p3rsogb;

import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;


public class LoadItems {
    private final JavaPlugin plugin;

    public LoadItems(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public void LoadItem(){
        int k = 0;
        String sType = "Items.item"+k+".Type";
        String sMin = "Items.item"+k+".Min";
        String sMax = "Items.item"+k+".Max";
        String sChance = "Items.item"+k+".Chance";
        while(plugin.getConfig().getString(sType)!=null){
            Material s = Material.getMaterial(Objects.requireNonNull(plugin.getConfig().getString(sType)));
            P3rsoGB.ItemsType.add(s);
            P3rsoGB.ItemsMin.add(plugin.getConfig().getInt(sMin));
            P3rsoGB.ItemsMax.add(plugin.getConfig().getInt(sMax));
            P3rsoGB.ItemsChance.add(plugin.getConfig().getDouble(sChance));
            k++;
            sType = "Items.item"+k+".Type";
            sMin = "Items.item"+k+".Min";
            sMax = "Items.item"+k+".Max";
            sChance = "Items.item"+k+".Chance";
        }
        P3rsoGB.NumItems=k;
    }

}
