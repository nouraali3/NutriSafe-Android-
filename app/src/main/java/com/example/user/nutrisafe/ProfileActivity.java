package com.example.user.nutrisafe;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import datatypes.User;
import operations.ImageOperations;

public class ProfileActivity extends AppCompatActivity {


    User user;
    List<String> allergies;

    private WebView scriptResponseWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        Intent i=getIntent();
        user = (User) i.getSerializableExtra("user");
        allergies = user.getAllergies();

        //get id of items
        scriptResponseWebView = findViewById(R.id.webView);

        scriptResponseWebView.getSettings().setJavaScriptEnabled(true);
        scriptResponseWebView.loadUrl("http://nouraali.heliohost.org/cgi-bin/python.py?allergies=aaaaa&image=iiiii");

    }


}






