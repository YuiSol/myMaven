package datageneration.data.mydatatest.entity;

import java.io.Serializable;
import java.util.Random;

/**
 * Created by YuiSol on 2017/8/11.
 * 作品
 */
public class AuthorWork implements Serializable {
    //作者对应的编号
    private String number;
    //作品标题
    public AuthorWork() {
    }private String title;
    //作品对应的类别
    private String type;
    //作品阅读量
    public AuthorWork(String number, String title, String type, int thumbs_count, int comment_count, int read_count) {
        this.number = number;
        this.title = title;
        this.type = type;
        this.thumbs_count = thumbs_count;
        this.comment_count = comment_count;
        this.read_count = read_count;
    }private int read_count;
    //作品点赞量
    private int thumbs_count;
    //评论量
    private int comment_count;

    static final String PART_OF[]={"图片","实事","文化","生活"};

    static final Random random = new Random();
    @Override
    public String toString() {
        return "AuthorWork{" +
                "number='" + number + '\'' +
                ", type='" + type + '\'' +
                ", title='" + title + '\'' +
                ", read_count=" + read_count +
                ", thumbs_count=" + thumbs_count +
                ", comment_count=" + comment_count +
                '}';
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public int getRead_count() {
        return read_count;
    }

    public void setRead_count(int read_count) {
        this.read_count = read_count;
    }


}
