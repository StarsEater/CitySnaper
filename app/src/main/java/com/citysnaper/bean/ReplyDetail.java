package com.citysnaper.bean;

public class ReplyDetail {
    public String nickName,
           userLogo,
           commentId,
           content,
           status,
           createDate;
    Integer id;
    public ReplyDetail(String nN,String c){
        nickName = nN;
        content = c;

    }
}
