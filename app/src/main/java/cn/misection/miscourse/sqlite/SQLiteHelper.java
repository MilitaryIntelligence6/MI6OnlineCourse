package cn.misection.miscourse.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLiteHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "bxg.db";
    private static final int DB_VERSION = 1;
    public static final String TB_USERINFO = "userinfo";  // 用户表
    public static final String U_VIDEO_PLAY_LIST = "videoplaylist";  // 视频播放表

    public SQLiteHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists " + TB_USERINFO + "( "
                + "_id Integer primary key autoincrement,"
                + "username varchar,"
                + "nickname varchar,"
                + "sex varchar,"
                + "signature varchar )"
        );
        db.execSQL("create table if not exists " + U_VIDEO_PLAY_LIST + "("
                + "_id Integer primary key autoincrement,"
                + "username varchar,"
                + "chapterId int,"
                + "videoId int,"
                + "videoPath varchar,"
                + "title varchar,"
                + "secondTitle varchar )"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TB_USERINFO);
        db.execSQL("drop table if exists " + U_VIDEO_PLAY_LIST);
        onCreate(db);
    }
}
