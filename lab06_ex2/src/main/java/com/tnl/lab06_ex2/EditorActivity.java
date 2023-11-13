package com.tnl.lab06_ex2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;
import java.util.List;

public class EditorActivity extends AppCompatActivity
        implements View.OnClickListener , View.OnFocusChangeListener {

    private TextInputLayout txtName, txtPlace, txtDate, txtTime;
    private static final String[] ROOMS = {"C201", "C202", "C203","C204"};
    private int selectedRoom = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        initViews();
    }

    private void initViews() {
        txtName = findViewById(R.id.txtName);
        txtPlace = findViewById(R.id.txtPlace);
        txtDate = findViewById(R.id.txtDate);
        txtTime = findViewById(R.id.txtTime);

        txtPlace.getEditText().setOnClickListener(this);
        txtDate.getEditText().setOnClickListener(this);
        txtTime.getEditText().setOnClickListener(this);

        txtPlace.getEditText().setOnFocusChangeListener(this);
        txtDate.getEditText().setOnFocusChangeListener(this);
        txtTime.getEditText().setOnFocusChangeListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.editor_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_save) {
            saveData();
        }

        return super.onOptionsItemSelected(item);
    }



    private void saveData() {

        if (!isInputValid()) {
            return;
        }

        String name = txtName.getEditText().getText().toString();
        String place = txtPlace.getEditText().getText().toString();
        String date = txtDate.getEditText().getText().toString();
        String time = txtTime.getEditText().getText().toString();

        Event event = new Event(name, place, date, time);

        Intent intent = new Intent();
        intent.putExtra("data", event);

        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.txtPlaceValue) {
            showPlacesDialog();
        } else if (id == R.id.txtDateValue) {
            showDatePicker();
        } else if (id == R.id.txtTimeValue) {
            showTimePicker();
        }

    }

    private void showTimePicker() {

        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(System.currentTimeMillis());

        int hour = cal.get(Calendar.HOUR);
        int minute = cal.get(Calendar.MINUTE);

        TimePickerDialog dialog = new TimePickerDialog(this, (view, hourOfDay, minute1) -> {
            String time = hourOfDay + ":" + minute1;
            txtTime.getEditText().setText(time);
        }, hour, minute, true);
        dialog.show();
    }

    private void showDatePicker() {

        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(System.currentTimeMillis());

        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(this, (view, year1, month1, dayOfMonth) -> {
            String date = dayOfMonth + "/" + (month1 + 1) + "/" + year1;
            txtDate.getEditText().setText(date);
        }, year, month, day);
        dialog.show();
    }

    private void showPlacesDialog() {
        new AlertDialog.Builder(this)
        .setTitle("Select place")
        .setSingleChoiceItems(ROOMS, selectedRoom, (dialog, position) -> {
            selectedRoom = position;
            txtPlace.getEditText().setText(ROOMS[position]);
            dialog.dismiss();
        })
        .create().show();
    }

    private boolean isInputValid() {

        String name = txtName.getEditText().getText().toString();
        String place = txtPlace.getEditText().getText().toString();
        String date = txtDate.getEditText().getText().toString();
        String time = txtTime.getEditText().getText().toString();

        if (name.trim().isEmpty()) {
            txtName.setError("Please enter event name");
            return false;
        }

        if (place.trim().isEmpty()) {
            txtPlace.setError("Please select a room");
            return false;
        }

        if (date.trim().isEmpty()) {
            txtDate.setError("Please select event date");
            return false;
        }

        if (time.trim().isEmpty()) {
            txtTime.setError("Please select event time");
            return false;
        }
        return true;
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (!hasFocus) {
            return;
        }

        int id = v.getId();
        if (id == R.id.txtPlaceValue) {
            showPlacesDialog();
        } else if (id == R.id.txtDateValue) {
            showDatePicker();
        } else if (id == R.id.txtTimeValue) {
            showTimePicker();
        }
    }
}