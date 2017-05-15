package ua.init.team.bmi;

import android.renderscript.Double2;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import static ua.init.team.bmi.R.id.bt_calculate;
import static ua.init.team.bmi.R.id.et_height;
import static ua.init.team.bmi.R.id.et_weight;
import static ua.init.team.bmi.R.id.pb_correct_data;
import static ua.init.team.bmi.R.id.tv_result;

public class MainActivity extends AppCompatActivity {

    EditText mEtHeight;
    EditText mEtWeight;
    Button mBtCalculate;
    ProgressBar mPb;
    TextView mTvResult;

    static final int MIN_HEIGHT_VALUE = 100;
    static final int MAX_HEIGHT_VALUE = 300;
    static final double MIN_WEIGHT_VALUE = 40;
    static final double MAX_WEIGHT_VALUE = 200;
    static boolean isHeightValid = false;
    static boolean isWeightValid = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEtHeight = (EditText)findViewById(et_height);
        mEtWeight = (EditText)findViewById(et_weight);
        mBtCalculate = (Button)findViewById(bt_calculate);
        mPb = (ProgressBar)findViewById(pb_correct_data);
        mTvResult = (TextView)findViewById(tv_result);

        mEtHeight.setHint(MIN_HEIGHT_VALUE + " - " + MAX_HEIGHT_VALUE + "см");
        mEtWeight.setHint(MIN_WEIGHT_VALUE + " - " + MAX_WEIGHT_VALUE + "кг");

        mEtHeight.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                try {
                    if (Integer.parseInt(String.valueOf(mEtHeight.getText())) >= MIN_HEIGHT_VALUE &&
                            Integer.parseInt(String.valueOf(mEtHeight.getText())) <= MAX_HEIGHT_VALUE){

                        isHeightValid = true;
//                        mPb.setProgress(mPb.getProgress() + 50);
//                        if (mPb.getProgress() == 100) {
//                            mBtCalculate.setEnabled(true);
//                        }
//                        Toast.makeText(MainActivity.this, "" + isHeightValid + Integer.parseInt(String.valueOf(mEtHeight.getText())), Toast.LENGTH_SHORT).show();
                    } else {
                        isHeightValid = false;
//                        mPb.setProgress(mPb.getProgress() - 50);
//                        mBtCalculate.setEnabled(false);
//                        Toast.makeText(MainActivity.this, "" + isHeightValid + Integer.parseInt(String.valueOf(mEtHeight.getText())), Toast.LENGTH_SHORT).show();
                    }

                    //как лучше вынести в отдельный метод метод?


                    if (isHeightValid && isWeightValid){
                        mPb.setProgress(100);
                        mBtCalculate.setEnabled(true);
                    } else if (isHeightValid || isWeightValid){
                        mPb.setProgress(50);
                        mBtCalculate.setEnabled(false);
                    } else {
                        mPb.setProgress(0);
                        mBtCalculate.setEnabled(false);
                    }
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        });

        mEtWeight.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                try {
                    if (Double.parseDouble(String.valueOf(mEtWeight.getText())) >= MIN_WEIGHT_VALUE &&
                            Double.parseDouble(String.valueOf(mEtWeight.getText())) <= MAX_WEIGHT_VALUE){

                        isWeightValid = true;
//                        Toast.makeText(MainActivity.this, "" + isWeightValid + Double.parseDouble(String.valueOf(mEtWeight.getText())), Toast.LENGTH_SHORT).show();
                    } else {
                        isWeightValid = false;
//                        if (mPb.getProgress() == ) {
//                            mPb.setProgress(/*mPb.getProgress() - */50);
//                        } else {
//                        }
//                        mBtCalculate.setEnabled(false);
//                        Toast.makeText(MainActivity.this, "" + isWeightValid + Double.parseDouble(String.valueOf(mEtWeight.getText())), Toast.LENGTH_SHORT).show();
                    }

                    if (isHeightValid && isWeightValid){
                        mPb.setProgress(100);
                        mBtCalculate.setEnabled(true);
                    } else if (isHeightValid || isWeightValid){
                        mPb.setProgress(50);
                        mBtCalculate.setEnabled(false);
                    } else {
                        mPb.setProgress(0);
                        mBtCalculate.setEnabled(false);
                    }
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        });

//        mPb.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
//            @Override
//            public void onViewAttachedToWindow(View v) {
//                Toast.makeText(MainActivity.this, "onViewAttachedToWindow", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onViewDetachedFromWindow(View v) {
//                Toast.makeText(MainActivity.this, "onViewDetachedFromWindow", Toast.LENGTH_SHORT).show();
//            }
//        });

        //как скрыть клаву после нажатия кнопки?


        mBtCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    double bmi;
                    String bodyType;
                    bmi = Double.parseDouble(String.valueOf(mEtWeight.getText())) / Math.pow(Double.parseDouble(String.valueOf(mEtHeight.getText())) / 100, 2);
//                    Toast.makeText(MainActivity.this, "" + bmi, Toast.LENGTH_SHORT).show();
                    if (bmi < 16){
                        bodyType = "Выраженный дефицит массы тела";
                    } else if (bmi < 18.5){
                        bodyType = "Дефицит массы тела";
                    } else if (bmi >= 18.5 && bmi < 25){
                        bodyType = "Норма";
                    } else if (bmi >= 25 && bmi < 30){
                        bodyType = "Избыточная масса тела";
                    } else if (bmi >= 30 && bmi < 35){
                        bodyType = "Ожирение I степени";
                    } else if (bmi >= 35 && bmi <40){
                        bodyType = "Ожирение II степени";
                    } else /*if (bmi >= 40)*/ {
                        bodyType = "Ожирение III степени";
                    }
                    mTvResult.setText("BMI = " + Helper.roundMy(bmi, 2) + "\n" + bodyType);
                    Toast.makeText(MainActivity.this, "кликается" + Helper.roundMy(3.5449, 2) /*bmi*/ /*+ (Double.parseDouble(String.valueOf(mEtWeight.getText())) * 10000 / Math.sqrt(Double.parseDouble(String.valueOf(mEtHeight))))*/, Toast.LENGTH_SHORT).show();
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }


            }
        });

    }


}
