package com.citysnaper.adapter;

import android.content.Context;
import android.content.Intent;
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
import com.citysnaper.activity.ArticleDetail;
import com.citysnaper.bean.ArticlePostBean;
import com.citysnaper.view.ShowAllSpan;
import com.citysnaper.view.ShowAllTextView;
import com.wx.goodview.GoodView;

import java.util.ArrayList;

public class ArticlePostAdapter extends RecyclerView.Adapter<ArticlePostAdapter.ViewHolder> {


    private  String testContent ="四月一个晴朗的早晨，我在原宿后街同一个百分之百的女孩擦肩而过。\n" +
            "\n" +
            "不讳地说，女孩算不得怎么漂亮，并无吸引人之处，衣着也不出众，脑后的头发执着地带有睡觉挤压的痕迹。年龄也已不小了--—应该快有30了。严格地说来，恐怕很难称之为女孩。然而，相距50米开外我便一眼看出：对于我来说，她是个百分之百的女孩。从看见她身姿的那一瞬间，我的胸口便如发生地鸣一般的震颤，口中如沙漠干得沙沙作响。\n" +
            "\n" +
            "或许你也有你的理想女孩。例如喜欢足颈细弱的女孩，毕竟眼睛大的女孩，十指绝对好看的女孩，或不明所以地迷上慢慢花时间进食的女孩。我当然有自己的偏爱。在饭店时就曾看邻桌一个女孩的鼻形看得发呆。\n" +
            "\n" +
            "但要明确勾勒百分之百的女孩形象，任何人都无法做到。我就绝对想不起她长有怎样的鼻子。甚至是否有鼻子都已记不真切，现在我所能记的，只有她并非十分漂亮这一点。事情也真是不可思议。\n" +
            "\n" +
            "“昨天在路上同一个百分之百的女孩擦肩而过。”我对一个人说。\n" +
            "\n" +
            "“唔，”他应道，“人可漂亮？”\n" +
            "\n" +
            "“不，不是说这个。”\n" +
            "\n" +
            "“那，是合你口味那种类型喽？”\n" +
            "\n" +
            "“记不得了。眼睛什么样啦，胸部是大是小啦，统统忘得一干二净。”\n" +
            "\n" +
            "“莫名其妙啊！”\n" +
            "\n" +
            "“是莫名其妙。”\n" +
            "\n" +
            "“那么，”他显得兴味索然，“你做什么了？搭话了？还是跟踪了？”\n" +
            "\n" +
            "“什么都没有做。”我说，“仅仅是擦肩而过。”\n" +
            "\n" +
            "她由东往西走，我从西向东去，在四月里一个神清气爽的早晨。\n" +
            "\n" +
            "我想和她说话，哪怕30分钟也好。想打听她的身世，也想全盘托出自己的身世。而更重要的，是想弄清导致1981年4月一个晴朗的早晨我们在原宿后街擦肩而过这一命运的原委。里面肯定充满和平时代的古老机器般温馨的秘密。\n" +
            "\n" +
            "如此谈罢，我们可以找地方吃午饭，看伍迪。爱伦的影片，再顺路到宾馆里的酒吧喝鸡尾酒什么的。弄得好，喝完说不定能同她睡上一觉。\n" +
            "\n" +
            "可能性在扣击我的心扉。\n" +
            "\n" +
            "我和她之间的距离以近至十五六米了。\n" +
            "\n" +
            "问题是，我到底该如何向她搭话呢？\n" +
            "\n" +
            "“你好！和我说说话可以吗？哪怕30分钟也好。”\n" +
            "\n" +
            "过于傻气，简直象劝人加入保险。\n" +
            "\n" +
            "“请问，这一带有24小时营业的洗衣店吗？”\n" +
            "\n" +
            "这也同样傻里傻气。何况我岂非连洗衣袋都没带！有谁能相信我的道白呢？\n" +
            "\n" +
            "也许开门见山好些。“你好！你对我可是百分之百的女孩哟！”\n" +
            "\n" +
            "不，不成，她恐怕不会相信我的表白。纵然相信，也未必愿同我说什么话。她可能这样说：即便我对你是百分之百的女孩，你对我可不是百分之百的男人，抱歉！而这是大有可能的。假如陷入这般境地，我肯定全然不知所措。这一打击说不定使我一蹶不振。我已32岁，所谓上年纪归根结底便是这么一回事。\n" +
            "\n" +
            "我是在花店门前和她擦肩而过的，那暖暖的小小的气块儿触到我的肌肤。柏油路面洒了水，周围荡漾着玫瑰花香。连向她打声招呼我都未能做到。她身穿白毛衣，右手拿一个尚未贴邮票的四方信封。她给谁写了封信。那般睡眼惺忪，说不定整整写了一个晚上。那四方信封里有可能装有她的全部秘密。\n" +
            "\n" +
            "走几步回头时，她的身影早已消失在人群中。\n" +
            "\n" +
            "当然，今天我已完全清楚当时应怎样向她搭话。但不管怎么说，那道白实在太长，我笃定表达不好──就是这样，我所想到的每每不够实用。\n" +
            "\n" +
            "总之，道白自“很久很久以前”开始，而以“你不觉得这是个忧伤的故事吗”结束。\n" +
            "\n" +
            "很久很久以前，有个地方有一个少男和一个少女。少男18，少女16。少男算不得英俊，少女也不怎么漂亮，无非随处可见的孤独而平常的少男少女。但两人一直坚信世上某个地方一定存在百分之百适合自己的少女和少男。是的，两人相信奇迹，而奇迹果真发生了。\n" +
            "\n" +
            "一天两人在街头不期而遇。\n" +
            "\n" +
            "“真巧！我一直在寻找你。也许你不相信，你对我是百分之百的男孩。从头到脚跟我想象的一模一样。简直是在做梦。\n" +
            "\n" +
            "两人坐在公园长椅上，手拉手，百谈不厌。两人已不再孤独。百分之百需求对方，百分之百已被对方需求。而百分之百需求对方和百分之百地被对方需求是何等美妙的事情啊！这已是宇宙奇迹！\n" +
            "\n" +
            "但两人心中掠过一个小小的，的确小而又小的疑虑：梦想如此轻易成真是否就是好事？\n" +
            "\n" +
            "交谈突然中断时，少男这样说道：\n" +
            "\n" +
            "“我说，再尝试一次吧！如果我们两人真是一对百分之百的恋人的话，肯定还会有一天在哪里相遇。下次相遇时如果仍觉得对方百分之百，就马上在那里结婚，好么？\n" +
            "\n" +
            "“好的。”少女回答。\n" +
            "\n" +
            "于是两人分开，各奔东西。\n" +
            "\n" +
            "然而说实在话，根本没有必要尝试，纯属多此一举。为什么呢？因为两人的的确确是一对百分之百的恋人，因为那是奇迹般的邂逅。但两人过于年轻，没办法知道这许多。于是无情的命运开始捉弄两人。\n" +
            "\n" +
            "一年冬天，两人都染上了那年肆虐的恶性流感。在死亡线徘徊几个星期后，过去的记忆丧失殆尽。事情也真是离奇。当两人睁眼醒来时，脑袋里犹如D.H劳伦斯少年时代的贮币盒一样空空如也。\n" +
            "\n" +
            "但这对青年男女毕竟聪颖豁达且极有毅力，经过不懈努力，终于再度获得了新的知识新的情感，胜任愉快地重返社会生活。啊，我的上帝！这两人真是无可挑剔！他们完全能够换乘地铁，能够在邮局寄交快信了。并且分别体验了百分之七十五和百分之八十五的恋爱。\n" +
            "\n" +
            "如此一来二去，少男32，少女31岁了。时光以惊人的速度流逝。\n" +
            "\n" +
            "四月一个晴朗的早晨，少男为喝折价早咖啡沿原宿后街由西向东走，少女为买快信邮票沿同一条街由东向西去，两人恰在路中间失之交臂。失却的记忆的微光刹那间照亮两颗心。\n" +
            "\n" +
            "两人胸口陡然悸颤，并且得知：\n" +
            "\n" +
            "她对我是百分之百的女孩。\n" +
            "\n" +
            "他对我是百分之百的男孩。\n" +
            "\n" +
            "然而两人记忆的烛光委实过于微弱，两人的话语也不似十四年前那般清晰。结果连句话也没说便擦身而过，径直消失在人群中，永远永远。\n" +
            "\n" +
            "你不觉得这是个令人感伤的故事么？\n" +
            "\n" +
            "是的，我本该这样向她搭话。";

    private Context mContext;
    private ArrayList<ArticlePostBean> mArticlePostList;

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

    public ArticlePostAdapter(ArrayList<ArticlePostBean> x){
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
       ArticlePostBean articlePost = mArticlePostList.get(i);


       mGoodView = new GoodView(mContext);
       Glide.with(mContext).load(articlePost.avatarImageUrl).into(viewHolder.avatarImage);
       Glide.with(mContext).load(articlePost.travelPictureUrl).into(viewHolder.travelPicture);

       TextPaint paint = viewHolder.likeNum.getPaint();
       paint.setFakeBoldText(true);

       viewHolder.articleContent.setMaxShowLines(6);
       viewHolder.articleContent.setMyText(testContent);
       viewHolder.articleContent.setOnAllSpanClickListener(new ShowAllSpan.OnAllSpanClickListener() {
           @Override
           public void onClick(View widget) {
               mContext.startActivity(new Intent(mContext,ArticleDetail.class));
           }
       });

       viewHolder.comments.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent mIntent = new Intent(mContext, ArticleDetail.class);
               mContext.startActivity(mIntent);
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
