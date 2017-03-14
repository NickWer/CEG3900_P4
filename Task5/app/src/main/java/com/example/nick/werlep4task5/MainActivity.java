package com.example.nick.werlep4task5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void compute_onClick(View view){
        Intent i = new Intent(MainActivity.this, ResultActivity.class);
        Integer n = Integer.parseInt(((EditText)findViewById(R.id.editText2)).getText().toString());
        i.putExtra("n",n);

        startActivity(i);
    }
}
