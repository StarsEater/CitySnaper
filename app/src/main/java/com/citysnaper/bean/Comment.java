package com.citysnaper.bean;

import java.util.ArrayList;
/**
 * 大的评论框架，含有CommentDetail的细节
 * CommentDetail 里分为 一级评论 和 对评论的回复 ReplyDetail
 * */
public class Comment {
    public Integer code;
    public String message;
    public  Data data;
    public class  Data{
        public Integer total;
        public ArrayList<CommentDetail> list;


    }
}
