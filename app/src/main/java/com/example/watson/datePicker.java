package com.example.watson;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.widget.DatePicker;
import android.widget.EditText;

import java.time.Year;
import java.util.Calendar;

public class datePicker extends DialogFragment
        implements DatePickerDialog.OnDateSetListener{
        public String dateV;
        public inputFragment fragment;

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }
        public void onDateSet(DatePicker view, int year, int month, int day) {
        //EditText date=(EditText) findViewById(R.id.date);
        //date.setText(dateV);
            dateV=year+"."+month+"."+day;
            fragment.setDate(dateV);
        }
}



