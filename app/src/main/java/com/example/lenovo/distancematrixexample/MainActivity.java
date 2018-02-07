package com.example.lenovo.distancematrixexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements GeoTask.Geo {
    EditText editText1, editText2;
    Button button1;
    TextView result1, result2;
    String string1,string2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                string1 = editText1.getText().toString();
                string2 = editText2.getText().toString();
                String url = "https://maps.googleapis.com/maps/api/distancematrix/json?origins=" + string1 + "&destinations=" + string2 + "&mode=driving&language=fr-FR&avoid=tolls&key=YOUR_API_KEY";
                new GeoTask(MainActivity.this).execute(url);

            }
        });

    }

    @Override
    public void setDouble(String result) {
        String res[] = result.split(",");
        Double min = Double.parseDouble(res[0]) / 60;
        int dist = Integer.parseInt(res[1]) / 1000;
        result1.setText("Duration= "  + (int) (min / 60) + "  hr " + (int) (min % 60) + " minutes");
        result2.setText("Distance= "  + dist + "  kilometers");

    }

    public void initialize() {
        editText1 = (EditText) findViewById(R.id.editText1);
        editText2 = (EditText) findViewById(R.id.editText2);
        button1 = (Button) findViewById(R.id.button1);
        result1 = (TextView) findViewById(R.id.result1);
        result2 = (TextView) findViewById(R.id.result2);

    }
}

