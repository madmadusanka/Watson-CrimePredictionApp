package com.example.watson;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static android.content.Context.MODE_PRIVATE;

public class predictionFragment extends Fragment {
    View view;
     Context thiscontext;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.prediction_layot, container, false);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("sharedPref", MODE_PRIVATE);
        thiscontext = getContext();
        jsonParse();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

    }
    private void jsonParse() {
        final SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("sharedPref", MODE_PRIVATE);
        final String username = sharedPreferences.getString("username","fale");
        final String WebUrl = sharedPreferences.getString("WebUrl","fale");
        String url = WebUrl;
        RequestQueue requestQueue= Volley.newRequestQueue(thiscontext);
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                users user=new users();
                String pstfu=user.getpst(username);

                TextView tw=view.findViewById(R.id.tw);
                String prediction="";
                int i = 0;
                while ( i < response.length()) {
                    try {
                        JSONObject employee = response.getJSONObject(i);
                        String psid = employee.getString("policeStationId");
                        if(pstfu.equals("Officer_rasitha")){
                            if(psid.equals("GAMPAHA")){
                                String date = employee.getString("date");
                                String location = employee.getString("location");
                                String crimetype = employee.getString("crimeType");
                                String prob = employee.getString("prob");
                                prediction = prediction + "In this date " + date + " " + crimetype + " will like to be happen in " + location + " area." + "Probability : " + prob + "\n";
                                tw.setText(prediction);
                            }
                        }
                        else if(psid.equals(pstfu)) {
                            String date = employee.getString("date");
                            String location = employee.getString("location");
                            String crimetype = employee.getString("crimeType");
                            String prob = employee.getString("prob");
                            prediction = prediction + "In this date " + date + " " + crimetype + " will like to be happen in " + location + " area." + "Probability : " + prob + "\n";
                            tw.setText(prediction);

                        }



                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    i++;

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonArrayRequest);

    }

}

