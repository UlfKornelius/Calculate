package ru.bondarev.android1.calculate;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Integer arg1;
    private Integer arg2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(view -> {
            EditText input1 = findViewById(R.id.input1);
            EditText input2 = findViewById(R.id.input2);
            TextView result = findViewById(R.id.result);

            try {
                arg1 = Integer.valueOf(input1.getText().toString());
                arg2 = Integer.valueOf(input2.getText().toString());
                result.setText(String.valueOf(arg1 / arg2));
            }catch (NumberFormatException e) {
                result.setText("Please enter a number");
            }



        });
    }
}