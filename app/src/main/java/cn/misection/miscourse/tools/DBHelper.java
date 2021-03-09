package cn.misection.miscourse.tools;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import cn.misection.miscourse.bean.UserBean;
import cn.misection.miscourse.bean.VideoBean;
import cn.misection.miscourse.sqlite.SQLiteHelper;

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
        cv.put("username", bean.getUsername());
        cv.put("nickname", bean.getNickname());
        cv.put("sex", bean.getSex());
        cv.put("signature", bean.getSignature());
        db.insert(SQLiteHelper.TB_USERINFO, null, cv);
    }

    // 获取个人资料
    public UserBean getUserInfo(String username) {
        String sql = "select * from " + SQLiteHelper.TB_USERINFO + " where username = ?";
        Cursor cursor = db.rawQuery(sql, new String[]{username});
        UserBean bean = null;
        while (cursor.moveToNext()) {
            bean = new UserBean();
            bean.setUsername(cursor.getString(cursor.getColumnIndex("username")));
            bean.setNickname(cursor.getString(cursor.getColumnIndex("nickname")));
            bean.setSex(cursor.getString(cursor.getColumnIndex("sex")));
            bean.setSignature(cursor.getString(cursor.getColumnIndex("signature")));
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
        if (hasVideoPlay(videoBean.getChapterId(), videoBean.getVideoId(), username)) {
            // 删除之前存入的播放记录
            boolean isDelete = delVideoPlay(videoBean.getChapterId(), videoBean.getVideoId(), username);
            if (!isDelete) {
                // 没有删除成功时，则需跳出此方法不再执行下面的语句
                return;
            }
        }
        ContentValues cv = new ContentValues();
        cv.put("username", username);
        cv.put("chapterId", videoBean.getChapterId());
        cv.put("videoId", videoBean.getVideoId());
        cv.put("videoPath", videoBean.getVideoPath());
        cv.put("title", videoBean.getTitle());
        cv.put("secondTitle", videoBean.getSecondTitle());
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
            bean.setChapterId(cursor.getInt(cursor.getColumnIndex("chapterId")));
            bean.setVideoId(cursor.getInt(cursor.getColumnIndex("videoId")));
            bean.setVideoPath(cursor.getString(cursor.getColumnIndex("videoPath")));
            bean.setTitle(cursor.getString(cursor.getColumnIndex("title")));
            bean.setSecondTitle(cursor.getString(cursor.getColumnIndex("secondTitle")));
            vb1.add(bean);
            bean = null;
        }
        cursor.close();
        return vb1;
    }
}
