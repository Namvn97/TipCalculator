package com.example.namp5.tipcalculator;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String NUMBER_ZERO = "0";
    public static final String NUMBER_ONE = "1";
    public static final String NUMBER_TWO = "2";
    public static final String NUMBER_THREE = "3";
    public static final String NUMBER_FOUR = "4";
    public static final String NUMBER_FIVE = "5";
    public static final String NUMBER_SIX = "6";
    public static final String NUMBER_SEVEN = "7";
    public static final String NUMBER_EIGHT = "8";
    public static final String NUMBER_NINE = "9";
    public static final String NUMBER_DOT = ".";
    public static final String NUMBER_PLUS = "+";
    public static final String NUMBER_SUB = "-";
    public static final String NUMBER_MUL = "x";
    public static final String NUMBER_DIV = "/";
    public static final String NUMBER_DIV_100 = "%";
    public static final String SAVE_RESULT = "dataCal";
    public static final float NUMBER_100 = 100;
    private int[] mIdButton = {R.id.text_number0, R.id.text_number1, R.id.text_number2,
            R.id.text_number3, R.id.text_number4, R.id.text_number5, R.id.text_number6,
            R.id.text_number7, R.id.text_number8, R.id.text_number9, R.id.text_equals,
            R.id.text_dot, R.id.text_plus, R.id.text_subtraction, R.id.text_multiplication,
            R.id.text_division, R.id.text_division_100, R.id.text_plus_subtraction, R.id.text_ac};
    private TextView mTextInputNumber;
    private TextView mTextResult;
    private float mSaveResult;
    private float mResult;
    private String mOperator;
    private SharedPreferences mSaveMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextInputNumber = findViewById(R.id.text_inputnumber);
        mTextResult = findViewById(R.id.text_result);
        for (int id : mIdButton) {
            TextView textView = findViewById(id);
            textView.setOnClickListener(this);
        }
        mSaveMenu = getSharedPreferences(SAVE_RESULT, MODE_PRIVATE);
    }

    private void summation() {
        if (mSaveResult == 0) {
            mResult = Float.parseFloat(mTextInputNumber.getText().toString());
            mTextInputNumber.append(NUMBER_PLUS);
            mTextResult.setText(mTextInputNumber.getText().toString());
            mTextInputNumber.setText("");
            mOperator = NUMBER_PLUS;
        } else if (mSaveResult != 0) {
            mResult = mSaveResult;
            mTextResult.setText("");
            mTextResult.setText(castString(parseFloatToInt(mResult), NUMBER_PLUS));
            mTextInputNumber.setText("");
            mOperator = NUMBER_PLUS;
        }
    }

    private void subtraction() {
        if (mSaveResult == 0) {
            mResult = Float.parseFloat(mTextInputNumber.getText().toString());
            mTextInputNumber.append(NUMBER_SUB);
            mTextResult.setText(mTextInputNumber.getText().toString());
            mTextInputNumber.setText("");
            mOperator = NUMBER_SUB;
        } else if (mSaveResult != 0) {
            mResult = mSaveResult;
            mTextResult.setText("");
            mTextResult.setText(castString(parseFloatToInt(mResult), NUMBER_SUB));
            mTextInputNumber.setText("");
            mOperator = NUMBER_SUB;
        }
    }

    private void multiplication() {
        if (mSaveResult == 0) {
            mResult = Float.parseFloat(mTextInputNumber.getText().toString());
            mTextInputNumber.append(NUMBER_MUL);
            mTextResult.setText(mTextInputNumber.getText().toString());
            mTextInputNumber.setText("");
            mOperator = NUMBER_MUL;
        } else if (mSaveResult != 0) {
            mResult = mSaveResult;
            mTextResult.setText("");
            mTextResult.setText(castString(parseFloatToInt(mResult), NUMBER_MUL));
            mTextInputNumber.setText("");
            mOperator = NUMBER_MUL;
        }
    }

    private void division() {
        if (mSaveResult == 0) {
            mResult = Float.parseFloat(mTextInputNumber.getText().toString());
            mTextInputNumber.append(NUMBER_DIV);
            mTextResult.setText(mTextInputNumber.getText().toString());
            mTextInputNumber.setText("");
            mOperator = NUMBER_DIV;
        } else if (mSaveResult != 0) {
            mResult = mSaveResult;
            mTextResult.setText("");
            mTextResult.setText(castString(parseFloatToInt(mResult), NUMBER_DIV));
            mTextInputNumber.setText("");
            mOperator = NUMBER_DIV;
        }
    }

    private void division_100() {
        if (mSaveResult != 0) {
            mResult = mResult / NUMBER_100;
            mTextResult.setText("");
            mTextResult.setText(castString(NUMBER_DIV_100, mTextInputNumber.getText().toString()));
            mTextInputNumber.setText(parseFloatToInt(mResult));
            mSaveResult = mResult;
        } else if (mSaveResult == 0) {
            mResult = 0;
            mTextResult.append(mTextInputNumber.getText().toString());
            mTextInputNumber.setText(parseFloatToInt(mResult));
            mSaveResult = mResult;
        }
    }

    private void checkPlus_Subtraction() {
        if (mTextInputNumber.getText().toString().contains("-")) {
            mTextInputNumber.setText(mTextInputNumber.getText().toString().substring(1));
            mSaveResult = Float.parseFloat(mTextInputNumber.getText().toString().substring(1));
        } else {
            mTextInputNumber.setText(castString(NUMBER_SUB, mTextInputNumber.getText().toString()));
            mSaveResult = Float.parseFloat(mTextInputNumber.getText().toString());
        }
    }

    private void setTextAc(){
        mTextInputNumber.setText("");
        mTextResult.setText("");
        mResult = 0;
        mSaveResult = 0;
    }

    private String castString(String... strings) {
        StringBuilder builder = new StringBuilder();
        for (String string : strings) {
            builder.append(string);
        }
        return builder.toString();
    }

    private String parseFloatToInt(float result) {
        int number = (int) result;
        if (number == result) return String.valueOf(number);
        return String.valueOf(result);
    }

    private void operatorPlus() {
        mResult = mResult + Float.parseFloat(mTextInputNumber.getText().toString());
        mTextResult.append(mTextInputNumber.getText().toString());
        mTextInputNumber.setText(parseFloatToInt(mResult));
        mSaveResult = mResult;
    }

    private void operatorSub() {
        mResult = mResult - Float.parseFloat(mTextInputNumber.getText().toString());
        mTextResult.append(mTextInputNumber.getText().toString());
        mTextInputNumber.setText(parseFloatToInt(mResult));
        mSaveResult = mResult;
    }

    private void operatorMuli() {
        mResult = mResult * Float.parseFloat(mTextInputNumber.getText().toString());
        mTextResult.append(mTextInputNumber.getText().toString());
        mTextInputNumber.setText(parseFloatToInt(mResult));
        mSaveResult = mResult;
    }

    private void operatorDiv() {
        mResult = mResult / Float.parseFloat(mTextInputNumber.getText().toString());
        mTextResult.append(mTextInputNumber.getText().toString());
        mTextInputNumber.setText(parseFloatToInt(mResult));
        mSaveResult = mResult;
    }

    private void setResult(String mOperator) {
        switch (mOperator) {
            case NUMBER_PLUS:
                operatorPlus();
                break;
            case NUMBER_SUB:
                operatorSub();
                break;
            case NUMBER_MUL:
                operatorMuli();
                break;
            case NUMBER_DIV:
                operatorDiv();
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_clear:
                break;
            case R.id.menu_save:
                SharedPreferences.Editor editor = mSaveMenu.edit();
                editor.putFloat(SAVE_RESULT, mSaveResult);
                editor.apply();
                break;
            case R.id.menu_get:
                mResult = mSaveMenu.getFloat(SAVE_RESULT, 0);
                mTextInputNumber.setText(parseFloatToInt(mResult));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.text_number0:
                mTextInputNumber.append(NUMBER_ZERO);
                break;
            case R.id.text_number1:
                mTextInputNumber.append(NUMBER_ONE);
                break;
            case R.id.text_number2:
                mTextInputNumber.append(NUMBER_TWO);
                break;
            case R.id.text_number3:
                mTextInputNumber.append(NUMBER_THREE);
                break;
            case R.id.text_number4:
                mTextInputNumber.append(NUMBER_FOUR);
                break;
            case R.id.text_number5:
                mTextInputNumber.append(NUMBER_FIVE);
                break;
            case R.id.text_number6:
                mTextInputNumber.append(NUMBER_SIX);
                break;
            case R.id.text_number7:
                mTextInputNumber.append(NUMBER_SEVEN);
                break;
            case R.id.text_number8:
                mTextInputNumber.append(NUMBER_EIGHT);
                break;
            case R.id.text_number9:
                mTextInputNumber.append(NUMBER_NINE);
                break;
            case R.id.text_dot:
                mTextInputNumber.append(NUMBER_DOT);
                break;
            case R.id.text_plus:
                summation();
                break;
            case R.id.text_subtraction:
                subtraction();
                break;
            case R.id.text_multiplication:
                multiplication();
                break;
            case R.id.text_division:
                division();
                break;
            case R.id.text_division_100:
                division_100();
                break;
            case R.id.text_plus_subtraction:
                checkPlus_Subtraction();
                break;
            case R.id.text_equals:
                setResult(mOperator);
                break;
            case R.id.text_ac:
                setTextAc();
                break;
            default:
                break;
        }
    }
}
