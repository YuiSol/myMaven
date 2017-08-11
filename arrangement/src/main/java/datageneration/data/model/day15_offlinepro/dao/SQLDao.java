package datageneration.data.model.day15_offlinepro.dao;

import java.util.List;

/**
 * Created by tourbis on 2017/8/11.
 */
public interface SQLDao<T> {
    List<T> query(String sql, T t);
    void addData(String sql);
    void addData(List<T> t, String sql);

}
