package com.example.manarpatel.book_app;

/**
 * Created by Manar Patel on 10-Mar-16.
 */

/*
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.io.IOException;
import java.io.UnsupportedEncodingException;


public class JSONParser {

        static InputStream is = null;
        static JSONObject jobj = null;
        static String json = "";

        public JSONParser(){

        }


public JSONObject makeHttpRequest(String url, String method, List<NameValuePair> params){
        try{
        if(method == "POST"){
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new  HttpPost(url);
        httpPost.setEntity(new UrlEncodedFormEntity(params));

        HttpResponse httpResponse = httpClient.execute(httpPost);
        HttpEntity httpEntity = httpResponse.getEntity();
        is = httpEntity.getContent();

        }else if (method  == "GET"){
        DefaultHttpClient httpClient = new DefaultHttpClient();
        String paramString = URLEncodedUtils.format(params,"utf-8");
        url += "?" + paramString;
        HttpGet httpGet = new HttpGet(url);

        HttpResponse httpResponse = httpClient.execute(httpGet);
        HttpEntity httpEntity = httpResponse.getEntity();
        is = httpEntity.getContent();


        }

        }catch (UnsupportedEncodingException e){
        e.printStackTrace();
        }catch (ClientProtocolException e){
        e.printStackTrace();
        }catch (IOException e){
        e.printStackTrace();
        }
        try {
        BufferedReader reader = new BufferedReader(new InputStreamReader(
        is, "iso-8859-1"), 8);
        StringBuilder sb = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) {
        sb.append(line + "\n");
        }
        is.close();
        json = sb.toString();
        } catch (Exception e) {
        Log.e("Buffer Error", "Error converting result " + e.toString());
        }

        try {
        jobj  = new JSONObject(json);
        } catch (JSONException e) {
        Log.e("JSON Parser", "Error parsing data " + e.toString());
        }

        // return JSON String
        return jobj;
        }

public JSONObject getJSONFromUrl(final String url){
        try {
        // Construct the client and the HTTP request.
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url);

        // Execute the POST request and store the response locally.
        HttpResponse httpResponse = httpClient.execute(httpPost);
        // Extract data from the response.
        HttpEntity httpEntity = httpResponse.getEntity();
        // Open an inputStream with the data content.
        is = httpEntity.getContent();

        } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
        } catch (ClientProtocolException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }

        try {
        // Create a BufferedReader to parse through the inputStream.
        BufferedReader reader = new BufferedReader(new InputStreamReader(
        is, "iso-8859-1"), 8);
        // Declare a string builder to help with the parsing.
        StringBuilder sb = new StringBuilder();
        // Declare a string to store the JSON object data in string form.
        String line = null;

        // Build the string until null.
        while ((line = reader.readLine()) != null) {
        sb.append(line + "\n");
        }

        // Close the input stream.
        is.close();
        // Convert the string builder data to an actual string.
        json = sb.toString();
        } catch (Exception e) {
        Log.e("Buffer Error", "Error converting result " + e.toString());
        }

        try {
        jobj = new JSONObject(json);
        } catch (JSONException e) {
        Log.e("JSON Parser", "Error parsing data " + e.toString());
        }

        // Return the JSON Object.
        return jobj;
        }
        }

*/


import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;

public class JSONParser {

        String charset = "UTF-8";
        HttpURLConnection conn;
        DataOutputStream wr;
        StringBuilder result;
        URL urlObj;
        JSONObject jObj = null;
        StringBuilder sbParams;
        String paramsString;
        static String json = "";
        static InputStream is = null;
        static JSONObject jobj = null;

        public JSONObject makeHttpRequest(String url, String method,
                                          HashMap<String, String> params) {

                sbParams = new StringBuilder();
                int i = 0;
                for (String key : params.keySet()) {
                        try {
                                if (i != 0){
                                        sbParams.append("&");
                                }
                                sbParams.append(key).append("=")
                                        .append(URLEncoder.encode(params.get(key), charset));

                        } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                        }
                        i++;
                }

                if (method.equals("POST")) {
                        // request method is POST
                        try {
                                urlObj = new URL(url);

                                conn = (HttpURLConnection) urlObj.openConnection();

                                conn.setDoOutput(true);

                                conn.setRequestMethod("POST");

                                conn.setRequestProperty("Accept-Charset", charset);

                                conn.setReadTimeout(10000);
                                conn.setConnectTimeout(15000);

                                conn.connect();

                                paramsString = sbParams.toString();

                                wr = new DataOutputStream(conn.getOutputStream());
                                wr.writeBytes(paramsString);
                                wr.flush();
                                wr.close();

                        } catch (IOException e) {
                                e.printStackTrace();
                        }
                }
                else if(method.equals("GET")){
                        // request method is GET

                        if (sbParams.length() != 0) {
                                url += "?" + sbParams.toString();
                        }

                        try {
                                urlObj = new URL(url);

                                conn = (HttpURLConnection) urlObj.openConnection();

                                conn.setDoOutput(false);

                                conn.setRequestMethod("GET");

                                conn.setRequestProperty("Accept-Charset", charset);

                                conn.setConnectTimeout(15000);

                                conn.connect();

                        } catch (IOException e) {
                                e.printStackTrace();
                        }

                }

                try {
                        //Receive the response from the server
                        InputStream in = new BufferedInputStream(conn.getInputStream());
                        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                        result = new StringBuilder();
                        String line;
                        while ((line = reader.readLine()) != null) {
                                result.append(line);
                        }

                        Log.d("JSON Parser", "result: " + result.toString());

                } catch (IOException e) {
                        e.printStackTrace();
                }

                conn.disconnect();

                // try parse the string to a JSON object
                try {
                        jObj = new JSONObject(result.toString());
                } catch (JSONException e) {
                        Log.e("JSON Parser", "Error parsing data " + e.toString());
                }

                // return JSON Object
                return jObj;
        }
        public JSONObject getJSONFromUrl(final String url){
                try {
                        // Construct the client and the HTTP request.
                        DefaultHttpClient httpClient = new DefaultHttpClient();
                        HttpPost httpPost = new HttpPost(url);

                        // Execute the POST request and store the response locally.
                        HttpResponse httpResponse = httpClient.execute(httpPost);
                        // Extract data from the response.
                        HttpEntity httpEntity = httpResponse.getEntity();
                        // Open an inputStream with the data content.
                        is = httpEntity.getContent();

                } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                } catch (ClientProtocolException e) {
                        e.printStackTrace();
                } catch (IOException e) {
                        e.printStackTrace();
                }

                try {
                        // Create a BufferedReader to parse through the inputStream.
                        BufferedReader reader = new BufferedReader(new InputStreamReader(
                                is, "iso-8859-1"), 8);
                        // Declare a string builder to help with the parsing.
                        StringBuilder sb = new StringBuilder();
                        // Declare a string to store the JSON object data in string form.
                        String line = null;

                        // Build the string until null.
                        while ((line = reader.readLine()) != null) {
                                sb.append(line + "\n");
                        }

                        // Close the input stream.
                        is.close();
                        // Convert the string builder data to an actual string.
                        json = sb.toString();
                } catch (Exception e) {
                        Log.e("Buffer Error", "Error converting result " + e.toString());
                }

                try {
                        jobj = new JSONObject(json);
                } catch (JSONException e) {
                        Log.e("JSON Parser", "Error parsing data " + e.toString());
                }

                // Return the JSON Object.
                return jobj;
        }



}
