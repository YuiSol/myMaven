package datageneration.data.order.service;

import datageneration.data.order.config.MyConfig;
import datageneration.data.order.util.DataUtil;
import datageneration.data.order.util.DateUtil;

import java.util.Calendar;

/**
 * Created by YuiSol on 2017/8/14.
 */
public class ArticleRandom {
    /*
    private String articleId;
    private String partOf;
    private String releaseTime;
    private String title;
    private int read_count;
    private int comment_count;
    private int thumbs_count;*/
    private int read_count;
    public ArticleRandom(){
        this(DataUtil.randomNumber(200000));
    }
    public ArticleRandom(int read_count){
        this.read_count=read_count;
    }
    public String getPartOf(){
        return MyConfig.PART_OF[DataUtil.randomNumber(MyConfig.PART_OF.length)];
    }
    public String getReleaseTime(String time){
        DateUtil.setTime_start(time);
        return DateUtil.randomTime(Calendar.DATE, 60);
    }
    public String getTitle(){
        return DataUtil.getUUid_Max();
    }
    public int getRead_count(){
        return this.read_count;
    }
    public int getComment_count(){
        return DataUtil.randomNumber(this.read_count);
    }
    public int getThumbs_count(){
        return DataUtil.randomNumber(this.read_count);
    }
}
