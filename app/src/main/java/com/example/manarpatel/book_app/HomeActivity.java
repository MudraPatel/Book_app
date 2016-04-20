package com.example.manarpatel.book_app;

import android.annotation.TargetApi;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.android.volley.RequestQueue;

import java.util.List;



@TargetApi(Build.VERSION_CODES.M)
public class HomeActivity extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    SessionHandler sessionHandler;
    //Creating a List of superheroes
    private List<Book> listBookes;

    //Creating Views
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    private Button comp;
    private Button extc;
    private Button mech;
    private Button civil;
    private Button elec;

    //Volley Request Queue
    private RequestQueue requestQueue;

    //The request counter to send ?page=1, ?page=2  requests
    private int requestCount = 1;


    @TargetApi(Build.VERSION_CODES.M)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        sessionHandler = new SessionHandler(this);
      // TextView tv = (TextView) findViewById(R.id.profileemail1);
       //tv.setText("Welcome ," + getIntent().getExtras().getString("username"));
      //  greetingTextView.setText(String.format("Hello, %s", user.getUsername()));

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //        .setAction("Action", null).show();
                Intent sendIntent = new Intent(Intent.ACTION_VIEW);
                sendIntent.setType("plain/text");
                sendIntent.setData(Uri.parse("thrift_books@gmail.com"));
                sendIntent.setClassName("com.google.android.gm", "com.google.android.gm.ComposeActivityGmail");
                sendIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"thrift_books@gmail.com"});
                sendIntent.putExtra(Intent.EXTRA_SUBJECT, "test");
                sendIntent.putExtra(Intent.EXTRA_TEXT, "hello. this is a message sent from my demo app :-)");
                startActivity(sendIntent);
            }
        });
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        onNavigationprofile();

        comp = (Button) findViewById(R.id.button3);
        comp.setOnClickListener(this);

        extc = (Button) findViewById(R.id.button4);
        extc.setOnClickListener(this);

        mech = (Button) findViewById(R.id.button5);
        mech.setOnClickListener(this);

        civil = (Button) findViewById(R.id.button6);
        civil.setOnClickListener(this);

        elec = (Button) findViewById(R.id.button7);
        elec.setOnClickListener(this);

        // View v = LayoutInflater.from(navigationView.getContext())
        //    .inflate(R.layout.nav_header_home, navigationView, false);
        // Nav_Header_home viewHolder = new Nav_Header_home();
        //  TextView tv = (TextView) navigationView.findViewById(R.id.profileemail);
        //  tv.setText("Welcome ," + getIntent().getExtras().getString("username"));


        // TextView tv = (TextView) findViewById(R.id.profileemail);
        // tv.setText("Welcome ," + getIntent().getExtras().getString("username"));
        // home page
      /*  super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_computer);

        //Initializing Views
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //Initializing our superheroes list
        listBookes = new ArrayList<>();
        requestQueue = Volley.newRequestQueue(this);

        //Calling method to get data to fetch data
        getData();

        //Adding an scroll change listener to recyclerview
        recyclerView.setOnScrollChangeListener( this);

        //initializing our adapter
        adapter = new CardAdapter(listBookes, this);

        //Adding adapter to recyclerview
        recyclerView.setAdapter(adapter);
*/

    }



/*
        Intent intent = getIntent();

        Bundle intentBundle = intent.getExtras();

        String loggedUser = intentBundle.getString("USERNAME");

        loggedUser = capitalizeFirstCharacter(loggedUser);

        String message = intentBundle.getString("MESSAGE");

        TextView loginUsername = (TextView)findViewById(R.id.login_user);

        TextView successMessage = (TextView)findViewById(R.id.message);

        loginUsername.setText(loggedUser);

        successMessage.setText(message);  */





/*

    private String capitalizeFirstCharacter(String textInput){

        String input = textInput.toLowerCase();

        String output = input.substring(0, 1).toUpperCase() + input.substring(1);

        return output;
        }
*/

    /*
@Override
public boolean onOptionsItemSelected(MenuItem item) {
    return super.onOptionsItemSelected(item);
}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_search, menu);
        final SearchView searchView = (SearchView) menu.findItem(R.id.menu_search).getActionView();


        searchView.setIconifiedByDefault(true);
        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchView.setIconified(true);
                Intent intent = new Intent(HomeActivity.this, SearchActivity.class);
                startActivity(intent);

            }
        });
        return true;
    }

*/
/*

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_search, menu);
      final SearchView searchView = (SearchView) menu.findItem(R.id.menu_search).getActionView();


        searchView.setIconifiedByDefault(true);
        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchView.setIconified(true);
                Intent intent = new Intent(HomeActivity.this, SearchActivity.class);
                startActivity(intent);

            }
        });
        return true;
    }
*/


