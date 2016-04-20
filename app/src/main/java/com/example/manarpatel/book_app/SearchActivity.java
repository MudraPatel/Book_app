package com.example.manarpatel.book_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Manar Patel on 02-Apr-16.
 */


















public class SearchActivity extends AppCompatActivity {

    Button search;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

       search = (Button) findViewById(R.id.buttonGet);
        editText = (EditText) findViewById(R.id.editText);
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

}




/*

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;

import java.util.ArrayList;

public class SearchActivity extends Activity implements OnClickListener,
        OnEditorActionListener, OnItemClickListener {
    ListView mListView;
    MySimpleSearchAdapter mAdapter;
    Button btnSearch, btnLeft;
    EditText mtxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        mListView = (ListView) findViewById(R.id.mListView);
        mAdapter = new MySimpleSearchAdapter(this);
        btnSearch = (Button) findViewById(R.id.btnSearch);
        btnLeft = (Button) findViewById(R.id.btnLeft);
        mtxt = (EditText) findViewById(R.id.edSearch);
        mtxt.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (0 != mtxt.getText().length()) {
                    String spnId = mtxt.getText().toString();
                    setSearchResult(spnId);
                } else {
                    setData();
                }
            }
        });
        btnLeft.setOnClickListener(this);
        btnSearch.setOnClickListener(this);
        setData();
    }

    ArrayList<String> mAllData;

    String[] str = { "Hit me Hard", "GIJ, Rise Of Cobra", "Troy",
            "A walk To remember", "DDLJ", "Tom Peter Nmae", "David Miller",
            "Kings Eleven Punjab", "Kolkata Knight Rider", "Rest of Piece" };

    public void setData() {
        mAllData = new ArrayList<String>();
        mAdapter = new MySimpleSearchAdapter(this);
        for (int i = 0; i < str.length; i++) {
            mAdapter.addItem(str[i]);
            mAllData.add(str[i]);
        }
        mListView.setOnItemClickListener(this);
        mListView.setAdapter(mAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSearch:
                mtxt.setText("");
                setData();
                break;
            case R.id.btnLeft:

                break;
        }
    }

    public void setSearchResult(String str) {
        mAdapter = new MySimpleSearchAdapter(this);
        for (String temp : mAllData) {
            if (temp.toLowerCase().contains(str.toLowerCase())) {
                mAdapter.addItem(temp);
            }
        }
        mListView.setAdapter(mAdapter);
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        return false;
    }

    @Override
    public void onBackPressed() {
        setResult(Activity.RESULT_CANCELED);
        finish();
    }

    @Override
    public void onItemClick(AdapterView<?> arg0, View arg1, int position,
                            long arg3) {
        String str = mAdapter.getItem(position);
        Toast.makeText(this, str, Toast.LENGTH_LONG).show();
    }
}


*/





