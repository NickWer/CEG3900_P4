package com.example.nick.werlep4task4;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.AbstractMap;
import java.util.List;
import java.util.stream.Stream;

import werle.BookParser.BookListParser;

public class ResultActivity extends AppCompatActivity {
    TextView results;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        results = (TextView)findViewById(R.id.textView);

        InputStream is = null;
        try {
            is = getApplicationContext().getAssets().open("booklist.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            results.setText("Processing");
            new parseTask().execute(br.lines());
        } catch (Exception e) {
            results.setText("Unknown error occurred");
            e.printStackTrace();
        }
    }

    private class parseTask extends AsyncTask<Stream<String>, Void, List<AbstractMap.SimpleEntry<String, Integer>>> {
        BookListParser parser = new BookListParser();

        @Override
        protected List<AbstractMap.SimpleEntry<String, Integer>> doInBackground(Stream<String>... params) {
            return parser.parseList(params[0]);
        }

        @Override
        protected void onPostExecute(List<AbstractMap.SimpleEntry<String, Integer>> simpleEntries) {
            super.onPostExecute(simpleEntries);
            results.setText("");
            simpleEntries
                    .subList(0, getIntent().getIntExtra("n", 10) - 1)
                    .forEach(match -> {
                        String out = match.getKey() + " - " + match.getValue();
                        results.setText(results.getText() + "\n" + out);
                    });;

        }
    }
}
