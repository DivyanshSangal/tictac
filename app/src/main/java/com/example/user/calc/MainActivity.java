package com.example.user.calc;

import android.app.Activity;
import android.content.Intent;
import android.icu.text.DecimalFormat;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

//@RequiresApi(api = Build.VERSION_CODES.N)
public class MainActivity extends AppCompatActivity {

    Button[] b=new Button[18];
    EditText edit;
    TextView text,text1;
    private double valueOne = Double.NaN;
    private double valueTwo;
    private static final char ADD = '+';
    private static final char SUB = '-';
    private static final char MUL = '*';
    private static final char DIV = '/';
    DecimalFormat decimalFormat;
    private char CURRENT_ACTION;
    LinearLayout l1;
    Menu menu;
    Toolbar toolbar;
    ArrayList<String> strArr;
    int strNo=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //menu=(Menu)findViewById(R.id.)

        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        strArr=new ArrayList<String>();
        edit=(EditText)findViewById(R.id.edit);
        text=(TextView)findViewById(R.id.text);
        text1=(TextView)findViewById(R.id.text1);

        //l1=(LinearLayout)findViewById(R.id.linear3);
        //l1.setShowDividers(0);
        //b[1]=(Button)findViewById(R.id.b1);

        for(int i=0; i<=13; i++) {

            String buttonID = "b" + i;
            int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
            b[i] = ((Button) findViewById(resID));
            String temp="";
            //b[i].setLayoutParams (new ViewGroup.LayoutParams(50, ViewGroup.LayoutParams.WRAP_CONTENT));

            if(i<10)
            {
                temp = Integer.toString(i);
                final String finalI = temp;
                b[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        edit.setText(edit.getText()+ finalI);
                    }
                });

            }


            else
            {
                char a='1';
                if(i==10)
                    a=ADD;
                else if(i==11)
                    a=SUB;
                else if(i==12)
                    a=DIV;
                else if(i==13)
                    a=MUL;
                final char c=a;
                final String k=Character.toString(a );
                b[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String a=edit.getText().toString();
                        if(a.compareTo("")!=0)
                        computeCalculation();
                        CURRENT_ACTION=c;
                        text.setText(Double.toString(valueOne));
                        text1.setText(k);
                        edit.setText(null);
                    }
                });
            }

        }

        b[14]=(Button)findViewById(R.id.b14);
        b[15]=(Button)findViewById(R.id.b15);
        b[14].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s;
                s=edit.getText().toString();
                String s1;
                int a=s.length();
                if(a>=1)
                    a=a-1;
                s1=s.substring(0,a);
                edit.setText(s1);

            }
        });

        b[15].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valueOne = Double.NaN;
                edit.setText("");
                text.setText("");
                text1.setText("");
            }
        });

        b[16]=(Button)findViewById(R.id.b16);
        b[17]=(Button)findViewById(R.id.b17);
        b[16].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a=edit.getText().toString();
                if(a.compareTo("")!=0)
                computeCalculation();
                text.setText(Double.toString(valueOne));
                text1.setText("");
                edit.setText(null);
            }
        });

        b[17].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a= edit.getText().toString();
                edit.setText(a+".");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    public void clearStringArr(){
        strArr.clear();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.history)
        {
            Intent intent=new Intent(MainActivity.this,Fine.class);
            intent.putStringArrayListExtra("s",strArr);
            startActivityForResult(intent,0);
        }

        return super.onOptionsItemSelected(item);
    }

    private void computeCalculation() {
        if(!Double.isNaN(valueOne)) {

            try {
                valueTwo = Double.parseDouble(edit.getText().toString());
            }
            catch (Exception e){}
            String str=Double.toString(valueOne)+Character.toString(CURRENT_ACTION)+Double.toString(valueTwo);

            edit.setText(null);

            if(CURRENT_ACTION == ADD)
                valueOne = this.valueOne + valueTwo;
            else if(CURRENT_ACTION == SUB)
                valueOne = this.valueOne - valueTwo;
            else if(CURRENT_ACTION == MUL)
                valueOne = this.valueOne * valueTwo;
            else if(CURRENT_ACTION == DIV)
                valueOne = this.valueOne / valueTwo;
            str=str+"="+Double.toString(valueOne);
            strArr.add(str);
        }
        else {
            try {
                valueOne = Double.parseDouble(edit.getText().toString());
            }
            catch (Exception e){}
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK)
        {
            if(data.hasExtra("clear"))
                strArr.clear();
        }
    }
}
