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

public class VideoListActivity extends AppCompatActivity implements View.OnClickListener
{
    private TextView introTextView;

    private TextView videoTextView;

    private TextView chapterIntroTextView;

    private ListView VideoListView;

    private ScrollView chapterIntroScrollView;

    private VideoListAdapter adapter;

    private List<VideoBean> videoList;

    private int chapterId;

    private String intro;

    private DataBaseHelper dataBaseHelper;

    private SharedPreferLoginInfo sharedPreferLoginInfo;

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_list);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        // 从课程界面传递过来的章节Id
        chapterId = getIntent().getIntExtra("id", 0);
        // 从课程界面传递过来的章简介
        intro = getIntent().getStringExtra("intro");
        // 创建数据库工具类的对象
        dataBaseHelper = DataBaseHelper.getInstance(VideoListActivity.this);
        sharedPreferLoginInfo = new SharedPreferLoginInfo(this);
        initData();
        init();
    }

    private void init()
    {
        introTextView = findViewById(R.id.intro_text_view);
        videoTextView = findViewById(R.id.video_text_view);
        VideoListView = findViewById(R.id.video_list_view);
        chapterIntroTextView = findViewById(R.id.chapter_intro_text_view);
        chapterIntroScrollView = findViewById(R.id.chapter_intro_scroll_view);
        adapter = new VideoListAdapter(this, new VideoListAdapter.OnSelectListener()
        {
            @Override
            public void onSelect(int position, ImageView imageView)
            {
                adapter.setSelectedPosition(position);
                VideoBean bean = videoList.get(position);
                String videoPath = bean.getVideoPath();
                adapter.notifyDataSetChanged();  // 更新列表框
                if (TextUtils.isEmpty(videoPath))
                {
                    Toast.makeText(VideoListActivity.this, "本地没有此视频，暂无法播放", Toast.LENGTH_SHORT).show();
                    return;
                }
                else
                {
                    // 判断用户是否登录，若登录则把此视频添加到数据库
                    if (sharedPreferLoginInfo.hasLogin())
                    {
                        String username = sharedPreferLoginInfo.getLoginUsername();
                        dataBaseHelper.saveVideoPlayList(videoList.get(position), username);
                    }
                    // 跳转到视频播放界面
                    Intent intent = new Intent(VideoListActivity.this, VideoPlayActivity.class);
                    intent.putExtra("videoPath", videoPath);
                    intent.putExtra("position", position);
                    startActivityForResult(intent, 1);
                }
            }
        });
        VideoListView.setAdapter(adapter);
        introTextView.setOnClickListener(this);
        videoTextView.setOnClickListener(this);
        adapter.setData(videoList);
        chapterIntroTextView.setText(intro);
        introTextView.setBackgroundColor(Color.parseColor("#30B4FF"));
        videoTextView.setBackgroundColor(Color.parseColor("#FFFFFF"));
        introTextView.setTextColor(Color.parseColor("#FFFFFF"));
        videoTextView.setTextColor(Color.parseColor("#000000"));
    }

    private void initData()
    {
        JSONArray jsonArray;
        InputStream is = null;
        try
        {
            is = getResources().getAssets().open("data.json");
            jsonArray = new JSONArray(read(is));
            videoList = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++)
            {
                VideoBean bean = new VideoBean();
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                if (jsonObject.getInt("chapterId") == chapterId)
                {
                    bean.setChapterId(jsonObject.getInt("chapterId"));
                    bean.setVideoId(Integer.parseInt(jsonObject.getString("videoId")));
                    bean.setTitle(jsonObject.getString("title"));
                    bean.setSecondTitle(jsonObject.getString("secondTitle"));
                    bean.setVideoPath(jsonObject.getString("videoPath"));
                    videoList.add(bean);
                }
                bean = null;
            }
        }
        catch (IOException | JSONException e)
        {
            e.printStackTrace();
        }
    }

    private String read(InputStream is)
    {
        BufferedReader reader = null;
        StringBuilder sb = null;
        String line = null;
        try
        {
            sb = new StringBuilder();  // 实例化一个StringBuilder对象
            // 用InputStreamReader把is这个字节流转成字符流BufferedReader
            reader = new BufferedReader(new InputStreamReader(is));
            while ((line = reader.readLine()) != null)
            {
                sb.append(line);
                sb.append("\n");
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return "";
        }
        finally
        {
            try
            {
                if (is != null)
                {
                    is.close();
                }
                if (reader != null)
                {
                    reader.close();
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.intro_text_view:  // 简介
                VideoListView.setVisibility(View.GONE);
                chapterIntroScrollView.setVisibility(View.VISIBLE);
                introTextView.setBackgroundColor(Color.parseColor("#30B4FF"));
                videoTextView.setBackgroundColor(Color.parseColor("#FFFFFF"));
                introTextView.setTextColor(Color.parseColor("#FFFFFF"));
                videoTextView.setTextColor(Color.parseColor("#000000"));
                break;
            case R.id.video_text_view:  // 视频
                VideoListView.setVisibility(View.VISIBLE);
                chapterIntroScrollView.setVisibility(View.GONE);
                introTextView.setBackgroundColor(Color.parseColor("#FFFFFF"));
                videoTextView.setBackgroundColor(Color.parseColor("#30B4FF"));
                introTextView.setTextColor(Color.parseColor("#000000"));
                videoTextView.setTextColor(Color.parseColor("#FFFFFF"));
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null)
        {
            // 接收播放界面回传过来的被选中的视频的位置
            int position = data.getIntExtra("position", 0);
            // 设置被选中的位置
            adapter.setSelectedPosition(position);
            // 视频选项卡被选中时所有图标的颜色值
            VideoListView.setVisibility(View.VISIBLE);
            chapterIntroScrollView.setVisibility(View.GONE);
            introTextView.setBackgroundColor(Color.parseColor("#FFFFFF"));
            videoTextView.setBackgroundColor(Color.parseColor("#30B4FF"));
            introTextView.setTextColor(Color.parseColor("#000000"));
            videoTextView.setTextColor(Color.parseColor("#FFFFFF"));
        }
    }
}
