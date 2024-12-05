package house.duan.appchitieu.dao;

import static android.app.DownloadManager.COLUMN_ID;
import static house.duan.appchitieu.database.QuuanLyChiTieuSQLite.COLUMN_NAME;
import static house.duan.appchitieu.database.QuuanLyChiTieuSQLite.COLUMN_PRICE;
import static house.duan.appchitieu.database.QuuanLyChiTieuSQLite.COLUMN_NOTE;

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

    // Phương thức thêm chi tiêu (Cập nhật thêm cột ngày)
    public boolean insertItem(chiTieu chiTieu) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("ten", chiTieu.getName());
        values.put("gia", chiTieu.getPrice());
        values.put("ghi_chu", chiTieu.getNote());
        values.put("ngay", chiTieu.getDate());  // Thêm ngày vào bảng

        long result = db.insert("chitieus", null, values);
        db.close(); // Đảm bảo đóng database sau khi thao tác
        return result != -1;
    }

    // Phương thức cập nhật chi tiêu (Cập nhật ngày nếu cần)
    public boolean updateItem(int id, String name, double price, String note, String ngay) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("ten", name);
        values.put("gia", price);
        values.put("ghi_chu", note);
        values.put("ngay", ngay);  // Cập nhật ngày

        // Cập nhật theo id
        int rowsUpdated = db.update("chitieus", values, "id=?", new String[]{String.valueOf(id)});
        db.close(); // Đảm bảo đóng database sau khi thao tác
        return rowsUpdated > 0; // Trả về true nếu có ít nhất 1 dòng bị ảnh hưởng
    }

    // Phương thức xóa chi tiêu
    public boolean deleteItem(int id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int rowsDeleted = db.delete("chitieus", "id=?", new String[]{String.valueOf(id)});
        db.close(); // Đảm bảo đóng database sau khi thao tác
        return rowsDeleted > 0; // Trả về true nếu có ít nhất 1 dòng bị ảnh hưởng
    }

    // Phương thức lấy tất cả các chi tiêu (Cập nhật để lấy ngày)
    public List<chiTieu> getAllItems() {
        List<chiTieu> items = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = null;

        try {
            cursor = db.rawQuery("SELECT * FROM chitieus", null);
            if (cursor.moveToFirst()) {
                do {
                    chiTieu item = new chiTieu(
                            cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)),  // Lấy id là int
                            cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME)),
                            cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_PRICE)),
                            cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NOTE)),
                            cursor.getString(cursor.getColumnIndexOrThrow("ngay"))  // Lấy ngày là String
                    );
                    items.add(item);
                } while (cursor.moveToNext());
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }
        return items;
    }
}
