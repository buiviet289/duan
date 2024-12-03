package house.duan.appchitieu.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import house.duan.appchitieu.R;

public class adaterchitieu extends RecyclerView.Adapter<adaterchitieu.CategoryViewHolder> {

    private List<String> adaterchitieu;

    // Constructor nhận dữ liệu danh sách
    public adaterchitieu(List<String> adaterchitieu) {
        this.adaterchitieu = adaterchitieu;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate layout item_category
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_category, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        // Gán dữ liệu vào TextView
        String category = adaterchitieu.get(position);
        holder.tvCategory.setText(category);
    }

    @Override
    public int getItemCount() {
        return adaterchitieu.size(); // Trả về số lượng danh mục
    }

    // ViewHolder cho RecyclerView
    public static class CategoryViewHolder extends RecyclerView.ViewHolder {
        TextView tvCategory;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCategory = itemView.findViewById(R.id.tvCategory);
        }

    }
}

