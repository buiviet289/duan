package house.duan.appchitieu.model;



import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

import house.duan.appchitieu.model.chiTieu;

public class SharedViewModel extends ViewModel {
    private final MutableLiveData<ArrayList<chiTieu>> expenses = new MutableLiveData<>();

    public void setExpenses(ArrayList<chiTieu> data) {
        expenses.setValue(data);
    }

    public LiveData<ArrayList<chiTieu>> getExpenses() {
        return expenses;
    }
}
