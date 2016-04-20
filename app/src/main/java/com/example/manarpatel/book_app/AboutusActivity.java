package com.example.manarpatel.book_app;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

public class AboutusActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView mImageView2, mImageView3, mImageView4, mImageView5;
    private ImageView sImageView1, sImageView2, sImageView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutus);

        mImageView2 = (ImageView) findViewById(R.id.imageView2);
        mImageView2.setImageResource(R.drawable.bookapp1);

        mImageView3 = (ImageView) findViewById(R.id.imageView3);
        mImageView3.setImageResource(R.drawable.mudra2);

        mImageView4 = (ImageView) findViewById(R.id.imageView4);
        mImageView4.setImageResource(R.drawable.sneha1);

        mImageView5 = (ImageView) findViewById(R.id.imageView5);
        mImageView5.setImageResource(R.drawable.pooja1);

        sImageView1 = (ImageView) findViewById(R.id.socialim1);
        sImageView1.setImageResource(R.drawable.socialimage1);

        sImageView2 = (ImageView) findViewById(R.id.socialim2);
        sImageView2.setImageResource(R.drawable.socialimage1);

        sImageView3 = (ImageView) findViewById(R.id.socialim3);
        sImageView3.setImageResource(R.drawable.socialimage1);

        sImageView1.setOnClickListener(this);
        sImageView2.setOnClickListener(this);
        sImageView3.setOnClickListener(this);
        setupActionBar();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            startActivity(new Intent(this, HomeActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void setupActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            // Show the Up button in the action bar.
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    public static Intent getOpenFacebookIntent(Context context) {

        try {
            context.getPackageManager()
                    .getPackageInfo("com.facebook.katana", 0); //Checks if FB is even installed.
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("fb://profile/100002460037362")); // Trys to make intent with FB's URI.
            // Uri.parse("fb://profile/100001161178495"));
        } catch (Exception e) {
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.facebook.com/arkverse")); //catches and opens a url to the desired page
        }
    }
    public static Intent getOpenFacebookIntent2(Context context) {

        try {
            context.getPackageManager()
                    .getPackageInfo("com.facebook.katana", 0); //Checks if FB is even installed.
            return new Intent(Intent.ACTION_VIEW,

                    Uri.parse("fb://profile/100001161178495"));
        } catch (Exception e) {
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.facebook.com/arkverse")); //catches and opens a url to the desired page
        }
    }
    public static Intent getOpenFacebookIntent3(Context context) {

        try {
            context.getPackageManager()
                    .getPackageInfo("com.facebook.katana", 0); //Checks if FB is even installed.
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("fb://profile/100002950871660")); // Trys to make intent with FB's URI.
            // Uri.parse("fb://profile/100001161178495"));
        } catch (Exception e) {
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.facebook.com/arkverse")); //catches and opens a url to the desired page
        }
    }


        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.socialim1:
                    Intent facebookIntent = getOpenFacebookIntent(this);
                    startActivity(facebookIntent);
                    break;

                case R.id.socialim2:
                    Intent facebookIntent2 = getOpenFacebookIntent2(this);
                    startActivity(facebookIntent2);
                    break;

                case R.id.socialim3:
                    Intent facebookIntent3 = getOpenFacebookIntent3(this);
                    startActivity(facebookIntent3);
                    break;

            }

        }




}
