package com.ceg3900.nick.werlep4task2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void processFile_onClick(View view) {
        try {
            InputStream is = getApplicationContext().getAssets().open("auth.log.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            sendToProcessScreen(br.lines());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void processTextbox_onClick(View view){
        String data = ((EditText)findViewById(R.id.txtEditableLog)).getText().toString();
        List<String> lines = new ArrayList<String>(Arrays.asList(data.split("\n")));
        sendToProcessScreen(lines.stream());
    }

    private void sendToProcessScreen(Stream<String> lines) {
        Intent i = new Intent(MainActivity.this, DisplayActivity.class);
        DataSource.dataStream = lines;
        startActivity(i);
    }
}
