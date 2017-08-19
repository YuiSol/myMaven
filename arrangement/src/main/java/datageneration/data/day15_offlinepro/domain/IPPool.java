package datageneration.data.day15_offlinepro.domain;

import datageneration.data.day15_offlinepro.utils.IPUtils;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


/**
 * Created by tourbis on 2017/8/13.
 * IP池
 */
public class IPPool {
    private static final  ArrayList<String> ipPool=new ArrayList<String>();
    static {
        for (int i=0;i<20;i++) {
            ipPool.add(IPUtils.getIp());
        }
    }
    private synchronized void addIP2Pool(String ip){
        if(ipPool.isEmpty()){
            //throws Exception
        }
        ipPool.add(ip);
    }
    public String getIPFromPool(Random random){
        return ipPool.get(random.nextInt(ipPool.size()));
    }

    public void init(){
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                addIP2Pool(IPUtils.getIp());
                //System.out.println(ipPool);
            }
        },0,5000);
    }
}
