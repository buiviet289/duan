    package house.duan.appchitieu.database;

    import android.content.Context;
    import android.database.Cursor;
    import android.database.sqlite.SQLiteDatabase;
    import android.database.sqlite.SQLiteOpenHelper;

    public class QuuanLyChiTieuSQLite extends SQLiteOpenHelper {
        private static final String DATABASE_NAME = "QL.db";
        private static final int DATABASE_VERSION = 3;  // Cập nhật phiên bản

        public static final String TABLE_CHITIEU = "chitieus";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_NAME = "ten";
        public static final String COLUMN_PRICE = "gia";
        public static final String COLUMN_NOTE = "ghi_chu";
        public static final String COLUMN_DATE = "ngay";  // Cột mới cho ngày

        private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_CHITIEU + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_PRICE + " REAL, " +
                COLUMN_NOTE + " TEXT, " +
                COLUMN_DATE + " TEXT)";  // Thêm cột ngày

        public QuuanLyChiTieuSQLite(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_TABLE);  // Tạo bảng với cột mới
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            if (oldVersion < 3) {
                // Nếu đang nâng cấp lên phiên bản 3, thêm cột 'ngay'
                db.execSQL("ALTER TABLE " + TABLE_CHITIEU + " ADD COLUMN " + COLUMN_DATE + " TEXT");
            }
        }
    }
