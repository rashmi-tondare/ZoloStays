package com.assignment.zolostays.view;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import com.assignment.zolostays.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextInputLayout txtInputLayoutPhone = (TextInputLayout) findViewById(R.id.txt_input_layout_phone);
        LinearLayout lnrDummy = (LinearLayout) findViewById(R.id.lnr_dummy);
        lnrDummy.requestFocus();
    }
}
