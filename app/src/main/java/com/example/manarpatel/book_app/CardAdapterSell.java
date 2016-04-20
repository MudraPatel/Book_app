package com.example.manarpatel.book_app;

/**
 * Created by Manar Patel on 31-Mar-16.
 */


import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;


public class CardAdapterSell extends RecyclerView.Adapter<CardAdapterSell.ViewHolder> {

    //Imageloader to load image
    private ImageLoader imageLoader;
    private Context context;
    public NetworkImageView imageView;
    public TextView textViewName;
    public TextView textViewPublisher;
    public TextView textViewBookName;
    public TextView textViewEmail;
    public TextView textViewDepart;
    public TextView textViewSelling;
    public TextView textViewLoc;
    public TextView textViewCondition ;
    public TextView textViewBuy;

    ProgressDialog PD;


    //List to store all superheroes
    List<Book> book;

    //Constructor of this class
    public CardAdapterSell(List<Book> book, Context context){
        super();
        //Getting all superheroes
        this.book = book;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.book_listsell, parent, false);

        ViewHolder viewHolder = new ViewHolder(v);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        //Getting the particular item from the list
        final Book books =  book.get(position);

        //Loading image from url
        imageLoader = CustomRequest.getInstance(context).getImageLoader();
        imageLoader.get(books.getImageUrl(), ImageLoader.getImageListener(holder.imageView, R.drawable.image, android.R.drawable.ic_dialog_alert));

        //Showing data on the views
        holder.imageView.setImageUrl(books.getImageUrl(), imageLoader);
        holder.textViewEmail.setText(books.getEmail());
        holder.textViewName.setText(books.getName());
        holder.textViewBookName.setText(books.getBookName());
        holder.textViewPublisher.setText(books.getPublisher());
        holder.textViewDepart.setText(books.getDepartment());
        holder.textViewCondition.setText(books.getCondition());
        holder.textViewSelling.setText(books.getSelling());
        holder.textViewLoc.setText(books.getLocation());
        holder.textViewBuy.setText(books.getBuy());
          holder.sell1.setText(books.getSell());

        holder.sell1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //  Intent call = new Intent(Intent.ACTION_CALL);
                //  call.setData(Uri.parse("tel:9820111067"));
                // callIntent.setData(Uri.parse("tel:" + phone.getText().toString()));
                //  context.startActivity(call);

