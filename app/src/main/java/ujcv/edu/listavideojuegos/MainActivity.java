package ujcv.edu.listavideojuegos;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
    private RequestQueue queue2;
    private RecyclerView mrecyclerview;
    private ArrayList<ItemsRecycleView> mExampleList;
    private adaptadore_items mExampleAdapter;
    private TextView n_paginas;
    private Button btn_next;
    private Button btn_previous;
    String urlprincipal = "https://rawg-video-games-database.p.rapidapi.com/games?page=1";
    int contador = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mrecyclerview = findViewById(R.id.recycler_view);
        mrecyclerview.setHasFixedSize(true);
        mrecyclerview.setLayoutManager(new LinearLayoutManager(this));
        btn_next = findViewById(R.id.btn_next);
        btn_previous = findViewById(R.id.btn_previous);
        n_paginas = findViewById(R.id.n_paginas);

        mExampleList = new ArrayList<>();

        btn_next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                contador++;
                urlprincipal = "https://rawg-video-games-database.p.rapidapi.com/games?page=";
                urlprincipal += +contador;
                replaceOldListWithNewList();

                obtenerDatosVolley();
                n_paginas.setText("pag: "+contador);

            }
        });

        btn_previous.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if (contador != 1) {
                    contador--;
                    urlprincipal = "https://rawg-video-games-database.p.rapidapi.com/games?page="+contador;
                    replaceOldListWithNewList();
                    obtenerDatosVolley();
                    n_paginas.setText("pag: "+contador);
                }
            }
        });

        n_paginas.setText("pag: "+contador);
        obtenerDatosVolley();
    }

    private void replaceOldListWithNewList() {
        // clear old list
        mExampleList.clear();
        // notify adapter
        mExampleAdapter.notifyDataSetChanged();
    }

    private void obtenerDatosVolley() {


        queue = Volley.newRequestQueue(this);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, urlprincipal, null, new Response.Listener<JSONObject>() {

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