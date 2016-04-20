package com.example.manarpatel.book_app;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class UploadActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String SERVER_ADDRESS="http://usedbookapp.esy.es/";

    Button b1;
    Button b2;
    static final int cam_request = 1;

    private EditText Author_name, Publisher_name, Book_name, Selling_price, Depart ,uploadImageName, Mobile ,Name,Location;
    private EditText editTextEmail;
    private Button buttonupload;
    private Button buttonChoose;
    private Button buttonSubmit;

    String[] departm = {"Computer" , "EXTC" , "Mechnical" , "Civil" , "Electrical"};
    String[] conditionm = {"First Class","Second Class","Third Class"};

    Spinner departsp, conditionsp;

    public static final String UPLOAD_KEY = "image";
    public static final String TAG = "MY MESSAGE";
    private ImageView imageView;
    //private Bitmap bitmap;
    private Uri filePath;
    private int PICK_IMAGE_REQUEST = 1;
    private static final int RESULT_LOAD_IMAGE = 1;

  //  private String UPLOAD_URL = "http://localhost/Book_app/upload.php";

   // private String KEY_IMAGE = "image";
    //private String KEY_NAME = "name";
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

       // getSupportActionBar().setHomeButtonEnabled(true);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        Author_name = (EditText) findViewById(R.id.author_name);
        Publisher_name = (EditText) findViewById(R.id.publisher_name);
        Book_name = (EditText) findViewById(R.id.book_name);
        Selling_price = (EditText) findViewById(R.id.selling_price);
       Name = (EditText) findViewById(R.id.name);
        Mobile = (EditText) findViewById(R.id.mobile);
        Location = (EditText) findViewById(R.id.location);
       // Depart = (EditText) findViewById(R.id.depart);

        uploadImageName = (EditText) findViewById (R.id.etUploadName);

        buttonChoose = (Button) findViewById(R.id.buttonChoose);

        buttonupload = (Button) findViewById(R.id.buttonupload);

        buttonSubmit = (Button) findViewById(R.id.buttonSubmit);

        imageView = (ImageView) findViewById(R.id.imageView);
        departsp =(Spinner)findViewById(R.id.depart);
        conditionsp =(Spinner)findViewById(R.id.condition);







        ArrayAdapter<String> adapter= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,departm);
        adapter.setDropDownViewResource(R.layout.spinner_item);
       departsp.setAdapter(adapter);


        departsp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                // Get select item
                //int sid = departsp.getSelectedItemPosition();
                String departm = departsp.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });


        ArrayAdapter<String> adapter1= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,conditionm);
        adapter1.setDropDownViewResource(R.layout.spinner_item);
        conditionsp.setAdapter(adapter1);


        conditionsp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                // Get select item
                //int sid = departsp.getSelectedItemPosition();
                String conditionm = conditionsp.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });
        imageView.setOnClickListener(this);
        buttonChoose.setOnClickListener(this);
        buttonupload.setOnClickListener(this);
        buttonSubmit.setOnClickListener(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        b1 = (Button) findViewById(R.id.capture);
        //   b2=(Button)findViewById(R.id.upload);

      /*  FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendIntent = new Intent(Intent.ACTION_VIEW);
                sendIntent.setType("plain/text");
                sendIntent.setData(Uri.parse("book_worms@gmail.com"));
                sendIntent.setClassName("com.google.android.gm", "com.google.android.gm.ComposeActivityGmail");
                sendIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"book_worms@gmail.com"});
                sendIntent.putExtra(Intent.EXTRA_SUBJECT, "test");
                sendIntent.putExtra(Intent.EXTRA_TEXT, "hello. this is a message sent from my demo app :-)");
                startActivity(sendIntent);
            }
        }); */

/*
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                File file = getfile();
                camera_intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                startActivityForResult(camera_intent, cam_request);


            }
        }); */

        buttonChoose.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE);
            }
        });

       /* b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Create intent to Open Image applications like Gallery, Google Photos
                Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
// Start the Intent
                startActivityForResult(galleryIntent, RESULT_LOAD_IMG);
            }

        }); */

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
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
    private File getfile() {
        File folder = new File("sdcard/project_files");

        if (!folder.exists()) {
            folder.mkdir();
        }

        File image_file = new File(folder, "xyz.jpg");


        return image_file;
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonChoose:

                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE);
                break;

            case R.id.buttonupload:
                Bitmap image = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
                new UploadImage(image,uploadImageName.getText().toString()).execute();

                break;
            case R.id.buttonSubmit:
                upload();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null) {
            Uri SelectedImage = data.getData();
            imageView.setImageURI(SelectedImage);

        }
    }


    private class UploadImage extends AsyncTask<Void, Void, Void> {
        Bitmap image;
        String name;
        private Dialog loadingDialog;

        public UploadImage(Bitmap image, String name){
            this.image=image;
            this.name = name;
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            loadingDialog = ProgressDialog.show(UploadActivity.this, "Please wait", "Loading...");
        }
        @Override
       protected Void doInBackground(Void... params) {

                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                image.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
                String encodedImage = Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT);

                ArrayList<NameValuePair> dataToSend = new ArrayList();
                dataToSend.add(new BasicNameValuePair("image", encodedImage));
                dataToSend.add(new BasicNameValuePair("name", name));

                HttpParams httpRequestParams = getHttpRequestParams();

                HttpClient client = new DefaultHttpClient(httpRequestParams);
                HttpPost post = new HttpPost(SERVER_ADDRESS + "SavePicture.php");
                try {
                    post.setEntity(new UrlEncodedFormEntity(dataToSend));
                    client.execute(post);
                } catch (Exception e) {
                    e.printStackTrace();
                }


        return null;
        }

        @Override
       protected void onPostExecute(Void aVoid){
            super.onPostExecute(aVoid);
       
            loadingDialog.dismiss();

            Toast.makeText(getApplicationContext(), "Image Uploaded ", Toast.LENGTH_SHORT).show();
        }
    }

    private HttpParams getHttpRequestParams() {

        HttpParams httprequestParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httprequestParams, 1000 * 30);
        HttpConnectionParams.setSoTimeout(httprequestParams, 1000 * 30);
        return httprequestParams;
    }


