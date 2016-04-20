package com.example.manarpatel.book_app;


import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;


public class SessionHandler {
    // Editor for Shared preferences
    SharedPreferences.Editor editor;
    // Context
    Context _context;
    // All Shared Preferences Keys
    private static final String IS_LOGIN = "IsLoggedIn";
    // User name (make variable public to access from outside)
    public static final String KEY_NAME = "name";
    // Email address (make variable public to access from outside)
    public static final String KEY_PASS = "password";
    public static final String PREF_NAME = "MyPreference";
    SharedPreferences sharedPreferences;

    public SessionHandler(Context context){
        this._context = context;
        sharedPreferences = _context.getSharedPreferences(PREF_NAME, Activity.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void createLoginSession(String name, String password){
        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);
        // Storing name in pref
        editor.putString(KEY_NAME, name);
        // Storing email in pref
        editor.putString(KEY_PASS, password);
        // commit changes
        editor.commit();
    }

    /*public void checkLogin(){
        // Check login status
        if(!this.isLoggedIn()){
            // user is not logged in redirect him to Login Activity
            Intent i = new Intent(_context, SecondActivity.class);
            // Closing all the Activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Staring Login Activity
            _context.startActivity(i);
        }

    }*/


    public HashMap<String, String> getUserDetails(){
        HashMap<String, String> user = new HashMap<String, String>();
        // user name
        user.put(KEY_NAME, sharedPreferences.getString(KEY_NAME, null));

        // user email id
        user.put(KEY_PASS, sharedPreferences.getString(KEY_PASS, null));

        // return user
        return user;
    }

    public boolean isLoggedIn(){

        return sharedPreferences.getBoolean(IS_LOGIN, false);
    }

    public void logoutUser() {
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();
    }
}