/*  Final Code

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


public class SearchActivity extends Fragment
{
    View myFragmentView;
    SearchView search;
    ImageButton buttonBarcode;
    ImageButton buttonAudio;
    Typeface type;
    ListView searchResults;
    String found = "N";


    //This arraylist will have data as pulled from server. This will keep cumulating.
    ArrayList<Book> bookResults = new ArrayList<Book>();
   // List<NameValuePair> params = new ArrayList<NameValuePair>();
    //Based on the search string, only filtered books will be moved here from bookResults
    ArrayList<Book> filteredBookResults = new ArrayList<Book>();

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        //get the context of the HomeScreen Activity
        final HomeActivity activity = (HomeActivity) getActivity();

        //define a typeface for formatting text fields and listview.

        type= Typeface.createFromAsset(activity.getAssets(),"fonts/book.TTF");
        myFragmentView = inflater.inflate(R.layout.activity_search, container, false);

        search=(SearchView) myFragmentView.findViewById(R.id.searchView1);
        search.setQueryHint("Start typing to search...");

        searchResults = (ListView) myFragmentView.findViewById(R.id.listview_search);
        buttonBarcode = (ImageButton) myFragmentView.findViewById(R.id.imageButton2);
        buttonAudio = (ImageButton) myFragmentView.findViewById(R.id.imageButton1);


        //this part of the code is to handle the situation when user enters any search criteria, how should the
        //application behave?

        search.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener()
        {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                // TODO Auto-generated method stub

                //Toast.makeText(activity, String.valueOf(hasFocus),Toast.LENGTH_SHORT).show();
            }
        });

        search.setOnQueryTextListener(new OnQueryTextListener()
        {

            @Override
            public boolean onQueryTextSubmit(String query) {
                // TODO Auto-generated method stub

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                if (newText.length() > 3)
                {

                    searchResults.setVisibility(myFragmentView.VISIBLE);
                    myAsyncTask m= (myAsyncTask) new myAsyncTask().execute(newText);
                }
                else
                {

                    searchResults.setVisibility(myFragmentView.INVISIBLE);
                }



                return false;
            }

        });
        return myFragmentView;
    }

    //this filters books from bookResults and copies to filteredBookResults based on search text

    public void filterBookArray(String newText)
    {

        String pName;

        filteredBookResults.clear();
        for (int i = 0; i < bookResults.size(); i++)
        {
            pName = bookResults.get(i).getBookName().toLowerCase();
            if ( pName.contains(newText.toLowerCase()) ||
                    bookResults.get(i).getBookBarcode().contains(newText))
            {
                filteredBookResults.add(bookResults.get(i));

            }
        }

    }

    //in this myAsyncTask, we are fetching data from server for the search string entered by user.
    class myAsyncTask extends AsyncTask<String, Void, String>
    {
        JSONParser jParser;
        JSONArray bookList;
        String url=new String();
        String textSearch;
        ProgressDialog pd;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            bookList=new JSONArray();
            jParser = new JSONParser();
            pd= new ProgressDialog(getActivity());
            pd.setCancelable(false);
            pd.setMessage("Searching...");
            pd.getWindow().setGravity(Gravity.CENTER);
            pd.show();
        }

        @Override
        protected String doInBackground(String... sText) {

            url=   "http://usedbookapp.esy.es/upload.php"+sText[0];
            String returnResult = getBookList(url);
            this.textSearch = sText[0];
            return returnResult;

        }

        public String getBookList(String url)
        {

            Book tempBook = new Book();
            String matchFound = "N";
            //bookResults is an arraylist with all book details for the search criteria
            //bookResults.clear();


            try {


                JSONObject json = jParser.getJSONFromUrl(url);

                bookList = json.getJSONArray("BookList");

                //parse date for dateList
                for(int i=0;i<bookList.length();i++)
                {
                    tempBook = new Book();

                    JSONObject obj=bookList.getJSONObject(i);

                    tempBook.setBookCode(obj.getString("BookCode"));
                    tempBook.setBookName(obj.getString("BookName"));
                    tempBook.setBookGrammage(obj.getString("BookGrammage"));
                    tempBook.setBookBarcode(obj.getString("BookBarcode"));
                    tempBook.setBookDivision(obj.getString("BookCatCode"));
                    tempBook.setBookDepartment(obj.getString("BookSubCode"));
                    tempBook.setBookMRP(obj.getString("BookMRP"));
                    tempBook.setBookBBPrice(obj.getString("BookBBPrice"));

                    //check if this book is already there in bookResults, if yes, then don't add it again.
                    matchFound = "N";

                    for (int j=0; j < bookResults.size();j++)
                    {

                        if (bookResults.get(j).getBookCode().equals(tempBook.getBookCode())) {
                            matchFound = "Y";
                        }
                    }

                    if (matchFound == "N")
                    {
                        bookResults.add(tempBook);
                    }

                }

                return ("OK");

            } catch (Exception e) {
                e.printStackTrace();
                return ("Exception Caught");
            }
        }

        @Override
        protected void onPostExecute(String result) {

            super.onPostExecute(result);

            if(result.equalsIgnoreCase("Exception Caught"))
            {
                Toast.makeText(getActivity(), "Unable to connect to server,please try later", Toast.LENGTH_LONG).show();

                pd.dismiss();
            }
            else
            {


                //calling this method to filter the search results from bookResults and move them to
                //filteredBookResults
                filterBookArray(textSearch);
                searchResults.setAdapter(new SearchResultsAdapter(getActivity(),filteredBookResults));
                pd.dismiss();
            }
        }

    }
}

class SearchResultsAdapter extends BaseAdapter
{
    private LayoutInflater layoutInflater;

    private ArrayList<Book> bookDetails=new ArrayList<Book>();
    int count;
    Typeface type;
    Context context;

    //constructor method
    public SearchResultsAdapter(Context context, ArrayList<Book> book_details) {

        layoutInflater = LayoutInflater.from(context);

        this.bookDetails=book_details;
        this.count= book_details.size();
        this.context = context;
        type= Typeface.createFromAsset(context.getAssets(),"fonts/book.TTF");

    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public Object getItem(int arg0) {
        return bookDetails.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        return arg0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {

        ViewHolder holder;
        Book tempBook = bookDetails.get(position);

        if (convertView == null)
        {
            convertView = layoutInflater.inflate(R.layout.listtwoview_searchresult, null);
            holder = new ViewHolder();
            holder.book_name = (TextView) convertView.findViewById(R.id.book_name);
            holder.book_mrp = (TextView) convertView.findViewById(R.id.book_mrp);
            holder.book_mrpvalue = (TextView) convertView.findViewById(R.id.book_mrpvalue);
            holder.book_bb = (TextView) convertView.findViewById(R.id.book_bb);
            holder.book_bbvalue = (TextView) convertView.findViewById(R.id.book_bbvalue);
            holder.addToCart = (Button) convertView.findViewById(R.id.add_cart);

            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }


        holder.book_name.setText(tempBook.getBookName());
        holder.book_name.setTypeface(type);

        holder.book_mrp.setTypeface(type);

        holder.book_mrpvalue.setText(tempBook.getBookMRP());
        holder.book_mrpvalue.setTypeface(type);

        holder.book_bb.setTypeface(type);

        holder.book_bbvalue.setText(tempBook.getBookBBPrice());
        holder.book_bbvalue.setTypeface(type);

        return convertView;
    }

    static class ViewHolder
    {
        TextView book_name;
        TextView book_mrp;
        TextView book_mrpvalue;
        TextView book_bb;
        TextView book_bbvalue;
        TextView book_savings;
        TextView book_savingsvalue;
        TextView qty;
        TextView book_value;
        Button addToCart;

    }

}


*/

