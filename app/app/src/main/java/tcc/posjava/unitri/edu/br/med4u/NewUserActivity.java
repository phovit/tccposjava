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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setTitle("Med4U");

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
                                        showDialog("Cpf", (users.getString("cpf")));
                                        showDialog("email", (users.getString("email")));
                                        if ((users.getString("cpf").equalsIgnoreCase(cpf) || (users.getString("email").equalsIgnoreCase(email)))) {
                                            resp = "Usuário já cadastrado!";
                                        }
                                    }
                                    if (resp.equalsIgnoreCase("Usuário já cadastrado!")) {
                                        showDialog("Atenção", resp);
                                        Intent newUser = new Intent(NewUserActivity.this, LoginActivity.class);
                                        startActivity(newUser);
                                    } else {
                                        showDialog("Anteção", "Usuário não encontrado!");
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

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            /*case R.id.menuCadMedicines:
                Intent cadMedicines = new Intent(this, );
                startActivity(cadMedicines);
                break;*/
            case R.id.menuCadReceita:
                Intent cadastroReceita = new Intent(this, CadReceitaActivity.class);
                startActivity(cadastroReceita);
                break;
            case R.id.menuCadUsuario:
                Intent cadastroUsuario = new Intent(this, NewUserActivity.class);
                startActivity(cadastroUsuario);
                break;
            case R.id.menuConsFarmacia:
                Intent consultaFarm = new Intent(this, ConsFarmaciaActivity.class);
                startActivity(consultaFarm);
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
                Intent consultaReceita = new Intent(this, ConsReceitaActivity.class);
                startActivity(consultaReceita);
            /*case R.id.menuPerfilEditar:
                Intent editPerfil = new Intent(this, EditPerfil.class);
                startActivity(editPerfil);
                break;
            case R.id.menuPerfilVisualizar:
                Intent visPerfil = new Intent(this, visPerfil.class);
                startActivity(visPerfil);
                break;*/
            default:
                return false;
        }

        return super.onOptionsItemSelected(item);
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
