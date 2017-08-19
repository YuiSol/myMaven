package datageneration.data.day15_offlinepro.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * Created by tourbis on 2017/8/10.
 */
public class TimeUtils {
    private TimeUtils(){}
    private static String time_start="2013-02-12 07:00:00";
    private static SimpleDateFormat default_sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final Calendar instance = Calendar.getInstance();
    private static final Random random=new Random();
    private static int range;
    /**
     * 设置格式化工厂
     * @param sdf
     */
    public static void setSimpleDateFormat(SimpleDateFormat sdf){
        TimeUtils.default_sdf=sdf;
    }

    /**
     * 设置起始日期
     * @param beginTime 起始日期，默认值"2013-02-12 00:00:00"
     */
    public static void setTimeBegin(String beginTime){
        time_start=beginTime;
    }

    /**
     * 以秒的方式添加
     * @param range
     */
    @Deprecated
    public static String getTimeWithSecond(int range){
        TimeUtils.range=range;
        return transformation(Calendar.SECOND);
    }
    @Deprecated
    public static String getTimeWithMinute(int range){
        TimeUtils.range=range;
        return transformation(Calendar.MINUTE);
    }
    @Deprecated
    public static String getTimeWithHour(int range){
        TimeUtils.range=range;
        return transformation(Calendar.HOUR_OF_DAY);
    }
    @Deprecated
    public static String getTimeWithDay(int range){
        TimeUtils.range=range;
        return transformation(Calendar.DAY_OF_MONTH);
    }
    @Deprecated
    public static String getTimeWithMonth(int range){
        TimeUtils.range=range;
        return transformation(Calendar.MONTH);
    }

    public static String getTimeWiths(CallMethodID id,int range){
        TimeUtils.range=range;
        return transformation(id.getId());
    }

    private static String transformation(int id){
        try {
            instance.setTime(default_sdf.parse(time_start));
            __add__(id);
            time_start= default_sdf.format(instance.getTime());
        } catch (ParseException e) {
            System.out.println("格式化异常");
        }
        return time_start;
    }
    @Deprecated
    private static String transformation(int id,int range){
        try {
            instance.setTime(default_sdf.parse(time_start));
            __add__(id,range);
            time_start= default_sdf.format(instance.getTime());
        } catch (ParseException e) {
            System.out.println("格式化异常");
        }
        return time_start;
    }
    private static void __add__(int id){
        switch (id){
            case 13:instance.add(Calendar.SECOND, random.nextInt(range));break;
            case 12:instance.add(Calendar.MINUTE, random.nextInt(range));break;
            case 11:instance.add(Calendar.HOUR_OF_DAY, random.nextInt(range));break;
            case 5:instance.add(Calendar.DAY_OF_MONTH, random.nextInt(range));break;
            case 2:instance.add(Calendar.MONTH, random.nextInt(range));break;
        }
    }
    @Deprecated
    private static void __add__(int id,int range){
        switch (id){
            case 13:instance.add(Calendar.SECOND, random.nextInt(range));break;
            case 12:instance.add(Calendar.MINUTE, random.nextInt(range));break;
            case 10:instance.add(Calendar.HOUR_OF_DAY, random.nextInt(range));break;
            case 5:instance.add(Calendar.DAY_OF_MONTH, random.nextInt(range));break;
            case 2:instance.add(Calendar.MONTH, random.nextInt(range));break;
        }
    }
    /**
     * 支持时间过滤器
     * 默认调用分钟
     * @param time
     * @return
     */
    public static String timeFilter(String time){
        return timeFilter(7, 2, time,12);
    }

    //支持枚举写法
    public enum CallMethodID{
        WithSecond(13),WithMinute(12),WithHour(10),WithDay(5),WithMonth(2);
        private int id;
       CallMethodID(int id){
           this.id=id;
       }
        public int getId() {
            return id;
        }
    }
    public static String timeFilter(String time,CallMethodID id){
        return timeFilter(7, 2, time,id.getId());
    }
    public static String timeFilter(int startTime,int endTime,String time,CallMethodID id){
        return timeFilter(startTime,endTime,time,id.getId());
    }

    /**
     *用户注册，凌晨3点注册
     *
     * @param startTime 过滤器起始时间
     * @param endTime   过滤器结束时间
     * @param time  需要过滤的时间
     * @param callMethodIds 循环调用的方法id
     * @return
     */
    private static String timeFilter(int startTime,int endTime,String time,int callMethodIds){
        try {
            time_start=time;
             while(true){
                 Date date = default_sdf.parse(time_start);
                 instance.setTime(date);
                 int hour = instance.get(Calendar.HOUR_OF_DAY);//获取小时
                 if(hour>2&&hour<7){
                     time_start = callLoop(callMethodIds);
                 }else{
                     return default_sdf.format(date);
                 }
             }
        } catch (ParseException e) {
            e.printStackTrace();
        }
       return time;
    }
    private static String callLoop(int id){
        switch (id){
            case 13:return getTimeWithSecond(range);
            case 12:return getTimeWithMinute(range);
            case 10:return getTimeWithHour(range);
            case 5:return getTimeWithDay(range);
            case 2:return getTimeWithMonth(range);
            default:return null;
        }
    }
}
