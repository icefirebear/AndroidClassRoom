package com.example.test3;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView selectedItem;
    Button btnAdd, btnEdit, btnDel;
    ListView listView;
    int selectedNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("2105변웅섭");


        btnAdd = findViewById(R.id.btnAdd);
        btnEdit = findViewById(R.id.btnEdit);
        btnDel = findViewById(R.id.btnDel);
        listView = findViewById(R.id.listView);
        selectedItem = findViewById(R.id.selectedItem);

        final ArrayList<String> dataSet = new ArrayList<>();
        dataSet.add("데이터리스트1");
        dataSet.add("데이터리스트2");
        dataSet.add("데이터리스트3");
        dataSet.add("데이터리스트4");
        dataSet.add("데이터리스트5");

        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_single_choice, dataSet);

        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                selectedItem.setText("데이터리스트"+(position+1));
                selectedNum = position;
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataSet.add("데이터리스트"+(dataSet.size()+1));
                adapter.notifyDataSetChanged();
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataSet.remove(selectedNum);
                adapter.notifyDataSetChanged();
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText et = new EditText(getApplicationContext());

                AlertDialog.Builder alt = new AlertDialog.Builder(MainActivity.this)
                        .setTitle("라스트 아이템 수정")
                        .setIcon(R.mipmap.ic_launcher_round)
                        .setCancelable(false)
                        .setMessage("현재 데이터: 데이터리스트"+selectedNum)
                        .setNegativeButton("취소",null)
                        .setPositiveButton("수정", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dataSet.remove(selectedNum);
                                dataSet.add(selectedNum,et.getText().toString());
                                selectedItem.setText(et.getText().toString());
                                adapter.notifyDataSetChanged();
                            }
                        });
                alt.setView(et);
                alt.show();
            }
        });
    }
}
