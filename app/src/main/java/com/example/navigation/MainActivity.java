package com.example.navigation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    /*
    * Não esquecer de adicionar o componente navigation via ui, android resources -> opção navigation
    * adicionar as view e etc.
    * */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
