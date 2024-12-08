package house.duan.appchitieu.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

import house.duan.appchitieu.model.chiTieu;

public class SharedViewModel extends ViewModel {
    // MutableLiveData to hold the list of expenses (chiTieu)
    private final MutableLiveData<ArrayList<chiTieu>> expenses = new MutableLiveData<>(new ArrayList<>());  // Default to empty list

    // Method to set/update the expenses list
    public void setExpenses(ArrayList<chiTieu> data) {
        if (data != null) {
            expenses.setValue(new ArrayList<>(data));  // Create a copy of the new list to avoid direct manipulation
        }
    }

    // Method to get the expenses list
    public LiveData<ArrayList<chiTieu>> getExpenses() {
        return expenses;  // Expose the expenses list as LiveData
    }

    // Method to add an expense to the current list
    public void addExpense(chiTieu newExpense) {
        if (newExpense != null) {
            ArrayList<chiTieu> currentExpenses = expenses.getValue();
            if (currentExpenses != null) {
                currentExpenses.add(newExpense);  // Add the new expense
                expenses.setValue(new ArrayList<>(currentExpenses));  // Create a new list to avoid direct manipulation
            }
        }
    }

    // Method to remove an expense from the current list
    public void removeExpense(chiTieu expenseToRemove) {
        if (expenseToRemove != null) {
            ArrayList<chiTieu> currentExpenses = expenses.getValue();
            if (currentExpenses != null && currentExpenses.contains(expenseToRemove)) {
                currentExpenses.remove(expenseToRemove);  // Remove the expense
                expenses.setValue(new ArrayList<>(currentExpenses));  // Create a new list to avoid direct manipulation
            }
        }
    }
}
