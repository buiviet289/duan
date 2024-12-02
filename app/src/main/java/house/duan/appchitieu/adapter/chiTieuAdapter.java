package house.duan.appchitieu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import house.duan.appchitieu.R;
import house.duan.appchitieu.dao.chiTieuDAO;
import house.duan.appchitieu.model.chiTieu;

public class chiTieuAdapter extends RecyclerView.Adapter<chiTieuAdapter.ItemViewHolder> {
    private List<chiTieu> items;
    private Context context;

    public chiTieuAdapter(Context context, List<chiTieu> items, chiTieuDAO chiTieuDAO) {
        this.context = context;
        this.items = items;
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
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView name, price, note;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.txt_ten);
            price = itemView.findViewById(R.id.txt_gia);
            note = itemView.findViewById(R.id.txt_ghichu);
        }
    }
}

