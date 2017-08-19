package datageneration.data.order.util;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

/**
 * Created by YuiSol on 2017/8/14.
 */
public class SQLUtil {
    private SQLUtil(){}
    private static JdbcTemplate jdbcTemplate;
    private static DataSource dataSource;
    public static DataSource getDataSource(){
        synchronized (SQLUtil.class){
             if(dataSource==null){
                dataSource=new ComboPooledDataSource("world");
            }
        }
        return dataSource;
    }
    public static <T> List<T> select(JdbcTemplate jdbc,String sql,T t){
        return jdbc.query(sql,new BeanPropertyRowMapper<T>());
    }
    public static <T> void update (JdbcTemplate jdbc,List<T> t,String sql,Class<T> clazz){
        jdbc.batchUpdate(sql,t,t.size(), new ParameterizedPreparedStatementSetter<T>() {
            public void setValues(PreparedStatement preparedStatement, T t) throws SQLException {

            }
        });
    }
}
