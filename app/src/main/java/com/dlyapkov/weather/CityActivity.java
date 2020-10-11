package com.dlyapkov.weather;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CityActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);
        final EditText et = findViewById(R.id.editTextCity);
        final CheckBox cb = findViewById(R.id.checkBoxInformation);
        et.setOnKeyListener(
                new View.OnKeyListener() {
                    @Override
                    public boolean onKey(View v, int keyCode, KeyEvent event) {
                        if (event.getAction() == KeyEvent.ACTION_DOWN &&
                                (keyCode == KeyEvent.KEYCODE_ENTER)) {
                            String city = String.valueOf(et.getText());
                            setContentView(R.layout.activity_main);
                            TextView tv = findViewById(R.id.textView3);
                            tv.setText(city);
                            return true;
                        }
                        return false;
                    }
                });
    }
}
