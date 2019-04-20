package com.citysnaper.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.citysnaper.R;
import com.citysnaper.bean.ArticlePost;
import com.citysnaper.view.ShowAllTextView;
import com.wx.goodview.GoodView;

import java.util.ArrayList;

public class ArticlePostAdapter extends RecyclerView.Adapter<ArticlePostAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<ArticlePost> mArticlePostList;

    private GoodView mGoodView;
    static class ViewHolder extends RecyclerView.ViewHolder{

        ImageView avatarImage,moreAction,travelPicture,good,comments,send,bookmark;
        TextView authorName,likeNum,commentNum,time;
        ShowAllTextView articleContent;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            avatarImage = itemView.findViewById(R.id.avator_image);
            moreAction = itemView.findViewById(R.id.more_action);
            travelPicture = itemView.findViewById(R.id.travel_picture);
            good = itemView.findViewById(R.id.good);
            comments = itemView.findViewById(R.id.comment);
            send = itemView.findViewById(R.id.send);
            bookmark = itemView.findViewById(R.id.bookmark);
            authorName = itemView.findViewById(R.id.author_name);
            articleContent = itemView.findViewById(R.id.article_content);
            likeNum = itemView.findViewById(R.id.like_num);
            commentNum = itemView.findViewById(R.id.comment_num);
            time = itemView.findViewById(R.id.time);
        }
    }

    public ArticlePostAdapter(ArrayList<ArticlePost> x){
        mArticlePostList = x;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        if (mContext == null){
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_fragment_home_post,parent,false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ArticlePostAdapter.ViewHolder viewHolder, int i) {
       ArticlePost articlePost = mArticlePostList.get(i);

       mGoodView = new GoodView(mContext);
       Glide.with(mContext).load(articlePost.avatarImageUrl).into(viewHolder.avatarImage);
       Glide.with(mContext).load(articlePost.travelPictureUrl).into(viewHolder.travelPicture);

       TextPaint paint = viewHolder.likeNum.getPaint();
       paint.setFakeBoldText(true);

       viewHolder.comments.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

           }
       });
       viewHolder.send.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

           }
       });

       viewHolder.good.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               ((ImageView)v).setImageResource(R.drawable.good_checked);
               mGoodView.setText("+1");
               mGoodView.show(v);
           }
       });
       viewHolder.bookmark.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               ((ImageView)v).setImageResource(R.drawable.bookmark_checked);
               mGoodView.setTextInfo("收藏成功", Color.parseColor("#ff941A"),12);
               mGoodView.show(v);
           }
       });
    }

    @Override
    public int getItemCount() {
        return mArticlePostList.size();
    }

}
