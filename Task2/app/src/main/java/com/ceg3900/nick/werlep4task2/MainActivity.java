package com.ceg3900.nick.werlep4task2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Range;
import android.view.View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void processFile_onClick(View view) {

    }

    public void processTextbox_onClick(View view){
        try {
            InputStream is = getApplicationContext().getAssets().open("auth.log.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void sendToProcessScreen() {

    }
}
