package tcc.posjava.unitri.edu.br.med4u;

import android.content.DialogInterface;
import android.content.Intent;
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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DetailsMedicineActivity extends AppCompatActivity {

    private static String TAG = "DetailsMedicineActivity";
    private String url = "http://med4u.herokuapp.com/medicine/";
    private String autorizacao;
    private String nome;
    private String barCode;
    private String registro;
    private String indicacao;
    private String contraIndicacao;
    private String reacoes;
    private String precaucoes;


    protected void onCreate(Bundle savedInstanceState, final String name) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_medicines);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setTitle("Med4U");
        EditText edDetNameMedicines = findViewById(R.id.edDetNameMedicines);
        EditText edDetBarCodeMedicines = findViewById(R.id.edDetBarCodeMedicines);
        EditText edDetRegMedicines = findViewById(R.id.edDetRegMedicines);
        EditText edDetIndMedicines = findViewById(R.id.edDetIndMedicines);
        EditText edDetContIndMedicines = findViewById(R.id.edDetContIndMedicines);
        EditText edDetReactMedicines = findViewById(R.id.edDetReactMedicines);
        EditText edDetPrecMedicines = findViewById(R.id.edDetPrecMedicines);

        Intent it = getIntent();
        autorizacao = it.getStringExtra("autorizacao");
        nome = it.getStringExtra("name");

        // Initialize a new RequestQueue instance
        RequestQueue requestQueue = Volley.newRequestQueue(DetailsMedicineActivity.this);

        JsonArrayRequest getRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>()
                {
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
                                if (medicine.getString("name").toLowerCase().contains(nome.toLowerCase())) {
                                    barCode = medicine.getString("codebar");
                                    registro = medicine.getString("msRecord");
                                    indicacao = medicine.getString("indications");
                                    contraIndicacao = medicine.getString("contraindications");
                                    reacoes = medicine.getString("adverseReactions");
                                    precaucoes = medicine.getString("precautions");
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub
                        Log.d("ERROR","error => "+error.toString());
                    }
                }
        )
        {
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

        edDetNameMedicines.setText(nome);
        edDetBarCodeMedicines.setText(barCode);
        edDetContIndMedicines.setText(contraIndicacao);
        edDetIndMedicines.setText(indicacao);
        edDetPrecMedicines.setText(precaucoes);
        edDetReactMedicines.setText(reacoes);
        edDetRegMedicines.setText(registro);

        // Initialize a new JsonArrayRequest instance
        /*JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url + name,
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
                                nome = medicine.getString("name");
                                barCode = medicine.getString("codebar");
                                indicacao = medicine.getString("indications");
                                contraIndicacao = medicine.getString("contraindications");
                                reacoes = medicine.getString("adverseReactions");
                                registro = medicine.getString("msRecord");
                                precaucoes = medicine.getString("precautions");

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
        requestQueue.add(jsonArrayRequest);*/

        edDetNameMedicines.setText(nome);
        edDetBarCodeMedicines.setText(barCode);
        edDetContIndMedicines.setText(contraIndicacao);
        edDetIndMedicines.setText(indicacao);
        edDetPrecMedicines.setText(precaucoes);
        edDetReactMedicines.setText(reacoes);
        edDetRegMedicines.setText(registro);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }



    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
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
                Intent cadastroReceita = new Intent(this, CadReceitaActivity.class);
                cadastroReceita.putExtra("autorizacao", autorizacao);
                startActivity(cadastroReceita);
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
                Intent consultaReceita = new Intent(this, ConsReceitaActivity.class);
                consultaReceita.putExtra("autorizacao", autorizacao);
                startActivity(consultaReceita);
            /*case R.id.menuPerfilEditar:
                Intent editPerfil = new Intent(this, EditPerfil.class);
                startActivity(editPerfil);
                break;
            case R.id.menuPerfilVisualizar:
                Intent visPerfil = new Intent(this, visPerfil.class);
                startActivity(visPerfil);
                break;*/
            case R.id.menuSair:
                finish();
            default:
                return false;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showDialog(String title, String message) {
        AlertDialog alertDialog = new AlertDialog.Builder(DetailsMedicineActivity.this).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

}

