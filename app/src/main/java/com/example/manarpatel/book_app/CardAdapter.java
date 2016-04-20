package com.example.manarpatel.book_app;

/**
 * Created by Manar Patel on 31-Mar-16.
 */


import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;


public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {

    //Imageloader to load image
    private ImageLoader imageLoader;
    private Context context;




    //List to store all superheroes
    List<Book> book;

    //Constructor of this class
    public CardAdapter(List<Book> book, Context context){
        super();
        //Getting all superheroes
        this.book = book;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.book_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

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
        holder.textViewSold.setText(books.getSell());

        holder.buy1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /// button click event

               // Intent i = new Intent(context, HomeActivity.class);
               // context.startActivity(i);

              //  Intent call = new Intent(Intent.ACTION_CALL);
              //  call.setData(Uri.parse("tel:9820111067"));
               // callIntent.setData(Uri.parse("tel:" + phone.getText().toString()));
              //  context.startActivity(call);
                try {
                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse("tel:" + books.getBuy()));
                    context.startActivity(callIntent);

                } catch (ActivityNotFoundException e) {
                    Log.e("helloandroid dialing ", "Call failed", e);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return book.size();
    }

  public static class ViewHolder extends RecyclerView.ViewHolder {
        //Views
        public NetworkImageView imageView;
        public TextView textViewName;
        public TextView textViewPublisher;
        public TextView textViewBookName;
        public TextView textViewEmail;
        public TextView textViewDepart;
        public TextView textViewSelling;
        public TextView textViewLoc;
        public TextView textViewCondition,textViewSold  ;
           public Button buy1;

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
            this.textViewSold= (TextView) itemView.findViewById(R.id.textViewsold);

            this. buy1 = (Button) itemView.findViewById(R.id.buybt);
//            button = (Button) findViewById(R.id.buttonCall);



         }


  }

    }