/*

    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }
    private void uploadImage(){
        class UploadImage extends AsyncTask<Bitmap,Void,String> {

            ProgressDialog loading;
            RequestHandler rh = new RequestHandler();

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(UploadActivity.this, "Uploading Image", "Please wait...", true, true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(getApplicationContext(),s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Bitmap... params) {
                Bitmap bitmap = params[0];
                String uploadImage = getStringImage(bitmap);

                HashMap<String,String> data = new HashMap<>();
                data.put(UPLOAD_KEY, uploadImage);

                String result = rh.sendPostRequest(UPLOAD_URL,data);

                return result;
            }
        }

        UploadImage ui = new UploadImage();
        ui.execute(bitmap);
    }

    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            filePath = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String path = "sdcard/project_files/xyz &.jpg";
    }


*/
    public void upload() {
        //String email = editTextEmail.getText().toString();
        String author_name = Author_name.getText().toString();
        String publisher_name = Publisher_name.getText().toString();
        String book_name = Book_name.getText().toString();
        String departm =  departsp.getSelectedItem().toString();
        String conditionm =  conditionsp.getSelectedItem().toString();
        String selling_price = Selling_price.getText().toString();
        String etUploadName = uploadImageName.getText().toString();
        String name = Name.getText().toString();
        String mobile = Mobile.getText().toString();
        String location = Location.getText().toString();

        //String email1 = email.getText().toString().trim();
        ImageView imageView = null;
        int selprice = Integer.parseInt(selling_price);
       // int mob = Integer.parseInt(mobile);


        boolean invalid = false;
        if (author_name.equals("")) {
            invalid = true;
            Toast.makeText(getApplicationContext(), "Please Enter your Author Name",
                    Toast.LENGTH_SHORT).show();
        } else if (publisher_name.equals("")) {
            invalid = true;
            Toast.makeText(getApplicationContext(), "Please Enter your  Publisher Name",
                    Toast.LENGTH_SHORT).show();
        } else if (book_name.equals("")) {
            invalid = true;
            Toast.makeText(getApplicationContext(), "Please Enter your  Book Name",
                    Toast.LENGTH_SHORT).show();
        }
        else if (departm.equals("")) {
            invalid = true;
            Toast.makeText(getApplicationContext(), "Please Enter your  Department Name",
                    Toast.LENGTH_SHORT).show();
        }
        else if (conditionm.equals("")) {
            invalid = true;
            Toast.makeText(getApplicationContext(), "Please Enter your  Conditon of Book",
                    Toast.LENGTH_SHORT).show();
        }
        else if (selling_price.equals("")) {
            invalid = true;
            Toast.makeText(getApplicationContext(), "Please Enter your  Selling Price",
                    Toast.LENGTH_SHORT).show();
        }
        else if (etUploadName.equals("")) {
            invalid = true;
            Toast.makeText(getApplicationContext(), "Upload File Name",
                    Toast.LENGTH_SHORT).show();
        }
        else if (name.equals("")) {
            invalid = true;
            Toast.makeText(getApplicationContext(), "Please Enter Your Name",
                    Toast.LENGTH_SHORT).show();
        }
        else if (mobile.equals("")) {
            invalid = true;
            Toast.makeText(getApplicationContext(), "Please Enter Your Number",
                    Toast.LENGTH_SHORT).show();
        }
        else if (location.equals("")) {
            invalid = true;
            Toast.makeText(getApplicationContext(), "Please Enter Your Location",
                    Toast.LENGTH_SHORT).show();
        }
        else if (selprice >= 275) {
            invalid = true;
            Toast.makeText(getApplicationContext(), "Selling price should be less than Market Price",
                    Toast.LENGTH_SHORT).show();
        }
        else if(mobile.length() != 10){
            Toast.makeText(getApplicationContext(), "Invalid Contact",
                    Toast.LENGTH_SHORT).show();
        }
        else if (invalid == false) {
            uploadToDatabase(author_name, publisher_name, book_name, departm, conditionm, selling_price, etUploadName, name , mobile,location);

            // finish();
        }
    }



    public void uploadToDatabase( String author_name, String publisher_name, String book_name, String departm ,String conditionm ,String selling_price,
                                  String etUploadName, String name, String mobile , String location) {
        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            private ProgressDialog progressDialog;

            @Override
            protected String doInBackground(String... params) {
                // String paramEmail = params[0];
                String paramAuthorName = params[0];
                String paramPublisherName = params[1];
                String ParamBookName = params[2];
                String paramDepart = params[3];
                String paramCondition = params[4];
                String paramSellingPrice= params[5];
                String paramuploadImageName = params[6];
                String paramName = params[7];
                String paramMobile = params[8];
                String paramLocation = params[9];


                //String email = editTextEmail.getText().toString();
                String author_name = Author_name.getText().toString();
                String publisher_name = Publisher_name.getText().toString();
                String  book_name = Book_name.getText().toString();
                String departm =  departsp.getSelectedItem().toString();
                String conditionm =  conditionsp.getSelectedItem().toString();
                String selling_price = Selling_price.getText().toString();
                String etUploadName = uploadImageName.getText().toString();
                String  name = Name.getText().toString();
                String  mobile = Mobile.getText().toString();
                String  location = Location.getText().toString();

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                // nameValuePairs.add(new BasicNameValuePair("email", email));
                nameValuePairs.add(new BasicNameValuePair("author_name", author_name));
                nameValuePairs.add(new BasicNameValuePair("publisher_name", publisher_name));
                nameValuePairs.add(new BasicNameValuePair("book_name", book_name));
                nameValuePairs.add(new BasicNameValuePair("departm", departm));
                nameValuePairs.add(new BasicNameValuePair("conditionm", conditionm));
                nameValuePairs.add(new BasicNameValuePair("selling_price", selling_price));
                nameValuePairs.add(new BasicNameValuePair("etUploadName" ,etUploadName));
                nameValuePairs.add(new BasicNameValuePair("name", name));
                nameValuePairs.add(new BasicNameValuePair("mobile", mobile));
                nameValuePairs.add(new BasicNameValuePair("location", location));

                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(
                            "http://usedbookapp.esy.es/upload.php");
                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                    HttpResponse response = httpClient.execute(httpPost);

                    HttpEntity entity = response.getEntity();


                } catch (ClientProtocolException e) {

                } catch (IOException e) {

                }
                return "success";

            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(UploadActivity.this);
                alertDialog.setTitle("Successful...");
                alertDialog.setMessage("Uploaded Data Successful!!!");
                //   alertDialog.setIcon(R.drawable.tick);
                alertDialog.setPositiveButton("Next", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i = new Intent(UploadActivity.this,HomeActivity.class);
                        finish();
                        startActivity(i);
                    }
                });
                alertDialog.show();
                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();

            }

        }
        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute(author_name, publisher_name, book_name, departm, conditionm, selling_price,etUploadName , name ,mobile,location);
    }

}
    /*
    @Override
    public void onClick(View v) {

        if(v == buttonChoose){
            showFileChooser();
        }

        if(v == buttonupload){
            upload();
        }
    }

}
*/





  /*  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            // When an Image is picked
            if (requestCode == RESULT_LOAD_IMG && resultCode == RESULT_OK
                    && null != data) {
                // Get the Image from data

                Uri selectedImage = data.getData();
                String[] filePathColumn = { MediaStore.Images.Media.DATA };

                // Get the cursor
                Cursor cursor = getContentResolver().query(selectedImage,
                        filePathColumn, null, null, null);
                // Move to first row
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                imgDecodableString = cursor.getString(columnIndex);
                cursor.close();
                ImageView imgView = (ImageView) findViewById(R.id.imgView);
                // Set the Image in ImageView after decoding the String
                imgView.setImageBitmap(BitmapFactory
                        .decodeFile(imgDecodableString));

            } else {
                Toast.makeText(this, "You haven't picked Image",
                        Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG)
                    .show();
        }

    } /*

    /*protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            switch (requestCode) {

                case REQUEST_CODE:
                    if (resultCode == Activity.RESULT_OK) {
                        //data gives you the image uri. Try to convert that to bitmap
                        break;
                    } else if (resultCode == Activity.RESULT_CANCELED) {
                        Log.e(TAG, "Selecting picture cancelled");
                    }
                    break;
            }
        } catch (Exception e) {
            Log.e(TAG, "Exception in onActivityResult : " + e.getMessage());
        }
    }*/




