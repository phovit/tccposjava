package tcc.posjava.unitri.edu.br.med4u;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CadRememberActivity extends AppCompatActivity {

    private String url = "http://med4u.herokuapp.com/reminders";
    private String urlMed = "http://med4u.herokuapp.com/medicine";
    private String urlUsers = "http://med4u.herokuapp.com/users";
    private String autorizacao;
    private String nomeMedicamento;
    private List<String> opcoes;

    private String periodo;
    private String dosesDia;
    private String nome;

    private Spinner spnFreq;
    private List<String> frequencia = new ArrayList<String>();
    private JSONObject result = null;

    private Calendar calendar;
    private TextView dateView;
    private int year, month, day;

    private TextView timeView;

    private EditText etPeriodo;

    private static String TAG = "CadRememberActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_remember);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setTitle("Med4U");
        Intent it = getIntent();
        autorizacao = it.getStringExtra("autorizacao");
        final EditText edFindNameCadMedicines = findViewById(R.id.edCadRemNameMed);
        dateView = findViewById(R.id.etCadRemDt);
        timeView = findViewById(R.id.etCadRemHr);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);

        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year, month + 1, day);

        //busca do medicamento
        Button btFindMedCadRec = findViewById(R.id.btFindMedCadRec);
        btFindMedCadRec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText edFindNameMedicines = findViewById(R.id.edCadRemNameMed);
                nomeMedicamento = edFindNameMedicines.getText().toString();

                RequestQueue requestQueue = Volley.newRequestQueue(CadRememberActivity.this);

                JsonArrayRequest getRequest = new JsonArrayRequest(Request.Method.GET, urlMed, null,
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
                                        if (medicine.getString("name").toString().equalsIgnoreCase(nomeMedicamento)) {
                                            edFindNameMedicines.setText(medicine.getString("name"));

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

                // Adding the request to the queue along with a unique string tag

                requestQueue.add(getRequest);
                ((InputMethodManager) getSystemService(ConsMedicineActivity.INPUT_METHOD_SERVICE)).toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, 0);
            }
        });

        Button btnHour = findViewById(R.id.btnHora);
        btnHour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(CadRememberActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        if (selectedMinute == 0) {
                            timeView.setText(selectedHour + ":" + selectedMinute + "0");
                        } else {
                            timeView.setText(selectedHour + ":" + selectedMinute);
                        }
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });


        Button btCadRemember = findViewById(R.id.btCadaRemember);
        btCadRemember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText etCadRemNomeMed = findViewById(R.id.edCadRemNameMed);
                String name = etCadRemNomeMed.getText().toString();

                EditText etCadRemDoseDia = findViewById(R.id.edCadRemDoseDia);
                String doseDia = etCadRemDoseDia.getText().toString();

                EditText etDate = findViewById(R.id.etCadRemDt);
                String etDateString = etDate.getText().toString();

                EditText etHour = findViewById(R.id.etCadRemHr);
                String etHourString = etHour.getText().toString();

                EditText etDoses = findViewById(R.id.edCadRemDoses);
                String etDosesString = etDoses.getText().toString();

                EditText etInterval = findViewById(R.id.edCadInterval);
                String etIntervalString = etInterval.getText().toString();


                RequestQueue queue = Volley.newRequestQueue(CadRememberActivity.this);

                JSONObject postRequest = new JSONObject();

                String auxData = formataData(etDateString, etHourString);

                try {
                    postRequest.put("name", name);
                    /*postRequest.put("firstDose", auxData);*/
                    postRequest.put("observation", etDoses);
                    postRequest.put("medicine", name);
                    postRequest.put("dosage", doseDia);
                    postRequest.put("period", etDosesString);
                    postRequest.put("observation", etIntervalString);

                    JSONObject user = new JSONObject();
                    int ident = 5005;
                    user = buscaUsuario(ident);

                    postRequest.put("user", user);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
                JsonObjectRequest jsonObjReq = new JsonObjectRequest(
                        Request.Method.POST,
                        url,
                        postRequest,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                if (response == null) {
                                    Log.d(TAG, "API Response: null");
                                } else {
                                    Log.d(TAG, "API Response: " + response.toString());
                                    if (response.equals("Ocorreu um erro ao chamar a API com.android.volley.ServerError")) {
                                        Toast.makeText(CadRememberActivity.this, "Lembrete cadastrado com sucesso.", Toast.LENGTH_LONG).show();
                                    }
                                }
                                /*Toast.makeText(CadRememberActivity.this, "Lembrete cadastrado com sucesso.", Toast.LENGTH_LONG).show();*/
                            }

                        },

                        /* Callback chamado em caso de erro */

                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                                NetworkResponse response = error.networkResponse;
                                if (error instanceof ServerError && response != null) {
                                    try {
                                        String res = new String(response.data,
                                                HttpHeaderParser.parseCharset(response.headers, "utf-8"));
                                        // Now you can use any deserializer to make sense of data
                                        JSONObject obj = new JSONObject(res);
                                    } catch (UnsupportedEncodingException e1) {
                                        // Couldn't properly decode data to string
                                        e1.printStackTrace();
                                    } catch (JSONException e2) {
                                        // returned data is not JSONObject?
                                        e2.printStackTrace();
                                    }
                                }

                                Log.d(TAG, "Ocorreu um erro ao chamar a API " + error);
                            }
                        }) {
                    //This is for Headers If You Needed
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("Content-Type", "application/json; charset=UTF-8");
                        try {
                            Object obj = new JSONObject(autorizacao).get("Authorization");
                            params.put("Authorization", (String) obj);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        return params;
                    }

                    //Pass Your Parameters here
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<String, String>();
                        return params;
                    }
                };
                queue.add(jsonObjReq);

                etCadRemNomeMed.setText("");
                etCadRemDoseDia.setText("");
                etDate.setText("");
                etHour.setText("");
                etDoses.setText("");
                etInterval.setText("");


            }

        });

    }

    public JSONObject buscaUsuario(final int id) {
        // Initialize a new RequestQueue instance

        RequestQueue requestQueue = Volley.newRequestQueue(CadRememberActivity.this);

        // Initialize a new JsonArrayRequest instance
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                urlUsers,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        opcoes = new ArrayList<>();

                        try {
                            // Loop through the array elements
                            for (int i = 0; i < response.length(); i++) {
                                // Get current json object
                                JSONObject users = response.getJSONObject(i);
                                if (users.getInt("id") == id) {
                                    result = response.getJSONObject(i);
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
        return result;
    }

    public String formataData(String data, String hora) {
        return data + "T" + hora + "Z";
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
                    Toast.makeText(CadRememberActivity.this, "Necessita autenticação", Toast.LENGTH_LONG).show();
                }
            case R.id.menuCadMedicines:
                if (autorizacao != null) {
                    Intent cadMedicines = new Intent(this, CadMedicinesActivity.class);
                    cadMedicines.putExtra("autorizacao", autorizacao);
                    startActivity(cadMedicines);
                } else {
                    Toast.makeText(CadRememberActivity.this, "Necessita autenticação", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.menuCadReceita:
                if (autorizacao != null) {
                    Intent cadastroReceita = new Intent(this, CadRememberActivity.class);
                    cadastroReceita.putExtra("autorizacao", autorizacao);
                    startActivity(cadastroReceita);
                } else {
                    Toast.makeText(CadRememberActivity.this, "Necessita autenticação", Toast.LENGTH_LONG).show();
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
                    Toast.makeText(CadRememberActivity.this, "Necessita autenticação", Toast.LENGTH_LONG).show();
                }
                break;

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
                Intent intent = new Intent(CadRememberActivity.this,
                        PrincipalActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            default:
                return false;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("deprecation")
    public void setDate(View view) {
        showDialog(999);
        Toast.makeText(getApplicationContext(), "Selecione a data inicial",
                Toast.LENGTH_SHORT)
                .show();
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this,
                    myDateListener, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0,
                                      int arg1, int arg2, int arg3) {
                    showDate(arg1, arg2 + 1, arg3);
                }
            };

    private void showDate(int year, int month, int day) {
        dateView.setText(new StringBuilder().append(day).append("-")
                .append(month).append("-").append(year));
    }
}

