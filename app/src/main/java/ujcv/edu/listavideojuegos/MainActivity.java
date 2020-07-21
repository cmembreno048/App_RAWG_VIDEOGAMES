package ujcv.edu.listavideojuegos;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {

    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        obtenerDatosVolley();
    }
    private void obtenerDatosVolley() {


        String url = "https://api.androidhive.info/contacts/";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {

                try {

                    JSONArray mJsonArray = response.getJSONArray("contacts");

                    for (int i = 0 ; i<mJsonArray.length() ; i++){

                        JSONObject mJsonObj = mJsonArray.getJSONObject(i);
                        String name = mJsonObj.getString("name");
                        Toast.makeText(MainActivity.this, "Pokemos: "+name, Toast.LENGTH_SHORT).show();
                    }

                }
                catch (JSONException e) {

                    e.printStackTrace();

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        queue.add(request);
    }



}