package com.avigator.avigator.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.avigator.avigator.R;
import com.avigator.avigator.activity.model.Message;
import com.avigator.avigator.activity.network.ApiClient;
import com.avigator.avigator.activity.network.ApiInterface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsActivity extends AppCompatActivity {
    private String address, city, state, country, postalCode, knownName, loc;
    private Double lat, lng;
    private Geocoder geocoder;
    private List<Address> addresses =  new ArrayList<>();

    private List<Message> messages = new ArrayList<>();

    private static final int PROGRESS = 0x1;

    private ProgressBar mProgress;
    private int mProgressStatus = 0;

    private boolean mHandler;
    private ProgressDialog progress;
    private TextView info;
    private String display;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        info = (TextView) findViewById(R.id.textViewInfo);
//        mProgress = (ProgressBar) findViewById(R.id.progress_bar);
//        progress=new ProgressDialog(this);
//        progress.setMessage("Loading...");0
//        progress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
//        progress.setIndeterminate(true);
//        progress.setProgress(0);
//        progress.show();
        Intent intent = getIntent();
        display = intent.getStringExtra("EXTRA_INFO");
        info.setText(display);

    }

}
