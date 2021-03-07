package com.example.miscourse.tools;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.miscourse.bean.UserBean;
import com.example.miscourse.bean.VideoBean;
import com.example.miscourse.sqlite.SQLiteHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHelper {

    private static SQLiteHelper helper;
    private static SQLiteDatabase db;
    private static DBHelper instance = null;

    private DBHelper(Context context) {
        helper = new SQLiteHelper(context);
        db = helper.getWritableDatabase();
    }

    public static DBHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DBHelper(context);
        }
        return instance;
    }

    // 保存个人资料
    public void saveUserInfo(UserBean bean) {
        ContentValues cv = new ContentValues();
        cv.put("username", bean.username);
        cv.put("nickname", bean.nickname);
        cv.put("sex", bean.sex);
        cv.put("signature", bean.signature);
        db.insert(SQLiteHelper.TB_USERINFO, null, cv);
    }

    // 获取个人资料
    public UserBean getUserInfo(String username) {
        String sql = "select * from " + SQLiteHelper.TB_USERINFO + " where username = ?";
        Cursor cursor = db.rawQuery(sql, new String[]{username});
        UserBean bean = null;
        while (cursor.moveToNext()) {
            bean = new UserBean();
            bean.username = cursor.getString(cursor.getColumnIndex("username"));
            bean.nickname = cursor.getString(cursor.getColumnIndex("nickname"));
            bean.sex = cursor.getString(cursor.getColumnIndex("sex"));
            bean.signature = cursor.getString(cursor.getColumnIndex("signature"));
        }
        cursor.close();
        return bean;
    }

    // 更新个人资料
    public void updateUserInfo(String key, String value, String username) {
        ContentValues cv = new ContentValues();
        cv.put(key, value);
        db.update(SQLiteHelper.TB_USERINFO, cv, "username = ?", new String[]{username});
    }

    public void saveVideoPlayList(VideoBean videoBean, String username) {
        // 判断如果里面有此播放记录则需删除重新存放
        if (hasVideoPlay(videoBean.chapterId, videoBean.videoId, username)) {
            // 删除之前存入的播放记录
            boolean isDelete = delVideoPlay(videoBean.chapterId, videoBean.videoId, username);
            if (!isDelete) {
                // 没有删除成功时，则需跳出此方法不再执行下面的语句
                return;
            }
        }
        ContentValues cv = new ContentValues();
        cv.put("username", username);
        cv.put("chapterId", videoBean.chapterId);
        cv.put("videoId", videoBean.videoId);
        cv.put("videoPath", videoBean.videoPath);
        cv.put("title", videoBean.title);
        cv.put("secondTitle", videoBean.secondTitle);
        db.insert(SQLiteHelper.U_VIDEO_PLAY_LIST, null, cv);
    }

    // 删除视频
    private boolean delVideoPlay(int chapterId, int videoId, String username) {
        boolean delSuccess = false;
        int row = db.delete(SQLiteHelper.U_VIDEO_PLAY_LIST, " chapterId=? and videoId=? and username=?", new String[]{chapterId + "", videoId + "", username});
        if (row > 0) {
            delSuccess = true;
        }
        return delSuccess;
    }

    // 判断视频记录是否存在
    private boolean hasVideoPlay(int chapterId, int videoId, String username) {
        boolean hasVideo = false;
        String sql = "select * from " + SQLiteHelper.U_VIDEO_PLAY_LIST + " where chapterId=? and videoId=? and username=?";
        Cursor cursor = db.rawQuery(sql, new String[]{chapterId + "", videoId + "", username});
        if (cursor.moveToFirst()) {
            hasVideo = true;
        }
        cursor.close();
        return hasVideo;
    }

    public List<VideoBean> getVideoHistory(String loginUsername) {
        String sql = "select * from " + SQLiteHelper.U_VIDEO_PLAY_LIST + " where username=?";
        Cursor cursor = db.rawQuery(sql, new String[]{loginUsername});
        List<VideoBean> vb1 = new ArrayList<>();
        VideoBean bean = null;
        while (cursor.moveToNext()) {
            bean = new VideoBean();
            bean.chapterId = cursor.getInt(cursor.getColumnIndex("chapterId"));
            bean.videoId = cursor.getInt(cursor.getColumnIndex("videoId"));
            bean.videoPath = cursor.getString(cursor.getColumnIndex("videoPath"));
            bean.title = cursor.getString(cursor.getColumnIndex("title"));
            bean.secondTitle = cursor.getString(cursor.getColumnIndex("secondTitle"));
            vb1.add(bean);
            bean = null;
        }
        cursor.close();
        return vb1;
    }
}
