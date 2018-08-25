package com.example.android.miwok;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        makeNumbersTextView();
    }

    //Пример програмного переопределения метода onClick без использования данного атрибута в xml
    private void makeNumbersTextView() {
        TextView numbers = (TextView) findViewById(R.id.numbers);
        //Меры безопасности
        if (numbers != null) {
            numbers.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    /*Здесь можно переопределить метод onClick, но т.к. я уже написал для него метод, я просто его же и вызвал.
                    Если бы я просто указал атрибут onClick в xml файле, достаточно было бы просто написать метод openNumbersList,
                    как я это сделал с остальными TextView
                     */
                    openNumbersList(findViewById(R.id.numbers));
                }
            });
        }
    }

    public void openColorsList(View view) {
        Intent intent = new Intent(MainActivity.this, ColorsActivity.class);
        startActivity(intent);
    }

    public void openFamilyList(View view) {
        Intent intent = new Intent(MainActivity.this, FamilyActivity.class);
        startActivity(intent);
    }

    public void openNumbersList(View view) {
        Intent intent = new Intent(MainActivity.this, NumbersActivity.class);
        startActivity(intent);
    }

    public void openPhrasesList(View view) {
        Intent intent = new Intent(MainActivity.this, PhrasesActivity.class);
        startActivity(intent);
    }

    public void openTestList(View view) {
        Intent intent = new Intent(MainActivity.this, TestActivity.class);
        startActivity(intent);
    }

    public void openTastyList(View view) {
        Intent intent = new Intent(MainActivity.this, TastyActivity.class);
        startActivity(intent);
    }
}
