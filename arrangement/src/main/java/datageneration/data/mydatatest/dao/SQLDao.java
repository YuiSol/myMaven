package datageneration.data.mydatatest.dao;

import java.util.List;

/**
 * Created by YuiSol on 2017/8/14.
 */
public interface SQLDao<T> {
    void insert(String sql);
    void insert(String sql,List<T> t);
    void insert(List<T> t);
    List<T> select(String sql,T t);
}
