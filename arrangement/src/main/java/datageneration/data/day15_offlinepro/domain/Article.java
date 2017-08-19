package datageneration.data.day15_offlinepro.domain;

import datageneration.data.day15_offlinepro.constance.ConstancePools;
import datageneration.data.day15_offlinepro.utils.TimeUtils;
import java.io.Serializable;
import java.util.Random;
import java.util.UUID;

/**
 * Created by tourbis on 2017/8/11.
 * 创建表
 */
public class Article implements Serializable {
    private String articleId;
    private String partOf;//所属类目
    private String releaseTime;//文章发布日期
    private String title;
    private int read_count;
    private int comment_count;
    static final Random random = new Random();
    public static final String PART_OF[]={ConstancePools.SUFFIX_NAME_PHOTO,ConstancePools.SUFFIX_NAME_COMMUNITY,
                                    ConstancePools.SUFFIX_NAME_CULTURE,ConstancePools.SUFFIX_NAME_LIFE};
    public Article() {

    }
    public Article(String articleId, String partOf,String releaseTime, String title, int read_count, int comment_count) {
        this.articleId = articleId;
        this.partOf = partOf;
        this.releaseTime=releaseTime;
        this.title = title;
        this.read_count = read_count;
        this.comment_count = comment_count;
    }
    public Article(String articleId,String releaseTime, String title, int read_count, int comment_count) {
       this(articleId,getPartOf(),releaseTime,title,read_count,comment_count);
    }
    public Article(String articleId,String releaseTime,int read_count, int comment_count) {
        this(articleId,releaseTime,getRandomTitle(),read_count,comment_count);
    }
    public Article(String articleId){
        this(articleId,getReleaseTimes(),getRandomTitle(),getRandomReadCount(),getRandomCommentCount());

    }
    public static String getReleaseTimes(){
        return TimeUtils.timeFilter(TimeUtils.getTimeWiths(TimeUtils.CallMethodID.WithMinute,7500));
    }

    public static String getPartOf(){
        return PART_OF[random.nextInt(PART_OF.length)];
    }

    public static int getRandomCommentCount(){
        return random.nextInt(666);
    }
    public static int getRandomReadCount(){
        return random.nextInt(9999);
    }
    public static String getRandomTitle(){
        return UUID.randomUUID().toString().replaceAll("-","");
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

    public String getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(String releaseTime) {
        this.releaseTime = releaseTime;
    }

    @Override
    public String toString() {
        return
                "articleId='" + articleId + '\'' +
                ", partOf='" + partOf + '\'' +
                ", releaseTime='" + releaseTime + '\'' +
                ", title='" + title + '\'' +
                ", read_count=" + read_count +
                ", comment_count=" + comment_count;
    }
}
