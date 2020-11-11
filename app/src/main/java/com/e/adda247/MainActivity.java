package com.e.adda247;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.e.adda247.adapter.main_adapter;
import com.e.adda247.modal.main_modal;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager layoutManager;
    List<main_modal> lst;

    String url = "https://gorest.co.in/public-api/users";
    RequestQueue rq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rq = Volley.newRequestQueue(this);

        recyclerView = (RecyclerView) findViewById(R.id.recycleViewContainer);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);

        lst = new ArrayList<>();

        sendRequest();


    }

    public void sendRequest() {

//        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
//            @Override
//            public void onResponse(JSONArray response) {
//
//                try {
//                    JSONArray array = new JSONArray(response);
//
//                    System.out.println("the array is "+array);
//                    for(int i=0;i<array.length();i++){
//                        JSONObject data = array.getJSONObject(i);
//
//                        lst.add(new main_modal(
//                           data.getString("id"),
//                                data.getString("name"),
//                                data.getString("email"),
//                                data.getString("gender"),
//                                data.getString("status"),
//                                data.getString("created_at"),
//                                data.getString("updated_at")
//
//                        ));
//
//                    }
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//
//                mAdapter = new main_adapter(MainActivity.this, lst);
//
//                recyclerView.setAdapter(mAdapter);
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.i("Volley Error: ", error.toString());
//            }
//        });
//
//        rq.add(jsonArrayRequest);


        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //getting the whole json object from the response
                            JSONObject obj = new JSONObject(response);

                            //we have the array named hero inside the object
                            //so here we are getting that json array
                            JSONArray datarow = obj.getJSONArray("data");

                            //now looping through all the elements of the json array
                            for (int i = 0; i < datarow.length(); i++) {
                                //getting the json object of the particular index inside the array
                                JSONObject dataObject = datarow.getJSONObject(i);

                                //creating a hero object and giving them the values from json object
                                main_modal modal_data = new main_modal(dataObject.getString("id"),
                                        dataObject.getString("name"),
                                        dataObject.getString("email"),
                                        dataObject.getString("gender"),
                                        dataObject.getString("status"),
                                        dataObject.getString("created_at"),
                                        dataObject.getString("updated_at"));

                                //adding the hero to herolist
                                lst.add(modal_data);
                            }

                            //creating custom adapter object
                            main_adapter adapter = new main_adapter(getApplicationContext(), lst);

                            //adding the adapter to listview
                            recyclerView.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //displaying the error in toast if occurrs
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        //creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //adding the string request to request queue
        requestQueue.add(stringRequest);


    }

}

