package cn.misection.miscourse.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cn.misection.miscourse.R;
import cn.misection.miscourse.adapter.PlayHistoryAdapter;
import cn.misection.miscourse.bean.VideoBean;
import cn.misection.miscourse.util.DBHelper;
import cn.misection.miscourse.util.SPLoginInfo;

import java.util.ArrayList;
import java.util.List;

public class PlayHistoryActivity extends AppCompatActivity {
    private TextView tvMainTitle, tvBack, tvNone;
    private RelativeLayout rlTitleBar;
    private ListView lvList;
    private PlayHistoryAdapter adapter;
    private List<VideoBean> vb1;
    private DBHelper db;
    SPLoginInfo spLoginInfo;

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_history);
        // 设置此界面为竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        db = DBHelper.getInstance(this);
        vb1 = new ArrayList<>();
        // 从数据库中获取播放记录信息
        spLoginInfo = new SPLoginInfo(this);
        vb1 = db.getVideoHistory(spLoginInfo.getLoginUsername());
        init();
    }

    private void init() {
        tvMainTitle = findViewById(R.id.tv_main_title);
        tvMainTitle.setText("播放记录");
        rlTitleBar = findViewById(R.id.title_bar);
        rlTitleBar.setBackgroundColor(Color.parseColor("#30b4ff"));
        tvBack = findViewById(R.id.text_view_back);
        lvList = findViewById(R.id.lv_list);
        tvNone = findViewById(R.id.tv_none);
        if (vb1.size() == 0) {
            tvNone.setVisibility(View.VISIBLE);
        }
        adapter = new PlayHistoryAdapter(this);
        adapter.setData(vb1);
        lvList.setAdapter(adapter);
        // 后退按钮的点击事件
        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlayHistoryActivity.this.finish();
            }
        });
    }
}
