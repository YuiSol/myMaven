package datageneration.data.day15_offlinepro.app;

import datageneration.data.day15_offlinepro.domain.Article;
import datageneration.data.day15_offlinepro.domain.RegisterUserInfo;
import datageneration.data.day15_offlinepro.utils.TimeUtils;
import datageneration.data.day15_offlinepro.utils.UrlUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by tourbis on 2017/8/11.
 */
public class WriterArticle2SQL {
    private static List<RegisterUserInfo> registerUserInfo;
    private static Log log = LogFactory.getLog(WriterArticle2SQL.class);
    private static final Random random=new Random();
    static {
        if(registerUserInfo==null){
            registerUserInfo= UrlUtils.getRegisterUserInfo();
        }
        log.info("数据加载成功...");
    }

    public static String getArticleID(){
        return registerUserInfo.get(random.nextInt(registerUserInfo.size())).getArticleID();
    }
    public static void main(String[] args) {
        TimeUtils.setTimeBegin("2012-03-17 07:20:12");
        ArrayList<Article> list = new ArrayList<Article>();
        for (int i=0;i<600;i++) {
            Article article = new Article(getArticleID());
            list.add(article);
            System.out.println(article);
        }
        //批量插入数据库
        //ArticleSQLDataImpl sqlData = new ArticleSQLDataImpl();
       // String sql="INSERT INTO `register_info`.`t_user_article`(`articleId`,`partOf`,`releaseTime`,`title`,`read_count`,`comment_count`) value(?,?,?,?,?,?)";
       // sqlData.addData(list,sql);


    }
}
