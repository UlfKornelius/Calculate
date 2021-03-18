package ru.bondarev.android1.calculate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {


    SharedPreferences sharedPreferences;
    private Button settings;


    private CalculatorModel calculator;

    private TextView mScreenResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculator_activity);

        sharedPreferences = getSharedPreferences(Constants.MY_PREFERENCES, MODE_PRIVATE);

        settings = findViewById(R.id.settings);

        mScreenResult = findViewById(R.id.screenResult);


        settings.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivityForResult(intent, RESULT_OK);
        });

        checkNightModeActivated();




        int[] numberIds = new int[]{
                R.id.button0,
                R.id.button1,
                R.id.button2,
                R.id.button3,
                R.id.button4,
                R.id.button5,
                R.id.button6,
                R.id.button7,
                R.id.button8,
                R.id.button9,
                R.id.buttonPt,
        };

        int[] actionsIds = new int[]{
                R.id.buttonPlus,
                R.id.buttonMinus,
                R.id.buttonMultiplication,
                R.id.buttonDivision,
                R.id.buttonResult,
                R.id.buttonOn,
                R.id.buttonOff,
                R.id.buttonC
        };

        int[] adminIds = new int[]{
                R.id.buttonOn,
                R.id.buttonOff,
                R.id.buttonC
        };


        calculator = new CalculatorModel();

        View.OnClickListener numberButtonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculator.onNumPressed(view.getId());
                mScreenResult.setText(calculator.getText());
            }
        };

        View.OnClickListener actionButtonOnclickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculator.onActionPressed(view.getId());
                mScreenResult.setText(calculator.getText());
            }
        };

        View.OnClickListener adminButtonOnclickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculator.onAdminPressed(view.getId());
                mScreenResult.setText(calculator.getText());
            }
        };


        for (int i = 0; i < numberIds.length; i++) {
            findViewById(numberIds[i]).setOnClickListener(numberButtonClickListener);
        }

        for (int i = 0; i < actionsIds.length; i++) {
            findViewById(actionsIds[i]).setOnClickListener(actionButtonOnclickListener);
        }

        for (int i = 0; i < adminIds.length; i++) {
            findViewById(adminIds[i]).setOnClickListener(adminButtonOnclickListener);
        }

        findViewById(R.id.screenResult).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculator.reset();
                mScreenResult.setText(calculator.getText());
            }
        });
    }

    public void checkNightModeActivated() {
        if (sharedPreferences.getBoolean(Constants.KEY_NIGHT_MODE, false)) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle state) {
        super.onSaveInstanceState(state);
        state.putString(Constants.KEY_SCREEN, mScreenResult.getText().toString());
//        state.putString(KEY_EQUATION, calculator.getEquation());
    }

    @Override
    protected void onRestoreInstanceState(Bundle state) {
        super.onRestoreInstanceState(state);
        mScreenResult.setText(state.getString(Constants.KEY_SCREEN));
//        calculator.setEquation(state.getString(KEY_EQUATION));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode != RESULT_CANCELED) {
            super.onActivityResult(requestCode, resultCode, data);
        } else if (resultCode == RESULT_OK) {
            saveNightModeState(data.getExtras().getBoolean(Constants.KEY_NIGHT_MODE));
            recreate();
        }
    }

    private void saveNightModeState(boolean nightMode) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(Constants.KEY_NIGHT_MODE, nightMode).apply();
    }

}