package cn.misection.miscourse.ui.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import cn.misection.miscourse.R;
import cn.misection.miscourse.ui.adapter.VideoListAdapter;
import cn.misection.miscourse.entity.VideoBean;
import cn.misection.miscourse.util.DataBaseHelper;
import cn.misection.miscourse.util.SharedPreferLoginInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class VideoListActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tvIntro, tvVideo, tvChapterIntro;
    private ListView lvVideoList;
    private ScrollView svChapterIntro;
    private VideoListAdapter adapter;
    private List<VideoBean> videoList;
    private int chapterId;
    private String intro;
    private DataBaseHelper db;
    SharedPreferLoginInfo sharedPreferLoginInfo;

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_list);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        // 从课程界面传递过来的章节Id
        chapterId = getIntent().getIntExtra("id", 0);
        // 从课程界面传递过来的章简介
        intro = getIntent().getStringExtra("intro");
        // 创建数据库工具类的对象
        db = DataBaseHelper.getInstance(VideoListActivity.this);
        sharedPreferLoginInfo = new SharedPreferLoginInfo(this);
        initData();
        init();
    }

    private void init() {
        tvIntro = findViewById(R.id.tv_intro);
        tvVideo = findViewById(R.id.tv_video);
        lvVideoList = findViewById(R.id.lv_video_list);
        tvChapterIntro = findViewById(R.id.tv_chapter_intro);
        svChapterIntro = findViewById(R.id.sv_chapter_intro);
        adapter = new VideoListAdapter(this, new VideoListAdapter.OnSelectListener() {
            @Override
            public void onSelect(int position, ImageView imageView) {
                adapter.setSelectedPosition(position);
                VideoBean bean = videoList.get(position);
                String videoPath = bean.getVideoPath();
                adapter.notifyDataSetChanged();  // 更新列表框
                if (TextUtils.isEmpty(videoPath)) {
                    Toast.makeText(VideoListActivity.this, "本地没有此视频，暂无法播放", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    // 判断用户是否登录，若登录则把此视频添加到数据库
                    if (sharedPreferLoginInfo.hasLogin()) {
                        String username = sharedPreferLoginInfo.getLoginUsername();
                        db.saveVideoPlayList(videoList.get(position), username);
                    }
                    // 跳转到视频播放界面
                    Intent intent = new Intent(VideoListActivity.this, VideoPlayActivity.class);
                    intent.putExtra("videoPath", videoPath);
                    intent.putExtra("position", position);
                    startActivityForResult(intent, 1);
                }
            }
        });
        lvVideoList.setAdapter(adapter);
        tvIntro.setOnClickListener(this);
        tvVideo.setOnClickListener(this);
        adapter.setData(videoList);
        tvChapterIntro.setText(intro);
        tvIntro.setBackgroundColor(Color.parseColor("#30B4FF"));
        tvVideo.setBackgroundColor(Color.parseColor("#FFFFFF"));
        tvIntro.setTextColor(Color.parseColor("#FFFFFF"));
        tvVideo.setTextColor(Color.parseColor("#000000"));
    }

    private void initData() {
        JSONArray jsonArray;
        InputStream is = null;
        try {
            is = getResources().getAssets().open("data.json");
            jsonArray = new JSONArray(read(is));
            videoList = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                VideoBean bean = new VideoBean();
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                if (jsonObject.getInt("chapterId") == chapterId) {
                    bean.setChapterId(jsonObject.getInt("chapterId"));
                    bean.setVideoId(Integer.parseInt(jsonObject.getString("videoId")));
                    bean.setTitle(jsonObject.getString("title"));
                    bean.setSecondTitle(jsonObject.getString("secondTitle"));
                    bean.setVideoPath(jsonObject.getString("videoPath"));
                    videoList.add(bean);
                }
                bean = null;
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

    private String read(InputStream is) {
        BufferedReader reader = null;
        StringBuilder sb = null;
        String line = null;
        try {
            sb = new StringBuilder();  // 实例化一个StringBuilder对象
            // 用InputStreamReader把is这个字节流转成字符流BufferedReader
            reader = new BufferedReader(new InputStreamReader(is));
            while ((line = reader.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_intro:  // 简介
                lvVideoList.setVisibility(View.GONE);
                svChapterIntro.setVisibility(View.VISIBLE);
                tvIntro.setBackgroundColor(Color.parseColor("#30B4FF"));
                tvVideo.setBackgroundColor(Color.parseColor("#FFFFFF"));
                tvIntro.setTextColor(Color.parseColor("#FFFFFF"));
                tvVideo.setTextColor(Color.parseColor("#000000"));
                break;
            case R.id.tv_video:  // 视频
                lvVideoList.setVisibility(View.VISIBLE);
                svChapterIntro.setVisibility(View.GONE);
                tvIntro.setBackgroundColor(Color.parseColor("#FFFFFF"));
                tvVideo.setBackgroundColor(Color.parseColor("#30B4FF"));
                tvIntro.setTextColor(Color.parseColor("#000000"));
                tvVideo.setTextColor(Color.parseColor("#FFFFFF"));
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            // 接收播放界面回传过来的被选中的视频的位置
            int position = data.getIntExtra("position", 0);
            // 设置被选中的位置
            adapter.setSelectedPosition(position);
            // 视频选项卡被选中时所有图标的颜色值
            lvVideoList.setVisibility(View.VISIBLE);
            svChapterIntro.setVisibility(View.GONE);
            tvIntro.setBackgroundColor(Color.parseColor("#FFFFFF"));
            tvVideo.setBackgroundColor(Color.parseColor("#30B4FF"));
            tvIntro.setTextColor(Color.parseColor("#000000"));
            tvVideo.setTextColor(Color.parseColor("#FFFFFF"));
        }
    }
}