package tcc.posjava.unitri.edu.br.med4u;

import android.content.Intent;
import android.content.SharedPreferences;
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
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConsMedicoActivity extends AppCompatActivity {

    private static String TAG = "ConsMedicoActivity";
    private String autorizacao;
    private String url = "http://med4u.herokuapp.com/doctors";
    private String doctorName;
    private String doctorCrm;

    private List<String> doctors;
    private ArrayAdapter<String> adaptador;
    private ListView lvDoctors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cons_medico);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setTitle("Med4U");
        Intent it = getIntent();
        autorizacao = it.getStringExtra("autorizacao");

        RequestQueue requestQueue = Volley.newRequestQueue(ConsMedicoActivity.this);

        Button btConsultaMedicos = findViewById(R.id.btConsMed);
        btConsultaMedicos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText edFindCrmDoctors = findViewById(R.id.edFindCrmMed);
                doctorCrm = edFindCrmDoctors.getText().toString();
                EditText edFindNameDoctors = findViewById(R.id.edFindNameMed);
                doctorName = edFindNameDoctors.getText().toString();

                RequestQueue requestQueue = Volley.newRequestQueue(ConsMedicoActivity.this);

                JsonArrayRequest getRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                        new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {
                                // response
                                Log.d("Response", response.toString());
                                // Process the JSON
                                lvDoctors = findViewById(R.id.listViewDoctors);
                                doctors = new ArrayList<>();
                                Toast.makeText(ConsMedicoActivity.this, "Tamanho da resposta" + response.length(), Toast.LENGTH_LONG).show();
                                try {
                                    // Loop through the array elements
                                    for (int i = 0; i < response.length(); i++) {
                                        // Get current json object
                                        JSONObject doctor = response.getJSONObject(i);
                                        if (doctor.getString("name").toLowerCase().contains(doctorName.toLowerCase())) {
                                            doctors.add(doctor.getString("name"));
                                        }
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                adaptador = new ArrayAdapter<>(ConsMedicoActivity.this, android.R.layout.simple_list_item_1, doctors);
                                lvDoctors.setAdapter(adaptador);
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // TODO Auto-generated method stub
                                Log.d("ERROR", "error => " + error.toString());
                            }
                        }
                ) {
                    //This is for Headers If You Needed
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("Content-Type", "application/json; charset=UTF-8");
                        params.put("token", autorizacao);
                        return params;
                    }

                    //Pass Your Parameters here
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<String, String>();
                        return params;
                    }
                };
                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                queue.add(getRequest);
