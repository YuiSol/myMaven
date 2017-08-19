package datageneration.data.order.dao.Impl;

import datageneration.data.order.dao.SQLDao;
import datageneration.data.order.daomain.Article;
import datageneration.data.order.daomain.RegisterUserInfo;
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
public class ArticleImpl implements SQLDao<Article> {
    private JdbcTemplate jdbc;

    public ArticleImpl() {
        this(null);
    }

    public ArticleImpl(JdbcTemplate jdbc) {
        if(jdbc!=null){
            this.jdbc =new JdbcTemplate( SQLUtil.getDataSource());
        }else {
            this.jdbc=jdbc;
        }
    }

    public List<Article> select(String sql, Article article) {

        return jdbc.query(sql, new BeanPropertyRowMapper<Article>(Article.class));
    }

    public List<Article> select(String before_time, String after_time,Article article) {
        String sql=String.format("select *  from article where releaseTime between '%s' and '%s';",before_time,after_time);
        return this.select(sql,article);
    }

    public void insert(String sql, List<Article> t) {
            jdbc.batchUpdate(sql,t,t.size(), new ParameterizedPreparedStatementSetter<Article>() {
                public void setValues(PreparedStatement pre, Article t) throws SQLException {
                    pre.setString(1,t.getArticleId());
                    pre.setString(2,t.getPartOf());
                    pre.setString(3,t.getReleaseTime());
                    pre.setString(4,t.getTitle());
                    pre.setInt(5, t.getRead_count());
                    pre.setInt(6, t.getComment_count());
                    pre.setInt(6, t.getThumbs_count());

                }
            });

    }

    public void update(String sql) {
        jdbc.update(sql);
    }

    public void insert(List<Article> t) {
        String sql="INSERT INTO `article`(`articleId`,`partOf`,`registerTime`,`title`,`read_count`,`comment_count`,`thumbs_count`) value(?,?,?,?,?,?,?)";
        this.insert(sql,t);
    }
}
