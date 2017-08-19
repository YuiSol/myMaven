package datageneration.data.day15_offlinepro.dao;

import datageneration.data.day15_offlinepro.domain.RegisterUserInfo;
import datageneration.data.day15_offlinepro.utils.SQLUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by tourbis on 2017/8/11.
 */
public class RegisterUserInfoSQLDataImpl implements SQLDao<RegisterUserInfo> {
    private JdbcTemplate jdbcTemplate;
    public RegisterUserInfoSQLDataImpl(){
        this(null);
    }
    public RegisterUserInfoSQLDataImpl(JdbcTemplate jdbcTemplate){
        if(jdbcTemplate==null){
            this.jdbcTemplate= new JdbcTemplate(SQLUtils.getDataSource());
        }else{
            this.jdbcTemplate=jdbcTemplate;
        }
    }

    /**
     * 调用示例代码：
     *      RegisterUserInfoSQLDataImpl sqlData = new RegisterUserInfoSQLDataImpl();
     *      List<RegisterUserInfo> query = sqlData.query("select * from register_info.t_user limit 0,10", new RegisterUserInfo());
     * @param sql
     * @param registerUserInfo
     * @return
     */
    public List<RegisterUserInfo> query(String sql,RegisterUserInfo registerUserInfo) {
    List<RegisterUserInfo> data = jdbcTemplate.query(sql, new BeanPropertyRowMapper<RegisterUserInfo>(RegisterUserInfo.class));
    return data;
}
    //插入一条数据到sql
    public void addData(String sql){
        if(sql!=null){
            jdbcTemplate.update(sql);
        }
    }
    //预编译
    public void addData(List<RegisterUserInfo> data,String sql) {
            jdbcTemplate.batchUpdate(sql, data, data.size(), new ParameterizedPreparedStatementSetter<RegisterUserInfo>() {
                public void setValues(PreparedStatement ps, RegisterUserInfo user) throws SQLException {
                    ps.setString(1, user.getIsVip());
                    ps.setString(2, user.getUserName());
                    ps.setString(3, user.getRegisterTime());
                    ps.setString(4, user.getArticleID());
                }
            });
    }
    /**
     * 示例代码，允许测试使用
     * @param data
     */
    public void addDataExample(List<RegisterUserInfo> data){
        if(data==null){return;}
        String sql="INSERT INTO `register_info.`test`(`isVip`,`userName`,`registerTime`,`articleID`) value(?,?,?,?)";
        addData(data, sql);
    }

}
