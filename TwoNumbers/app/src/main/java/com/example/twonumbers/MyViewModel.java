package com.example.twonumbers;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class MyViewModel extends ViewModel {
    private MutableLiveData<ArrayList<String>> list;
    public LiveData<ArrayList<String>> getList(){
        if (list == null){
            list = new MutableLiveData<ArrayList<String>>();
            list.setValue(new ArrayList<String>());
        }
        return list;
    }
    public void addItem(String item){
        ArrayList<String> temp = new ArrayList<String>(list.getValue());
        temp.add(0, item);
        list.setValue(temp);
    }
}
