package datageneration.data.mydatatest.dao;

import datageneration.data.mydatatest.entity.AuthorPersonal;
import datageneration.data.order.util.SQLUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by YuiSol on 2017/8/14.
 */
public class AuthorPersonalImpl implements SQLDao<AuthorPersonal> {
    private JdbcTemplate jdbc;

    public AuthorPersonalImpl() {
        this(null);
    }

    public void insert(String sql) {
        jdbc.update(sql);
    }

    public AuthorPersonalImpl(JdbcTemplate jdbc) {
       if(jdbc==null){
           jdbc=new JdbcTemplate(SQLUtil.getDataSource());
       }else{
           this.jdbc = jdbc;
       }
    }

    public void insert(String sql,List<AuthorPersonal> authorPersonal) {

        jdbc.batchUpdate(sql, authorPersonal, authorPersonal.size(), new ParameterizedPreparedStatementSetter<AuthorPersonal>() {
            public void setValues(PreparedStatement ps, AuthorPersonal user) throws SQLException {
                ps.setString(1, user.getName());
                ps.setString(2, user.getSex());
                ps.setString(3, user.getNumber());
                ps.setString(4, user.getRegisterTime());
            }
        });
    }

    public void insert(List<AuthorPersonal> t) {
        String sql="INSERT INTO `register_info.`test`(`isVip`,`userName`,`registerTime`,`articleID`) value(?,?,?,?)";
        this.insert(sql,t);
    }

    public List<AuthorPersonal> select(String sql, AuthorPersonal authorPersonal) {
        List<AuthorPersonal> query = jdbc.query(sql, new BeanPropertyRowMapper<AuthorPersonal>());
        return query;
    }

}
