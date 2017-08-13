package datageneration.data.mydatatest.entity;

import datageneration.data.mydatatest.util.StringRandom;

import java.io.Serializable;

/**
 * Created by YuiSol on 2017/8/11.
 * 作者的个人资料
 */
public class AuthorPersonal implements Serializable {
    //作者名字
    private String name;
    //作者注册时间
    private String registerTime;
    //作者作品编号
    private  String number;
    //作者性别
    private String sex;

    public AuthorPersonal() {
        this(null,null,null,null);
    }

    public AuthorPersonal(String name, String registerTime, String number, String sex) {
        if(name==null){
            name= StringRandom.getName(6);
        }
        this.name = name;
        this.registerTime = registerTime;
        this.number = number;
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "AuthorPersonal{" +
                "name='" + name + '\'' +
                ", registerTime='" + registerTime + '\'' +
                ", number='" + number + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(String registerTime) {
        this.registerTime = registerTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}


