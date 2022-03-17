package com.example.helloworld;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class MyViewModel extends ViewModel {
    private MutableLiveData<Integer> number;
    private MutableLiveData<ArrayList<String>> list;
    public LiveData<Integer> getNumber() {
        if (number == null) {
            number = new MutableLiveData<Integer>();
            number.setValue(0);
        }
        return number;
    }
    public LiveData<ArrayList<String>> getList() {
        if (list == null) {
            list = new MutableLiveData<ArrayList<String>>();
            list.setValue(new ArrayList<String>());
        }
        return list;
    }
    public void addItem(String string){
        ArrayList<String> temp = new ArrayList<String>(list.getValue());
        temp.add(string);
        list.setValue(temp);
    }
    public void removeItem(int i){
        ArrayList<String> temp = new ArrayList<String>(list.getValue());
        temp.remove(i);
        list.setValue(temp);
    }
    public void setItem(int i, String num){
        ArrayList<String> temp = new ArrayList<String>(list.getValue());
        temp.set(i, num);
        list.setValue(temp);
    }
    public void increaseNumber(){
        number.setValue(number.getValue() + 1);
    }
    public void decreaseNumber(){
        number.setValue(number.getValue() - 1);
    }
}
