package datageneration.data.order.config;

import datageneration.data.code_1.day15_offlinepro.constance.ConstancePools;

/**
 * Created by YuiSol on 2017/8/14.
 */
public interface MyConfig {
    String START_TIME="2013-02-12 07:00:00";

    String DEFAULT_SDF="yyyy-MM-dd HH:mm:ss";

    int USER_SIZE=10;

    int USERNAME_MAX=10;

    int USERNAME_MIN=4;

    String[] SEX=new String[]{"男","女"};

    String  ISVIP[]=new String []{"true","false"};

    String PART_OF[]={"首页","图片","时事",
            "时尚","娱乐","文史","健康","生活","旅游","体育","财经","科技","汽车","艺术"};
}
