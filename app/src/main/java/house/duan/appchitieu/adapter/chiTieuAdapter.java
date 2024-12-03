package house.duan.appchitieu.adapter;

import android.app.DatePickerDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import house.duan.appchitieu.R;
import house.duan.appchitieu.dao.chiTieuDAO;
import house.duan.appchitieu.model.chiTieu;

public class chiTieuAdapter extends RecyclerView.Adapter<chiTieuAdapter.ItemViewHolder> {
    private List<chiTieu> items;
    private Context context;
    private chiTieuDAO chiTieuDAO;

    public chiTieuAdapter(Context context, List<chiTieu> items, chiTieuDAO chiTieuDAO) {
        this.context = context;
        this.items = items;
        this.chiTieuDAO = chiTieuDAO;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_view, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        chiTieu item = items.get(position);

        holder.name.setText(item.getName());
        holder.price.setText(String.valueOf(item.getPrice()));
        holder.note.setText(item.getNote());
        holder.date.setText(item.getDate()); // Hiển thị ngày chi tiêu

        // Sự kiện chọn ngày cho TextView date
        holder.date.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

            // Tạo DatePickerDialog để chọn ngày
            DatePickerDialog datePickerDialog = new DatePickerDialog(context,
                    (view, year1, month1, dayOfMonth1) -> {
                        // Chuyển đổi ngày thành định dạng yyyy-MM-dd
                        String selectedDate = year1 + "-" + (month1 + 1) + "-" + dayOfMonth1;
                        // Hiển thị ngày đã chọn vào TextView
                        holder.date.setText(selectedDate);
                        // Cập nhật ngày vào đối tượng chiTieu
                        item.setDate(selectedDate);

                        // Cập nhật chi tiêu trong cơ sở dữ liệu
                        boolean updateSuccess = chiTieuDAO.updateItem(item.getId(), item.getName(), item.getPrice(), item.getNote(), selectedDate);

                        if (updateSuccess) {
                            Toast.makeText(context, "Ngày chi tiêu đã được cập nhật", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Cập nhật ngày chi tiêu thất bại", Toast.LENGTH_SHORT).show();
                        }

                        // Cập nhật lại RecyclerView
                        notifyItemChanged(position);
                    }, year, month, dayOfMonth);
            datePickerDialog.show();
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    // Thêm một phương thức để thêm chi tiêu mới vào RecyclerView và cơ sở dữ liệu
    public void addItem(chiTieu newItem) {
        items.add(newItem);
        notifyItemInserted(items.size() - 1);

        // Cập nhật vào cơ sở dữ liệu
        boolean success = chiTieuDAO.insertItem(newItem);
        if (!success) {
            Toast.makeText(context, "Thêm chi tiêu thất bại!", Toast.LENGTH_SHORT).show();
        }
    }

    // ViewHolder để giữ các thành phần
    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView name, price, note, date;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.txt_ten);
            price = itemView.findViewById(R.id.txt_gia);
            note = itemView.findViewById(R.id.txt_ghichu);
            date = itemView.findViewById(R.id.txt_ngay);  // TextView cho ngày chi tiêu
        }
    }
}
