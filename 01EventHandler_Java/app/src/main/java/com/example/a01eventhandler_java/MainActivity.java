package com.example.a01eventhandler_java;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


// 2.1
//class OnClickCallback implements View.OnClickListener{
//
//    @Override
//    public void onClick(View view) {
//        Log.d("BTN","Button Clicked");
//    }
//}
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        Button btn = (Button) findViewById(R.id.btn);
        //2.2
//        btn.setOnClickListener(new OnClickCallback());
        ///3.
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.d("BTN", "Button Clicked");
//            }
//        });
        //4
//        View.OnClickListener cb = (view) -> Log.d("BTN", "clicked");
//        btn.setOnClickListener(cb);
//      btn.setOnClickListener(view -> Log.d("BTN", "Button Clicked"));


//        ex1
//        setContentView(R.layout.example1_layout);
//        EditText etNum1 = findViewById(R.id.num1);
//        EditText etNum2 = findViewById(R.id.num2);
//        Button btnAdd = findViewById(R.id.btnAdd);
//        TextView tvResult = findViewById(R.id.res);
//
//        tvResult.setVisibility(View.INVISIBLE);
//        etNum1.requestFocus();
//
//        btnAdd.setOnClickListener(view -> {
//            int n1 = Integer.valueOf(etNum1.getText().toString());
//            int n2 = Integer.valueOf(etNum2.getText().toString());
//            int result = n1 + n2;
//            String msg = "Result :" + result;
//            tvResult.setVisibility(View.VISIBLE);
//            tvResult.setText(msg);
//            Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
/**
 It is not possible to set the gravity of toast in android 11 because this method is deprecated in API 30+
 **/
//            Toast toast = Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG);
//            toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 0);
//            toast.show();
//        });

        /**  CheckBox **/
//        setContentView(R.layout.checkbox_layout);
//        CheckBox checkCheese = findViewById(R.id.checkbox_cheese);
//        CheckBox checkMeat = findViewById(R.id.checkbox_meat);

//        checkCheese.setOnCheckedChangeListener((v, isChecked) -> {
//            if (isChecked) {
//                Toast.makeText(this, "Cheese Checked", Toast.LENGTH_LONG).show();
//            Log.d("BTN","checked");
//            }
//        });

        // multiple checkbox
//        CompoundButton.OnCheckedChangeListener checkListener = (view, checked) -> {
//
//            switch (view.getId()) {
//                case R.id.checkbox_meat:
//                    if (checked) {
//                        Log.d("BTN", "Meat Checked");
//                    } else {
//                        Log.d("BTN", "Meat UnChecked");
//                    }
//                    break;
//                case R.id.checkbox_cheese:
//                    if (checked) {
//                        Log.d("BTN", "Cheese Checked");
//                    } else {
//                        Log.d("BTN", "Cheese UnChecked");
//
//                    }
//                    break;
//            }
//        };
//        checkCheese.setOnCheckedChangeListener(checkListener);
//        checkMeat.setOnCheckedChangeListener(checkListener);

        /**
         * RadioGroup
         * */
        setContentView(R.layout.radio_layout);
        RadioGroup radioGroup = findViewById(R.id.rdGroup);
        Button btnSubmit = findViewById(R.id.btnSubmit);
        Button btnClear = findViewById(R.id.btnClear);
        radioGroup.setOnCheckedChangeListener((RadioGroup group, int checkedId) -> {
            switch (checkedId) {
                case R.id.radio_yes:
                    Log.d("BTN", "Yes");
                    break;
                case R.id.radio_no:
                    Log.d("BTN", "No");
                    break;
            }
        });

        btnSubmit.setOnClickListener(v -> {
            int selectedId = radioGroup.getCheckedRadioButtonId();
            if (selectedId == -1) {
                Toast.makeText(MainActivity.this,
                        "No answer has been selected",
                        Toast.LENGTH_SHORT)
                        .show();
            } else {
                // Get the selected Radio Button
                RadioButton radioButton = radioGroup.findViewById(selectedId);

                // Now display the value of selected item
                // by the Toast message
                Toast.makeText(getApplicationContext(),
                        radioButton.getText(),
                        Toast.LENGTH_SHORT)
                        .show();
            }
        });

        btnClear.setOnClickListener(v -> {
            radioGroup.clearCheck();
        });


    }
//    1.
//    public void  onButtonClick(View v){
//        Log.d("BTN","Button Clicked");
//    }
}