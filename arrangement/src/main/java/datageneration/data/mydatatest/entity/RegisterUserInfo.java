package datageneration.data.mydatatest.entity;

import java.io.Serializable;
import java.util.Random;

/**
 * Created by YuiSol on 2017/8/12.
 * 网页的点击流
 */
public class RegisterUserInfo  implements Serializable{
    //是不是VIP
    private String isVip;

    public RegisterUserInfo() {
    }

    @Override
    public String toString() {
        return "RegisterUserInfo{" +
                "isVip='" + isVip + '\'' +
                ", userName='" + userName + '\'' +
                ", isRegiest='" + isRegiest + '\'' +
                ", registerTime='" + registerTime + '\'' +
                ", articleID='" + articleID + '\'' +

                '}';
    }

    public RegisterUserInfo(String isVip, String articleID, String registerTime, String isRegiest, String userName) {
        this.isVip = isVip;
        this.articleID = articleID;
        this.registerTime = registerTime;
        this.isRegiest = isRegiest;
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

    public String getIsRegiest() {
        return isRegiest;
    }

    public void setIsRegiest(String isRegiest) {
        this.isRegiest = isRegiest;
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

    //用户名
    private String userName;
    //是否注册用户
    private String isRegiest;
    //上线时间
    private String registerTime;
    //阅读文章id
    private String articleID;

    static final boolean IS_VIP_FLAG[]={true,false};
    static final Random random = new Random();
}
