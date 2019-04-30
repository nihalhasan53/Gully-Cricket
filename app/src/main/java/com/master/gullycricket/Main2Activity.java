package com.master.gullycricket;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    CheckBox wide;
    CheckBox noball;
    EditText wicket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        wide=(CheckBox)findViewById(R.id.wide);
        noball=(CheckBox)findViewById(R.id.noball);
        wicket=(EditText)findViewById(R.id.wickets);
    }
    public void proceed(View view)
    {
        Intent i=new Intent(this,MainActivity.class);
        if(wide.isChecked())
        {
            if(noball.isChecked())
            {
                i.putExtra("wide",1);
                i.putExtra("noball",1);
            }
            else
            {
                i.putExtra("wide",1);
                i.putExtra("noball",0);
            }
        }
        else if(noball.isChecked())
        {
            i.putExtra("wide",0);
            i.putExtra("noball",1);
        }
        else
        {
            i.putExtra("wide",0);
            i.putExtra("noball",0);
        }

        try
        {
            int wick=Integer.parseInt(wicket.getText().toString());
            if(wick>0 && wick<=10)
            {
                i.putExtra("wicket",wick);
                startActivity(i);
            }
            else
            {
                Toast.makeText(getApplicationContext(),"Wickets should lie in the range(1,10)",Toast.LENGTH_SHORT).show();
            }
        }
        catch (Exception e)
        {
            Toast.makeText(getApplicationContext(),"Please enter a valid Input",Toast.LENGTH_SHORT).show();
        }

    }
}
