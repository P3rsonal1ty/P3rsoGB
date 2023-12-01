package org.p3rsonal1ty.p3rsogb.math;

import org.p3rsonal1ty.p3rsogb.P3rsoGB;


public class NearPlayerToMoney {
    public  void nearblock() {
        if(P3rsoGB.playersnear.size()<3){
            P3rsoGB.count=9;
        }
        if(P3rsoGB.playersnear.size()>=3 && P3rsoGB.playersnear.size()<5){
            P3rsoGB.count=11;
        }
        if(P3rsoGB.playersnear.size()>=5 && P3rsoGB.playersnear.size()<8){
            P3rsoGB.count=14;
        }
        if(P3rsoGB.playersnear.size()>=8 && P3rsoGB.playersnear.size()<10){
            P3rsoGB.count=19;
        }
        if(P3rsoGB.playersnear.size()>=10){
            P3rsoGB.count=23;
        }
    }
}
