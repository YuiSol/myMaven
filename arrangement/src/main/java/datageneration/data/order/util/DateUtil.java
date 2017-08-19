package datageneration.data.order.util;

import datageneration.data.order.config.MyConfig;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * Created by YuiSol on 2017/8/14.
 */
public class DateUtil {
    private DateUtil(){}
    private static StringBuffer time_start=new StringBuffer(MyConfig.START_TIME);
    private static SimpleDateFormat sf=new SimpleDateFormat(MyConfig.DEFAULT_SDF);
    private static final Calendar instance = Calendar.getInstance();
    private static final Random random=new Random();
    private static Date date;

    public  static  int getTimecount(String time1,String time2){
        int count=0;
        String[] split1 = time1.split(":");
        String[] split2 = time2.split(":");
        count+=(Integer.parseInt(split2[0])-Integer.parseInt(split1[0]))*3600;
        count+=(Integer.parseInt(split2[1])-Integer.parseInt(split1[1]))*60;
        count+=(Integer.parseInt(split2[2])-Integer.parseInt(split1[2]));
        return count;
    }
    public static SimpleDateFormat getDefault_sdf() {
        return sf;
    }

    public static void setDefault_sdf(SimpleDateFormat default_sdf) {
        DateUtil.sf = default_sdf;
    }

    public static String getTime_start() {
        return time_start.toString();
    }

    public static void setTime_start(String time_start) {
        DateUtil.time_start.setLength(0);
        DateUtil.time_start.append(time_start);
    }
    public static String randomTime(int field,int maxSize){
        try {
            date= sf.parse(time_start.toString());
            instance.setTime(date);
            instance.add(field, random.nextInt(maxSize));
            time_start.setLength(0);
            time_start.append(sf.format(instance.getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
        return sf.format(instance.getTime());
    }

}
