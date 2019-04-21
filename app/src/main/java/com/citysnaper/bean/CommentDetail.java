package com.citysnaper.bean;

import java.util.ArrayList;

public class CommentDetail {
    public Integer id,replyTotal;
    public String nickName,
           userLogo,
           content,
           imgId,
           createDate;
    public ArrayList<ReplyDetail> replyList;
    public CommentDetail(String n,String c,String cD){
        nickName = n;
        content = c;
        createDate = cD;
    }


}
