package com.example.manarpatel.book_app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Manar Patel on 02-Apr-16.
 */
public class Nav_Header_home extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_header_home);

        //TextView tv = (TextView) findViewById(R.id.profileemail);
        //tv.setText("Welcome ," + getIntent().getExtras().getString("username"));
    }

}