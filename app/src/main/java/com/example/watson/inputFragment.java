package com.example.watson;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

public class inputFragment extends Fragment {
    inputFragment fragment;
    private EditText date;
    private EditText time;
    public View view;
    public int crimerid;
    Context thiscontext;
    Spinner spinner1,spinner2,spinner3;
    private RequestQueue requestQueue;
    String data;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.input_layot, container, false);

        thiscontext=getContext();

       data=" { \"crimeId\":8, \"date\":2020-12-15,\" typeId\":2,\"stationId\":2, \"status\":0 }";
        Button btn=view.findViewById(R.id.submitButton);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SubmitFragment dialog = new SubmitFragment();
                dialog.setTargetFragment(inputFragment.this, 1);
                dialog.show(getFragmentManager(),"");
                submits();






               //SharedPreferences sharedPreferences = getActivity().getSharedPreferences("sharedPref", MODE_PRIVATE);
                //String username = sharedPreferences.getString("username", "null");
                //String crime = spinner1.getSelectedItem().toString();
                //String division = spinner2.getSelectedItem().toString();
                //String station = spinner3.getSelectedItem().toString();
                //EditText datetxt=(EditText) view.findViewById(R.id.date);
               // Ids ids=new Ids();
              //  int crimeid=ids.crime(crime);
               // int stationid =ids.station(station);
                //String data="CrimeId:"+crimerid+",date:"+date+",typeId:"+crimeid+",stationId:"+stationid+",status:0";

            }
        });
        date = view.findViewById(R.id.date);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputFragment.this.date();
            }
        });

        spinner1 = (Spinner) view.findViewById(R.id.crimeType);
        spinner2 = (Spinner) view.findViewById(R.id.policeDivision);

        spinner3 = (Spinner) view.findViewById(R.id.policeStation);
        spinner();


        jsonParse();

        return view;
    }


    public void spinner() {
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(getActivity(), R.array.crime, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(getActivity(), R.array.police, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(getActivity(), R.array.pstation, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(adapter3);
        //String text = spinner.getSelectedItem().toString();
    }

    public void date(){
        datePicker frgment = new datePicker();
        frgment.fragment = this;
        frgment.show(this.getFragmentManager(),"mm");
    }
    public void time(){
        TimePickerFragment frgment = new TimePickerFragment();
        frgment.fragment = this;
        frgment.show(this.getFragmentManager(),"mm");

    }
    public void setTime(String t){
        time.setText(t,TextView.BufferType.NORMAL);
    }

    public void setDate(String d) {
        date.setText(d, TextView.BufferType.NORMAL);
    }




    public void jsonParse() {
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("sharedPref", MODE_PRIVATE);
        final String username = sharedPreferences.getString("username","fale");
        users user=new users();
        final String pstfu=user.getpst(username);
        String url = "http://192.168.8.100:9191/api/v1/crimeReport";
        RequestQueue requestQueue= Volley.newRequestQueue(thiscontext);
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                crimerid= response.length()+1;
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonArrayRequest);
    }



  public void Submit(String data)
   {
       final String savedata= data;
       String URL="http://192.168.8.101:9191/api/v1/crimeReport";

       requestQueue = Volley.newRequestQueue(thiscontext);
       StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
           @Override
           public void onResponse(String response) {
               try {
                   JSONObject objres=new JSONObject(response);

               } catch (JSONException e) {

               }

           }
       }, new Response.ErrorListener() {
           @Override
           public void onErrorResponse(VolleyError error) {


               Toast.makeText(thiscontext, error.getMessage(), Toast.LENGTH_SHORT).show();
           }
       }) {
           @Override
           public String getBodyContentType() {
               return "application/json; charset=utf-8";
           }

           @Override
           public byte[] getBody() throws AuthFailureError {
               try {
                   return savedata == null ? null : savedata.getBytes("utf-8");
               } catch (UnsupportedEncodingException uee) {

                   return null;
               }
           }
       };
       requestQueue.add(stringRequest);
   }

   public void submits(){
      String url = "http://192.168.8.100:9191/api/v1/crimeReport";
       StringRequest postRequest = new StringRequest(Request.Method.POST, url,
               new Response.Listener<String>()
               {
                   @Override
                   public void onResponse(String response) {
                       // response
                   }
               },
               new Response.ErrorListener()
               {
                   @Override
                   public void onErrorResponse(VolleyError error) {
                   }
               }
       ) {
           @Override
           protected Map<String, String> getParams()
           {
               Map<String, String > params = new HashMap<String, String>();

               return params;
           }
       };
       requestQueue.add(postRequest);
   }
}











