package com.example.watson;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.DialogPreference;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private String mTextViewResult;
    private RequestQueue mQueue;
    public static Fragment fragment = null;
    public predictionFragment pfargmetn;
    final Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        fragment = new predictionFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.fragmntArea, fragment);
        ft.commit();
        setTitle("Predictions");

        if (getIntent().getBooleanExtra("exit", false))
        {
            boolean t=getIntent().getBooleanExtra("exit",false);
            if (t==true) {
                super.finish();
            }

        }
        if(getIntent().getBooleanExtra("logout",false)){
            boolean t=getIntent().getBooleanExtra("logout",false);
            if (t==true) {
                 SharedPreferences sharedPreferences=getSharedPreferences("sharedPref",MODE_PRIVATE);
                SharedPreferences.Editor state=sharedPreferences.edit();
                state.putBoolean("loginState",false);
                state.commit();
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
            }

        }
        if(getIntent().getBooleanExtra("submit",false)){
            boolean t=getIntent().getBooleanExtra("submit",false);
            if (t==true) {
                Bundle bundle = new Bundle();
                bundle.putBoolean("submit", true);
                inputFragment fragobj = new inputFragment();
                fragobj.setArguments(bundle);
                fragment = new inputFragment();
                fragmentManager = getSupportFragmentManager();
                ft = fragmentManager.beginTransaction();
                ft.replace(R.id.fragmntArea, fragment);
                ft.commit();
            }
        }

        mQueue = Volley.newRequestQueue(this);


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            fragment= new predictionFragment();

            setTitle("Predictions");
        } else if (id == R.id.nav_gallery) {
            setTitle("Input Crimes");
            fragment= new inputFragment();
        } else if (id == R.id.nav_slideshow) {
            setTitle("Profile");
            fragment= new profileFragment();
        } else if (id == R.id.logout) {
            DialogFragment frgment=new logoutDialog();
            frgment.show(getSupportFragmentManager(),"mm");




        } else if (id == R.id.exit) {

           DialogFragment newFragment = new dialog();
        newFragment.show(getSupportFragmentManager(), "missiles");
        }



        if(fragment!=null){
            FragmentManager fragmentManager=getSupportFragmentManager();
            FragmentTransaction ft=fragmentManager.beginTransaction();
            ft.replace(R.id.fragmntArea,fragment);
            ft.commit();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



}
