package datageneration.data.order.daomain;

/**
 * Created by YuiSol on 2017/8/14.
 */
public class RegisterUserInfo {
    private int rid;
    private String isVip;
    private String userName;
    private String registerTime;
    private String articleID;
    private String sex;

    public RegisterUserInfo() {
    }

    public RegisterUserInfo(String isVip, String userName, String registerTime, String articleID, String sex) {
            this(0,isVip,userName,registerTime,articleID,sex);
    }

    public RegisterUserInfo(int rid, String isVip, String userName, String registerTime, String articleID, String sex) {

        this.rid = rid;
        this.isVip = isVip;
        this.userName = userName;
        this.registerTime = registerTime;
        this.articleID = articleID;
        this.sex = sex;
    }

    public int getRid() {

        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "RegisterUserInfo{" +
                "rid=" + rid +
                ", isVip='" + isVip + '\'' +
                ", userName='" + userName + '\'' +
                ", registerTime='" + registerTime + '\'' +
                ", articleID='" + articleID + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }
}
