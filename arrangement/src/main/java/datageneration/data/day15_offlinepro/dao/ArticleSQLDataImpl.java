package datageneration.data.day15_offlinepro.dao;

import datageneration.data.day15_offlinepro.domain.Article;
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
public class ArticleSQLDataImpl implements SQLDao<Article> {
    private JdbcTemplate jdbcTemplate;
    public ArticleSQLDataImpl(){
        this(null);
    }
    public ArticleSQLDataImpl(JdbcTemplate jdbcTemplate){
        if(jdbcTemplate==null){
            this.jdbcTemplate= new JdbcTemplate(SQLUtils.getDataSource());
        }else{
            this.jdbcTemplate=jdbcTemplate;
        }
    }

    /**
     * SELECT * from `register_info`.`test` ;
     */
    public List<Article> query(String sql,Article article) {
        return  jdbcTemplate.query(sql, new BeanPropertyRowMapper<Article>(Article.class));
    }

    public void addData(String sql) {
        if(sql!=null){
            jdbcTemplate.update(sql);
        }
    }

    public void addData(List<Article> data,String sql) {
        jdbcTemplate.batchUpdate(sql, data, data.size(), new ParameterizedPreparedStatementSetter<Article>() {
            public void setValues(PreparedStatement ps, Article article) throws SQLException {
                ps.setString(1, article.getArticleId());
                ps.setString(2, article.getPartOf());
                ps.setString(3,article.getReleaseTime());
                ps.setString(4,article.getTitle());
                ps.setInt(5, article.getRead_count());
                ps.setInt(6, article.getComment_count());
            }
        });
    }
}
