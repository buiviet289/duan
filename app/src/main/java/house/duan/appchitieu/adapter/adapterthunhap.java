package house.duan.appchitieu.adapter;



import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import house.duan.appchitieu.R;

public class adapterthunhap extends RecyclerView.Adapter<adapterthunhap.CategoryViewHolder> {

    private List<String> adapterthunhap;

    // Constructor nhận dữ liệu danh sách
    public adapterthunhap(List<String> adapterthunhap) {
        this.adapterthunhap = adapterthunhap;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate layout item_category
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_thunhap, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        // Gán dữ liệu vào TextView
        String category1 = adapterthunhap.get(position);
        holder.tvCategory.setText(category1);
    }

    @Override
    public int getItemCount() {
        return adapterthunhap.size(); // Trả về số lượng danh mục
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


