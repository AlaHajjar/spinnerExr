package com.example.android.languageperfersnce;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.preference.PreferenceManager;
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
import android.widget.Toast;

import java.util.Locale;

public class WelcomeAct extends AppCompatActivity  {
    //Spinner langSpinner;
    TextView mText;
    Locale mlocale;
    Button btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        loadLocale();
        setContentView(R.layout.activity_welcome);




      //  langSpinner = (Spinner) findViewById(R.id.spn1);
        mText = (TextView)findViewById(R.id.tv);


        btn = (Button) findViewById(R.id.btn);
       // setupSpinner();
        String lang = Locale.getDefault().getLanguage();
        if (lang.equals("en")){
            mText.setText("English");
        }else if (lang.equals("ar")){
            mText.setText("العربية ");
        }


    }











    public void loadLocale() {
        String langPref = "Language";
        SharedPreferences prefs = getSharedPreferences("CommonPrefs",
                Activity.MODE_PRIVATE);
        String language = prefs.getString(langPref, "ar");
        changeLang(language);
    }

    public void changeLang(String lang) {
        if (lang.equalsIgnoreCase(""))
            return;
        mlocale = new Locale(lang);
        Locale.setDefault(mlocale);
        saveLocale(lang);

        Configuration config = new Configuration();
        config.setLocale(mlocale);
        getBaseContext().getResources().updateConfiguration(config,getBaseContext().getResources().getDisplayMetrics());

    }

    public void saveLocale(String lang) {
        String langPref = "Language";
        SharedPreferences prefs = getSharedPreferences("CommonPrefs",
                Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(langPref, lang);
        editor.apply();
    }

        public void Go_btn (View view){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();

        }


    public void choseLanguage(View view) {
        builtAlertDialog();
    }

    public void builtAlertDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        int checkedItem;
        String systemLang =Locale.getDefault().getLanguage();
         if (systemLang.equals("en")){checkedItem=0;}else
             if (systemLang.equals("ar")){checkedItem=1;}else checkedItem=0;


        dialogBuilder.setTitle("Chose Language..").setSingleChoiceItems(R.array.languages_array, checkedItem, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (i==0){
                    changeLang("en");
                    recreate();
                }else if (i==1 && !Locale.getDefault().getLanguage().equals("ar")){
                    changeLang("ar");
                    recreate();
                }
                dialogInterface.dismiss();
            }

        });
       AlertDialog dialog= dialogBuilder.create();
       dialog.show();
    }
}
