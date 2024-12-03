package house.duan.appchitieu.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import house.duan.appchitieu.model.chiTieu;

public class QuuanLyChiTieuSQLite extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "QL.db";
    private static final int DATABASE_VERSION = 2;

    private static final String TABLE_CHITIEU = "chitieus";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "ten";
    private static final String COLUMN_PRICE = "gia";
    private static final String COLUMN_NOTE = "ghi_chu";

    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_CHITIEU + " (" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_NAME + " TEXT, " +
            COLUMN_PRICE + " REAL, " +
            COLUMN_NOTE + " TEXT) ";

    public QuuanLyChiTieuSQLite(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CHITIEU);
        onCreate(db);
    }


}
