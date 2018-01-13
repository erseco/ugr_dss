package es.ernesto.dss.pharmacydss.controller;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import es.ernesto.dss.pharmacydss.ui.MainActivity;

/**
 * Created by ernesto on 12/01/2018.
 */

public class ProductsRestDownloader extends AsyncTask<String, String, String> {

    HttpURLConnection urlConnection;

    @Override
    protected String doInBackground(String... args) {

        StringBuilder result = new StringBuilder();

        try {

            URL url = new URL("http://dss.ernesto.es:8080/products/");
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());

            BufferedReader reader = new BufferedReader(new InputStreamReader(in));


            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }

            JSONArray jArray = new JSONArray(result.toString());
            for(int i=0; i < jArray.length(); i++) {

                JSONObject jObject = jArray.getJSONObject(i);

                String _id = jObject.getString("_id");
                String name = jObject.getString("name");
                String price = jObject.getString("price");
                String manufacturer = jObject.getJSONArray("manufacturer").getString(0);
                String status = jObject.getJSONArray("status").getString(0);

                MainActivity.db.insertProduct(_id, name, price, manufacturer, status);

            } // End Loop









        }catch( Exception e) {
            e.printStackTrace();
        }
        finally {
            urlConnection.disconnect();
        }


        return result.toString();
    }

    @Override
    protected void onPostExecute(String result) {


        String TAG = "MyActivity";

        //Do something with the JSON string

        Log.i(TAG, result);

    }

}