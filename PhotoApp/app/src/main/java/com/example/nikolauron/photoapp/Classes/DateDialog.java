package com.example.nikolauron.photoapp.Classes;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.Calendar;

/**
 * Created by A00923196 on 2017-10-31.
 */

public class DateDialog extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    EditText date;

    public DateDialog(View view) {
        date = (EditText)view;

    }

    public Dialog onCreateDialog(Bundle savedInstancedState) {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH;

        return new DatePickerDialog(getActivity(), this, year, month, day)''
    }

    public void onDateSet(DatPick)
}
