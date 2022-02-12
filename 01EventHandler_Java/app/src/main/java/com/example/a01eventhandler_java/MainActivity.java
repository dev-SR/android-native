package com.example.a01eventhandler_java;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


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
//        setContentView(R.layout.radio_layout);
//        RadioGroup radioGroup = findViewById(R.id.rdGroup);
//        Button btnSubmit = findViewById(R.id.btnSubmit);
//        Button btnClear = findViewById(R.id.btnClear);
//        radioGroup.setOnCheckedChangeListener((RadioGroup group, int checkedId) -> {
//            switch (checkedId) {
//                case R.id.radio_yes:
//                    Log.d("BTN", "Yes");
//                    break;
//                case R.id.radio_no:
//                    Log.d("BTN", "No");
//                    break;
//            }
//        });
//
//        btnSubmit.setOnClickListener(v -> {
//            int selectedId = radioGroup.getCheckedRadioButtonId();
//            if (selectedId == -1) {
//                Toast.makeText(MainActivity.this,
//                        "No answer has been selected",
//                        Toast.LENGTH_SHORT)
//                        .show();
//            } else {
//                // Get the selected Radio Button
//                RadioButton radioButton = radioGroup.findViewById(selectedId);
//
//                // Now display the value of selected item
//                // by the Toast message
//                Toast.makeText(getApplicationContext(),
//                        radioButton.getText(),
//                        Toast.LENGTH_SHORT)
//                        .show();
//            }
//        });
//
//        btnClear.setOnClickListener(v -> {
//            radioGroup.clearCheck();
//        });

        /**
         * Spinner
         * */
//        setContentView(R.layout.spinner_layout);
//        Spinner spinner = (Spinner) findViewById(R.id.spinner);
//        // Create an ArrayAdapter using the string array and a `default spinner layout`
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
//                R.array.planets_array, R.layout.custom_spinner);
//        // Specify the layout to use when the list of choices appears
//        adapter.setDropDownViewResource(R.layout.custom_spinner_dropdown);
//        // Apply the adapter to the spinner
//        spinner.setAdapter(adapter);
//        /**
//         * `simple_spinner_item` and `simple_spinner_dropdown_item` both are default layout
//         * */
//        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
////                Get the selected item out a spinner using:
//                String value = spinner.getSelectedItem().toString();
//                String value1 = spinner.getItemAtPosition(pos).toString();
//                Log.d("BTN", value + " " + pos + " " + id);
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });

//        creating Spinner Dynamically
        setContentView(R.layout.spinner_layout);
        Spinner spinner = findViewById(R.id.spinner);
        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("Item 1");
        categories.add("Item 2");
        categories.add("Item 3");
        categories.add("Item 4");
        categories.add("Item 5");
        categories.add("Item 6");
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("BTN", spinner.getItemAtPosition(i).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }
//    1.
//    public void  onButtonClick(View v){
//        Log.d("BTN","Button Clicked");
//    }
}