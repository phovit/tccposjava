package tcc.posjava.unitri.edu.br.med4u;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CadMedicinesActivity extends AppCompatActivity {

    private Uri file;
    private static String TAG = "CadMedicinesActivity";
    String url = "http://med4u.herokuapp.com/medicine";
    String autorizacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_medicines);
        Intent it = getIntent();
        autorizacao = it.getStringExtra("autorizacao");
        Toast.makeText(CadMedicinesActivity.this, "autorizacao: " + autorizacao, Toast.LENGTH_LONG).show();

        ActionBar actionbar = getSupportActionBar();
        actionbar.setTitle("Med4U");

        Button btCadNU = findViewById(R.id.buttonCadMedicines);
        btCadNU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText etNameCadMedicine = findViewById(R.id.etNameCadMedicine);
                String name = etNameCadMedicine.getText().toString();

                EditText etBarCodeMedicine = findViewById(R.id.etBarCodeMedicine);
                String codeBar = etBarCodeMedicine.getText().toString();

                EditText etRegMedicine = findViewById(R.id.etRegMedicine);
                String reg = etRegMedicine.getText().toString();

                EditText etIndMedicine = findViewById(R.id.etIndMedicine);
                String indication = etIndMedicine.getText().toString();

                EditText etContIndMedicine = findViewById(R.id.etContIndMedicine);
                String conIndi = etContIndMedicine.getText().toString();

                EditText etReactMedicine = findViewById(R.id.etReactMedicine);
                String reactMedicine = etReactMedicine.getText().toString();

                EditText etPrecMedicine = findViewById(R.id.etPrecMedicine);
                String prec = etPrecMedicine.getText().toString();

                RequestQueue queue = Volley.newRequestQueue(CadMedicinesActivity.this);

                JSONObject postRequest = new JSONObject();

                try {
                    postRequest.put("name", name);
                    postRequest.put("indications", indication);
                    postRequest.put("contraindications", conIndi);
                    postRequest.put("adverseReactions", reactMedicine);
                    postRequest.put("precautions", prec);
                    postRequest.put("codebar", codeBar);
                    postRequest.put("msRecord", reg);


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
                                    if (response.equals("com.android.volley.ParseError: org.json.JSONException: End of input at character 0 of")) {
                                        Toast.makeText(CadMedicinesActivity.this, "Medicamento cadastrado com sucesso.", Toast.LENGTH_LONG).show();
                                    }
                                }

                            }

                        },

                        /* Callback chamado em caso de erro */

                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

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

                etBarCodeMedicine.setText("");
                etContIndMedicine.setText("");
                etIndMedicine.setText("");
                etNameCadMedicine.setText("");
                etPrecMedicine.setText("");
                etReactMedicine.setText("");
                etRegMedicine.setText("");


            }

        });/*Intent returnIntent = new Intent();
                returnIntent.putExtra("autorizacao",autorizacao);
                setResult(RESULT_OK,returnIntent);*/


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
                    Toast.makeText(CadMedicinesActivity.this, "Necessita autenticação", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.menuCadMedicines:
                if (autorizacao != null) {
                    Intent cadMedicines = new Intent(this, CadMedicinesActivity.class);
                    cadMedicines.putExtra("autorizacao", autorizacao);
                    startActivity(cadMedicines);
                } else {
                    Toast.makeText(CadMedicinesActivity.this, "Necessita autenticação", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.menuCadReceita:
                if (autorizacao != null) {
                    Intent cadastroReceita = new Intent(this, CadReceitaActivity.class);
                    cadastroReceita.putExtra("autorizacao", autorizacao);
                    startActivity(cadastroReceita);
                } else {
                    Toast.makeText(CadMedicinesActivity.this, "Necessita autenticação", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.menuCadUsuario:
                Intent cadastroUsuario = new Intent(this, NewUserActivity.class);
                cadastroUsuario.putExtra("autorizacao", autorizacao);
                startActivity(cadastroUsuario);
                break;
            case R.id.menuConsLembretes:
                if (autorizacao != null) {
                    Intent consLembretes = new Intent(this, ConsRememberActivity.class);
                    consLembretes.putExtra("autorizacao", autorizacao);
                    startActivity(consLembretes);
                } else {
                    Toast.makeText(CadMedicinesActivity.this, "Necessita autenticação", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.menuConsFarmacia:
                Intent consultaFarm = new Intent(this, ConsFarmaciaActivity.class);
                consultaFarm.putExtra("autorizacao", autorizacao);
                startActivity(consultaFarm);
                break;
            case R.id.menuConsMedicamentos:
                Intent consultaMedicines = new Intent(this, ConsMedicineActivity.class);
                consultaMedicines.putExtra("autorizacao", autorizacao);
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
                    Toast.makeText(CadMedicinesActivity.this, "Necessita autenticação", Toast.LENGTH_LONG).show();
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
                Intent intent = new Intent(CadMedicinesActivity.this,
                        PrincipalActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            default:
                return false;
        }

        return super.onOptionsItemSelected(item);
    }

}