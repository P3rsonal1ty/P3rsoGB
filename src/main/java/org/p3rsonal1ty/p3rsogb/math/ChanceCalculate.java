package org.p3rsonal1ty.p3rsogb.math;

import org.p3rsonal1ty.p3rsogb.P3rsoGB;

public class ChanceCalculate {
    public void Chance(){
        double nextstart =0;
        for(int i = 0; i< P3rsoGB.NumItems; i++){
            P3rsoGB.ItemsChanceResult.add(nextstart);
            nextstart+=P3rsoGB.ItemsChance.get(i)*10;
        }
        double i = 1000;
        P3rsoGB.ItemsChanceResult.add(i);
    }
}
