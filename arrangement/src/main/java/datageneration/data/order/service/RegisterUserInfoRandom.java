package datageneration.data.order.service;


import datageneration.data.order.config.MyConfig;
import datageneration.data.order.dao.Impl.RegisterUserInfoImpl;
import datageneration.data.order.dao.SQLDao;
import datageneration.data.order.daomain.RegisterUserInfo;
import datageneration.data.order.util.DataUtil;
import datageneration.data.order.util.DateUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by YuiSol on 2017/8/15.
 */
public class RegisterUserInfoRandom {

   /* private int rid;
    private String isVip;
    private String userName;
    private String registerTime;
    private String articleID;
    private String sex;*/
    private SQLDao sql=new RegisterUserInfoImpl();
    public String getIsVip(){
        return MyConfig.ISVIP[DataUtil.randomNumber(MyConfig.ISVIP.length-1)];
    }
    public String getUserName(){
        return DataUtil.randomName(MyConfig.USERNAME_MIN, MyConfig.USERNAME_MAX);
    }
    public String getRegisterTime(){
        return DateUtil.randomTime(Calendar.MINUTE, 120);
    }
    public String getRegisterTime(String start_time){
        DateUtil.setTime_start(start_time);
        return this.getRegisterTime();
    }
    public String getArticleID(){
        return DataUtil.randomUUID();
    }
    public String getSex(){
        return MyConfig.SEX[DataUtil.randomNumber(MyConfig.SEX.length-1)];
    }
    public void addData(RegisterUserInfo reg){
        List li=new ArrayList<RegisterUserInfo>();
        li.add(reg);
        sql.insert(li);
    }
    public void addData(List<RegisterUserInfo> li){
        sql.insert(li);
    }
    public List<RegisterUserInfo> select(){
        String sql="select * from registeruserinfo";
        return this.sql.select(sql,new RegisterUserInfo());
    }
    public List<RegisterUserInfo> select(String before_time,String after_time){
        return this.sql.select(before_time,after_time,after_time);
    }
    public List<RegisterUserInfo> select(String userName){
        String sql="select * from registeruserinfo where userName = '"+userName+" ';" ;
        return this.sql.select(sql,new RegisterUserInfo());
    }
}
