package tcc.posjava.unitri.edu.br.med4u;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class ConsMedicineActivity extends AppCompatActivity {

    private static String TAG = "ConsMedicineActivity";
    private String nomeMedicamento = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cons_medicines);

        Button btConsultaMedicines = findViewById(R.id.btConsMed);
        btConsultaMedicines.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText edFindNameMedicines = findViewById(R.id.edFindNameMedicines);
                nomeMedicamento = edFindNameMedicines.getText().toString();
                String url = "http://med4u.herokuapp.com/medicine";
                RequestQueue queue = Volley.newRequestQueue(ConsMedicineActivity.this);
                JSONObject postRequest = new JSONObject();
                try {
                    postRequest.put("username", nomeUsuario) ;

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
                                    if ((response.toString().contains(("Authorization\":\"Bearer eyJhbGciOiJIUzUxMiJ9.")))) {
                                        Intent cadRec = new Intent(LoginActivity.this, CadReceitaActivity.class);
                                        startActivity(cadRec);
                                    } else {
                                        showDialog("Informação", "Nome de usuário ou senha inválidos.");
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
                };

                queue.add(jsonObjReq);
            }
        });
            }
        });
    }
}
