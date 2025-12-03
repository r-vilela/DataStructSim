package com.example.datastructsim.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.datastructsim.domain.ListVisualizerView;
import com.example.datastructsim.R;

import java.util.ArrayList;
import java.util.List;

public class ProgActivity extends AppCompatActivity {

    private ListVisualizerView visualizer;
    private EditText inputValue;
    private List<Integer> listData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.prog_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        visualizer = findViewById(R.id.listVisualizer);
        inputValue = findViewById(R.id.inputValue);
        Button btnInsert = findViewById(R.id.btnInsert);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = inputValue.getText().toString();
                if(!text.isEmpty()){
                    int value = Integer.parseInt(text);
                    insertValue(value);
                    inputValue.setText("");
                }
            }
        });
    }

    private void insertValue(int value){
        listData.add(value);
        visualizer.setElements(listData);
    }
}