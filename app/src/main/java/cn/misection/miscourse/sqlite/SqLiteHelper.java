package cn.misection.miscourse.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import cn.misection.miscourse.constant.config.Macro;
import cn.misection.miscourse.constant.util.db.EnumDbInfo;

/**
 * @author Administrator
 */
public class SqLiteHelper extends SQLiteOpenHelper
{
    public SqLiteHelper(@Nullable Context context)
    {
        super(context, EnumDbInfo.DB_NAME.value(), null, Macro.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(String.format("create table if not exists " +
                        "%s( _id Integer primary key autoincrement," +
                        "username varchar,nickname varchar,sex varchar,signature varchar )",
                EnumDbInfo.TB_USER_INFO.value())
        );
        db.execSQL(String.format("create table if not exists " +
                        "%s(_id Integer primary key autoincrement," +
                        "username varchar,chapterId int,videoId int," +
                        "videoPath varchar,title varchar,secondTitle varchar )",
                EnumDbInfo.U_VIDEO_PLAY_LIST.value())
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL(String.format("drop table if exists %s", EnumDbInfo.TB_USER_INFO.value()));
        db.execSQL(String.format("drop table if exists %s", EnumDbInfo.U_VIDEO_PLAY_LIST.value()));
        onCreate(db);
    }
}
