package ujcv.edu.listavideojuegos;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import ujcv.edu.listavideojuegos.recycler_view.ItemsRecycleView;
import ujcv.edu.listavideojuegos.recycler_view.adaptadore_items;


public class MainActivity extends AppCompatActivity {

    private RequestQueue queue;
    private RecyclerView mrecyclerview;
    private ArrayList<ItemsRecycleView> mExampleList;
    private adaptadore_items mExampleAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mrecyclerview = findViewById(R.id.recycler_view);
        mrecyclerview.setHasFixedSize(true);
        mrecyclerview.setLayoutManager(new LinearLayoutManager(this));

        mExampleList = new ArrayList<>();


        obtenerDatosVolley();
    }
    private void obtenerDatosVolley() {

        queue = Volley.newRequestQueue(this);

        String url = "https://rawg-video-games-database.p.rapidapi.com/games";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray mJsonArray = response.getJSONArray("results");

                    for (int i = 0 ; i<mJsonArray.length() ; i++){

                        JSONObject mJsonObj = mJsonArray.getJSONObject(i);

                        String nombre_juego = mJsonObj.getString("name");
                       String foto = mJsonObj.getString("background_image");
                       Double rating = mJsonObj.getDouble("rating");

                       mExampleList.add(new ItemsRecycleView(foto,nombre_juego,rating));

                    }


                    mExampleAdapter = new adaptadore_items(MainActivity.this, mExampleList);
                    mrecyclerview.setAdapter(mExampleAdapter);

                }
                catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        })
        {

          public Map getHeaders() throws AuthFailureError{
              HashMap headers = new HashMap();
              headers.put("x-rapidapi-host", "rawg-video-games-database.p.rapidapi.com");
              headers.put("x-rapidapi-key", "67036e1220msh05a699a10c679c1p151158jsn4d3d4d2bb094");
              return headers;

        }

        };

        queue.add(request);
    }



}