package com.movile.next.tvON.database.manual;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.movile.next.tvON.database.manual.entity.FavoriteEntity;

public class tvONDbHelper extends SQLiteOpenHelper{
    public tvONDbHelper(Context context) {
        super(context, DatabaseConfiguration.NAME, null, DatabaseConfiguration.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(FavoriteEntity.FavoriteEntityFields.createSql());
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        database.execSQL(FavoriteEntity.FavoriteEntityFields.dropSql());
        onCreate(database);
    }
}
