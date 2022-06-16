package com.example.checkboxpractice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    CheckBox pizza, burger, coffee;
    Button orderButton;
    TextView result1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addListenerOnOrderButton();
    }

    public void addListenerOnOrderButton() {
        pizza = (CheckBox) findViewById(R.id.Pizza);
        coffee = (CheckBox) findViewById(R.id.Cofee);
        burger = (CheckBox) findViewById(R.id.Burger);
        orderButton = (Button) findViewById(R.id.button);
        result1 = (TextView) findViewById(R.id.textView);
        orderButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                int total_amount = 0;
                StringBuilder result = new StringBuilder();
                result.append("Selected Items:");
                if (pizza.isChecked()) {
                    result.append("\nPizza 100Rs");
                    total_amount += 100;

                }
                if (coffee.isChecked()) {
                    result.append("\nCoffe 50Rs");
                    total_amount += 50;
                }
                if (burger.isChecked()) {
                    result.append("\nBurger 120Rs");
                    total_amount += 120;
                }
                result.append("\nTotal: " + total_amount + "Rs");

                result1.setText(result.toString());
                //Displaying the message on the toast
                Toast.makeText(getApplicationContext(), result.toString(), Toast.LENGTH_LONG).show();
            }

        });
    }
}