package datageneration.data.day15_offlinepro.domain;

import java.io.Serializable;
import java.util.Random;
import java.util.UUID;

/**
 * Created by tourbis on 2017/8/10.
 */
public class RegisterUserInfo implements Serializable {
       private String id;//TODO 不必使用它，测试用的
       private String isVip;
       private String userName;
       private String registerTime;
       private String articleID;
       static final boolean IS_VIP_FLAG[]={true,false};
       static final Random random = new Random();
       public RegisterUserInfo(){}
       public RegisterUserInfo(String isVip, String userName, String registerTime,String articleID) {
        this.isVip = isVip;
        this.userName = userName;
        this.registerTime = registerTime;
        this.articleID = articleID;
    }
        public RegisterUserInfo(String userName, String registerTime,String articleID){
            this(getIsVipFlag(),userName,registerTime,articleID);
        }
        public RegisterUserInfo(String userName, String registerTime){
            this(getIsVipFlag(),userName,registerTime,getUUID());
        }
       public static String getIsVipFlag(){
            return IS_VIP_FLAG[random.nextInt(IS_VIP_FLAG.length)]?String.valueOf(true):String.valueOf(false);
        }

       public static String getUUID(){
            UUID uuid = UUID.randomUUID();
            int index= uuid.toString().indexOf("-");
            return uuid.toString().substring(0, index);
        }

    public String getArticleID() {
        return articleID;
    }

    public void setArticleID(String articleID) {
        this.articleID = articleID;
    }

    public String getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(String registerTime) {
        this.registerTime = registerTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getIsVip() {
        return isVip;
    }

    public void setIsVip(String isVip) {
        this.isVip = isVip;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
       public String toString() {
           return String.format("%s|%s|%s|%s",isVip,userName,registerTime,articleID);
       }
   }
