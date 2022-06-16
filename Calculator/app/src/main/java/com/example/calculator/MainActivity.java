 package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

 public class MainActivity extends AppCompatActivity {
     EditText number1,number2;
     Button adding;
     Button product;
     Button div;
     Button sub;
     //Button a
     TextView result;
    // TextView result1;
    // TextView addresult;
    // TextView result2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        number1 = findViewById(R.id.num1);
        number2 = findViewById(R.id.num2);
        adding = findViewById(R.id.addresult);
        product = findViewById(R.id.proresult);
        div = findViewById(R.id.divresult);
        sub = findViewById(R.id.subresult);
        result = findViewById(R.id.result);
       // result1= findViewById(R.id.proresult);
       // result2 = findViewById(R.id.subreslult)

        //add = findViewById(R.id.add);

        adding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String  firstnumber = number1.getText().toString();
                String secondnumber = number2.getText().toString();

                int res = Integer.parseInt(firstnumber) + Integer.parseInt(secondnumber);
               // int product = Integer.parseInt(firstnumber) * Integer.parseInt(secondnumber);
                result.setText("addition = " + res);
               // result1.setText("product = "+product);
              //  result.setText("subAnwer is " +res );
                //subresult.setText("Subtration is =" +sub);
            }
        });

        product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String  firstnumber = number1.getText().toString();
                String secondnumber = number2.getText().toString();

                //int res = Integer.parseInt(firstnumber) + Integer.parseInt(secondnumber);
                 int product = Integer.parseInt(firstnumber) * Integer.parseInt(secondnumber);
               // result.setText("Answer = " + res);
                 result.setText("product = "+product);
                //  result.setText("subAnwer is " +res );
                //subresult.setText("Subtration is =" +sub);
            }
        });
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String  firstnumber = number1.getText().toString();
                String secondnumber = number2.getText().toString();

                //int res = Integer.parseInt(firstnumber) + Integer.parseInt(secondnumber);
                int sub = Integer.parseInt(firstnumber) - Integer.parseInt(secondnumber);
                // result.setText("Answer = " + res);
                result.setText("sub = "+sub);
                //  result.setText("subAnwer is " +res );
                //subresult.setText("Subtration is =" +sub);
            }
        });
        div.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String  firstnumber = number1.getText().toString();
                String secondnumber = number2.getText().toString();

                //int res = Integer.parseInt(firstnumber) + Integer.parseInt(secondnumber);
                int div = Integer.parseInt(firstnumber) / Integer.parseInt(secondnumber);
                // result.setText("Answer = " + res);
                result.setText("division = "+div);
                //  result.setText("subAnwer is " +res );
                //subresult.setText("Subtration is =" +sub);
            }
        });

    }
}