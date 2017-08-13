package datageneration.data.model.day15_offlinepro.domain;

import java.io.Serializable;
import java.util.Random;

/**
 * Created by tourbis on 2017/8/11.
 */
public class Article implements Serializable {
    private String articleId;
    private String partOf;
    private String title;
    private int read_count;
    private int comment_count;
    static final Random random = new Random();
    static final String PART_OF[]={"图片","实事","文化","生活"};
    public Article() {

    }
    public Article(String articleId, String partOf, String title, int read_count, int comment_count) {
        this.articleId = articleId;
        this.partOf = partOf;
        this.title = title;
        this.read_count = read_count;
        this.comment_count = comment_count;
    }
    public Article(String articleId, String title, int read_count, int comment_count) {
       this(articleId,title,PART_OF[random.nextInt(PART_OF.length)],read_count,comment_count);
    }
    public Article(String articleId,int read_count, int comment_count) {
        this(articleId,null,read_count,comment_count);
    }
    public static String getPartOf(){
        return PART_OF[random.nextInt(PART_OF.length)];
    }

    public static int getRandomCommentCount(){
        return random.nextInt(9999);
    }
    public static int u (){
        return random.nextInt(666);
    }
    public static String getRandomTitle(){
        return null;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public void setPartOf(String partOf) {
        this.partOf = partOf;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getRead_count() {
        return read_count;
    }

    public void setRead_count(int read_count) {
        this.read_count = read_count;
    }

    public int getComment_count() {
        return comment_count;
    }

    public void setComment_count(int comment_count) {
        this.comment_count = comment_count;
    }

    @Override
    public String toString() {
        return
                "articleId='" + articleId + '\'' +
                ", partOf='" + partOf + '\'' +
                ", title='" + title + '\'' +
                ", read_count=" + read_count +
                ", comment_count=" + comment_count;
    }
}
