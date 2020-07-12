package com.example.watson;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;

import static android.content.Context.MODE_PRIVATE;

    public class SavedFile extends AppCompatActivity {

       SharedPreferences sharedPreferences = getSharedPreferences("sharedPref", MODE_PRIVATE);
          String username = sharedPreferences.getString("username", "null");
          String WebUrl= sharedPreferences.getString("WebUrl", "NotSet");

}
