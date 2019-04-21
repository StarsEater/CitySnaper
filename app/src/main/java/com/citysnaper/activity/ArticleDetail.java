package com.citysnaper.activity;

import android.graphics.Color;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.citysnaper.R;
import com.citysnaper.adapter.CommentExpandAdapter;
import com.citysnaper.bean.Comment;
import com.citysnaper.bean.CommentDetail;
import com.citysnaper.bean.ReplyDetail;
import com.citysnaper.view.CommentExpandableListView;
import com.google.gson.Gson;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import de.hdodenhof.circleimageview.CircleImageView;

public class ArticleDetail extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "ArticleDetail" ;
    @InjectView(R.id.detail_page_userLogo)
    CircleImageView avatarImage;
    @InjectView(R.id.detail_page_story)
    TextView articleContent;
    @InjectView(R.id.detail_page_image)
    ImageView  travelPicture;
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.detail_page_lv_comment)
    CommentExpandableListView expandableListView;
    @InjectView(R.id.detail_page_do_comment)
    TextView btComment;
    @InjectView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbarLayout;

    private CommentExpandAdapter adapter;
    private Comment comment;
    private ArrayList<CommentDetail> commentDetailArrayList;
    private BottomSheetDialog dialog;
    static String p3Adress = "https://cdn.dribbble.com/users/1627881/screenshots/4834764/assistant_1.jpg";
    static String p4Adress = "https://cdn.dribbble.com/users/1518862/screenshots/5862796/safari_2x.png";

    private String testJson = "{\n" +
            "\t\"code\": 1000,\n" +
            "\t\"message\": \"查看评论成功\",\n" +
            "\t\"data\": {\n" +
            "\t\t\"total\": 3,\n" +
            "\t\t\"list\": [{\n" +
            "\t\t\t\t\"id\": 42,\n" +
            "\t\t\t\t\"nickName\": \"程序猿\",\n" +
            "\t\t\t\t\"userLogo\": \"http://ucardstorevideo.b0.upaiyun.com/userLogo/9fa13ec6-dddd-46cb-9df0-4bbb32d83fc1.png\",\n" +
            "\t\t\t\t\"content\": \"时间是一切财富中最宝贵的财富。\",\n" +
            "\t\t\t\t\"imgId\": \"xcclsscrt0tev11ok364\",\n" +
            "\t\t\t\t\"replyTotal\": 1,\n" +
            "\t\t\t\t\"createDate\": \"三分钟前\",\n" +
            "\t\t\t\t\"replyList\": [{\n" +
            "\t\t\t\t\t\"nickName\": \"沐風\",\n" +
            "\t\t\t\t\t\"userLogo\": \"http://ucardstorevideo.b0.upaiyun.com/userLogo/9fa13ec6-dddd-46cb-9df0-4bbb32d83fc1.png\",\n" +
            "\t\t\t\t\t\"id\": 40,\n" +
            "\t\t\t\t\t\"commentId\": \"42\",\n" +
            "\t\t\t\t\t\"content\": \"时间总是在不经意中擦肩而过,不留一点痕迹.\",\n" +
            "\t\t\t\t\t\"status\": \"01\",\n" +
            "\t\t\t\t\t\"createDate\": \"一个小时前\"\n" +
            "\t\t\t\t}]\n" +
            "\t\t\t},\n" +
            "\t\t\t{\n" +
            "\t\t\t\t\"id\": 41,\n" +
            "\t\t\t\t\"nickName\": \"设计狗\",\n" +
            "\t\t\t\t\"userLogo\": \"http://ucardstorevideo.b0.upaiyun.com/userLogo/9fa13ec6-dddd-46cb-9df0-4bbb32d83fc1.png\",\n" +
            "\t\t\t\t\"content\": \"这世界要是没有爱情，它在我们心中还会有什么意义！这就如一盏没有亮光的走马灯。\",\n" +
            "\t\t\t\t\"imgId\": \"xcclsscrt0tev11ok364\",\n" +
            "\t\t\t\t\"replyTotal\": 1,\n" +
            "\t\t\t\t\"createDate\": \"一天前\",\n" +
            "\t\t\t\t\"replyList\": [{\n" +
            "\t\t\t\t\t\"nickName\": \"沐風\",\n" +
            "\t\t\t\t\t\"userLogo\": \"http://ucardstorevideo.b0.upaiyun.com/userLogo/9fa13ec6-dddd-46cb-9df0-4bbb32d83fc1.png\",\n" +
            "\t\t\t\t\t\"commentId\": \"41\",\n" +
            "\t\t\t\t\t\"content\": \"时间总是在不经意中擦肩而过,不留一点痕迹.\",\n" +
            "\t\t\t\t\t\"status\": \"01\",\n" +
            "\t\t\t\t\t\"createDate\": \"三小时前\"\n" +
            "\t\t\t\t}]\n" +
            "\t\t\t},\n" +
            "\t\t\t{\n" +
            "\t\t\t\t\"id\": 40,\n" +
            "\t\t\t\t\"nickName\": \"产品喵\",\n" +
            "\t\t\t\t\"userLogo\": \"http://ucardstorevideo.b0.upaiyun.com/userLogo/9fa13ec6-dddd-46cb-9df0-4bbb32d83fc1.png\",\n" +
            "\t\t\t\t\"content\": \"笨蛋自以为聪明，聪明人才知道自己是笨蛋。\",\n" +
            "\t\t\t\t\"imgId\": \"xcclsscrt0tev11ok364\",\n" +
            "\t\t\t\t\"replyTotal\": 0,\n" +
            "\t\t\t\t\"createDate\": \"三天前\",\n" +
            "\t\t\t\t\"replyList\": []\n" +
            "\t\t\t}\n" +
            "\t\t]\n" +
            "\t}\n" +
            "}";
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_detail);
        ButterKnife.inject(this);
        initView();

    }
    private  void initView(){
        Glide.with(this).load(p3Adress).into(avatarImage);
        Glide.with(this).load(p4Adress).into(travelPicture);
        articleContent.setText(testContent);
        btComment.setOnClickListener(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        collapsingToolbarLayout.setTitle("攻略详情");
        commentDetailArrayList = generateTestData();
        Log.d(TAG,"是空的码"+commentDetailArrayList.get(0).content+"");
        initExpandableListView(commentDetailArrayList);
    }

    /**
     *  初始化评论和回复列表
     * @param
     */
     private void initExpandableListView(final ArrayList<CommentDetail> c){
         expandableListView.setGroupIndicator(null);
         //默认展开所有回复

         adapter = new CommentExpandAdapter(this,c);
         expandableListView.setAdapter(adapter);

         for (int i = 0; i < c.size(); i++){
             expandableListView.expandGroup(i);
         }
         expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
             @Override
             public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                 boolean isExpand = parent.isGroupExpanded(groupPosition);
                 Log.d(TAG,"onGroupClick : 当前评论id >>>" + c.get(groupPosition).id);
 //               if(isExpanded){
//                    expandableListView.collapseGroup(groupPosition);
//                }else {
//                    expandableListView.expandGroup(groupPosition, true);
//                }
                 showReplyDialog(groupPosition);
                 return true;
             }
         });
         expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
             @Override
             public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                 Log.d(TAG,"你点击了回复");
                 return false;
             }
         });
         expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
             @Override
             public void onGroupExpand(int groupPosition) {

             }
         });
     }

    /**
     * 生成测试数据
     * @param
     */
     private ArrayList<CommentDetail> generateTestData(){
         comment = new Gson().fromJson(testJson,Comment.class);
         ArrayList<CommentDetail> commentDetailsList = comment.data.list;

         return commentDetailsList;
     }
     @Override
    public boolean onOptionsItemSelected(MenuItem item){
         if( item.getItemId() == android.R.id.home){
             finish();
             return true;
         }
         return super.onOptionsItemSelected(item);

    }
    @Override
    public void onClick(View v) {
    switch (v.getId()){
        case R.id.detail_page_do_comment:
            showCommentDialog();
            break;
        default:
            break;
        }
     }
    /**
     * 弹出评论框
     */
    private void showCommentDialog(){
        dialog = new BottomSheetDialog(this);
        View commentView = LayoutInflater.from(this).inflate(R.layout.comment_dialog_layout,null);
        final EditText commentText = commentView.findViewById(R.id.dialog_comment_et);
        final Button btComment = commentView.findViewById(R.id.dialog_comment_bt);
        dialog.setContentView(commentView);
        /**
         * 解决bsd显示不全的情况
         */
         View parent = (View) commentView.getParent();
        BottomSheetBehavior behaviour = BottomSheetBehavior.from(parent);
        commentView.measure(0,0);
        behaviour.setPeekHeight(commentView.getMeasuredHeight());

        btComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String commentContent = commentText.getText().toString().trim();
                if(!commentContent.isEmpty()){

                    //commentOnWork(commentContent);
                    dialog.dismiss();
                    CommentDetail detail = new CommentDetail("小明", commentContent,"刚刚");
                    adapter.addTheCommentData(detail);
                    Toast.makeText(ArticleDetail.this,"评论成功",Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(ArticleDetail.this,"评论内容不能为空",Toast.LENGTH_SHORT).show();
                }
            }
        });
        commentText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!TextUtils.isEmpty(charSequence) && charSequence.length()>2){
                    btComment.setBackgroundColor(Color.parseColor("#FFB568"));
                }else {
                    btComment.setBackgroundColor(Color.parseColor("#D8D8D8"));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        dialog.show();
    }

    /**
     * 弹出回复框
     * @param position
     */
    private void showReplyDialog(final int position){
        dialog = new BottomSheetDialog(this);
        View commentView = LayoutInflater.from(this).inflate(R.layout.comment_dialog_layout,null);
        final EditText commentText = (EditText) commentView.findViewById(R.id.dialog_comment_et);
        final Button btComment = (Button) commentView.findViewById(R.id.dialog_comment_bt);
        commentText.setHint("回复 " + commentDetailArrayList.get(position).nickName + " 的评论:");
        dialog.setContentView(commentView);
        btComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String replyContent = commentText.getText().toString().trim();
                if(!TextUtils.isEmpty(replyContent)){

                    dialog.dismiss();
                    ReplyDetail detailBean = new ReplyDetail("小红",replyContent);
                    adapter.addTheReplyData(detailBean, position);
                    expandableListView.expandGroup(position);
                    Toast.makeText(ArticleDetail.this,"回复成功",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(ArticleDetail.this,"回复内容不能为空",Toast.LENGTH_SHORT).show();
                }
            }
        });
        commentText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!TextUtils.isEmpty(charSequence) && charSequence.length()>2){
                    btComment.setBackgroundColor(Color.parseColor("#FFB568"));
                }else {
                    btComment.setBackgroundColor(Color.parseColor("#D8D8D8"));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        dialog.show();
    }
}
