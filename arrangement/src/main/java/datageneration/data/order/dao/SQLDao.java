package datageneration.data.order.dao;

import java.util.List;

/**
 * Created by YuiSol on 2017/8/14.
 */
public interface SQLDao<T> {
    List<T> select(String sql,T t);
    List<T> select (String before_time,String after_time,T t);
    void insert(String sql,List<T> t);
    void update(String sql);
    void insert(List<T> t);

}
