package com.example.a01eventhandler_java;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


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
        setContentView(R.layout.activity_main);
        Button btn = findViewById(R.id.btn);
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
        btn.setOnClickListener(view -> Log.d("BTN", "Button Clicked"));

    }

//    1.
//    public void  onButtonClick(View v){
//        Log.d("BTN","Button Clicked");
//    }
}