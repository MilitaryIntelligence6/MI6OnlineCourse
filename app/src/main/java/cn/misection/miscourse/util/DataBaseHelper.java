package cn.misection.miscourse.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import cn.misection.miscourse.constant.util.db.EnumColumn;
import cn.misection.miscourse.constant.util.db.EnumContentKey;
import cn.misection.miscourse.entity.UserBean;
import cn.misection.miscourse.entity.VideoBean;
import cn.misection.miscourse.sqlite.SqLiteHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 */
public class DataBaseHelper
{
    private Context context;

    private static SqLiteHelper helper;

    private static SQLiteDatabase database;

    private volatile static DataBaseHelper instance = null;

    private DataBaseHelper(Context context)
    {
        this.context = context;
        init();
    }

    public static DataBaseHelper requireInstance(Context context)
    {
        if (instance == null)
        {
            synchronized (DataBaseHelper.class)
            {
                if (instance == null)
                {
                    instance = new DataBaseHelper(context);
                }
            }
        }
        return instance;
    }

    private void init()
    {
        helper = new SqLiteHelper(context);
        database = helper.getWritableDatabase();
    }

    /**
     * 保存个人资料;
     * @param user
     */
    public void saveUserInfo(UserBean user)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(EnumContentKey.USERNAME.literal(), user.getUsername());
        contentValues.put(EnumContentKey.NICKNAME.literal(), user.getNickname());
        contentValues.put(EnumContentKey.SEX.literal(), user.getSex());
        contentValues.put(EnumContentKey.SIGNATURE.literal(), user.getSignature());
        database.insert(SqLiteHelper.TB_USER_INFO, null, contentValues);
    }

    /**
     * 获取个人资料;
     * @param username
     * @return
     */
    public UserBean getUserInfo(String username)
    {
        String sql = String.format("select * from %s where username = ?", SqLiteHelper.TB_USER_INFO);
        Cursor cursor = database.rawQuery(sql, new String[]{username});
        UserBean user = null;
        while (cursor.moveToNext())
        {
            user = new UserBean();
            user.setUsername(cursor.getString(cursor.getColumnIndex("username")));
            user.setNickname(cursor.getString(cursor.getColumnIndex("nickname")));
            user.setSex(cursor.getString(cursor.getColumnIndex("sex")));
            user.setSignature(cursor.getString(cursor.getColumnIndex("signature")));
        }
        cursor.close();
        return user;
    }

    /**
     * 更新个人资料;
     * @param key
     * @param value
     * @param username
     */
    public void updateUserInfo(String key, String value, String username)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(key, value);
        database.update(SqLiteHelper.TB_USER_INFO, contentValues, "username = ?", new String[]{username});
    }

    public void saveVideoPlayList(VideoBean video, String username)
    {
        // 判断如果里面有此播放记录则需删除重新存放
        if (hasVideoPlay(video.getChapterId(), video.getVideoId(), username))
        {
            // 删除之前存入的播放记录
            boolean isDelete = delVideoPlay(video.getChapterId(), video.getVideoId(), username);
            if (!isDelete)
            {
                // 没有删除成功时，则需跳出此方法不再执行下面的语句
                return;
            }
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put(EnumContentKey.USERNAME.literal(), username);
        contentValues.put(EnumContentKey.CHAPTER_ID.literal(), video.getChapterId());
        contentValues.put(EnumContentKey.VIDEO_ID.literal(), video.getVideoId());
        contentValues.put(EnumContentKey.VIDEO_PATH.literal(), video.getVideoPath());
        contentValues.put(EnumContentKey.TITLE.literal(), video.getTitle());
        contentValues.put(EnumContentKey.SECOND_TITLE.literal(), video.getSecondTitle());
        database.insert(SqLiteHelper.U_VIDEO_PLAY_LIST, null, contentValues);
    }

    // 删除视频
    private boolean delVideoPlay(int chapterId, int videoId, String username)
    {
        boolean delSuccess = false;
        int row = database.delete(SqLiteHelper.U_VIDEO_PLAY_LIST, " chapterId=? and videoId=? and username=?", new String[]{chapterId + "", videoId + "", username});
        if (row > 0)
        {
            delSuccess = true;
        }
        return delSuccess;
    }

    /**
     * 判断视频记录是否存在;
     * @param chapterId
     * @param videoId
     * @param username
     * @return
     */
    private boolean hasVideoPlay(int chapterId, int videoId, String username)
    {
        boolean hasVideo = false;
        String sql = String.format("select * from %s where chapterId=? and videoId=? and username=?", SqLiteHelper.U_VIDEO_PLAY_LIST);
        Cursor cursor = database.rawQuery(sql, new String[]{chapterId + "", videoId + "", username});
        if (cursor.moveToFirst())
        {
            hasVideo = true;
        }
        cursor.close();
        return hasVideo;
    }

    public List<VideoBean> getVideoHistory(String loginUsername)
    {
        String sql = String.format("select * from %s where username=?", SqLiteHelper.U_VIDEO_PLAY_LIST);
        Cursor cursor = database.rawQuery(sql, new String[]{loginUsername});
        List<VideoBean> videoList = new ArrayList<>();
        VideoBean video = null;
        while (cursor.moveToNext())
        {
            video = new VideoBean();
            video.setChapterId(cursor.getInt(cursor.getColumnIndex(EnumColumn.CHAPTER_ID.literal())));
            video.setVideoId(cursor.getInt(cursor.getColumnIndex(EnumColumn.VIDEO_ID.literal())));
            video.setVideoPath(cursor.getString(cursor.getColumnIndex(EnumColumn.VIDEO_PATH.literal())));
            video.setTitle(cursor.getString(cursor.getColumnIndex(EnumColumn.TITLE.literal())));
            video.setSecondTitle(cursor.getString(cursor.getColumnIndex(EnumColumn.SECOND_TITLE.literal())));
            videoList.add(video);
            video = null;
        }
        cursor.close();
        return videoList;
    }
}
