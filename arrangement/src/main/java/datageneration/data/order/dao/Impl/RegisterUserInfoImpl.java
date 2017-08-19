package datageneration.data.order.dao.Impl;

import datageneration.data.order.dao.SQLDao;
import datageneration.data.order.daomain.Article;
import datageneration.data.order.daomain.RegisterUserInfo;
import datageneration.data.order.util.SQLUtil;
import org.junit.Test;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by YuiSol on 2017/8/14.
 */
public class RegisterUserInfoImpl  implements SQLDao<RegisterUserInfo> {
    private JdbcTemplate jdbc;

    public RegisterUserInfoImpl() {
        this(null);
    }

    public RegisterUserInfoImpl(JdbcTemplate jdbc) {
        if(jdbc==null){
            this.jdbc =new JdbcTemplate( SQLUtil.getDataSource());
        }else {
            this.jdbc=jdbc;
        }
    }
    public List<RegisterUserInfo> select(String sql, RegisterUserInfo registerUserInfo) {
        return jdbc.query(sql, new BeanPropertyRowMapper<RegisterUserInfo>(RegisterUserInfo.class));
    }

    public void insert(String sql, List<RegisterUserInfo> t) {
        jdbc.batchUpdate(sql, t, t.size(), new ParameterizedPreparedStatementSetter<RegisterUserInfo>() {
            public void setValues(PreparedStatement pre, RegisterUserInfo t) throws SQLException {
                pre.setString(1, t.getIsVip());
                pre.setString(2, t.getUserName());
                pre.setString(3, t.getRegisterTime());
                pre.setString(4, t.getArticleID());
                pre.setString(5, t.getSex());
            }
        });

    }

    public void update(String sql) {
        jdbc.update(sql);
    }

    public void insert(List<RegisterUserInfo> t) {
        String sql="INSERT INTO `registeruserinfo`(`isVip`,`userName`,`registerTime`,`articleID`,`sex`) value(?,?,?,?,?);";
        this.insert(sql,t);
    }

    public List<RegisterUserInfo> select(String before_time, String after_time,RegisterUserInfo article) {
        String sql=String.format("select *  from registeruserinfo where releaseTime between '%s' and '%s';",before_time,after_time);
        return this.select(sql,article);
    }

}
