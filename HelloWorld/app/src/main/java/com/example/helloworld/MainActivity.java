package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.example.helloworld.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private MyViewModel model;

    private ArrayAdapter<String> arrayAdapter;
    private ArrayList<String> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View viewRoot = binding.getRoot();
        setContentView(viewRoot);

        arrayList = new ArrayList<String>();
        arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                arrayList);
        binding.lvCount.setAdapter(arrayAdapter);

        model = new ViewModelProvider(this).get(MyViewModel.class);

        model.getNumber().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                binding.tvCount.setText("" + integer);
                model.addItem("" + integer);
            }
        });
        model.getList().observe(this, new Observer<ArrayList<String>>() {
            @Override
            public void onChanged(ArrayList<String> strings) {
                arrayList = new ArrayList<String>(strings);
                arrayAdapter = new ArrayAdapter<String>(MainActivity.this,
                        android.R.layout.simple_list_item_1,
                        arrayList);
                binding.lvCount.setAdapter(arrayAdapter);
            }
        });

        binding.btnUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model.increaseNumber();
            }
        });

        binding.btnDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model.decreaseNumber();
            }
        });

        binding.lvCount.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                model.removeItem(i);
                return false;
            }
        });

        binding.lvCount.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra("number", arrayList.get(i));
                intent.putExtra("index", i);
                startActivityForResult(intent, 123);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 123) {
            if(resultCode == Activity.RESULT_OK){
                String newNum = data.getStringExtra("newNumber");
                int index = data.getIntExtra("index", 0);
                model.setItem(index, newNum);
            }
        }
    }
}