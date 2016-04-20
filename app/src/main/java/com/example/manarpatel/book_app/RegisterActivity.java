package com.example.manarpatel.book_app;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RegisterActivity extends AppCompatActivity {

    private EditText editTextName;
    private EditText editTextEmail;
    private EditText editTextContact;
    private EditText editTextPassword;
    private ProgressDialog progressDialog;
    String[] cities = {"Mumbai","Navi_Mumbai ","Pune"};
    String[] Area = {"CST","Cottongreen","GTB","Mankhurd","Kharghar","Vashi","Panvel","Vadala","Churgate","Malad","Nerul","Thane","Dadar"};
    Spinner spinnerDropDown;
    Spinner spinnerDropDown1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextContact = (EditText) findViewById(R.id.editTextContact);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        spinnerDropDown =(Spinner)findViewById(R.id.spinner1);
        spinnerDropDown1 =(Spinner)findViewById(R.id.spinner2);
        //Spinner For Cities
        ArrayAdapter<String> adapter= new ArrayAdapter<String>(this,android.
                R.layout.simple_spinner_dropdown_item,cities);

        spinnerDropDown.setAdapter(adapter);

        spinnerDropDown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                // Get select item
                int sid1 = spinnerDropDown.getSelectedItemPosition();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });
        //Spinner For States
        ArrayAdapter<String> adapter1= new ArrayAdapter<String>(this,android.
                R.layout.simple_spinner_dropdown_item,Area);

        spinnerDropDown1.setAdapter(adapter1);

        spinnerDropDown1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                // Get select item
                int sid2 = spinnerDropDown.getSelectedItemPosition();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });
        Button login = (Button)findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);

                startActivity(intent);

            }

        });


    }




        public void insert(View view) {
        String name = editTextName.getText().toString();
        String email = editTextEmail.getText().toString();
        String sid1 =  spinnerDropDown.getSelectedItem().toString();
        String sid2 =  spinnerDropDown1.getSelectedItem().toString();
        String contact = editTextContact.getText().toString();
        String password = editTextPassword.getText().toString();

     //   String cities = cities.getText().toString();
       // String states = states.getText().toString();

        //String email1 = email.getText().toString().trim();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        boolean invalid = false;
        if (name.equals("")) {
            invalid = true;
            Toast.makeText(getApplicationContext(), "Please Enter your Name",
                    Toast.LENGTH_SHORT).show();
        } else if (email.equals("")) {
            invalid = true;
            Toast.makeText(getApplicationContext(), "Please Enter your  EmailID",
                    Toast.LENGTH_SHORT).show();
        } else if (!email.matches(emailPattern)) {
            invalid = true;
            Toast.makeText(getApplicationContext(),
                    "Please enter Correct EmailID", Toast.LENGTH_SHORT).show();
        }
        else if (!email.contains("@")) {
            invalid = true;
            Toast.makeText(getApplicationContext(), "Please Enter your  Valid EmailID",
                    Toast.LENGTH_SHORT).show();
        }else if (contact.equals("")) {
            invalid = true;
            Toast.makeText(getApplicationContext(), "Please Enter your Contact No.",
                    Toast.LENGTH_SHORT).show();
        } else if (contact.length() != 10) {
            invalid = true;
            Toast.makeText(getApplicationContext(),
                    "Please enter atleast 10 digits contact number", Toast.LENGTH_SHORT).show();
        } else if (password.equals("")) {
            invalid = true;
            Toast.makeText(getApplicationContext(), "Please Enter your Password", Toast.LENGTH_SHORT).show();
        } else if (password.length() < 6) {
            invalid = true;
            Toast.makeText(getApplicationContext(),
                    "Please enter atleast 6 digits password", Toast.LENGTH_SHORT).show();
        } else if (invalid == false) {
            insertToDatabase(name, email, contact, password,sid1, sid2);
            // finish();
        }
    }


    public void insertToDatabase(String name, String email, String contact, String password,String sid1, String sid2) {
        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            private ProgressDialog progressDialog;

            @Override
            protected String doInBackground(String... params) {
                String paramUsername = params[0];
                String paramEmail = params[1];
                String ParamContact = params[2];
                String paramPassword = params[3];
                String paramsDropDown = params[4];
                String paramsDropDown1 = params[5];



                String name = editTextName.getText().toString();
                String email = editTextEmail.getText().toString();
                String contact = editTextContact.getText().toString();
                String password = editTextPassword.getText().toString();
                String sid1 = spinnerDropDown.getSelectedItem().toString();
                String sid2 = spinnerDropDown1.getSelectedItem().toString();



                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("name", name));
                nameValuePairs.add(new BasicNameValuePair("email", email));
                nameValuePairs.add(new BasicNameValuePair("contact", contact));
                nameValuePairs.add(new BasicNameValuePair("password", password));
                nameValuePairs.add(new BasicNameValuePair("sid1", sid1));
                nameValuePairs.add(new BasicNameValuePair("sid2", sid2));

                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(
                            "http://usedbookapp.esy.es/insert.php");
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
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(RegisterActivity.this);
                alertDialog.setTitle("Successful...");
                alertDialog.setMessage("Registration Successful!!!");
             //   alertDialog.setIcon(R.drawable.tick);
                alertDialog.setPositiveButton("Next", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i = new Intent(RegisterActivity.this,LoginActivity.class);
                        finish();
                        startActivity(i);
                    }
                });
                alertDialog.show();
                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();

            }

        }
        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute(name, email, contact, password,sid1,sid2);
    }

}


