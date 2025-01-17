package com.example.lab2;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ListView cityList;
    ArrayAdapter<String> cityadapter;
    ArrayList<String> dataList;

    int SelectedPosition = 0;

    Button addButton, deleteButton, confirmButton;

    EditText inputCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        EdgeToEdge.enable(this);
//
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });

        cityList = findViewById(R.id.city_list);

        String[] cities = {"Edmonton","London", "Paris","Ottawa"};

        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));

        cityadapter = new ArrayAdapter<>(this,R.layout.content,dataList);
        cityList.setAdapter(cityadapter);



        cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SelectedPosition = position;


            }
        });
        deleteButton = findViewById(R.id.delete_button);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataList.remove(SelectedPosition);

                cityadapter.notifyDataSetChanged();

            }
        });


        addButton = findViewById(R.id.add_button);
        confirmButton = findViewById(R.id.confirm_button);
        inputCity = findViewById(R.id.input_city);

        inputCity.setVisibility(View.GONE);
        confirmButton.setVisibility(View.GONE);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                inputCity.setVisibility(View.VISIBLE); // Show input field
                confirmButton.setVisibility(View.VISIBLE);

            }
        });

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String city = inputCity.getText().toString();
                if (!city.isEmpty()) {
                    dataList.add(city); // Add city to the list
                    cityadapter.notifyDataSetChanged(); // Refresh ListView
                    inputCity.setText(""); // Clear the input field
                    inputCity.setVisibility(View.GONE); // Hide input field
                    confirmButton.setVisibility(View.GONE);
                }

            }
        });

    }
}