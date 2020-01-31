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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TimePicker;
import android.widget.Toast;

import com.rightside.fisioclin.R;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HourFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {


    public interface TimePickerListener{
        void OnTimeSet(TimePicker timePicker, int i, int i1);
    }

    TimePickerListener mListener;


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
        Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int min = c.get(Calendar.MINUTE);
        return new TimePickerDialog(getActivity(), this, hour, min, true);
    }


    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i1) {
        mListener.OnTimeSet(timePicker,i, i1);
    }
}
