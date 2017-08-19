package datageneration.data.mydatatest.dao;

import datageneration.data.mydatatest.entity.AuthorPersonal;

import java.util.List;

/**
 * Created by YuiSol on 2017/8/14.
 */
public class AuthorImpl implements SQLDao<AuthorPersonal> {
    public void insert(String sql) {

    }

    public void insert(String sql, List<AuthorPersonal> t) {

    }

    public void insert(List<AuthorPersonal> t) {

    }

    public List<AuthorPersonal> select(String sql, AuthorPersonal authorPersonal) {
        return null;
    }
}
