package org.p3rsonal1ty.p3rsogb.math;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.p3rsonal1ty.p3rsogb.P3rsoGB;

import static java.lang.Math.*;

public class PlayersNearGoldBlock {
    public void near(){
        int size = P3rsoGB.players.size();
        double x = 0;
        double y = 0;
        double z = 0;
        double dist;
        double mindist=10000000;
        P3rsoGB.playername = P3rsoGB.players.get(0).toString();
        P3rsoGB.playername = P3rsoGB.playername.replace("CraftPlayer{name=","");
        P3rsoGB.playername = P3rsoGB.playername.replace("}","");
        for(int i =0;i<size;i++){
            String tempname = P3rsoGB.players.get(i).toString();
            tempname = tempname.replace("CraftPlayer{name=","");
            tempname = tempname.replace("}","");
            Player player = Bukkit.getPlayer(tempname);
            assert player != null;
            x=player.getX();
            y=player.getY();
            z=player.getZ();
            dist = sqrt(pow((P3rsoGB.GoldBlockx-0.5)-x,2)*pow(P3rsoGB.GoldBlocky-y,2)*pow((P3rsoGB.GoldBlockz-0.5)-z,2));
            if(min(mindist,dist)!=mindist){
                P3rsoGB.playername = tempname;
                mindist = dist;
            }
        }
    }
}
