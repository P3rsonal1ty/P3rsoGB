package org.p3rsonal1ty.p3rsogb.handler;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.p3rsonal1ty.p3rsogb.P3rsoGB;


public class Events implements Listener {
    @EventHandler
    public void InteractGoldBlock(PlayerInteractEvent e){
        if(e.getClickedBlock().getX()== P3rsoGB.GoldBlockx && e.getClickedBlock().getY()==P3rsoGB.GoldBlocky && e.getClickedBlock().getZ()==P3rsoGB.GoldBlockz){
            Player player = e.getPlayer();
            player.openInventory(P3rsoGB.GoldBlockInventrory);

        }
    }
}
