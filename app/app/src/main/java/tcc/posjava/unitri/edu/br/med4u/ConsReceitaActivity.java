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

public class ConsReceitaActivity extends AppCompatActivity {

    private String autorizacao;
    String url = "http://med4u.herokuapp.com/medicalPrescriptions";
    private List<String> opcoes;
    private ArrayAdapter<String> adaptador;
    private ListView lvOpcoes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cons_receita);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setTitle("Med4U");
        Intent it = getIntent();
        autorizacao = it.getStringExtra("autorizacao");

        consulta();


    }

    @Override
    protected void onResume() {
        super.onResume();

            RequestQueue requestQueue = Volley.newRequestQueue(ConsReceitaActivity.this);

            JsonArrayRequest getRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            // response
                            Log.d("Response", response.toString());
                            // Process the JSON
                            lvOpcoes = findViewById(R.id.lvReceitas);
                            opcoes = new ArrayList<>();
                            try {
                                // Loop through the array elements
                                for (int i = 0; i < response.length(); i++) {
                                    // Get current json object
                                    JSONObject prescriptions = response.getJSONObject(i);
                                    opcoes.add(prescriptions.getString("cid"));
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            adaptador = new ArrayAdapter<>(ConsReceitaActivity.this, android.R.layout.simple_list_item_1, opcoes);
                            lvOpcoes.setAdapter(adaptador);
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

            requestQueue.add(getRequest);
            ((InputMethodManager) getSystemService(ConsMedicineActivity.INPUT_METHOD_SERVICE)).toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, 0);

            /*lvOpcoes.setOnItemClickListener(new AdapterView.OnItemClickListener() {*/

                /*@Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    // ListView Clicked item index
                    int itemPosition = position;

                    // ListView Clicked item value
                    String itemValue = (String) lvOpcoes.getItemAtPosition(position);

                    // Show Alert
                    Toast.makeText(ConsReceitaActivity.this, "Item selecionado" + itemValue, Toast.LENGTH_LONG).show();

                    Intent details = new Intent(ConsReceitaActivity.this, DetailsMedicineActivity.class);
                    details.putExtra("name", itemValue);

                    startActivity(details);

                }*/
            /*});*/
        }



    /*public boolean onCreateOptionsMenu(Menu menu) {
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
                    Toast.makeText(ConsReceitaActivity.this, "Necessita autenticação", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.menuCadMedicines:
                if (autorizacao != null) {
                    Intent cadMedicines = new Intent(this, CadMedicinesActivity.class);
                    startActivity(cadMedicines);
                } else {
                    Toast.makeText(ConsReceitaActivity.this, "Necessita autenticação", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.menuCadReceita:
                if (autorizacao != null) {
                    Intent cadastroReceita = new Intent(this, CadReceitaActivity.class);
                    startActivity(cadastroReceita);
                } else {
                    Toast.makeText(ConsReceitaActivity.this, "Necessita autenticação", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.menuCadUsuario:
                Intent cadastroUsuario = new Intent(this, NewUserActivity.class);
                startActivity(cadastroUsuario);
                break;
            case R.id.menuConsFarmacia:
                Intent consultaFarm = new Intent(this, ConsFarmaciaActivity.class);
                startActivity(consultaFarm);
                break;
            case R.id.menuConsLembretes:
                if (autorizacao != null) {
                    Intent consLembretes = new Intent(this, ConsRememberActivity.class);
                    consLembretes.putExtra("autorizacao", autorizacao);
                    startActivity(consLembretes);
                } else {
                    Toast.makeText(ConsReceitaActivity.this, "Necessita autenticação", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.menuConsMedicamentos:
                Intent consultaMedicines = new Intent(this, ConsMedicineActivity.class);
                startActivity(consultaMedicines);
                break;
            case R.id.menuConsMedico:
                Intent contultaMedicos = new Intent(this, ConsMedicoActivity.class);
                startActivity(contultaMedicos);
                break;
            case R.id.menuConsReceita:
                if (autorizacao != null) {
                    Intent consultaReceita = new Intent(this, ConsReceitaActivity.class);
                    startActivity(consultaReceita);
                } else {
                    Toast.makeText(ConsReceitaActivity.this, "Necessita autenticação", Toast.LENGTH_LONG).show();
                }
            *//*case R.id.menuPerfilEditar:
                Intent editPerfil = new Intent(this, EditPerfil.class);
                startActivity(editPerfil);
                break;
            case R.id.menuPerfilVisualizar:
                Intent visPerfil = new Intent(this, visPerfil.class);
                startActivity(visPerfil);
                break;*//*
            case R.id.menuSair:
                finish();
            default:
                return false;
        }

        return super.onOptionsItemSelected(item);
    }*/
/*}*/

public void consulta(){
    RequestQueue requestQueue = Volley.newRequestQueue(ConsReceitaActivity.this);

    JsonArrayRequest getRequest = new JsonArrayRequest(Request.Method.GET, url, null,
            new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    // response
                    Log.d("Response", response.toString());
                    // Process the JSON
                    lvOpcoes = findViewById(R.id.lvReceitas);
                    opcoes = new ArrayList<>();
                    try {
                        // Loop through the array elements
                        for (int i = 0; i < response.length(); i++) {
                            // Get current json object
                            JSONObject prescriptions = response.getJSONObject(i);
                            opcoes.add(prescriptions.getString("cid"));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    adaptador = new ArrayAdapter<>(ConsReceitaActivity.this, android.R.layout.simple_list_item_1, opcoes);
                    lvOpcoes.setAdapter(adaptador);
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

    requestQueue.add(getRequest);
    ((InputMethodManager) getSystemService(ConsMedicineActivity.INPUT_METHOD_SERVICE)).toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, 0);

    /*lvOpcoes.setOnItemClickListener(new AdapterView.OnItemClickListener() {*/

                /*@Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    // ListView Clicked item index
                    int itemPosition = position;

                    // ListView Clicked item value
                    String itemValue = (String) lvOpcoes.getItemAtPosition(position);

                    // Show Alert
                    Toast.makeText(ConsReceitaActivity.this, "Item selecionado" + itemValue, Toast.LENGTH_LONG).show();

                    Intent details = new Intent(ConsReceitaActivity.this, DetailsMedicineActivity.class);
                    details.putExtra("name", itemValue);

                    startActivity(details);

                }*/
    /*});*/
}
}


    /*public boolean onCreateOptionsMenu(Menu menu) {
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
                    Toast.makeText(ConsReceitaActivity.this, "Necessita autenticação", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.menuCadMedicines:
                if (autorizacao != null) {
                    Intent cadMedicines = new Intent(this, CadMedicinesActivity.class);
                    startActivity(cadMedicines);
                } else {
                    Toast.makeText(ConsReceitaActivity.this, "Necessita autenticação", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.menuCadReceita:
                if (autorizacao != null) {
                    Intent cadastroReceita = new Intent(this, CadReceitaActivity.class);
                    startActivity(cadastroReceita);
                } else {
                    Toast.makeText(ConsReceitaActivity.this, "Necessita autenticação", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.menuCadUsuario:
                Intent cadastroUsuario = new Intent(this, NewUserActivity.class);
                startActivity(cadastroUsuario);
                break;
            case R.id.menuConsFarmacia:
                Intent consultaFarm = new Intent(this, ConsFarmaciaActivity.class);
                startActivity(consultaFarm);
                break;
            case R.id.menuConsLembretes:
                if (autorizacao != null) {
                    Intent consLembretes = new Intent(this, ConsRememberActivity.class);
                    consLembretes.putExtra("autorizacao", autorizacao);
                    startActivity(consLembretes);
                } else {
                    Toast.makeText(ConsReceitaActivity.this, "Necessita autenticação", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.menuConsMedicamentos:
                Intent consultaMedicines = new Intent(this, ConsMedicineActivity.class);
                startActivity(consultaMedicines);
                break;
            case R.id.menuConsMedico:
                Intent contultaMedicos = new Intent(this, ConsMedicoActivity.class);
                startActivity(contultaMedicos);
                break;
            case R.id.menuConsReceita:
                if (autorizacao != null) {
                    Intent consultaReceita = new Intent(this, ConsReceitaActivity.class);
                    startActivity(consultaReceita);
                } else {
                    Toast.makeText(ConsReceitaActivity.this, "Necessita autenticação", Toast.LENGTH_LONG).show();
                }
            *//*case R.id.menuPerfilEditar:
                Intent editPerfil = new Intent(this, EditPerfil.class);
                startActivity(editPerfil);
                break;
            case R.id.menuPerfilVisualizar:
                Intent visPerfil = new Intent(this, visPerfil.class);
                startActivity(visPerfil);
                break;*//*
            case R.id.menuSair:
                finish();
            default:
                return false;
        }

        return super.onOptionsItemSelected(item);
    }*/
/*}*/

