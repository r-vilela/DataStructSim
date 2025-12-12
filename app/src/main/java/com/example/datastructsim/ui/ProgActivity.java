package com.example.datastructsim.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.datastructsim.R;

import java.util.ArrayList;
import java.util.List;

public class ProgActivity extends AppCompatActivity {

    private Renderer renderer;
    private TextView titleTxt;
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

        renderer = findViewById(R.id.listRenderer);
        titleTxt = findViewById(R.id.titleTxt);
        inputValue = findViewById(R.id.inputValue);
        Button btnInsert = findViewById(R.id.btnInsert);

        SharedPreferences prefs = getSharedPreferences("CODE_PREFS", MODE_PRIVATE);

        Intent intent = getIntent();
        String titulo = intent.getStringExtra("titulo");
        int id = intent.getIntExtra("id", 0);

        titleTxt.setText((titulo!="" ? titulo : "Data Struct\nSimulator"));

        String codeStr = prefs.getString(String.valueOf(id), "");
        inputValue.setText(codeStr);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = inputValue.getText().toString();
                SharedPreferences.Editor editor = prefs.edit();
                if(!code.isEmpty()){
                    compileCode(code);
                    editor.putString(String.valueOf(id), code);
                    editor.apply();
                }
            }
        });
    }

    private void compileCode(String value){
        renderer.setListCode(value);
    }
}