package house.duan.appchitieu.model;

import android.os.Parcel;
import android.os.Parcelable;

public class chiTieu implements Parcelable {
    private int id;
    private String name;
    private double price;
    private String note;

    public chiTieu() {
    }

    public chiTieu(int id, String name, double price, String note) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.note = note;
    }

    public chiTieu(String name, double price, String note) {
        this.name = name;
        this.price = price;
        this.note = note;
    }

    // Phương thức đọc dữ liệu từ Parcel
    protected chiTieu(Parcel in) {
        id = in.readInt();
        name = in.readString();
        price = in.readDouble();
        note = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeDouble(price);
        dest.writeString(note);
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
}
