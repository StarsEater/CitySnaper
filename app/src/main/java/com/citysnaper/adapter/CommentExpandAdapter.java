package com.citysnaper.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.citysnaper.R;
import com.citysnaper.bean.Comment;
import com.citysnaper.bean.CommentDetail;
import com.citysnaper.bean.ReplyDetail;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**评论与回复列表的适配器*/
public class CommentExpandAdapter extends BaseExpandableListAdapter {
    String TAG = "CommentExpandAdapter";
    ArrayList<CommentDetail> commentDetailsList;
    ArrayList<ReplyDetail>  replyDetailArrayList;
    private Context mContext;
    Integer pageIndex = 1;

    public CommentExpandAdapter(Context x, ArrayList<CommentDetail> cD){
        mContext = x;
        commentDetailsList = cD;
    }

    @Override
    public int getGroupCount() {
        return commentDetailsList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        if(commentDetailsList.get(groupPosition).replyList == null)
               return 0;
        return commentDetailsList.get(groupPosition).replyList.size()>0?commentDetailsList.get(groupPosition).replyList.size():0;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return commentDetailsList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return commentDetailsList.get(groupPosition).replyList.get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return getCombinedChildId(groupPosition,childPosition);
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
    boolean isLike = false;
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        final GroupHolder groupHolder;
        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.comment_item_layout,parent,false);
            groupHolder = new GroupHolder(convertView);
            convertView.setTag(groupHolder);
        }else{
            groupHolder = (GroupHolder) convertView.getTag();
        }
        String a1Adress = "https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1949.webp";
        Glide.with(mContext).load(a1Adress).into(groupHolder.avatorImage);
        groupHolder.tvName.setText(commentDetailsList.get(groupPosition).nickName);
        groupHolder.tvTime.setText(commentDetailsList.get(groupPosition).createDate);
        groupHolder.tvContent.setText(commentDetailsList.get(groupPosition).content);
        groupHolder.ivLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isLike){
                    isLike = false;
                    groupHolder.ivLike.setColorFilter(Color.parseColor("#aaaaaa"));
                }else{
                    isLike = true;
                    groupHolder.ivLike.setColorFilter(Color.parseColor("#FF5C5C"));
                }
            }
        });
        return  convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final ChildHolder childHolder;
        if(convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.comment_reply_item_layout,parent,false);
            childHolder = new ChildHolder(convertView);
            convertView.setTag(childHolder);
        }else{
            childHolder = (ChildHolder) convertView.getTag();
        }
        String replyUser = commentDetailsList.get(groupPosition).replyList.get(childPosition).nickName;
        if(replyUser.isEmpty()){
            childHolder.tvName.setText("无名：");
        }else{
            childHolder.tvName.setText(replyUser+":");
        }
        childHolder.tvContent.setText(commentDetailsList.get(groupPosition).replyList.get(childPosition).content);
        return  convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
    private class GroupHolder{
        private CircleImageView avatorImage;
        private TextView tvName,tvContent,tvTime;
        private ImageView ivLike;
        GroupHolder(View view){
            avatorImage = view.findViewById(R.id.comment_item_logo);
            tvContent = view.findViewById(R.id.comment_item_content);
            tvName = view.findViewById(R.id.comment_item_userName);
            tvTime = view.findViewById(R.id.comment_item_time);
            ivLike = view.findViewById(R.id.comment_item_like);
        }
    }
    private class ChildHolder{
        private  TextView tvName,tvContent;
        ChildHolder(View view){
            tvName = view.findViewById(R.id.reply_item_user);
            tvContent = view.findViewById(R.id.reply_item_content);
        }


    }

    /***
     *  评论成功后传入一条数据
     *  新的评论数据
     */
    public void addTheCommentData(CommentDetail newCommentDetail){
        if(newCommentDetail!=null){
            commentDetailsList.add(newCommentDetail);
            notifyDataSetChanged();
        }else{
            throw  new IllegalArgumentException("评论数据为空！");
        }
    }
    /**
     * 回复成功后插入一条数据
     * 新的回复数据
     * */
    public void addTheReplyData(ReplyDetail r,int newGroupPosition){
        if(r!= null){
            Log.d(TAG,"addTheReplyData:>>该刷新回复列表了："+ r);
            if (commentDetailsList.get(newGroupPosition).replyList != null){
                commentDetailsList.get(newGroupPosition).replyList.add(r);
            }else{
                ArrayList<ReplyDetail> replyList = new ArrayList<>();
                replyList.add(r);
                commentDetailsList.get(newGroupPosition).replyList = replyList;
            }
            notifyDataSetChanged();
        }else{
            throw new IllegalArgumentException("回复数据为空!");
        }
    }
    /**
     * 添加展示所有回复
     * 当前评论的所有回复数据
     */
     private void addReplyList(ArrayList<ReplyDetail> r, int groupPosition){
         if(commentDetailsList.get(groupPosition).replyList!=null){
             commentDetailsList.get(groupPosition).replyList.clear();
             commentDetailsList.get(groupPosition).replyList.addAll(r);
         }else{
             commentDetailsList.get(groupPosition).replyList = r;
         }
         notifyDataSetChanged();
     }
}
