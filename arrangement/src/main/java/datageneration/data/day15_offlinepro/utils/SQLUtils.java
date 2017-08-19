package datageneration.data.day15_offlinepro.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

/**
 * Created by tourbis on 2017/8/11.
 */
public class SQLUtils {
    private static DataSource dataSource;
    private static JdbcTemplate jdbcTemplate;
    public synchronized static DataSource getDataSource(){
        if(dataSource==null){
            dataSource=new ComboPooledDataSource("world");
        }
        return dataSource;
    }
    public static List<Map<String, Object>> query2List(String sql){
        if(jdbcTemplate==null){
            jdbcTemplate=new JdbcTemplate(getDataSource());
        }
        return jdbcTemplate.queryForList(sql);
    }
}
