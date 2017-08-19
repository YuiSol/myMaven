package datageneration.data.order.daomain;

import java.io.Serializable;

/**
 * Created by YuiSol on 2017/8/14.
 */
public class Article implements Serializable {
    private int aid;
    private String articleId;
    private String partOf;
    private String releaseTime;
    private String title;
    private int read_count;
    private int comment_count;
    private int thumbs_count;
    public Article() {

    }

    public Article(int aid, String articleId, String title, String releaseTime, String partOf, int comment_count, int read_count, int thumbs_count) {
        this.aid = aid;
        this.articleId = articleId;
        this.title = title;
        this.releaseTime = releaseTime;
        this.partOf = partOf;
        this.comment_count = comment_count;
        this.read_count = read_count;
        this.thumbs_count = thumbs_count;
    }

    public Article(int thumbs_count, int comment_count, int read_count, String releaseTime, String title, String partOf, String articleId) {
       this(0,articleId,title,releaseTime,partOf,comment_count,read_count,thumbs_count);
    }
    @Override
    public String toString() {
        return "Article{" +
                ", articleId='" + articleId + '\'' +
                ", partOf='" + partOf + '\'' +
                ", releaseTime='" + releaseTime + '\'' +
                ", title='" + title + '\'' +
                ", read_count=" + read_count +
                ", comment_count=" + comment_count +
                ", thumbs_count=" + thumbs_count +
                '}';
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(String releaseTime) {
        this.releaseTime = releaseTime;
    }

    public String getPartOf() {
        return partOf;
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

    public int getThumbs_count() {
        return thumbs_count;
    }

    public void setThumbs_count(int thumbs_count) {
        this.thumbs_count = thumbs_count;
    }


}
