package org.p3rsonal1ty.p3rsogb;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.managers.RemovalStrategy;
import com.sk89q.worldguard.protection.regions.ProtectedCuboidRegion;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import net.md_5.bungee.api.ChatColor;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.p3rsonal1ty.p3rsogb.handler.Events;
import org.p3rsonal1ty.p3rsogb.math.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public final class P3rsoGB extends JavaPlugin {
    public static Economy econ = null;
    public static int GoldBlockx;
    public static int GoldBlocky;
    public static int GoldBlockz;
    public static int Index;
    public static Inventory GoldBlockInventrory = Bukkit.createInventory(null,54, ChatColor.GOLD+"           Золотой блок");
    public static int NumItems;
    public static List<Material> ItemsType=new ArrayList<>();
    public static List<Integer> ItemsMin=new ArrayList<>();
    public static List<Integer> ItemsMax=new ArrayList<>();
    public static List<Double> ItemsChance=new ArrayList<>();
    public static List<Double> ItemsChanceResult=new ArrayList<>();
    public static  List players;
    public static String playername;
    public static Collection playersnear;
    public static  int count;

    @Override
    public void onEnable() {
        System.out.println("┏━━━┳━━━┓       ┏━━━┳━━┓");
        System.out.println("┃┏━┓┃┏━┓┃       ┃┏━┓┃┏┓┃");
        System.out.println("┃┗━┛┣┛┏┛┣━┳━━┳━━┫┃ ┗┫┗┛┗┓");
        System.out.println("┃┏━━╋┓┗┓┃┏┫━━┫┏┓┃┃ ┏┫┏━┓┃");
        System.out.println("┃┃  ┃┗━┛┃┃┣━━┃┗┛┃┗━┛┃┗━┛┃");
        System.out.println("┗┛  ┗━━━┻┛┗━━┻━━┻━━━┻━━━┛");
        getServer().getPluginManager().registerEvents(new Events(), this);
        new LoadItems(this).LoadItem();
        new ChanceCalculate().Chance();
        new ItemsToGoldBlock().Inv();
        World world = Bukkit.getWorld("world");
        int delay1 = 1;
        int period1=120;
        Bukkit.getScheduler().runTaskTimer(this,()->{
            new LocationGoldBlock().cord();
            Location location = new Location(world,GoldBlockx,GoldBlocky,GoldBlockz);
            Block block = location.getBlock();
            block.setType(Material.GOLD_BLOCK);
            BlockVector3 min = BlockVector3.at(GoldBlockx-5,GoldBlocky-5,GoldBlockz-5);
            BlockVector3 max = BlockVector3.at(GoldBlockx+5,GoldBlocky+5,GoldBlockz+5);
            ProtectedRegion region = new ProtectedCuboidRegion("GoldBlock",min,max);
            RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
            RegionManager regions = container.get(BukkitAdapter.adapt(world));
            regions.addRegion(region);
            new ItemsToGoldBlock().Inv();
            Bukkit.broadcastMessage(org.bukkit.ChatColor.YELLOW+"Золотой блок появился на координатах:"+ org.bukkit.ChatColor.WHITE+" "+GoldBlockx+" "+GoldBlocky+" "+GoldBlockz);
        }, delay1 * 20L, period1 * 20L);
        int delay =1;
        int period =1;
        Bukkit.getScheduler().runTaskTimer(this,()->{
            Location location = new Location(world,GoldBlockx,GoldBlocky,GoldBlockz);
            count =9;
            Block block = location.getBlock();
            block.setType(Material.GOLD_BLOCK);
            Collection list = location.getNearbyPlayers(2);
            playersnear = location.getNearbyPlayers(60);
            if(!list.isEmpty()){
                players = list.stream().toList();
                new PlayersNearGoldBlock().near();
                new NearPlayerToMoney().nearblock();
                econ.depositPlayer(Bukkit.getPlayer(playername),count);
                Bukkit.getPlayer(playername).sendMessage(org.bukkit.ChatColor.DARK_GREEN+"+"+count+"$");
            }
        }, delay * 20L, period * 20L);
        int delay2 =90;
        int period2=120;
        Bukkit.getScheduler().runTaskTimer(this,()->{
            Location location = new Location(world,GoldBlockx,GoldBlocky,GoldBlockz);
            Block block = location.getBlock();
            Bukkit.broadcastMessage(org.bukkit.ChatColor.BLUE+"Золотой блок пропал!");
            block.setType(Material.AIR);
            GoldBlockx=15000;
            GoldBlocky=255;
            GoldBlockz=15000;
            BlockVector3 min = BlockVector3.at(GoldBlockx-5,GoldBlocky-5,GoldBlockz-5);
            BlockVector3 max = BlockVector3.at(GoldBlockx+5,GoldBlocky+5,GoldBlockz+5);
            RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
            RegionManager regions = container.get(BukkitAdapter.adapt(world));
            regions.removeRegion("GoldBlock", RemovalStrategy.UNSET_PARENT_IN_CHILDREN);
            ProtectedRegion region = new ProtectedCuboidRegion("GoldBlock",min,max);
            regions.addRegion(region);
        }, delay2 * 20L, period2 * 20L);
    }

    @Override
    public void onDisable() {
        getLogger().info(String.format("[%s] Disabled Version %s", getDescription().getName(), getDescription().getVersion()));
    }

    public boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }
    public static Economy getEconomy() {
        return econ;
    }
}
