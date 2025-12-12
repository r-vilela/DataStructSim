package com.example.datastructsim.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datastructsim.R;
import com.example.datastructsim.data.local.ItemDAO;
import com.example.datastructsim.data.model.Item;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Renderer visualizer;
    private EditText inputValue;
    private List<Integer> listData = new ArrayList<>();
    private Button btnAdd;
    private RecyclerView recyclerView;
    private Adapter adapter;
    private ArrayList<Item> list = new ArrayList<>();
    private ItemDAO itemDAO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnAdd = findViewById(R.id.btnAdd);
        recyclerView = findViewById(R.id.recyclerView);
        itemDAO = new ItemDAO(this);

        adapter = new Adapter(this, list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });

        adapter.setOnItemClickListener(new Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Item item = list.get(position);
                Intent intent = new Intent(MainActivity.this, ProgActivity.class);
                intent.putExtra("titulo", item.getTitulo());
                intent.putExtra("id", item.getId());
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(int position) {
                Item item = list.get(position);
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                intent.putExtra("id", item.getId());
                startActivity(intent);
            }
        });

//        visualizer = findViewById(R.id.listVisualizer);
//        inputValue = findViewById(R.id.inputValue);
//        Button btnInsert = findViewById(R.id.btnInsert);
//
//        btnInsert.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String text = inputValue.getText().toString();
//                if(!text.isEmpty()){
//                    int value = Integer.parseInt(text);
//                    insertValue(value);
//                    inputValue.setText("");
//                }
//            }
//        });
//    }
//
//    private void insertValue(int value){
//        listData.add(value);
//        visualizer.setElements(listData);
//    }
    }
    @Override
    protected void onResume() {
        super.onResume();
        carregarLista();
    }
    private void carregarLista() {
        list.clear();
        list.addAll(itemDAO.listar());
        adapter.notifyDataSetChanged();
    }
}
