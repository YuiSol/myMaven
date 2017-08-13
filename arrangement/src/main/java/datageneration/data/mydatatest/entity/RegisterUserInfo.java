package datageneration.data.mydatatest.entity;

import datageneration.data.mydatatest.util.DataUtil;
import datageneration.data.mydatatest.util.StringRandom;

import java.io.Serializable;
import java.util.Random;

/**
 * Created by YuiSol on 2017/8/12.
 * 网页的点击流
 */
public class RegisterUserInfo  implements Serializable{
    //是不是VIP
    private String isVip;
    //用户名
    private String userName;
    //上线时间
    private String registerTime;
    //阅读文章id
    private String articleID;
    private String[] vip=new String[]{"会员账号","普通账号","未登录账号"};
    private DataUtil data= new DataUtil();
    public RegisterUserInfo() {
        this(null,null,null,null);
    }

    public RegisterUserInfo(String articleID) {
        this(null,articleID,null,null);
    }

    @Override
    public String toString() {
        return "RegisterUserInfo{" +
                "isVip='" + isVip + '\'' +
                ", userName='" + userName + '\'' +
                ", registerTime='" + registerTime + '\'' +
                ", articleID='" + articleID + '\'' +

                '}';
    }

    public RegisterUserInfo(String isVip, String articleID, String registerTime, String userName) {
        if(isVip==null){
            isVip=vip[StringRandom.getNO(vip.length)];
        }
        if(userName==null){
            if(isVip==vip[0]||isVip==vip[1]){
                userName=StringRandom.getName(10,true);
            }else{
                userName=vip[vip.length-1];
            }
        }
        if(registerTime==null){
            registerTime=data.getRamdomTime();
        }
        if(articleID==null){
            articleID=StringRandom.getNO();
        }
        this.isVip = isVip;
        this.articleID = articleID;
        this.registerTime = registerTime;
        this.userName = userName;
    }

    public String getIsVip() {
        return isVip;

    }

    public void setIsVip(String isVip) {
        this.isVip = isVip;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(String registerTime) {
        this.registerTime = registerTime;
    }

    public String getArticleID() {
        return articleID;
    }

    public void setArticleID(String articleID) {
        this.articleID = articleID;
    }



    static final boolean IS_VIP_FLAG[]={true,false};
    static final Random random = new Random();
}
