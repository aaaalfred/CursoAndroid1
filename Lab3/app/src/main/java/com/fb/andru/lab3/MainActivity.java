package com.fb.andru.lab3;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView txt1;
    String arrayPlayas[] =  {"CANCUN", "PTO VALLARTA", "TUXPAN"};
    ListView lista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        txt1 = (TextView) findViewById(R.id.text1);
        lista = (ListView) findViewById(R.id.lista);

        lista.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayPlayas));

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                txt1.setText(arrayPlayas[position]);
            }
        });
    }
}
