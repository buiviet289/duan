package house.duan.appchitieu.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import house.duan.appchitieu.model.chiTieu;
import house.duan.appchitieu.database.QuuanLyChiTieuSQLite;

public class chiTieuDAO {
    private QuuanLyChiTieuSQLite dbHelper;

    public chiTieuDAO(Context context) {
        dbHelper = new QuuanLyChiTieuSQLite(context);
    }

    public boolean insertItem(chiTieu chiTieu) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("ten", chiTieu.getName());
        values.put("gia", chiTieu.getPrice());
        values.put("ghi_chu", chiTieu.getNote());
        long check =  db.insert("chitieus", null, values);
        if(check == -1){
            return false;
        }
        return true;
    }

    public int updateItem(int id, String name, double price, String note) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("ten", name);
        values.put("gia", price);
        values.put("ghi_chu", note);
        return db.update("chitieus", values, "id=?", new String[]{String.valueOf(id)});
    }

    public int deleteItem(int id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        return db.delete("chitieus", "id=?", new String[]{String.valueOf(id)});
    }

    public List<chiTieu> getAllItems() {
        List<chiTieu> items = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM chitieus", null);
        if (cursor.moveToFirst()) {
            do {
                chiTieu item = new chiTieu(
                        cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                        cursor.getString(cursor.getColumnIndexOrThrow("ten")),
                        cursor.getDouble(cursor.getColumnIndexOrThrow("gia")),
                        cursor.getString(cursor.getColumnIndexOrThrow("ghi_chu"))
                );
                items.add(item);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return items;
    }
}
