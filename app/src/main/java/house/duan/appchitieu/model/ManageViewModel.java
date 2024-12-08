package house.duan.appchitieu.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class ManageViewModel extends ViewModel {
    private MutableLiveData<List<NameItem>> expenses;
    private MutableLiveData<List<NameItem>> incomes;

    public ManageViewModel() {
        expenses = new MutableLiveData<>(new ArrayList<>());
        incomes = new MutableLiveData<>(new ArrayList<>());
    }

    public LiveData<List<NameItem>> getExpenses() {
        return expenses;
    }

    public void setExpenses(List<NameItem> expenses) {
        this.expenses.setValue(expenses);
    }

    public LiveData<List<NameItem>> getIncomes() {
        return incomes;
    }

    public void setIncomes(List<NameItem> incomes) {
        this.incomes.setValue(incomes);
    }
}