// Adding the request to the queue along with a unique string tag
                /*Volley.getInstance(this).addToRequestQueue(getRequest, "headerRequest");*/

                requestQueue.add(getRequest);
                ((InputMethodManager) getSystemService(ConsMedicineActivity.INPUT_METHOD_SERVICE)).toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, 0);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        populaLista();
    }

    public void populaLista() {

        // Initialize a new RequestQueue instance
        RequestQueue requestQueue = Volley.newRequestQueue(ConsMedicoActivity.this);
        // Initialize a new JsonArrayRequest instance
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // Process the JSON
                        lvDoctors = findViewById(R.id.listViewDoctors);
                        doctors = new ArrayList<>();

                        try {
                            // Loop through the array elements
                            for (int i = 0; i < response.length(); i++) {
                                // Get current json object
                                JSONObject doctor = response.getJSONObject(i);

                                doctors.add(doctor.getString("name"));

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        adaptador = new ArrayAdapter<>(ConsMedicoActivity.this, android.R.layout.simple_list_item_1, doctors);

                        lvDoctors.setAdapter(adaptador);
                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
        // Add JsonArrayRequest to the RequestQueue
        requestQueue.add(jsonArrayRequest);
        ((InputMethodManager) getSystemService(ConsMedicineActivity.INPUT_METHOD_SERVICE)).toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, 0);

        if (lvDoctors != null) {
            lvDoctors.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    // ListView Clicked item index
                    int itemPosition = position;

                    // ListView Clicked item value
                    String itemValue = (String) lvDoctors.getItemAtPosition(position);

                    // Show Alert
                    Toast.makeText(ConsMedicoActivity.this, "Item selecionado", Toast.LENGTH_LONG).show();

                }
            });
        }

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuCadLembretes:
                if (autorizacao != null) {
                    Intent cadLembretes = new Intent(this, CadRememberActivity.class);
                    cadLembretes.putExtra("autorizacao", autorizacao);
                    startActivity(cadLembretes);
                } else {
                    Toast.makeText(ConsMedicoActivity.this, "Necessita autenticação", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.menuCadMedicines:
                if (autorizacao != null) {
                    Intent cadMedicines = new Intent(this, CadMedicinesActivity.class);
                    cadMedicines.putExtra("autorizacao", autorizacao);
                    startActivity(cadMedicines);
                } else {
                    Toast.makeText(ConsMedicoActivity.this, "Necessita autenticação", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.menuCadReceita:
                if (autorizacao != null) {
                    Intent cadastroReceita = new Intent(this, CadReceitaActivity.class);
                    cadastroReceita.putExtra("autorizacao", autorizacao);
                    startActivity(cadastroReceita);
                } else {
                    Toast.makeText(ConsMedicoActivity.this, "Necessita autenticação", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.menuCadUsuario:
                Intent cadastroUsuario = new Intent(this, NewUserActivity.class);
                cadastroUsuario.putExtra("autorizacao", autorizacao);
                startActivity(cadastroUsuario);
                break;
            case R.id.menuConsFarmacia:
                Intent consultaFarm = new Intent(this, ConsFarmaciaActivity.class);
                consultaFarm.putExtra("autorizacao", autorizacao);
                startActivity(consultaFarm);
                break;
            case R.id.menuConsLembretes:
                if (autorizacao != null) {
                    Intent consLembretes = new Intent(this, ConsRememberActivity.class);
                    consLembretes.putExtra("autorizacao", autorizacao);
                    startActivity(consLembretes);
                } else {
                    Toast.makeText(ConsMedicoActivity.this, "Necessita autenticação", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.menuConsMedicamentos:
                Intent consultaMedicines = new Intent(this, ConsMedicineActivity.class);
                startActivity(consultaMedicines);
                break;
            case R.id.menuConsMedico:
                Intent contultaMedicos = new Intent(this, ConsMedicoActivity.class);
                contultaMedicos.putExtra("autorizacao", autorizacao);
                startActivity(contultaMedicos);
                break;
            case R.id.menuConsReceita:
                if (autorizacao != null) {
                    Intent consultaReceita = new Intent(this, ConsReceitaActivity.class);
                    consultaReceita.putExtra("autorizacao", autorizacao);
                    startActivity(consultaReceita);
                } else {
                    Toast.makeText(ConsMedicoActivity.this, "Necessita autenticação", Toast.LENGTH_LONG).show();
                }
            /*case R.id.menuPerfilEditar:
                Intent editPerfil = new Intent(this, EditPerfil.class);
                startActivity(editPerfil);
                break;
            case R.id.menuPerfilVisualizar:
                Intent visPerfil = new Intent(this, visPerfil.class);
                startActivity(visPerfil);
                break;*/
            case R.id.menuSair:
                SharedPreferences myPrefs = getSharedPreferences("Logout",
                        MODE_PRIVATE);
                SharedPreferences.Editor editor = myPrefs.edit();
                editor.clear();
                editor.commit();
                /*AppState.getSingleInstance().setLoggingOut(true);*/
                Log.d(TAG, "Now log out and start the activity login");
                Intent intent = new Intent(ConsMedicoActivity.this,
                        PrincipalActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            default:
                return false;
        }

        return super.onOptionsItemSelected(item);
    }
}
