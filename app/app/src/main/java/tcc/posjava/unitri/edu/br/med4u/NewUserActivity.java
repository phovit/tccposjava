package tcc.posjava.unitri.edu.br.med4u;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

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
import java.util.List;

public class NewUserActivity extends AppCompatActivity {

    /*ImageView campoFoto = findViewById(R.id.formulario_foto);*/
    private static String TAG = "NewUserActivity";
    private String cpf = "";
    private String email = "";
    private String url = "http://med4u.herokuapp.com/users";
    private String resp = "";
    private List<String> opcoes;
    private String autorizacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setTitle("Med4U");
        Intent it = getIntent();
        autorizacao = it.getStringExtra("autorizacao");

        Button btCadastro = findViewById(R.id.buttonCadastro);
        btCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText etCpf = findViewById(R.id.etCPFNewUser);
                cpf = etCpf.getText().toString().toLowerCase();
                EditText etEmail = findViewById(R.id.etMailNewUser);
                email = etEmail.getText().toString().toLowerCase();

                // Initialize a new RequestQueue instance
                RequestQueue requestQueue = Volley.newRequestQueue(NewUserActivity.this);

                // Initialize a new JsonArrayRequest instance
                JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                        Request.Method.GET,
                        url,
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
                                        if ((users.getString("cpf").equalsIgnoreCase(cpf) || (users.getString("email").equalsIgnoreCase(email)))) {
                                            resp = "Usuário já cadastrado!";
                                        }
                                    }
                                    if (resp.equalsIgnoreCase("Usuário já cadastrado!")) {
                                        Toast.makeText(NewUserActivity.this, "Usuário já cadastrado!", Toast.LENGTH_LONG).show();
                                        Intent newUser = new Intent(NewUserActivity.this, LoginActivity.class);
                                        newUser.putExtra("cpf", cpf);
                                        newUser.putExtra("email", email);
                                        startActivity(newUser);
                                    } else {
                                        Toast.makeText(NewUserActivity.this, "Usuário não encontrado!", Toast.LENGTH_LONG).show();
                                        Intent nuContinue = new Intent(NewUserActivity.this, NewUserContinueActivity.class);
                                        nuContinue.putExtra("cpf", cpf);
                                        nuContinue.putExtra("email", email);
                                        startActivity(nuContinue);
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
            }
        });

    }

    private void showDialog(String title, String message) {
        AlertDialog alertDialog = new AlertDialog.Builder(NewUserActivity.this).create();
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
