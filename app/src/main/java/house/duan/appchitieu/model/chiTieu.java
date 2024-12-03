package house.duan.appchitieu.model;

import android.os.Parcel;
import android.os.Parcelable;

public class chiTieu implements Parcelable {
    private int id;
    private String name;
    private double price;
    private String note;
    private String date;  // Cột ngày mới

    public chiTieu() {
    }

    public chiTieu(int id, String name, double price, String note, String date) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.note = note;
        this.date = date;  // Khởi tạo cột ngày
    }

    public chiTieu(String name, double price, String note, String date) {
        this.name = name;
        this.price = price;
        this.note = note;
        this.date = date;  // Khởi tạo cột ngày
    }

    // Phương thức đọc dữ liệu từ Parcel
    protected chiTieu(Parcel in) {
        id = in.readInt();
        name = in.readString();
        price = in.readDouble();
        note = in.readString();
        date = in.readString();  // Đọc giá trị ngày từ Parcel
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeDouble(price);
        dest.writeString(note);
        dest.writeString(date);  // Ghi giá trị ngày vào Parcel
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<chiTieu> CREATOR = new Creator<chiTieu>() {
        @Override
        public chiTieu createFromParcel(Parcel in) {
            return new chiTieu(in);
        }

        @Override
        public chiTieu[] newArray(int size) {
            return new chiTieu[size];
        }
    };

    // Getters và setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getDate() {
        return date;  // Getter cho ngày
    }

    public void setDate(String date) {
        this.date = date;  // Setter cho ngày
    }
}
