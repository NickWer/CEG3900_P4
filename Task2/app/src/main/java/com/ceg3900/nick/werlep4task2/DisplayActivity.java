package com.ceg3900.nick.werlep4task2;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.IOException;

import werle.logParser.IParseAcceptor;
import werle.logParser.IParseLineProcessed;
import werle.logParser.LogParser;

public class DisplayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        TextView lblSource = (TextView)findViewById(R.id.lblSource);
        TextView lblOutput = (TextView)findViewById(R.id.lblOutput);

        LogParser parser = new LogParser(DataSource.dataStream);

        try {
            parser.parse(new IParseAcceptor() {
                @Override
                public void onLogMessage(String data) {
                    lblOutput.setText(lblOutput.getText() + "\n" + data);
                }
            }, new IParseLineProcessed() {
                @Override
                public void onLineProcessed(String line) {
                    lblSource.setText(lblSource.getText() + "\n" + line);
                }
            });
        } catch (IOException e) {
            AlertDialog dialog = new AlertDialog.Builder(DisplayActivity.this).create();
            dialog.setTitle("Error");
            dialog.setMessage("Failed to parse log");
            dialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Dang", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            dialog.show();
        }
    }
}
