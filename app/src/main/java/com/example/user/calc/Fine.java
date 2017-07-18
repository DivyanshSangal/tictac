package com.example.user.calc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Fine extends AppCompatActivity {

    String s[]={"kjk","kjk"};
    //String stringArr[];
    ArrayList<String> stringArr;
    Toolbar toolbar1;
    ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fine);
        Intent intent=getIntent();
        //stringArr= intent.getStringArrayExtra("strarr");
        stringArr = intent.getStringArrayListExtra("s");
        toolbar1=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar1);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        list= (ListView)findViewById(R.id.list);
        ArrayAdapter<String> ad=new ArrayAdapter<>(Fine.this, android.R.layout.simple_list_item_1,stringArr);

        list.setAdapter(ad);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_fine,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.nice)
        {
            stringArr.clear();
            Intent data = new Intent();
            data.putExtra("clear",1);
            setResult(RESULT_OK, data);
            finish();
            //list.setAdapter(ad);
        }
        return super.onOptionsItemSelected(item);
    }
}
