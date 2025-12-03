package com.example.datastructsim.ui;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.datastructsim.R;
import com.example.datastructsim.data.local.ItemDAO;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textfield.TextInputEditText;

public class AddActivity extends AppCompatActivity {
    private TextInputEditText edtTitle;
    private Button btnDelete, btnSave;
    private ItemDAO itemDAO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        edtTitle = findViewById(R.id.edtTitle);
        btnDelete = findViewById(R.id.btnDelete);
        btnSave = findViewById(R.id.btnSave);
        itemDAO = new ItemDAO(this);

        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(v -> finish());

        Intent intent = getIntent();
        final int id = intent.getIntExtra("id", 0);
        if(id != 0){
            ContentValues cv = itemDAO.pesquisarPorId(id);
            edtTitle.setText(cv.getAsString("titulo"));
            btnDelete.setVisibility(View.VISIBLE);
        } else {
            btnDelete.setVisibility(View.GONE);
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String titulo = edtTitle.getText().toString();

                if(titulo.isEmpty()){
                    Toast.makeText(AddActivity.this, "Fill in the role title!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(id != 0){
                    itemDAO.atualizar(id, titulo);
                    Toast.makeText(AddActivity.this, "Item updated!", Toast.LENGTH_SHORT).show();
                } else {
                    ContentValues cv = new ContentValues();
                    cv.put("titulo", titulo);
                    long res = itemDAO.inserir(cv);
                    if(res>0){
                        Toast.makeText(AddActivity.this, "Successfully registered!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(AddActivity.this, "Error when registering!", Toast.LENGTH_SHORT).show();
                    }
                }
                finish();
            }
        });

        btnDelete.setOnClickListener(v -> {
            if(id != 0){
                itemDAO.deletar(id);
                Toast.makeText(AddActivity.this, "Item deleted!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }
}