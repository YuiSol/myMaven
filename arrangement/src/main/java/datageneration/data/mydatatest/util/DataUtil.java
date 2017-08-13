package datageneration.data.mydatatest.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

/**
 * Created by Administrator on 2017/8/13.
 */
public class DataUtil  {
    private  SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private  String starttime="2010-01-01 00:00:00";
    private  Calendar calendar=Calendar.getInstance();
    private  Random ra= new Random();
    private  int size=500;
    public  String getRamdomTime() {
        int count=this.ra.nextInt(size);
        try {
            calendar.setTime(sf.parse(starttime));
            calendar.add(Calendar.MILLISECOND
                    ,count*1000);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return sf.format(calendar.getTime());
    }




}
