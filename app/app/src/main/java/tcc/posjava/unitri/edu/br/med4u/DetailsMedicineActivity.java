package tcc.posjava.unitri.edu.br.med4u;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
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
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.OkHttpClient;

public class DetailsMedicineActivity extends AppCompatActivity {

    private static String TAG = "DetailsMedicineActivity";
    private String url = "http://med4u.herokuapp.com/medicine";
    private String autorizacao;
    private String nome;

    private EditText edDetNameMedicines; /*= findViewById(R.id.edDetNameMedicines);*/
    private EditText edDetBarCodeMedicines; /*= findViewById(R.id.edDetBarCodeMedicines);*/
    private EditText edDetRegMedicines; /*= findViewById(R.id.edDetRegMedicines);*/
    private EditText edDetIndMedicines; /*= findViewById(R.id.edDetIndMedicines);*/
    private EditText edDetContIndMedicines; /*= findViewById(R.id.edDetContIndMedicines);*/
    private EditText edDetReactMedicines; /*= findViewById(R.id.edDetReactMedicines);*/
    private EditText edDetPrecMedicines; /*= findViewById(R.id.edDetPrecMedicines);*/

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_medicines);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setTitle("Med4U");
        populaLista();

        Intent it = getIntent();
        autorizacao = it.getStringExtra("autorizacao");
        nome = it.getStringExtra("name");
        Toast.makeText(DetailsMedicineActivity.this, "Nome: " + nome, Toast.LENGTH_LONG).show();

    }

    public void populaLista() {

        edDetNameMedicines = findViewById(R.id.edDetNameMedicines);
        edDetBarCodeMedicines = findViewById(R.id.edDetBarCodeMedicines);
        edDetRegMedicines = findViewById(R.id.edDetRegMedicines);
        edDetIndMedicines = findViewById(R.id.edDetIndMedicines);
        edDetContIndMedicines = findViewById(R.id.edDetContIndMedicines);
        edDetReactMedicines = findViewById(R.id.edDetReactMedicines);
        edDetPrecMedicines = findViewById(R.id.edDetPrecMedicines);

        alteraEdicaoBotoes(edDetNameMedicines, edDetBarCodeMedicines, edDetRegMedicines, edDetIndMedicines, edDetContIndMedicines, edDetReactMedicines, edDetPrecMedicines);

        // Initialize a new RequestQueue instance
        RequestQueue requestQueue = Volley.newRequestQueue(DetailsMedicineActivity.this);
        // Initialize a new JsonArrayRequest instance
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // Process the JSON
                        try {
                            // Loop through the array elements
                            for (int i = 0; i < response.length(); i++) {
                                // Get current json object
                                JSONObject medicine = response.getJSONObject(i);
                                if (medicine.getString("name").toString().equalsIgnoreCase(nome)) {
                                    edDetNameMedicines.setText(medicine.getString("name"));
                                    edDetBarCodeMedicines.setText(medicine.getString("codebar"));
                                    edDetContIndMedicines.setText(medicine.getString("contraindications"));
                                    edDetIndMedicines.setText(medicine.getString("indications"));
                                    edDetPrecMedicines.setText(medicine.getString("precautions"));
                                    edDetReactMedicines.setText(medicine.getString("adverseReactions"));
                                    edDetRegMedicines.setText(medicine.getString("msRecord"));

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

                    }
                }
        );
        // Add JsonArrayRequest to the RequestQueue
        requestQueue.add(jsonArrayRequest);
        ((InputMethodManager) getSystemService(ConsMedicineActivity.INPUT_METHOD_SERVICE)).toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, 0);

        // Initialize a new RequestQueue instance
        Button btDetails = findViewById(R.id.btDetMed);
        btDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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
                                        JSONObject medicine = response.getJSONObject(i);
                                        if (medicine.getString("name").equalsIgnoreCase(nome)) {
                                            edDetNameMedicines.setText(medicine.getString("name"));
                                            edDetBarCodeMedicines.setText(medicine.getString("codebar"));
                                            edDetContIndMedicines.setText(medicine.getString("contraindications"));
                                            edDetIndMedicines.setText(medicine.getString("indications"));
                                            edDetPrecMedicines.setText(medicine.getString("precautions"));
                                            edDetReactMedicines.setText(medicine.getString("adverseReactions"));
                                            edDetRegMedicines.setText(medicine.getString("msRecord"));
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
            }
        });

        Button btEdit = findViewById(R.id.btEditDetMed);
        btEdit.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                edDetNameMedicines.setEnabled(true);
                edDetBarCodeMedicines.setEnabled(true);
                edDetRegMedicines.setEnabled(true);
                edDetIndMedicines.setEnabled(true);
                edDetContIndMedicines.setEnabled(true);
                edDetReactMedicines.setEnabled(true);
                edDetPrecMedicines.setEnabled(true);
            }
        });

        Button btSave = findViewById(R.id.btSaveDetMed);
        btSave.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View view) {
                Toast.makeText(DetailsMedicineActivity.this, "clicado ", Toast.LENGTH_LONG).show();
                RequestQueue requestQueue = Volley.newRequestQueue(DetailsMedicineActivity.this);
                JSONObject putRequest = new JSONObject();
                try {
                    putRequest.put("name", edDetNameMedicines.getText().toString());
                    putRequest.put("indications", edDetIndMedicines.getText().toString());
                    putRequest.put("contraindications", edDetContIndMedicines.getText().toString());
                    putRequest.put("adverseReactions", edDetReactMedicines.getText().toString());
                    putRequest.put("precautions", edDetPrecMedicines.getText().toString());
                    putRequest.put("codebar", edDetBarCodeMedicines.getText().toString());
                    putRequest.put("msRecord", edDetRegMedicines.getText().toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                JsonArrayRequest getRequest = new JsonArrayRequest(Request.Method.PUT, url, null,
                        new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {
                                if (response == null) {
                                    Log.d(TAG, "API Response: null");
                                } else {
                                    Log.d(TAG, "API Response: " + response.toString());
                                    if (response.equals("com.android.volley.ParseError: org.json.JSONException: End of input at character 0 of")) {
                                        Toast.makeText(DetailsMedicineActivity.this, "Medicamento atualizado com sucesso.", Toast.LENGTH_LONG).show();
                                    }
                                }

                            }

                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // error
                                Log.d("Error.Response", error.toString());
                            }
                        }
                ) {

                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("name", edDetNameMedicines.getText().toString());
                        params.put("indications", edDetIndMedicines.getText().toString());
                        params.put("contraindications", edDetContIndMedicines.getText().toString());
                        params.put("adverseReactions", edDetReactMedicines.getText().toString());
                        params.put("precautions", edDetPrecMedicines.getText().toString());
                        params.put("codebar", edDetBarCodeMedicines.getText().toString());
                        params.put("msRecord", edDetRegMedicines.getText().toString());

                        Toast.makeText(DetailsMedicineActivity.this, "name: " + edDetNameMedicines.getText().toString(), Toast.LENGTH_LONG).show();


                        return params;
                    }

                    //This is for Headers If You Needed
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("Content-Type", "application/json; charset=UTF-8");
                        params.put("token", autorizacao);
                        return params;
                    }

                };
                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                queue.add(getRequest);

                // Adding the request to the queue along with a unique string tag

                requestQueue.add(getRequest);
            }
        });
    }

    private void alteraEdicaoBotoes(EditText edDetNameMedicines, EditText
            edDetBarCodeMedicines, EditText edDetRegMedicines, EditText edDetIndMedicines, EditText
                                            edDetContIndMedicines, EditText edDetReactMedicines, EditText edDetPrecMedicines) {
        if (edDetNameMedicines.isEnabled() == true) {
            edDetNameMedicines.setEnabled(false);
            edDetBarCodeMedicines.setEnabled(false);
            edDetRegMedicines.setEnabled(false);
            edDetIndMedicines.setEnabled(false);
            edDetContIndMedicines.setEnabled(false);
            edDetReactMedicines.setEnabled(false);
            edDetPrecMedicines.setEnabled(false);
        } else {
            edDetNameMedicines.setEnabled(false);
            edDetBarCodeMedicines.setEnabled(false);
            edDetRegMedicines.setEnabled(false);
            edDetIndMedicines.setEnabled(false);
            edDetContIndMedicines.setEnabled(false);
            edDetReactMedicines.setEnabled(false);
            edDetPrecMedicines.setEnabled(false);
        }
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
                    Toast.makeText(DetailsMedicineActivity.this, "Necessita autenticação", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.menuCadMedicines:
                if (autorizacao != null) {
                    Intent cadMedicines = new Intent(this, CadMedicinesActivity.class);
                    cadMedicines.putExtra("autorizacao", autorizacao);
                    startActivity(cadMedicines);
                } else {
                    Toast.makeText(DetailsMedicineActivity.this, "Necessita autenticação", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.menuCadReceita:
                if (autorizacao != null) {
                    Intent cadastroReceita = new Intent(this, CadReceitaActivity.class);
                    cadastroReceita.putExtra("autorizacao", autorizacao);
                    startActivity(cadastroReceita);
                } else {
                    Toast.makeText(DetailsMedicineActivity.this, "Necessita autenticação", Toast.LENGTH_LONG).show();
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
                    Toast.makeText(DetailsMedicineActivity.this, "Necessita autenticação", Toast.LENGTH_LONG).show();
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
                    Toast.makeText(DetailsMedicineActivity.this, "Necessita autenticação", Toast.LENGTH_LONG).show();
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
                Intent intent = new Intent(DetailsMedicineActivity.this,
                        PrincipalActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            default:
                return false;
        }

        return super.onOptionsItemSelected(item);

    }

}

