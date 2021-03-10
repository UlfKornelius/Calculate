package ru.bondarev.android1.calculate;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    /**
     * 1. Напишите обработку каждой кнопки из макета калькулятора.
     * 2. Создайте объект с данными и операциями калькулятора. Продумайте, каким образом будете
     * хранить введённые пользователем данные.
     * 3. * Создайте макет калькулятора для горизонтальной ориентации экрана и отображайте его в
     * ландшафтной ориентации.
     **/
    public static final String MY_TAG = "Lifecycle";
    public static final String KEY_FIST = MY_TAG + ".mFirst";
    public static final String KEY_SECOND = MY_TAG + ".mSecond";
    public static final String KEY_RESULT = MY_TAG + ".mResult";
    public static final String KEY_OPERATION = MY_TAG + ".mOperation";
    public static final String KEY_SCREEN_RESULT = MY_TAG + ".screenResult";

    private CalculatorModel calculator;

    private TextView mScreenResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homework3);

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


        mScreenResult = findViewById(R.id.screenResult);

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

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(MY_TAG, "onStart() ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e(MY_TAG, "onRestart() ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(MY_TAG, "onResume() ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(MY_TAG, "onPause() ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(MY_TAG, "onStop() ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(MY_TAG, "onDestroy() ");
    }

}