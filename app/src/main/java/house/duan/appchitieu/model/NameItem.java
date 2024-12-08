package house.duan.appchitieu.model;

public class NameItem {
    private String name;
    private boolean isExpense; // Kiểm tra xem đây là chi tiêu hay thu nhập

    public NameItem(String name, boolean isExpense) {
        this.name = name;
        this.isExpense = isExpense;
    }

    public String getName() {
        return name;
    }

    public boolean isExpense() {
        return isExpense;
    }
}
