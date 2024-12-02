package house.duan.appchitieu.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import house.duan.appchitieu.model.nguoidung;
import house.duan.appchitieu.database.QuanLyNguoiDungSqlite;

public class nguoiDungDAO {
    //goi lai sqlite
    private SQLiteDatabase sqLiteDatabase;
    QuanLyNguoiDungSqlite quanLyChiTieuSqlite;
    public nguoiDungDAO(Context context){
        quanLyChiTieuSqlite = new QuanLyNguoiDungSqlite(context);
        sqLiteDatabase = quanLyChiTieuSqlite.getWritableDatabase();
    }
    public long insertThuThu(nguoidung tt){
        // Tao contentvalues
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", tt.getMAND());
        contentValues.put("username", tt.getHOTEN());
        contentValues.put("password", tt.getMATKHAU());
        return  sqLiteDatabase.insert("users", null, contentValues);

    }
    public long updatePass(nguoidung tt){
        //Tao contentvalues
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", tt.getHOTEN());
        contentValues.put("password", tt.getMATKHAU());
        return sqLiteDatabase.update("users",contentValues, "id = ?", new String[]{tt.getMAND()});
    }
    public long deleteThuThu(String id){
        return sqLiteDatabase.delete("users", "id = ?", new String[]{id});
    }
    @SuppressLint("Range")
    public List<nguoidung> getData(String sql, String...selectionArgs){
        List<nguoidung> list = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()){
            nguoidung tt = new nguoidung();
            tt.setMAND(cursor.getString(cursor.getColumnIndex("id")));
            tt.setHOTEN(cursor.getString(cursor.getColumnIndex("username")));
            tt.setMATKHAU(cursor.getString(cursor.getColumnIndex("password")));
            Log.i("//=====", tt.toString());
            list.add(tt);
        }
        return list;
    }
    //get tat ca data
    public List<nguoidung> getAll(){
        String sql = "SELECT * FROM users";
        return getData(sql);
    }
    // get data theo id
    public nguoidung getID(String id){
        String sql = "SELECT * FROM users WHERE id =  ?";
        List<nguoidung> list = getData(sql, id);
        return  list.get(0);
    }
    // kiem tra login
    public int checkLogin(String id, String matkhau){
        String sql = "SELECT * FROM users WHERE id = ? AND password = ?";
        List<nguoidung> list = getData(sql, id, matkhau);
        if(list.size() ==  0){
            return -1;
        }else {
            return 1;
        }
    }


}
