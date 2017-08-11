package datageneration.data.model.day15_offlinepro.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;

/**
 * Created by tourbis on 2017/8/11.
 */
public class SQLUtils {
    private static DataSource dataSource;
    public synchronized static DataSource getDataSource(){
        if(dataSource==null){
            dataSource=new ComboPooledDataSource("world");
        }
        return dataSource;
    }
}
