package cn.misection.miscourse.ui.activity;

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
import cn.misection.miscourse.ui.adapter.PlayHistoryAdapter;
import cn.misection.miscourse.entity.VideoBean;
import cn.misection.miscourse.util.DataBaseHelper;
import cn.misection.miscourse.util.SharedPreferLoginInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 */
public class PlayHistoryActivity extends AppCompatActivity
{
    private TextView mainTitleTextView;

    private TextView backTextView;

    private TextView nullTextView;

    private RelativeLayout titleBarRelaLayout;

    private ListView listView;

    private PlayHistoryAdapter adapter;

    private List<VideoBean> videoList;

    private DataBaseHelper dataBaseHelper;

    SharedPreferLoginInfo sharedPreferLoginInfo;

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init()
    {
        initContent();
        initData();
        initView();
        initAdapter();
        initListener();
    }

    private void initContent()
    {
        this.setContentView(R.layout.activity_play_history);
        // 设置此界面为竖屏
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    private void initData()
    {
        dataBaseHelper = DataBaseHelper.requireInstance(this);
        videoList = new ArrayList<>();
        // 从数据库中获取播放记录信息
        sharedPreferLoginInfo = new SharedPreferLoginInfo(this);
        videoList = dataBaseHelper.getVideoHistory(sharedPreferLoginInfo.getLoginUsername());
    }

    private void initView()
    {
        mainTitleTextView = findViewById(R.id.main_title_text_view);
        mainTitleTextView.setText(R.string.play_history);
        titleBarRelaLayout = findViewById(R.id.title_bar);
        titleBarRelaLayout.setBackgroundColor(Color.parseColor("#30b4ff"));
        backTextView = findViewById(R.id.back_text_view);
        listView = findViewById(R.id.list_view);
        nullTextView = findViewById(R.id.null_text_view);
        if (videoList.size() == 0)
        {
            nullTextView.setVisibility(View.VISIBLE);
        }
    }

    private void initAdapter()
    {
        adapter = new PlayHistoryAdapter(this);
        adapter.putVideoList(videoList);
        listView.setAdapter(adapter);
    }

    private void initListener()
    {
        // 后退按钮的点击事件
        backTextView.setOnClickListener((View v) ->
                PlayHistoryActivity.this.finish());
    }
}
