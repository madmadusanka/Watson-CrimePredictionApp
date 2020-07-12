package com.example.watson;

import android.content.Context;
import android.content.SharedPreferences;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import static android.content.Context.MODE_PRIVATE;

public class profileFragment extends Fragment {

View _View;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.profile_layot,container,false);


        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("sharedPref", MODE_PRIVATE);
        final String username = sharedPreferences.getString("username","fale");
        users user=new users();
        TextView name=(TextView) view.findViewById(R.id.name);
        name.setText(user.name(username));
        TextView rank=(TextView) view.findViewById(R.id.rank);
        rank.setText(user.rank(username));
        TextView station=(TextView) view.findViewById(R.id.policestation);
        station.setText(user.pls(username));
        String WebUrl = sharedPreferences.getString("WebUrl", "NotSet");
        EditText weburltextbox = (EditText)view.findViewById(R.id.EditTextWebUrl);
        weburltextbox.setText(WebUrl);
        _View=view;
        return view;
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }









}
