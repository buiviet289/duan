package house.duan.appchitieu.model;

public class nguoidung {
    private String MAND;
    private String HOTEN;
    private String MATKHAU;

    public nguoidung() {
    }

    public nguoidung(String MAND, String HOTEN, String MATKHAU) {
        this.MAND = MAND;
        this.HOTEN = HOTEN;
        this.MATKHAU = MATKHAU;
    }

    public String getMAND() {
        return MAND;
    }

    public void setMAND(String MAND) {
        this.MAND = MAND;
    }

    public String getHOTEN() {
        return HOTEN;
    }

    public void setHOTEN(String HOTEN) {
        this.HOTEN = HOTEN;
    }

    public String getMATKHAU() {
        return MATKHAU;
    }

    public void setMATKHAU(String MATKHAU) {
        this.MATKHAU = MATKHAU;
    }
}
