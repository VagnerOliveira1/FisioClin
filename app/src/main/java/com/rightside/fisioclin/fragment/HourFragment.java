package com.rightside.fisioclin.fragment;


import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TimePicker;
import android.widget.Toast;

import com.rightside.fisioclin.R;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

/**
 * A simple {@link Fragment} subclass.
 */
public class HourFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {


    public interface TimePickerListener{
        void OnTimeSet(TimePicker timePicker, int i, int i1);
    }

    private TimePickerListener mListener;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (TimePickerListener) context;
        }catch (Exception e){

        }
    }

  

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        TimeZone zone = TimeZone.getTimeZone("GMT-03:00");
        Locale locale = new Locale("pt", "BR");
        Calendar c = Calendar.getInstance(zone, locale);
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int min = c.get(Calendar.MINUTE);

        Log.d("hora", String.valueOf(hour));
        return new TimePickerDialog(getActivity(), this, hour, min, true);
    }


    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i1) {
        mListener.OnTimeSet(timePicker,i, i1);
    }
}
