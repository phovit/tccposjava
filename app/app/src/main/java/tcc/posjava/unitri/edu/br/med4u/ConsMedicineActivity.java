package tcc.posjava.unitri.edu.br.med4u;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
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
import android.widget.Toolbar;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConsMedicineActivity extends AppCompatActivity {

    private static String TAG = "ConsMedicineActivity";
    private String nomeMedicamento = "";
    private List<String> opcoes;
    private ArrayAdapter<String> adaptador;
    private ListView lvOpcoes;
    private String url = "http://med4u.herokuapp.com/medicine";
    private String autorizacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setTitle("Med4U");
        setContentView(R.layout.activity_cons_medicines);
        Intent it = getIntent();
        autorizacao = it.getStringExtra("autorizacao");
        //consulta inicial

        RequestQueue requestQueue = Volley.newRequestQueue(ConsMedicineActivity.this);

        //buscar por nome
        Button btConsultaMedicines = findViewById(R.id.btConsMed);
        btConsultaMedicines.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText edFindNameMedicines = findViewById(R.id.edFindNameMedicines);
                nomeMedicamento = edFindNameMedicines.getText().toString();

                RequestQueue requestQueue = Volley.newRequestQueue(ConsMedicineActivity.this);

                JsonArrayRequest getRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                        new Response.Listener<JSONArray>()
                        {
                            @Override
                            public void onResponse(JSONArray response) {
                                // response
                                Log.d("Response", response.toString());
                                // Process the JSON
                                lvOpcoes = findViewById(R.id.lvMedicines);
                                opcoes = new ArrayList<>();
                                try {
                                    // Loop through the array elements
                                    for (int i = 0; i < response.length(); i++) {
                                        // Get current json object
                                        JSONObject medicine = response.getJSONObject(i);
                                        if (medicine.getString("name").toLowerCase().contains(nomeMedicamento.toLowerCase())) {
                                            opcoes.add(medicine.getString("name"));
                                        }
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                adaptador = new ArrayAdapter<>(ConsMedicineActivity.this, android.R.layout.simple_list_item_1, opcoes);
                                lvOpcoes.setAdapter(adaptador);
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

                requestQueue.add(getRequest);
                ((InputMethodManager) getSystemService(ConsMedicineActivity.INPUT_METHOD_SERVICE)).toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, 0);
                lvOpcoes.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        // ListView Clicked item index
                        int itemPosition = position;

                        // ListView Clicked item value
                        String itemValue = (String) lvOpcoes.getItemAtPosition(position);



                        // Show Alert
                        Toast.makeText(ConsMedicineActivity.this, "Item selecionado" + itemValue, Toast.LENGTH_LONG).show();

                        Intent details = new Intent(ConsMedicineActivity.this, DetailsMedicineActivity.class);
                        details.putExtra("name", itemValue);
                        startActivity(details);

                    }
                });
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
        RequestQueue requestQueue = Volley.newRequestQueue(ConsMedicineActivity.this);
        // Initialize a new JsonArrayRequest instance
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // Process the JSON
                        lvOpcoes = findViewById(R.id.lvMedicines);
                        opcoes = new ArrayList<>();

                        try {
                            // Loop through the array elements
                            for (int i = 0; i < response.length(); i++) {
                                // Get current json object
                                JSONObject medicine = response.getJSONObject(i);

                                opcoes.add(medicine.getString("name"));

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        adaptador = new ArrayAdapter<>(ConsMedicineActivity.this, android.R.layout.simple_list_item_1, opcoes);

                        lvOpcoes.setAdapter(adaptador);
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

        if (lvOpcoes != null) {
            lvOpcoes.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    // ListView Clicked item index
                    int itemPosition = position;

                    // ListView Clicked item value
                    String itemValue = (String) lvOpcoes.getItemAtPosition(position);

                    // Show Alert
                    Toast.makeText(ConsMedicineActivity.this, "Item selecionado" + itemValue, Toast.LENGTH_LONG).show();
                    Intent details = new Intent(ConsMedicineActivity.this, DetailsMedicineActivity.class);
                    details.putExtra("name", itemValue);

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
            case R.id.menuCadMedicines:
                if (autorizacao != null) {
                    Intent cadMedicines = new Intent(this, CadMedicinesActivity.class);
                    cadMedicines.putExtra("autorizacao", autorizacao);
                    startActivity(cadMedicines);
                } else {
                    Toast.makeText(ConsMedicineActivity.this, "Necessita autenticação", Toast.LENGTH_LONG).show();
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
                Toast.makeText(ConsMedicineActivity.this, "Autorizacao: " + autorizacao, Toast.LENGTH_LONG).show();
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
                System.exit(0);
            default:
                return false;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showDialog(String title, String message) {
        AlertDialog alertDialog = new AlertDialog.Builder(ConsMedicineActivity.this).create();
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

