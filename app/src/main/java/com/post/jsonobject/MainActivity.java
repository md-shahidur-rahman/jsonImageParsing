package com.post.jsonobject;

import android.media.MediaCodec;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {


    ProgressBar progressBar; ListView listView;
    ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();
    HashMap<String,String> hashMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        progressBar= findViewById(R.id.progressBar);

        listView= findViewById(R.id.listView);




        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        RequestQueue requestQueue = Volley.newRequestQueue(this);

         String url ="https://dummyjson.com/products";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObjectresponse) {
                progressBar.setVisibility(View.GONE);
                Log.d("serverRes",jsonObjectresponse.toString());

                try {


                    JSONArray jsonArray = jsonObjectresponse.getJSONArray("products");
                    JSONObject jsonObject =  jsonArray.getJSONObject(0);
                    String title = jsonObject.getString("title");
                    String description= jsonObject.getString("description");
                    String price= jsonObject.getString("price");
                    String thumbnail= jsonObject.getString("thumbnail");




                   /*******String[] images = new String[]{jsonObject.getString("thumbnail")};
                   String imagesArray= Arrays.getString(images);


                   Picasso.get().load(imageurl).placeholder(R.drawable.ic_launcher_background).into(imageView);
                        imageView.setImageResource(Integer.parseInt(imageurl));

                    *****/


                    hashMap = new HashMap<>();
                    hashMap.put("title",title);
                    hashMap.put("description",description);
                    hashMap.put("price",price);
                    hashMap.put("thumbnail",thumbnail);

                    arrayList.add(hashMap);



                    MyAdapter myAdapter = new MyAdapter(MainActivity.this,arrayList);
                    listView.setAdapter(myAdapter);






                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                progressBar.setVisibility(View.GONE);


            }
        });

        requestQueue.add(jsonObjectRequest);









    }












}