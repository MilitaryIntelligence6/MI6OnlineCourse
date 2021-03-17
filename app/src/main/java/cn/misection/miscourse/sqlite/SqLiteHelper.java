package cn.misection.miscourse.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

/**
 * @author Administrator
 */
public class SqLiteHelper extends SQLiteOpenHelper
{
    private static final String DB_NAME = "bxg.db";

    private static final int DB_VERSION = 1;

    /**
     * 用户表;
     */
    public static final String TB_USER_INFO = "userinfo";

    /**
     * 视频播放表;
     */
    public static final String U_VIDEO_PLAY_LIST = "videoplaylist";

    public SqLiteHelper(@Nullable Context context)
    {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(String.format("create table if not exists " +
                        "%s( _id Integer primary key autoincrement," +
                        "username varchar,nickname varchar,sex varchar,signature varchar )",
                TB_USER_INFO)
        );
        db.execSQL(String.format("create table if not exists " +
                        "%s(_id Integer primary key autoincrement," +
                        "username varchar,chapterId int,videoId int," +
                        "videoPath varchar,title varchar,secondTitle varchar )",
                U_VIDEO_PLAY_LIST)
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL(String.format("drop table if exists %s", TB_USER_INFO));
        db.execSQL(String.format("drop table if exists %s", U_VIDEO_PLAY_LIST));
        onCreate(db);
    }
}