              // holder.invokesell();
                String email =  books.getEmail().toString();
                String author = books.getName().toString();
                String publisher = books.getPublisher().toString();
                String department = books.getDepartment().toString();
                String bookname = books.getBookName().toString();
                String selling = books.getSelling().toString();
                String mobile =   books.getBuy().toString();
                String location = books.getLocation().toString();
                String condition = books.getCondition().toString();
                sellbk(email, author, publisher, department, bookname, selling, mobile, location, condition);

            }
        });
    }

    @Override
    public int getItemCount() {
        return book.size();
    }

  public  class ViewHolder extends RecyclerView.ViewHolder {
        //Views
        public NetworkImageView imageView;
        public TextView textViewName;
        public TextView textViewPublisher;
        public TextView textViewBookName;
        public TextView textViewEmail;
        public TextView textViewDepart;
        public TextView textViewSelling;
        public TextView textViewLoc;
        public TextView textViewCondition ;
        public TextView textViewBuy ;
           public Button sell1;

        //Initializing Views
        public ViewHolder(View itemView) {
            super(itemView);
            this.imageView = (NetworkImageView) itemView.findViewById(R.id.imageViewHero);
            this.textViewName = (TextView) itemView.findViewById(R.id.textViewName);
            this.textViewPublisher = (TextView) itemView.findViewById(R.id.textViewPublisher);
            this.textViewBookName = (TextView) itemView.findViewById(R.id.textViewBook_Name);
            this.textViewDepart = (TextView) itemView.findViewById(R.id.textViewDepart);
            this.textViewEmail = (TextView) itemView.findViewById(R.id.textViewEmail);
            this.textViewSelling = (TextView) itemView.findViewById(R.id.textViewSelling);
            this.textViewLoc= (TextView) itemView.findViewById(R.id.textViewLoc);
            this.textViewCondition= (TextView) itemView.findViewById(R.id.textViewCondition);
            this.textViewBuy= (TextView) itemView.findViewById(R.id.textViewBuy);

           this. sell1 = (Button) itemView.findViewById(R.id.sellbt);
//            button = (Button) findViewById(R.id.buttonCall);

         }
      public void invokesell() {

          String email =  textViewEmail.getText().toString();
          String author = textViewName.getText().toString();
          String publisher = textViewPublisher.getText().toString();
          String department = textViewDepart.getText().toString();
          String bookname = textViewBookName.getText().toString();
          String selling = textViewSelling.getText().toString();
          String mobile =  textViewBuy.getText().toString();
          String location = textViewLoc.getText().toString();
          String condition = textViewCondition.getText().toString();
          sellbk(email, author,publisher,department,bookname,selling,mobile,location,condition );
      }



  }


 private void sellbk(final String email, final String author, final String publisher, final String department, final String bookname, final String selling, final String mobile, final String location, final String condition) {

        class SellAsync extends AsyncTask<String, Void, String> {

            private Dialog loadingDialog;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loadingDialog = ProgressDialog.show(context , "Please wait", "Loading...");
            }

            @Override
            protected String doInBackground(String... params) {
                String email = params[0];
                String author = params[1];
                String publisher = params[2];
                String department = params[3];
                String bookname = params[4];
                String selling = params[5];
                String mobile = params[6];
                String location = params[7];
                String condition = params[8];


           /*     Book books = new Book();

                String email =  books.getEmail().toString();
                String author = books.getName().toString();
                String publisher = books.getPublisher().toString();
                String department = books.getDepartment().toString();
                String bookname = books.getBookName().toString();
                String selling = books.getSelling().toString();
                String mobile =   books.getBuy().toString();
                String location = books.getLocation().toString();
                String condition = books.getCondition().toString();

*/
                InputStream is = null;
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("email", email));
                nameValuePairs.add(new BasicNameValuePair("author", author));
                nameValuePairs.add(new BasicNameValuePair("publisher", publisher));
                nameValuePairs.add(new BasicNameValuePair("department", department));
                nameValuePairs.add(new BasicNameValuePair("bookname", bookname));
                nameValuePairs.add(new BasicNameValuePair("selling", selling));
                nameValuePairs.add(new BasicNameValuePair("mobile", mobile));
                nameValuePairs.add(new BasicNameValuePair("location", location));
                nameValuePairs.add(new BasicNameValuePair("condition", condition));

                String result = null;

                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(
                            "http://usedbookapp.esy.es/delete.php");
                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                    HttpResponse response = httpClient.execute(httpPost);

                    HttpEntity entity = response.getEntity();

                    is = entity.getContent();

                    BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"), 8);
                    StringBuilder sb = new StringBuilder();

                    String line = null;
                    while ((line = reader.readLine()) != null) {
                        sb.append(line + "\n");
                    }
                    result = sb.toString();
                } catch (ClientProtocolException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return result;
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
                alertDialog.setTitle("Successful...");
                alertDialog.setMessage("Sold Successful!!!");
                //   alertDialog.setIcon(R.drawable.tick);
                alertDialog.setPositiveButton("Next", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i = new Intent(context , SellingActivity.class);

                        context.startActivity(i);
                    }
                });
                alertDialog.show();
                Toast.makeText(context.getApplicationContext(), result, Toast.LENGTH_LONG).show();

            }


        }


        SellAsync la = new SellAsync();
        la.execute(email,author,publisher,department,bookname,selling,mobile,location,condition);

    }


    }
