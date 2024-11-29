package house.duan.appchitieu.fragment;



import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import house.duan.appchitieu.R;


import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class Profilefragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        // Biểu đồ chi tiêu
        PieChart pieChartExpenses = view.findViewById(R.id.pieChartExpenses);
        setupPieChart(pieChartExpenses, "Phân loại chi tiêu",
                new float[]{56.3f, 18.7f, 30.7f},
                new String[]{"Quần áo", "Ăn uống", "Hoa quả"});

        // Biểu đồ thu nhập
        PieChart pieChartIncome = view.findViewById(R.id.pieChartIncome);
        setupPieChart(pieChartIncome, "Phân loại thu nhập",
                new float[]{67.3f, 67.3f, 30.7f},
                new String[]{"Tiền thưởng", "Lương", "Bán hàng"});

        return view;
    }

    private void setupPieChart(PieChart pieChart, String label, float[] values, String[] categories) {
        ArrayList<PieEntry> entries = new ArrayList<>();
        for (int i = 0; i < values.length; i++) {
            entries.add(new PieEntry(values[i], categories[i]));
        }

        PieDataSet dataSet = new PieDataSet(entries, label);
        dataSet.setColors(ColorTemplate.MATERIAL_COLORS);

        PieData data = new PieData(dataSet);
        data.setValueTextSize(12f);

        pieChart.setData(data);
        pieChart.setUsePercentValues(true);
        pieChart.setEntryLabelTextSize(12f);
        pieChart.getDescription().setEnabled(false);
        pieChart.animateY(1000);
    }
}

