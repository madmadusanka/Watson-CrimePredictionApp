package com.example.watson;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.preference.DialogPreference;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    Boolean loginState = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences sharedPreferences = getSharedPreferences("sharedPref", MODE_PRIVATE);
        loginState = sharedPreferences.getBoolean("loginState", false);
        ImageView imageview = (ImageView) findViewById(R.id.imageView);
        imageview.setImageResource(R.drawable.aa);
        if(loginState==true){
            Intent intent = new Intent(this, home.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("exit", false);
            startActivity(intent);
            finish();
        }
        String WebUrl = sharedPreferences.getString("WebUrl", "NotSet");
        EditText weburltextbox = (EditText)findViewById(R.id.weburleditText);
        weburltextbox.setText(WebUrl);


    }

    public void login(View view) {
        TextView tw=(TextView)findViewById(R.id.state);
        EditText Usernameet = (EditText)findViewById(R.id.username);
        EditText Passwordet = (EditText)findViewById(R.id.password);
        String username = Usernameet.getText().toString();
        String password = Passwordet.getText().toString();
        users user=new users();
        Boolean logi=user.user(username,password);
        if (logi==true){
            SharedPreferences sharedPreferences = getSharedPreferences("sharedPref", MODE_PRIVATE);
            SharedPreferences.Editor state = sharedPreferences.edit();
            state.putBoolean("loginState", true);
            state.putString("username",username);
            state.commit();
            Intent intent = new Intent(this, home.class);
            startActivity(intent);
            finish();
        }
        else if(logi==false){

            tw.setText("Wrong Username or Password");
        }

        //BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        //backgroundWorker.execute(type, username, password);



    }
    public void saveweburl(View view ) {
        EditText weburltextbox = (EditText)findViewById(R.id.weburleditText);
        String weburl = weburltextbox.getText().toString();
        SharedPreferences sharedPreferences2 = getSharedPreferences("sharedPref", MODE_PRIVATE);
        SharedPreferences.Editor state=sharedPreferences2.edit();
        state.putString("WebUrl",weburl);
        state.commit();
    }

}






