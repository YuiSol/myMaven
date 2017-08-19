package datageneration.data.order.app;

import datageneration.data.order.daomain.RegisterUserInfo;
import datageneration.data.order.service.ArticleRandom;
import datageneration.data.order.service.RegisterUserInfoRandom;
import datageneration.data.order.util.SQLUtil;
import org.junit.Test;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Created by YuiSol on 2017/8/14.
 */
public class ArticleSQL {
    public static void main(String[] args) {
        createUser();

    }
    public static void createUser(){
        RegisterUserInfoRandom rer = new RegisterUserInfoRandom();
        RegisterUserInfo reg = new RegisterUserInfo(
                                rer.getIsVip(),
                                rer.getUserName(),
                                rer.getRegisterTime(),
                                rer.getArticleID(),
                                rer.getSex()
        );
        rer.addData(reg);
        for(int i=0;i<5000;i++){
            reg.setIsVip(rer.getIsVip());
            reg.setUserName(rer.getUserName());
            reg.setRegisterTime(rer.getRegisterTime());
            reg.setArticleID(rer.getArticleID());
            reg.setSex(rer.getSex());
            rer.addData(reg);
        }
    }
    public static void createArticle(){
        ArticleRandom art = new ArticleRandom();

    }
}
