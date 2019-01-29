package com.example.android.languageperfersnce;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Locale;

public class WelcomeAct extends AppCompatActivity {
Spinner langSpinner;

Locale mlocale;
Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
       // setLocale(Locale.getDefault().getLanguage());
        loadLocale();

        langSpinner = (Spinner)findViewById(R.id.spn1);
        btn = (Button)findViewById(R.id.btn);
        //languageNametxt = (TextView) findViewById(R.id.language);

        setupSpinner();
    }

    private void setupSpinner(){
        ArrayAdapter spinAdapter = ArrayAdapter.createFromResource(this,R.array.languages_array,android.R.layout.simple_spinner_dropdown_item);
       spinAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
       langSpinner.setAdapter(spinAdapter);
       langSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
              String s = (String)adapterView.getItemAtPosition(i);

               if (s.equals(getString(R.string.arabic)) && btn.getText().equals("Start") ){
              // if(!(Locale.getDefault().getLanguage().equals("ar"))){
                  setLocale("ar");
                  recreate();
              }else  if (s.equals(getString(R.string.english)) && btn.getText().equals("دخول")){
                 // if(!(Locale.getDefault().getLanguage().equals("en"))){
                   setLocale("en");

                   recreate();}
           }


           @Override
           public void onNothingSelected(AdapterView<?> adapterView) {
               // Another interface call back
           }
       });
    }

    public void setLocale(String language) {
         mlocale = new Locale(language);
        Locale.setDefault(mlocale);
        Resources res = this.getResources();

        Configuration confi = res.getConfiguration();
        confi.locale = mlocale;
        res.updateConfiguration(confi,res.getDisplayMetrics());


       SharedPreferences.Editor  editor = getSharedPreferences("lang",MODE_PRIVATE).edit();
        editor.putString("my Language",language);

        editor.apply();


    }

    public void loadLocale()
    {
        SharedPreferences pref = getSharedPreferences("lang", Activity.MODE_PRIVATE);
        String language = pref.getString("my Language","");

        setLocale(language);

    }

    public void Go_btn(View view) {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