/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // TODO Auto-generated method stub
        super.onCreateOptionsMenu(menu);
        MenuInflater mymenu = getMenuInflater();
        mymenu.inflate(R.menu.menu_search, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        switch (item.getItemId()) {
            case R.id.menu_search:
                Intent i = new Intent(this , SearchActivity.class);
                startActivity(i);
                break;


        }

        return false;
    }*/

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.home, menu);

        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent menu = new Intent(this,SettingsActivity.class);
            this.startActivity(menu);
            return true;
        }
        else if(id == R.id.action_logout) {
            sessionHandler.logoutUser();
            Intent menu = new Intent(this,LoginActivity.class);
            this.startActivity(menu);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void onNavigationprofile() {
        Nav_Header_home nav = new Nav_Header_home();

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            Intent menu = new Intent(this, UploadActivity.class);
            this.startActivity(menu);
            return true;
            // Handle the camera action
        } else if (id == R.id.nav_comp) {
            Intent menu = new Intent(this, ComputerActivity.class);
            this.startActivity(menu);
            return true;

        } else if (id == R.id.nav_civil) {
            Intent menu = new Intent(this, CivilActivity.class);
            this.startActivity(menu);
            return true;

        } else if (id == R.id.nav_mech) {
            Intent menu = new Intent(this, MechnicalActivity.class);
            this.startActivity(menu);
            return true;

        } else if (id == R.id.nav_extc) {
            Intent menu = new Intent(this, EXTCActivity.class);
            this.startActivity(menu);
            return true;

        } else if (id == R.id.nav_electrical) {
            Intent menu = new Intent(this, ElectricalActivity.class);
            this.startActivity(menu);
            return true;

        }
        else if (id == R.id.nav_sell) {
            Intent menu = new Intent(this, SellingActivity.class);
            this.startActivity(menu);
            return true;

        }else if (id == R.id.nav_contact) {
            Intent menu = new Intent(this, ContactusActivity.class);
            this.startActivity(menu);
            return true;

        } else if (id == R.id.nav_manage) {
            Intent menu = new Intent(this, SettingsActivity.class);
            this.startActivity(menu);
            return true;

        } else if (id == R.id.nav_share) {
            Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            String shareBody = "Here is the share content body";
            sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
            startActivity(Intent.createChooser(sharingIntent, "Share via"));

        } else if (id == R.id.nav_about_us) {
            Intent menu = new Intent(this, AboutusActivity.class);
            this.startActivity(menu);
            return true;
        } else if (id == R.id.nav_logout) {
            sessionHandler.logoutUser();
            Intent i = new Intent(HomeActivity.this, LoginActivity.class);
            startActivity(i);
            return true;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button3:

                Intent CompIntent = new Intent(this, ComputerActivity.class);
                startActivity(CompIntent);
                break;

            case R.id.button4:

                Intent ExtcIntent = new Intent(this, EXTCActivity.class);
                startActivity(ExtcIntent);
                break;

            case R.id.button5:

                Intent MechIntent = new Intent(this, MechnicalActivity.class);
                startActivity(MechIntent);
                break;

            case R.id.button6:

                Intent CivilIntent = new Intent(this, CivilActivity.class);
                startActivity(CivilIntent);
                break;

            case R.id.button7:

                Intent ElecIntent = new Intent(this, ElectricalActivity.class);
                startActivity(ElecIntent);
                break;

        }

    }
}

// home

    //Request to get json from server we are passing an integer here
    //This integer will used to specify the page number for the request ?page = requestcount
    //This method would return a JsonArrayRequest that will be added to the request queue

    /*private JsonArrayRequest getDataFromServer(int requestCount) {
        //Initializing ProgressBar
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar1);

        //Displaying Progressbar
        progressBar.setVisibility(View.VISIBLE);
        setProgressBarIndeterminateVisibility(true);

        //JsonArrayRequest of volley
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Config.DATA_URL1 + String.valueOf(requestCount),
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        //Calling method parseData to parse the json response
                        parseData(response);
                        //Hiding the progressbar
                        progressBar.setVisibility(View.GONE);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressBar.setVisibility(View.GONE);
                        //If an error occurs that means end of the list has reached
                        Toast.makeText(HomeActivity.this, "No More Items Available", Toast.LENGTH_SHORT).show();
                    }
                });

        //Returning the request
        return jsonArrayRequest;
    }

    //This method will get data from the web api
    private void getData() {
        //Adding the method to the queue by calling the method getDataFromServer
        requestQueue.add(getDataFromServer(requestCount));
        //Incrementing the request counter
        requestCount++;
    }

    //This method will parse json data
    private void parseData(JSONArray array) {
        for (int i = 0; i < array.length(); i++) {
            //Creating the superhero object
            Book books = new Book();
            JSONObject json = null;
            try {
                //Getting json
                json = array.getJSONObject(i);

                //Adding data to the superhero object
                books.setImageUrl(json.getString(Config.TAG_IMAGE_URL));
                books.setEmail(json.getString(Config.TAG_EMAIL));
                books.setName(json.getString(Config.TAG_AUTHOR_NAME));
                books.setPublisher(json.getString(Config.TAG_PUBLISHER_NAME));
                books.setBookName(json.getString(Config.TAG_BOOK_NAME));
                books.setDepartment(json.getString(Config.TAG_DEPART));
                books.setSelling(json.getString(Config.TAG_SELLING_PRICE));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            //Adding the superhero object to the list
            listBookes.add(books);
        }

        //Notifying the adapter that data has been added or changed
        adapter.notifyDataSetChanged();
    }

    //This method would check that the recyclerview scroll has reached the bottom or not
    private boolean isLastItemDisplaying(RecyclerView recyclerView) {
        if (recyclerView.getAdapter().getItemCount() != 0) {
            int lastVisibleItemPosition = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastCompletelyVisibleItemPosition();
            if (lastVisibleItemPosition != RecyclerView.NO_POSITION && lastVisibleItemPosition == recyclerView.getAdapter().getItemCount() - 1)
                return true;
        }
        return false;
    }


    public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
        //Ifscrolled at last then
        if (isLastItemDisplaying(recyclerView)) {
            //Calling the method getdata again
            getData();
        }
    }

*/


