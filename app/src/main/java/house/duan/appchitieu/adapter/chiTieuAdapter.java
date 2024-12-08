package house.duan.appchitieu.adapter;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import house.duan.appchitieu.EditChiTieuActivity;
import house.duan.appchitieu.R;
import house.duan.appchitieu.dao.chiTieuDAO;
import house.duan.appchitieu.model.chiTieu;

public class chiTieuAdapter extends RecyclerView.Adapter<chiTieuAdapter.ItemViewHolder> {
    private List<chiTieu> items;
    private final Context context;
    private final chiTieuDAO chiTieuDAO;
    private final SimpleDateFormat dateFormat;

    public chiTieuAdapter(Context context, List<chiTieu> items, chiTieuDAO chiTieuDAO) {
        this.context = context;
        this.items = items;
        this.chiTieuDAO = chiTieuDAO;
        this.dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
    }
    public void updateList(List<chiTieu> newList) {
        items.clear();
        items.addAll(newList);
        notifyDataSetChanged();
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

        holder.name.setText(item.getName() != null ? item.getName() : "Không có tên");
        holder.price.setText(String.valueOf(item.getPrice()));
        holder.note.setText(item.getNote() != null ? item.getNote() : "Không có ghi chú");
        holder.ngay.setText(item.getDate() != null ? item.getDate() : "Chưa chọn ngày");

        // Sự kiện nhấn vào item để chuyển sang activity sửa
        holder.itemView.setOnClickListener(v -> {
            // Chuyển tới activity sửa
            Intent intent = new Intent(context, EditChiTieuActivity.class);
            context.startActivity(intent); // Chuyển thẳng tới activity sửa
        });
    }

//    @Override
//    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
//        chiTieu item = items.get(position);
//
//        holder.name.setText(item.getName() != null ? item.getName() : "Không có tên");
//        holder.price.setText(String.valueOf(item.getPrice()));
//        holder.note.setText(item.getNote() != null ? item.getNote() : "Không có ghi chú");
//        holder.ngay.setText(item.getDate() != null ? item.getDate() : "Chưa chọn ngày");
//
//
//        // Sự kiện chọn ngày cho TextView date
//        holder.ngay.setOnClickListener(v -> {
//            Calendar calendar = Calendar.getInstance();
//
//            // Nếu item có ngày hiện tại, đặt làm ngày mặc định cho DatePickerDialog
//            if (item.getDate() != null) {
//                try {
//                    Date currentDate = dateFormat.parse(item.getDate());
//                    if (currentDate != null) {
//                        calendar.setTime(currentDate);
//                    }
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            int year = calendar.get(Calendar.YEAR);
//            int month = calendar.get(Calendar.MONTH);
//            int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
//
//            DatePickerDialog datePickerDialog = new DatePickerDialog(context, (view, year1, month1, dayOfMonth1) -> {
//                // Chuyển đổi ngày thành định dạng yyyy-MM-dd
//                calendar.set(year1, month1, dayOfMonth1);
//                String selectedDate = dateFormat.format(calendar.getTime());
//
//                // Hiển thị ngày đã chọn vào TextView
//                holder.ngay.setText(selectedDate);
//
//                // Cập nhật ngày vào đối tượng chiTieu
//                item.setDate(selectedDate);
//
//                // Cập nhật chi tiêu trong cơ sở dữ liệu
//                boolean updateSuccess = chiTieuDAO.updateItem(item.getId(), item.getName(), item.getPrice(), item.getNote(), selectedDate);
//
//                if (updateSuccess) {
//                    Toast.makeText(context, "Ngày chi tiêu đã được cập nhật", Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(context, "Cập nhật ngày chi tiêu thất bại", Toast.LENGTH_SHORT).show();
//                }
//
//                // Cập nhật lại RecyclerView
//                notifyItemChanged(position);
//            }, year, month, dayOfMonth);
//
//            datePickerDialog.show();
//        });
//    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    // Thêm một phương thức để thêm chi tiêu mới vào RecyclerView và cơ sở dữ liệu
    public void addItem(chiTieu newItem) {
        // Thêm item vào danh sách và thông báo RecyclerView
        items.add(newItem);
        notifyItemInserted(items.size() - 1);

        // Cập nhật vào cơ sở dữ liệu
        boolean success = chiTieuDAO.insertItem(newItem);
        if (!success) {
            Toast.makeText(context, "Thêm chi tiêu thất bại!", Toast.LENGTH_SHORT).show();
        }
    }

    // Đặt lại danh sách items và thông báo RecyclerView
    public void setItems(List<chiTieu> newItems) {
        this.items = newItems;
        notifyDataSetChanged();
    }

    // ViewHolder để giữ các thành phần
    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView name, price, note, ngay;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.txt_ten);
            price = itemView.findViewById(R.id.txt_gia);
            note = itemView.findViewById(R.id.txt_ghichu);
            ngay = itemView.findViewById(R.id.txt_ngay);
        }
    }
    public void removeItem(int position) {
        chiTieu item = items.get(position);
        boolean success = chiTieuDAO.deleteItem(item.getId());
        if (success) {
            items.remove(position);
            notifyItemRemoved(position);
            Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
        }
    }


}