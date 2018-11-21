package tcc.posjava.unitri.edu.br.med4u;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecoverUserActivity extends AppCompatActivity {

    private static String TAG = "RecoverUserActivity";
    private String url = "http://med4u.herokuapp.com/users";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setTitle("Med4U");
        setContentView(R.layout.activity_recover_user);

        Button recover = findViewById(R.id.btRecover);
        recover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText etRecover = findViewById(R.id.etMailRecover);
                final String email = etRecover.getText().toString();
                RequestQueue requestQueue = Volley.newRequestQueue(RecoverUserActivity.this);

                JsonArrayRequest getRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                        new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {
                                // response
                                Log.d("Response", response.toString());
                                // Process the JSON
                                try {
                                    // Loop through the array elements
                                    for (int i = 0; i < response.length(); i++) {
                                        // Get current json object
                                        JSONObject users = response.getJSONObject(i);
                                        Toast.makeText(RecoverUserActivity.this, "email: " + email , Toast.LENGTH_LONG).show();
                                        Toast.makeText(RecoverUserActivity.this, "email consulta: " + users.getString("email").toString(), Toast.LENGTH_LONG).show();
                                        if (users.getString("email").toString().equalsIgnoreCase(email)) {
                                            Toast.makeText(RecoverUserActivity.this, "Senha enviada para e-mail cadastrado.", Toast.LENGTH_LONG).show();
                                        }
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // TODO Auto-generated method stub
                                Log.d("ERROR", "error => " + error.toString());
                            }
                        }
                );
                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                queue.add(getRequest);
                requestQueue.add(getRequest);
            }
        });

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal, menu);
        return true;
    }
}

